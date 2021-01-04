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

    public static boolean hasIsolated()
    {

        System.out.println("IsolatedVertex: Running...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();


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
                System.out.println("IsolatedVertex: Graph contains Isolated vertices!");
                System.out.println("IsolatedVertex: vertex: " + i);
                System.out.println("IsolatedVertex: Finished running");
                return true;
            }
        }
        System.out.println("IsolatedVertex: Graph does not contain Isolated vertices");
        System.out.println("IsolatedVertex: Finished running");
        return false;
    }

}
