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
        Log.init();
        ConnectedVertices.makeMatrix();

    // run your algorithm here
        //FullyConnected.run();
        //IsolatedVertex.hasIsolated();
        //Bipartite.run();
        //ColoringExample.run();
        //IsolatedVertex.run();
        //System.out.println(Arrays.toString(IsolatedVertex.get()));
        Greedy.run();
        DSatur.run();

        //this is a test


        Log.close();
    }






    public static String getGraphName()
    {
        return graphName;
    }
}
