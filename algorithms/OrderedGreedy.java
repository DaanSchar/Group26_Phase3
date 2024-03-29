package algorithms;

import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;

public class OrderedGreedy
{

    private static int n;
    private static int m;
    private static ColEdge[] e;
    private static Color color;

    private static int[] degrees;
    private static int[] sortedDegrees;

    private static int chromaticNumber;

    public static void run()
    {
        Log.startTimer();
        System.out.println("OrderedGreedy:      Running...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();
        color = new Color(n);

        // sorts the vertices in order of degree
        degreeSort();

        // colors all vertices
        for(int i = 0; i < sortedDegrees.length; i++)
        {
            giveColor(sortedDegrees[i]);
        }

        chromaticNumber = color.chromNum();


        System.out.println("OrderedGreedy:      Chromatic Number: " + chromaticNumber);
        System.out.println("OrderedGreedy:      Finished Running.");
        Log.endTimer("OrderedGreedy", chromaticNumber);

    }

    /**
     * gives an appropriate color the the vertex at hand
     */
    private static void giveColor(int vertex)
    {
        int[] vertices = ConnectedVertices.get(vertex);

        if(color.getColor(vertex) == 0)
        {
            color.setColor(vertex, 1);
        }

        for (int i = 0; i < vertices.length; i++)
        {
            if(color.getColor(vertex) == color.getColor(vertices[i]))
            {
                color.setColor(vertex, color.getColor(vertices[i]) + 1);
                i = -1;
            }
        }
    }

    /**
     * sorts the vertices in order of degree and
     * puts this order in an array.
     */
    private static void degreeSort()
    {
        //make array that stores the degrees of each vertex
        degrees = new int[n];

        for(int i = 1; i <= n; i++)
        {
            for(int j = 0; j < e.length; j++)
            {
                if(i == e[j].u)
                {
                    degrees[i - 1] = degrees[i - 1] + 1;
                }
                else if(i == e[j].v)
                {
                    degrees[i - 1] = degrees[i - 1] + 1;
                }
            }
        }

        //calculate maximum degree
        int maxDegree = 0;

        for(int i = 0; i < degrees.length; i++)
        {
            maxDegree = Math.max(maxDegree, degrees[i]);
        }


        //make array storing vertices by decreasing order of degrees
        sortedDegrees = new int[n];

        int position = 0;

        for(int i = maxDegree; i >= 0; i--)
        {
            for(int j = 0; j < degrees.length; j++)
            {
                if(degrees[j] == i)
                {
                    sortedDegrees[position] = j + 1;
                    position++;
                }
            }
        }
    }


    public static int getChrom()
    {
        return chromaticNumber;
    }


}
