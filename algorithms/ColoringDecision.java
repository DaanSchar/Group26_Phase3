package algorithms;

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


    public static void run() {

        Log.startTimer();
        System.out.println("ColoringDecision:         Running ColoringDecision...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        color = new Color(n);

        LOWERBOUND = 3; //by default
        UPPERBOUND = n; //by default

        pc = LOWERBOUND;

        v = 1;

        graphColoring(pc, v);

    }

    private static boolean graphColoring(int pc, int v) {

        if(v == n + 1) {

            end();
            return true;

        }
        for(int c = 1; c <= pc; c++) {

            color.setColor(v, c);

            if(isValid(v, c)) {

                if(graphColoring(pc, (v+1))) {

                    return true;
                }
                color.setColor(v, 0);
            }
        }
        return false;
    }

    private static boolean isValid(int v, int c) {

        int [] connVertices = ConnectedVertices.get(v);

        for(int i = 0; i < connVertices.length; i++) {

            if(color.getColor(v) != 0 && color.getColor(v) == color.getColor(connVertices[i])) {

                System.out.println("isValid: false");
                return false;
            }
        }
        return true;
    }

    private static void end() {

        color.printColorList();

        int result = pc;
        System.out.println("ColoringDecision:         " + result + " colors have been used");
        System.out.println("ColoringDecision:         Finished running ColoringDecision");
        Log.endTimer("ColoringDecision", result);

    }

}
