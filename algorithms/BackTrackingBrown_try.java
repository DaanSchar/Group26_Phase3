package algorithms;

import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class BackTrackingBrown_try {

    private static int n;
    private static int m;
    private static ColEdge[] e;

    private static Color color;

    private static int[] C; //stores colors assigned to vertices in the best solution so far
    private static ArrayList U; //stores sets of feasible colors for each vertex k

    private static int[] order; //stores new ordering of the vertices

    private static int k; //the index of the vertex being considered
    private static int vertex; //the vertex being considered
    private static int ub;

    private static int UPPERBOUND;
    private static int LOWERBOUND;

    private static boolean go;

    private static boolean update;


    public static void run() {

        Log.startTimer();
        System.out.println("BackTrackingBrown:         Running BackTrackingBrown...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        color = new Color(n);

        Ordering.run();

        order = Ordering.getOrdering(); //get order of vertices after pre-ordering
        System.out.println("THIS IS THE ORDERING: " + Arrays.toString(order));

        U = new ArrayList <ArrayList<Integer>> (n);

        for(int i = 0; i < n; i++)
        {
            U.add(new ArrayList<Integer> (n));
        }

        k = 1; //start at index 1
        vertex = order[k]; //which is this vertex
        LOWERBOUND = LowerBound.get();
        UPPERBOUND = UpperBound.getUpperBound();
        ub = UPPERBOUND;

        //set first color
        color.setColor(order[0], 1);

        go = true;
        update = true;

        forwards(k);

    }

    //general architecture starts here

    private static void forwards(int k) {

        vertex = order[k];
        System.out.println("starting FORWARDS " + (vertex));

        ArrayList<Integer> FC = new ArrayList<>();

        //if update, determine set of feasible colors
        if(update) {

            System.out.println("FORWARDS: determining new U(k)");

            FC = getPosCol(k);

            //store FC in U(i)
            U.set(k, (ArrayList)FC);

        }
        else {

            System.out.println("FORWARDS: checking U(k)- no update");

            //else get FC = U.get(k)
            FC = (ArrayList) U.get(k);

        }

        System.out.println("U(k) at " + vertex + " = " + U.get(k));


        if (FC.size() == 0) {

            System.out.println("FORWARDS: vertex " + vertex + " is empty");

            //resumption point = k
            k -= 1;
            backwards(k);

        }
        else {

            if(C != null && getMin(FC) > ub - 1) {

                System.out.println("FORWARDS: larger than best: vertex " + vertex + " is empty");
                k -= 1;
                backwards(k);

            }

            if(go) {

                //set color of k to smallest possible in FC
                color.setColor(vertex, getMin(FC));

                //remove this color from FC(i)
                FC.remove(Integer.valueOf(color.getColor(vertex)));

                System.out.println("FC at " + vertex + " after removal: " + FC);

                //put FC(i) back into U(i)
                U.set(k, (ArrayList)FC);

                System.out.println("after coloring: U(k) at " + vertex + " = " + U.get(k));

                //set update to true
                update = true;

                if(k == (n - 1)) {

                    //a new complete coloring has been found
                    System.out.println("COMPLETE COLORING");
                    color.printColorList();
                    System.out.println(color.chromNum() + " colors have been used.");
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();


                    //store the current coloring
                    C = color.getColorList();

                    //update ub on the current best solution
                    ub = color.chromNum();

                    if(ub == LOWERBOUND) {

                        end();
                    }
                    else  {

                        System.out.println("CONTINUING COLORING");

                        //get vertex such that c(vertex) = ub
                        int maxVertex = (color.getVertex(ub) + 1);

                        System.out.println("vertex colored with ub: " + maxVertex);

                        //update k such that C(k + 1) = ub --- or c(k) = ub?
                        for(int i = 0; i < order.length; i++) {

                            if(order[i] == maxVertex) {

                                k = i;
                            }
                        }

                        //set k to k - 1
                        k -= 1;

                        vertex = order[k];

                        //another enumeration
                        System.out.println("Another enumeration...");
                        System.out.println("starting at vertex: " + vertex);
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println();

                        //set update to false
                        update = false;
                        forwards(k);

                    }
                }
                else {

                    //update k + 1 and forwards(k)
                    k += 1;
                    forwards(k);

                }
            }
        }
    }

    private static void backwards(int k) {

        vertex = order[k];

        System.out.println("BACKWARDS: " + vertex);

        //determine list of current predecessors
        //in this case, if k = 0 set is empty
        if(k == 0) {

            System.out.println("END");
            //STOP
            end();
        }
        else {

            //get set of feasible colors FC
            ArrayList<Integer> FC = (ArrayList<Integer>) U.get(k);

            //update FC - remove current color
            FC.remove(Integer.valueOf(color.getColor(vertex)));

            System.out.println("BACKWARDS: U(k) at " + vertex + " = " + U.get(k));

            if(FC.size() == 0) {

                System.out.println("BACKWARDS: vertex " + vertex + " is empty");

                //go backwards
                k -= 1;
                backwards(k);

            }
            else {

                //set update to false
                update = false;

                //go forwards(k)
                forwards(k);
            }
        }
    }


    private static ArrayList<Integer> getPosCol(int k) {

        vertex = order[k];
        System.out.println("vertex " + vertex);

        int[] vertices = ConnectedVertices.get(vertex);

        ArrayList<Integer> posCol = new ArrayList();


        // makes an arraylist which contains all colors smaller to n
        for(int i = 0; i < n; i++) {

            posCol.add(i+1);

        }

        // removes colors this vertex is adjacent to
        for(int i = 0; i< vertices.length; i++) {

            posCol.remove((Integer.valueOf(color.getColor(vertices[i]))));
        }

        return posCol;

    }

    private static int getMin(ArrayList<Integer> A) {

        int[] a = new int[A.size()];

        for(int i = 0; i < a.length; i++) {

            a[i] = A.get(i);
        }

        int min = a[0];

        for(int i = 0; i < a.length; i++) {
            if(a[i] < min)
            {
                min = a[i];
            }
        }
        return min;
    }


    private static void end() {

        go = false;

        int result = ub;

        System.out.println("BackTrackingBrown:         " + result);
        System.out.println("BackTrackingBrown:         Finished running BackTrackingBrown");
        Log.endTimer("BackTrackingBrown", result);
    }

    public static int getChrom()
    {
        return color.chromNum();
    }
}
