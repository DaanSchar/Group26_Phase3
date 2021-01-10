package main;

import algorithms.*;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;


public class Main
{

    static String graphName;

    public static void main(String[] args)
    {

    // data setup
        graphName = args[0];
        Graph.read(graphName);
        ConnectedVertices.makeMatrix();

        for(int i = 0; i < 1; i++)
        {
            runProgram();
        }
    }


    public static void runProgram()
    {
        Log.init();

        //FullyConnected.run();
        //Bipartite.run();
        //IsolatedVertex.run();
        UpperBound.run();
        //Greedy.run(Graph.getN());
        DSatur.run();
        //BackTracking.run();
        //BackTrackingSortedDegrees.run();

        Log.close();
    }





    public static String getGraphName()
    {
        return graphName;
    }
}
