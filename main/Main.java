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

        int graph = 10;

        for(int i = graph; i < graph+1; i++)
        {
            if(i != 15)
            {
                // data setup
                graphName = i + ".txt";
                Graph.read(graphName);
                ConnectedVertices.makeMatrix();
                runProgram();
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
                Greedy.run(Graph.getN() * Graph.getM());
                DSatur.run();
                ImplicitEnumeration.run();
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
        int list[] = new int[3];
        list[0] = Greedy.getChrom();
        list[1] = DSatur.getChrom();
        list[2] = ImplicitEnumeration.getChrom();

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
