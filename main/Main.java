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

        int graph = 12;

        for(int i = graph; i < graph+1; i++)
        {
            if(i != 15)
            {
                // data setup
                graphName = i + ".txt";
                Graph.read(graphName);
                ConnectedVertices.makeMatrix();
                LowerBound.get();
                //Bipartite.run();
                Log.init();
                Log.close();
            }
        }

    }


    public static void runProgram()
    {

        Log.init();

        // Circle.run();
        // if(!Circle.isCircle())
        // {
        if(!TreeDetection.isTree())
        {
            Bipartite.run();
            if(!Bipartite.isBipartite())
            {
                Greedy.run(Graph.getM());
                DSatur.run();
                ImplicitEnumeration.run();
                OrderedGreedy.run();
                chromaticNumber = getBest();
            } else {
                chromaticNumber = 2;
            }
        } else {
            chromaticNumber = 2;
        }

        System.out.println("The Chromatic number = " + chromaticNumber);

        Log.close();
    }


    public static int getBest()
    {
        int list[] = new int[4];
        list[0] = Greedy.getChrom();
        list[1] = DSatur.getChrom();
        list[2] = ImplicitEnumeration.getChrom();
        list[3] = OrderedGreedy.getChrom();

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
