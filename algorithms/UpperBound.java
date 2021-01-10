package algorithms;

import graph.ColEdge;
import graph.Graph;
import logging.Log;

import java.util.Arrays;

/**
 * calculates the upper bound on the chromatic number
 * @author Leo
 */

public class UpperBound {

    private static int n;
    private static int m;
    private static ColEdge[] e;

    private static int maxDegree;
    private static int ub;


    public static void run() {

        Log.startTimer();
        System.out.println("UpperBound:         Running UpperBound...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        calcMaxDegree();
        upperBound();

    }

    /**
     * calculates the upper bound on the chromatic number
     */
    private static void upperBound() {

        ub = maxDegree;
        //ub = maxDegree + 1; - if graph is complete or odd-cycle
        end();
    }

    /**
     * calculates the maximum degree
     */
    public static void calcMaxDegree() {

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

        System.out.println(Arrays.toString(degrees));


        //calculate maximum degree
        for (int i = 0; i < degrees.length; i++) {

            maxDegree = Math.max(maxDegree, degrees[i]);
        }
    }

    private static void end() {
        int result = getUpperBound();
        System.out.println("UpperBound:         " + result);
        System.out.println("UpperBound:         Finished running UpperBound");
        Log.endTimer("UpperBound", result);
    }

    public static int getUpperBound() {

        return ub;

    }

}

