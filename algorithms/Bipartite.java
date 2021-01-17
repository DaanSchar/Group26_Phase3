package algorithms;

import algorithms.exceptions.BipartiteInvalidException;
import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;

import java.util.Arrays;

/**
 *  This class checks if the graph is Bipartite.
 *  This means that the graph can be separated into
 *  2 sets of nodes that have no edges between one another within a set,
 *  but only have edges between 2 nodes from different sets,
 *  concluding that this graph can be colored with only 2 colors.
 */
public class Bipartite
{

    private static boolean DEBUG = false;

    private static int n;
    private static int m;
    private static ColEdge[] e;
    private static Color color;

    private static boolean isBipartite;

    private static boolean bipartite(int start)
    {

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();
        color = new Color(n);


        try
        {
            for(int startVertex = start; startVertex < n + 1; startVertex++)
            {
                //color = new Color(n);
                color.setColor(startVertex, 1);


                for (int vertex = 1; vertex < n + 1; vertex++) {
                    if(DEBUG)System.out.println("Bipartite:         " + vertex);


                    int[] vertices = ConnectedVertices.get(vertex);
                    if(DEBUG)System.out.println("Bipartite:         " + Arrays.toString(vertices));

                    for (int i = 0; i < vertices.length; i++) {
                        giveColor(vertices[i]);
                    }
                }

                if(Check.isCorrect(Bipartite.getColor()))
                {
                    isBipartite = true;
                    System.out.println("Bipartite:          isCorrect: " + Check.isCorrect(Bipartite.getColor()));
                    System.out.println("Bipartite:          Graph is Bipartite.");
                    System.out.println("Bipartite:          Finished running.");
                    Log.endTimer("Bipartite", true);
                    return true;
                }
            }
        }
        catch (BipartiteInvalidException e)
        {
            isBipartite = false;
            return false;
        }
        isBipartite = false;
        return false;
    }

    public static void run()
    {
        Log.startTimer();
        System.out.println("Bipartite:          Running...");

        n = Graph.getN();

        for(int i = 0; i < n; i++)
        {
            if(bipartite(i+1))
            {
                return;
            }
        }
        System.out.println("Bipartite:          Graph cannot be colored with 2 colors");
        System.out.println("Bipartite:          Finished running.");
        Log.endTimer("Bipartite", false);
    }

    /**
     * sets the colors of the adjacent vertices of the input vertex to the negated color
     * of the color of the input vertex.
     * so if the vertex = 4 and it's color = 1, it will color all adjacent vertices -1.
     */
    private static void giveColor(int vertex) throws BipartiteInvalidException
    {
        if (color.getColor(vertex) == 1)
        {
            int[] vertices = ConnectedVertices.get(vertex);

            for (int i = 0; i < vertices.length; i++)
            {
                if (color.getColor(vertices[i]) == 1)
                {
                    throw new BipartiteInvalidException();
                }

                // colors all adjacent vertices 1
                color.setColor(vertices[i], -1);
            }
        }


        if (color.getColor(vertex) == -1)
        {
            int[] vertices = ConnectedVertices.get(vertex);

            for (int i = 0; i < vertices.length; i++)
            {
                if (color.getColor(vertices[i]) == -1)
                {
                    throw new BipartiteInvalidException();
                }

                // colors all adjacent vertices -1
                color.setColor(vertices[i], 1);
            }
        }
    }


    public static boolean isBipartite()
    {
        run();
        return isBipartite;
    }

    public static Color getColor()
    {
        return color;
    }
}
