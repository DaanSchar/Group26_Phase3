package algorithms;

import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class checks if there are any "islands" in the graph, put in other words,
 * it checks if the graph can be divided into at least 2 sets of vertices, such that
 * the two sets are not connected to each other.
 */
public class FullyConnected
{

    private static int n;
    private static int m;
    private static ColEdge[] e;
    private static boolean fullyConnected;

    public static void run()
    {
        System.out.println("FullyConnected:     Running...");
        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        int startVertex = 1;

        // queue contains
        ArrayList queue = new ArrayList();
        queue.add(startVertex);
        boolean[] isSeen = new boolean[n];

        // making a list of which vertices are seen or not
        for(int i = 0; i < n; i++)
        {
            isSeen[i] = false;
        }
        isSeen[startVertex-1] = true;

        while(!(queue.size() == 0))
        {
            // makes a list of connected vertices and than removes it from the queue
            int[] vertices = ConnectedVertices.get((int)(queue.get(0)));
            queue.remove(0);

            for(int i = 0; i < vertices.length; i++)
            {
                // if a vertex is seen, it won't be acknowledged
                if(!(isSeen[vertices[i]-1]))
                {
                    // sets the connected vertex to true when it gets seen
                    queue.add(vertices[i]);
                    isSeen[vertices[i] - 1] = true;
                }
            }
        }

        for(int i = 0; i < n; i++)
        {
            if(!isSeen[i])
            {
                fullyConnected = false;
                System.out.println("FullyConnected:     Graph contains subGraphs that are not connected to each other");
                System.out.println("FullyConnected:     Finished Running.");
                return;
            }
        }
        System.out.println("FullyConnected:     Graph if fully connected.");
        System.out.println("FullyConnected:     Finished Running.");
        fullyConnected = true;
    }



    public static boolean isFullyConnected()
    {
        return fullyConnected;
    }

}
