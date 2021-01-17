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

public class ColoringDecision {

    private static int n;
    private static int m;
    private static ColEdge[] e;

    private static int v; //vertex
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

        color = new Color(n);

        LOWERBOUND = LowerBound.getLowerBound();
        UPPERBOUND = UpperBound.getUpperBound();

        pc = LOWERBOUND;

        v = 1;

        found = false;

        while(!found) {

            System.out.println("coloring with " + (pc - 1) + " colors not possible.");
            System.out.println("set pc += 1");

            coloring(pc, v);
            pc++;
        }

        end();
    }

    /**
     * method to recursively try all possible colorings of a graph with pc colors
     * @param pc number of colors allowed
     * @param v vertex to be colored
     * @return true if coloring with pc colors has been found
     */
    private static boolean coloring(int pc, int v) {

            //base case - if all vertices are colored, a pc coloring has been found (+1)
            if (v == n + 1) {

                found = true;
                return true;

            }

            //try color v with a color < pc
            for (int c = 1; c <= pc; c++) {

                color.setColor(v, c);

                //if coloring is allowed
                if (check(v, c)) {

                    //recursive call forwards to v + 1
                    if (coloring(pc, (v + 1))) {

                        return true;
                    }
                    //if recursive call returns false, set v to 0 and go backwards
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
