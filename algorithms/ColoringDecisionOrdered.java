package algorithms;

/**
 * k-coloring decision problem:
 * checks whether a graph is colorable with k colors
 * by trying all possibilities the vertices
 * if k is not possible, k is increased by 1
 *
 * @author Leo
 */

import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;

import java.util.Arrays;

public class ColoringDecisionOrdered {

    private static int n;
    private static int m;
    private static ColEdge[] e;

    private static int v; //vertex
    private static int k; //index of vertex
    private static int[] sortedDegrees;
    private static int pc; //possible colors

    private static Color color;

    private static int LOWERBOUND;
    private static int UPPERBOUND;

    private static boolean found;


    public static void run() {

        Log.startTimer();
        System.out.println("ColoringDecision:         Running ColoringDecision...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        degreeSort();

        color = new Color(n);

        LOWERBOUND = LowerBound.get();
        UPPERBOUND = UpperBound.get();

        pc = LOWERBOUND;

        k = 0;

        System.out.println("ordered vertices: " + Arrays.toString(sortedDegrees));

        found = false;

        while(!found) {

            System.out.println("coloring with " + (pc - 1) + " colors not possible.");
            System.out.println("set pc += 1");

            coloring(pc, k);
            pc++;
        }

        end();
    }

    /**
     * method to recursively try all possible colorings of a graph with pc colors
     * @param pc number of colors allowed
     * @param k vertex to be colored
     * @return true if coloring with pc colors has been found
     */
    private static boolean coloring(int pc, int k) {

        //base case - if all vertices are colored, a pc coloring has been found (+1)
        if (k == n) {

            found = true;
            return true;

        }

        int v = sortedDegrees[k];

        //try color k with a color < pc
        for (int c = 1; c <= pc; c++) {

            color.setColor(v, c);

            //if coloring is allowed
            if (check(v, c)) {

                //recursive call forwards to k + 1
                if (coloring(pc, (k + 1))) {

                    return true;
                }
                //if recursive call returns false, set k to 0 and go backwards
                color.setColor(v, 0);
            }
        }

        return false;
    }

        /**
         * checks whether coloring is allowed
         * @param v vertex to be colored
         * @param c color that is assigned
         * @return false if coloring is not allowed
         */
        private static boolean check(int v, int c) {

            int [] connVertices = ConnectedVertices.get(v);

            for(int i = 0; i < connVertices.length; i++) {

                if(color.getColor(v) != 0 && color.getColor(v) == color.getColor(connVertices[i])) {

                    return false;
                }
            }
            return true;

        }

    /**
     * sorts the vertices in order of degree and
     * puts this order in an array.
     */
    private static void degreeSort()
    {
        //make array that stores the degrees of each vertex
        int[] degrees = new int[n];

        for(int i = 1; i <= n; i++)
        {
            for(int j = 0; j < e.length; j++)
            {
                if(i == e[j].u)
                {
                    degrees[i - 1] = degrees[i - 1] + 1;
                }
                else if(i == e[j].v)
                {
                    degrees[i - 1] = degrees[i - 1] + 1;
                }
            }
        }

        //calculate maximum degree
        int maxDegree = 0;

        for(int i = 0; i < degrees.length; i++)
        {
            maxDegree = Math.max(maxDegree, degrees[i]);
        }


        //make array storing vertices by decreasing order of degrees
        sortedDegrees = new int[n];

        int position = 0;

        for(int i = maxDegree; i >= 0; i--)
        {
            for(int j = 0; j < degrees.length; j++)
            {
                if(degrees[j] == i)
                {
                    sortedDegrees[position] = j + 1;
                    position++;
                }
            }
        }
    }



    private static void end() {

        color.printColorList();

        int result = color.chromNum();

        System.out.println("ColoringDecision:         " + result + " colors have been used");
        System.out.println("ColoringDecision:         Finished running ColoringDecision");
        Log.endTimer("ColoringDecision", result);

    }

    public static int getChrom()
    {
        return color.chromNum();
    }

}
