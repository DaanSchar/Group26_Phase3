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
        int lowerBound = LowerBound.get();
        int upperBound = UpperBound.get();
        System.out.println("NEW BEST LOWER BOUND = " + lowerBound);

        Cycle.run();
        int cycle = Cycle.getChromNum();

        if (cycle == 2) {
            chromaticNumber = 2;
        } else if (cycle == 3) {
            chromaticNumber = 3;
            System.out.println("NEW BEST UPPER BOUND = " + (upperBound+1));
        } else if (TreeDetection.isTree()) {
            chromaticNumber = 2;
        } else if(Bipartite.isBipartite()) {
            chromaticNumber = 2;
        } else if (lowerBound == Graph.getN()){
                chromaticNumber = lowerBound;
        } else {
            runColorMethods();
        }

        System.out.println("NEW BEST UPPER BOUND = " + upperBound);
        System.out.println("CHROMATIC NUMBER = " + chromaticNumber);

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

    /**
     * used in log class
     */
    public static String getGraphName()
    {
        return graphName;
    }
}
