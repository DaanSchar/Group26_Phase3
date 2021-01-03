package algorithms;

import graph.ColEdge;
import graph.Graph;

/**
 * class that checks whether a graph contains vertices that don't contain any edges.
 */
public class IsolatedVertex
{
    private static int n;
    private static int m;
    private static ColEdge[] e;
    public static boolean hasIsolated;

    public static void run()
    {

        System.out.println("Running IsolatedVertex.");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        hasIsolated = false;

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

        System.out.println("Finished running IsolatedVertex");

        for(int i = 0; i < n; i++)
        {
            if(vertices[i] != 0)
            {
                hasIsolated = true;
                System.out.println("Graph contains Isolated vertices!");
            }
        }
    }
}
