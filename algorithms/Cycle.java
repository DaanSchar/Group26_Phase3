package algorithms;

/**
 * checks whether a graph is a cycle by traversing from vertex to the next adjacent vertex
 * if no vertices are visited twice before the starting vertex is reached the graph is cyclic
 *
 * @author Leo
 */

import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;

import java.util.ArrayList;

public class Cycle {

    private static int n;
    private static int m;
    private static ColEdge[] e;

    private static int start; //starting vertex
    private static int v; //current vertex
    private static int prev; //previous vertex
    private static int next; //next vertex

    private static ArrayList<Integer> isSeen; //stores vertices that have been visited already

    private static boolean isCycleEven;
    private static boolean isCycleOdd;


    public static void run() {

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        //setup
        start = 1; //starting at vertex 1
        isSeen = new ArrayList<>(); //no vertex has been visited yet

        isCycleEven = false;
        isCycleOdd = false;

        if (isCyclic(start)) {

            if (n % 2 == 0) {

                isCycleEven = true;
                System.out.println("Cycle: Graph is an even cycle");
                System.out.println("Cycle: Finished Running.");
                Log.endTimer("Cycle", true);
                return;
            } else {

                isCycleOdd = true;
                System.out.println("Cycle: Graph is an odd cycle");
                System.out.println("Cycle: Finished Running.");
                Log.endTimer("Cycle", true);
                return;

            }

        } else {

            System.out.println("Cycle: Graph is not a cycle");
            System.out.println("Cycle: Finished Running.");
            Log.endTimer("Cycle", false);
            return;


        }
    }

    /**
     * checks whether graph is cyclic by traversing all connections and checking whether a vertex has been visited
     * already
     * @param v
     * @return
     */
    private static boolean isCyclic(int v) {

        if (isSeen.size() == n) {

            return true;
        }

        //check connected vertices of v
        int[] connVertices = ConnectedVertices.get(v);

        System.out.println("CHECKING v : " + v);

        //check whether one of the connections has been visited already
        for (int i = 0; i < connVertices.length; i++) {

            for (int j = 0; j < isSeen.size(); j++) {

                if (connVertices[i] != prev && connVertices[i] != start && connVertices[i] == isSeen.get(j)) {

                    System.out.println("CONNECTED : FALSE");

                    return false;
                }
            }
        }

        //unvisited is taken as next
        for (int k = 0; k < connVertices.length; k++) {

            if (connVertices[k] != prev) {

                next = connVertices[k];
            }
        }

        prev = v;

        isSeen.add(prev);

        v = next;

        System.out.println("NOW CHECKING v : " + v);

        if (isCyclic(v)) {

            return true;

        }

        return false;
    }

    public static int getChromNum() {

        if (isCycleEven) {
            return 2;
        }
        else if (isCycleOdd) {

            return 3;
        }
        else {

            return 0;
        }
    }
}


