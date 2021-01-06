package algorithms;

import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;

public class ColoringExample
{
    private static int n;
    private static int m;
    private static ColEdge[] e;
    private static Color color;

    public static void run()
    {

        Log.startTimer();
        System.out.println("ColoringExample:Running...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        color = new Color(n);


        int[] path = new int[n];

        for(int i = 0; i < n; i++)
        {
            path[i] = i+1;
        }

        for(int i = 0; i < n; i++)
        {
            giveColor(path[i]);
        }

        System.out.println(color.chromNum());

        System.out.println("ColoringExample:Finished Running");
        Log.endTimer("ColoringExample", color.chromNum());

    }

    private static void giveColor(int vertex)
    {
        int[] vertices = ConnectedVertices.get(vertex);

        int max = 0;

        for(int i = 0; i < vertices.length; i++)
        {
            if(color.getColor(vertices[i]) > max)
            {
                max = color.getColor(vertices[i]);
            }
        }

        color.setColor(vertex, (max + 1));

    }
}
