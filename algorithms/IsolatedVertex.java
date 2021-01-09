package algorithms;

import graph.ColEdge;
import graph.Graph;
import logging.Log;

import java.util.ArrayList;

/**
 * class that checks whether a graph contains vertices that don't contain any edges.
 */
public class IsolatedVertex
{
    private static int n;
    private static int m;
    private static ColEdge[] e;
    private static boolean hasIsolated;
    private static ArrayList isolatedVertices;

    public static void run()
    {
        Log.startTimer();
        System.out.println("IsolatedVertex: Running...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        hasIsolated = false;
        isolatedVertices = new ArrayList();
        int[] vertices = new int[n];

        for(int i = 0; i < n; i++)
        {
            vertices[i] = i + 1;
        }

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if((e[j].u == (i+1) )||( e[j].v == (i+1)))
                {
                    vertices[i] = 0;
                }
            }
        }


        for(int i = 0; i < n; i++)
        {
            if(vertices[i] != 0)
            {
                System.out.println("IsolatedVertex: Graph contains Isolated vertices:   " + (i+1));
                isolatedVertices.add((i+1));
                hasIsolated = true;
            }
        }

        if(!hasIsolated) {
            System.out.println("IsolatedVertex: Graph does not contain Isolated vertices");
        }

        System.out.println("IsolatedVertex: Finished running");
        Log.endTimer("IsolatedVertex", hasIsolated);
    }


    public static boolean hasIsolated()
    {
        return hasIsolated;
    }


    /**
     * returns the isolated vertices as an array
     */
    public static int[] get()
    {

        int[] temp = new int[isolatedVertices.size()];

        for(int i = 0; i < isolatedVertices.size(); i++)
        {
            temp[i] = (int)isolatedVertices.get(i);
        }
        return temp;
    }

}
