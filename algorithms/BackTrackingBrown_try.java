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

        Ordering.get();

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


    }

    //general architecture starts here

    private static boolean coloring(int k) {

        //base case - arrived at first vertex in path
        if(k == 1) {

            return true;

        }
        else if(ub == LOWERBOUND) {

            return true;
        }

        vertex = order[k];

return true;

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
