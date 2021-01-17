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

        int graph = 22;

        for(int i = graph; i < 23; i++)
        {
            if(i != 35) {

                // data setup
                graphName = i + ".txt";
                Graph.read(graphName);
                ConnectedVertices.makeMatrix();

                Log.init();
                //runProgram();
                runColoring();
                Log.close();
            }
        }

    }

    public static void runColoring() {

        //ColoringDecision.run();
        ColoringDecisionOrdered.run();
    }

    public static void runProgram()
    {
        LowerBound.get();
        UpperBound.get();
        Cycle.run();
        TreeDetection.isTree();
        Bipartite.run();
        runColorMethods();
    }

    public static void runColorMethods()
    {
        Greedy.run(Graph.getM());
        DSatur.run();
        OrderedGreedy.run();
        LowerBoundGreedy.run();
        chromaticNumber = getBest();
    }



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
            System.out.println("possible chromatic number: " + list[i]);

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
