package algorithms;

/**
 * implements ordering of vertices such that vertex(i) is connected to more vertices vertex(1), ... , vertex(i-1) than
 * any vertex(j) such that j > i
 *
 * @author Leo
 */

import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Ordering {

    private static int n;
    private static int m;
    private static ColEdge[] e;

    private static int k;

    private static int[] sortedDegrees;
    private static ArrayList sortedGLF;

    private static boolean go;

    public static int[] get() {

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        sortedGLF = new ArrayList<Integer>(n);

        for(int i = 0; i < n; i++) {

            sortedGLF.add(i, i + 1);
        }

        //determine which vertices vertex k is adjacent to
        for (int i = 0; i < n; i++) {

            System.out.println("vertex: " + (i + 1) + " connected to: " + Arrays.toString(ConnectedVertices.get(i + 1)));

        }

        k = n - 1;
        go = true;

        sort(k);

        int [] arr = new int[sortedGLF.size()];

        //convert from ArrayList to array
        for(int i = 0; i < sortedGLF.size(); i++) {

            arr[i] = (Integer) sortedGLF.get(i);

        }

        return arr;


    }

    private static void sort(int k) {

        //get vertex at k from sortedGLF
        int vertex = (int) sortedGLF.get(k);

        //if k = n - 1 STOP
        if(k == 0) {
             //stop
            end();
        }
        else {
            //if connected(k) > number connected(k-1)
            if(connections(k) > connections((k-1)))
            {
                //swap (k, k+1)
                Collections.swap(sortedGLF, k, k-1);
                //go on to k-1
                sort(k-1);
            }
            //else check k-1
            else {
                sort(k-1);
            }
        }
    }

    private static int connections(int k) {

        //get vertex at k from sortedGLF
        int vertex = (int) sortedGLF.get(k);

        //get connected vertices of vertex at k
        int[] connections = ConnectedVertices.get(vertex);

        int count = 0;

        //check number of connected vertices up to current position
        for(int i = 0; i < connections.length; i++) {

            if(connections[i] < (k + 1)) {

                count++;
            }
        }

        return count;
    }

    private static void sortDegrees() {

        //sort by decreasing order of degree
        //make array that stores the degrees of each vertex
        int[] degrees = new int[n];

        for (int i = 1; i <= n; i++) {

            for (int j = 0; j < e.length; j++) {

                if (i == e[j].u) {

                    degrees[i - 1] = degrees[i - 1] + 1;
                } else if (i == e[j].v) {

                    degrees[i - 1] = degrees[i - 1] + 1;
                }
            }
        }

        //calculate maximum degree
        int maxDegree = 0;

        for(int i = 0; i < degrees.length; i++) {
            maxDegree = Math.max(maxDegree, degrees[i]);
        }

        //make array storing vertices by decreasing order of degrees
        sortedDegrees = new int[n];

        int position = 0;

        for(int i = maxDegree; i >= 0; i--) {
            for (int j = 0; j < degrees.length; j++) {
                if (degrees[j] == i) {
                    sortedDegrees[position] = j + 1;
                    position++;
                }
            }
        }
    }

    private static void end(){

        go = false;
        System.out.println("END");
        System.out.println("ordered:" + sortedGLF);

    }

    public static int[] getOrdering() {

        int [] arr = new int[sortedGLF.size()];

        //convert from ArrayList to array
        for(int i = 0; i < sortedGLF.size(); i++) {

            arr[i] = (Integer) sortedGLF.get(i);

        }

        return arr;

    }
}
