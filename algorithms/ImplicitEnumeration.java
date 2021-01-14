package algorithms;

import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;

import java.util.ArrayList;

public class ImplicitEnumeration {

    private static int n;
    private static int m;
    private static ColEdge[] e;

    private static Color color;

    private static int[] C; //stores colors assigned to vertices in the best solution so far
    private static ArrayList U; //stores sets of feasible colors for each vertex k

    private static int k; //r
    private static int ub;

    private static int UPPERBOUND;
    private static int LOWERBOUND;

    private static boolean go;


    public static void run() {

        Log.startTimer();
        System.out.println("ImplicitEnum:         Running ImplicitEnum...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        color = new Color(n);

        U = new ArrayList <ArrayList<Integer>> (n);

        for(int i = 0; i < n; i++)
        {
            U.add(new ArrayList<Integer> (n));
        }

        k = 1; //start at index 1, vertex 2
        LOWERBOUND = 1; //by default
        UPPERBOUND = n; //by default
        ub = UPPERBOUND;

        //set first color
        color.setColorBackTracking(0, 1);

        go = true;

        forwards(k);

    }

    //general architecture starts here

    private static void forwards(int k) {

        System.out.println("starting FORWARDS " + (k+1));

        for(int i = k; i < n; i++) {

            //determine set of feasible colors
            ArrayList<Integer> FC = getPosCol(i);

            //store FC in U(i)
            U.set(i, (ArrayList)FC);

            if (FC.size() == 0) {

                System.out.println("FORWARDS: vertex " + (i+1) + " is empty");

                //resumption point = k
                k = i - 1;
                backwards(k);
                break;

            }
            else {

                if(getMin(FC) > color.getColorBackTracking(i) && color.getColorBackTracking(i) != 0) {

                    System.out.println("FORWARDS: larger than current: vertex " + (i+1) + " is empty");
                    k = i - 1;
                    backwards(k);
                    break;
                }

                //set color of k to smallest possible in FC
                color.setColorBackTracking(i, getMin(FC));

                //remove this color from FC(i)
                FC.remove(Integer.valueOf(color.getColorBackTracking(i)));

                //put FC(i) back into U(i)
                U.set(i, (ArrayList)FC);

            }
        }

        if(go) {

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
                //update k such that C(k + 1) = ub --- or c(k) = ub?
                k = (color.getVertex(ub)) - 1;

                //another enumeration
                System.out.println("Another enumeration...");
                System.out.println("starting at vertex: " + (k+1));
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();

                forwards(k);

            }
        }


    }

    private static void backwards(int k) {

        System.out.println("BACKWARDS: " + (k+1));

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
            FC.remove(Integer.valueOf(color.getColorBackTracking(k)));

            if(FC.size() == 0) {

                System.out.println("BACKWARDS: vertex " + (k+1) + " is empty");

                //go backwards
                k -= 1;
                backwards(k);

            }
            else {
                //go forwards(k)
                forwards(k);
            }
        }
    }


    private static ArrayList<Integer> getPosCol(int k) {

        int[] vertices = ConnectedVertices.get(k+1);

        ArrayList<Integer> posCol = new ArrayList();

        int max = 0;

        if(C != null) {

            //get max C(j) where j < k

            for(int i = 0; i < k; i++) {

                if(C[i] > max) {

                    max = C[i];
                }
            }
        }
        else {

            max = n;

        }


        // makes an arraylist which contains all colors smaller to ub (the best solution so far)
        for(int i = 0; i < (ub-1); i++) {

            posCol.add(i+1);

        }

        // removes colors this vertex is adjacent to
        for(int i = 0; i< vertices.length; i++) {

            posCol.remove((Integer.valueOf(color.getColor(vertices[i]))));
        }

        //removes ub
        posCol.remove((Integer.valueOf(ub)));

        //removes own color
        posCol.remove((Integer.valueOf(color.getColorBackTracking(k))));

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

        System.out.println("ImplicitEnum:         " + result);
        System.out.println("ImplicitEnum:         Finished running ImplicitEnum");
        Log.endTimer("ImplicitEnum", result);
    }

    public static int getChrom()
    {
        return ub;
    }

}
