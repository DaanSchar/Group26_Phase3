package algorithms;

import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;

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

    public static void run() {

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        //setup
        start = 1; //starting at vertex 1
        isSeen = new ArrayList<>(); //no vertex has been visited yet

        if(isCyclic(start)) {

            System.out.println("Cyclic graph");
        }
        else {
            System.out.println("not a cycle");
        }
    }

    private static boolean isCyclic(int v) {

        if(isSeen.size() == n) {

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

        if(isCyclic(v)) {

            return true;

        }

        return false;
    }
}


