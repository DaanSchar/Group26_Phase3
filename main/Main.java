package main;

import algorithms.*;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;


public class Main
{

    static String graphName;
    static int chromaticNumber;

    public static void main(String[] args)
    {

        int graph = 5;

        for(int i = graph; i < graph + 1; i++)
        {
            // data setup
            graphName = "phase2/"+
                    i + ".txt";
            Graph.read(graphName);
            ConnectedVertices.makeMatrix();

            Log.init();
            runProgram();
            Log.close();
        }

    }

    /**
     * decision tree.
     */
    public static void runProgram()
    {
        int lowerBound = LowerBound.get();

        if(lowerBound == 2) {
            Cycle.run();
            if (Cycle.getChromNum() == 2) {
                chromaticNumber = 2;
            } else if (Cycle.getChromNum() == 3) {
                chromaticNumber = 3;
            } else if (TreeDetection.isTree()) {
                chromaticNumber = 2;
            } else if (lowerBound == 1) {
                chromaticNumber = 1;
            } else {
                runColorMethods();
            }
        }
        System.out.println("RESULT: " + chromaticNumber);

    }

    /**
     * runs the coloring algorithms
     */
    public static void runColorMethods()
    {
        Greedy.run(Graph.getM());
        DSatur.run();
        OrderedGreedy.run();
        LowerBoundGreedy.run();
        chromaticNumber = getBest();
    }


    /**
     * returns the smallest coloring of the coloring algorithms
     */
    public static int getBest()
    {
        int list[] = new int[4];
        list[0] = Greedy.getChrom();
        list[1] = DSatur.getChrom();
        list[2] = OrderedGreedy.getChrom();
        list[3] = LowerBoundGreedy.getChrom();

        int min = list[0];

        for(int i = 0; i < list.length; i++)
        {
            if (list[i] < min)
            {
                min = list[i];
            }
        }
        return min;
    }


    public static String getGraphName()
    {
        return graphName;
    }
}
