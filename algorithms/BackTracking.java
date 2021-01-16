package algorithms;

/**
 * backtracking algorithm that aims to find the best possible coloring using depth-first search
 * the idea is to try to color the graph with a maximum number of colors that is set to 3 in the beginning
 * every possible combination to color the graph is attempted
 * if it is not possible to color the graph with the specified maximum number of colors, we increase the number of
 * allowed colors by 1
 * the number of colors used for the first complete possible complete coloring is the chromatic number
 *
 */

import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;

public class BackTracking {

    private static int n;
    private static int m;
    private static ColEdge[] e;

    private static int pc; //possible colors
    private static int k; //index of vertex
    private static int vertex;
    private static int c; //color

    private static Color color; //Color object to manage and store coloring

    private static int LOWERBOUND;
    private static int UPPERBOUND;

    private static boolean stop;

    public static void run() {

        Log.startTimer();
        System.out.println("BackTracking:         Running BackTracking...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        color = new Color(n);

        LOWERBOUND = LowerBound.get(); //by default
        UPPERBOUND = UpperBound.getUpperBound(); //by default

        pc = LOWERBOUND; //set initial pc to 2
        vertex = 2; //set starting vertex to 2
        c = 1; //set starting color to 1

        stop = false;

        color.setColor(1, 1);

        coloring(vertex, c); //start coloring here

    }

    /**
     * colors a vertex
     * @param vertex vertex to be colored
     * @param c color
     */
    private static void coloring(int vertex, int c) {

        if(!stop) {

            color.setColor(vertex, c);
            checkAdjacency(vertex);
        }
    }

    /**
     * checks whether the coloring is legal
     * @param vertex vertex to be checked
     */
    private static void checkAdjacency(int vertex) {

        if(!stop) {

            int[] connVertices = ConnectedVertices.get(vertex);

            for (int i = 0; i < connVertices.length; i++) {

                if(color.getColor(vertex) == color.getColor(connVertices[i])) {

                    //update color
                    int newColor = color.getColor(vertex) + 1;
                    updateColor(vertex, newColor);
                    return;

                }
            }

            forwards(vertex); //one step forwards in the tree
            return;

        }
    }

    /**
     * checks if all vertices have been colored
     * if not, forwards the next vertex to the coloring method
     * @param vertex
     */
    private static void forwards (int vertex) {

        if (vertex == n) {

            //STOP
            end();
            return;

        } else {

            vertex += 1;
            System.out.println("FORWARDS: continuing at " + vertex);
            coloring(vertex, 1);
        }
    }

    /**
     * increases color by 1, if current coloring is not allowed
     * if not possible to increase color because c > pc, then backtracks to one vertex before,
     * and tries to change color there
     * @param vertex vertex to be colored
     * @param c color
     */
    private static void updateColor(int vertex, int c) {

        if(!stop) {

            if (c <= pc) {

                System.out.println("UPDATE COLOR SUCCESSFUL");
                coloring(vertex, c);
                return;

            } else {

                System.out.println("UPDATE COLOR NOT SUCCESSFUL");
                //backtrack
                vertex -= 1; //going back one step in the tree
                c = color.getColor(vertex) + 1; //trying out the next color

                System.out.println("UPDATE COLOR: c > pc: BACKTRACK...");
                System.out.println("UPDATE COLOR: BACKTRACKING TO VERTEX: " + vertex + "; COLOR: " + c);

                backtrack(vertex, c);
            }
        }
    }


    /**
     * backtrack: checks if vertex before can be colored differently
     * if c > pc, vertex cannot be colored because c exceeds maximum number of allowed colors
     * then backtrack to one vertex before
     * if vertex = 1: stop and increase number of allowed colors by 1
     * @param vertex
     * @param c
     */
    private static void backtrack(int vertex, int c) {

        if(!stop){

            if (vertex == 1) {

                setStart();
                return;

            } else {

                if (c <= pc) {

                    coloring(vertex, c);
                } else {

                    vertex -= 1; //going back one step in the tree
                    c = color.getColor(vertex) + 1; //trying out the next color

                    System.out.println("BACKTRACKING: c > pc: BACKTRACK...");
                    System.out.println("BACKTRACKING: BACKTRACKING TO VERTEX: " + vertex + "; COLOR: " + c);

                    backtrack(vertex, c);
                }
            }
        }
    }

    /**
     * starts another enumeration with number of allowed colors + 1
     */
    private static void setStart() {

        System.out.println("BACKTRACK: ANOTHER ENUMERATION");
        System.out.println("pc = " + (pc + 1));

        //another enumeration with pc + 1
        pc += 1; //set pc + 1
        coloring(2, 1); //start new at vertex 2, color 1

        //here, we could possibly step out of the recursion??
    }

    private static void end() {

        stop = true;

        color.printColorList();

        int result = color.chromNum();
        System.out.println("BackTracking:         " + result + " colors have been used");
        System.out.println("BackTracking:         Finished running BackTracking");
        Log.endTimer("BackTracking", result);

    }

    public static int getChrom()
    {
        return color.chromNum();
    }
}