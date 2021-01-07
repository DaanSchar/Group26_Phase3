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
        //Bipartite.run();
        //IsolatedVertex.run();
        //Greedy.run(Graph.getN());
        //DSatur.run();
        BackTracking.run();

        //this is a test


        Log.close();
    }






    public static String getGraphName()
    {
        return graphName;
    }
}
