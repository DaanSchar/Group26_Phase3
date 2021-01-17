package main;

import algorithms.*;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;

/**
 * LowerBound algorithms are commented out, just so you can see that we included them and how to use them if
 * you'd like to test them. they don't affect the final result, but only takes up unnecessary time.
 */
public class Main
{

    static String graphName;
    static int chromaticNumber;

    public static void main(String[] args)
    {

        // data setup
        graphName = args[0];
        Graph.read(graphName);
        ConnectedVertices.makeMatrix();


        Log.init();
        runProgram();
        Log.close();

    }

    /**
     * decision tree.
     */
    public static void runProgram()
    {
        // gets minimum required coloring.
        //LowerBound.get();
        UpperBound.get();

        Cycle.run();
        int cycle = Cycle.getChromNum();

        if (cycle == 2) {
            chromaticNumber = 2;
        } else if (cycle == 3) {
            chromaticNumber = 3;
        } else if (TreeDetection.isTree()) {
            chromaticNumber = 2;
        } else if(Bipartite.isBipartite()) {
            chromaticNumber = 2;
        } else {
            runColorMethods();
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
        //LowerBoundGreedy.run();
        chromaticNumber = getBest();
    }


    /**
     * returns the smallest coloring of the coloring algorithms
     */
    public static int getBest()
    {
        int list[] = new int[3];
        list[0] = Greedy.getChrom();
        list[1] = DSatur.getChrom();
        list[2] = OrderedGreedy.getChrom();
        //list[3] = LowerBoundGreedy.getChrom();

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

    /**
     * used in log class
     */
    public static String getGraphName()
    {
        return graphName;
    }
}
