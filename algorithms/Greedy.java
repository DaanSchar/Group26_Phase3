package algorithms;

import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;

import java.util.Arrays;
import java.util.Random;


public class Greedy {

    private static int n;
    private static int m;
    private static ColEdge[] e;
    private static Color color;
    private static int[] path;
    private static int chromNum;

    public static void run(int calculations)
    {

        System.out.println("Greedy:         Running...");
        Log.startTimer();

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        int[] chromNums = new int[calculations];

        for(int i = 0; i < n; i++)
        {
            color = new Color(n);
            colorGraph();
            chromNums[i] = color.chromNum();
        }

        chromNum = getMin(chromNums);

        System.out.println("Greedy:         Chromatic number:" + getMin(chromNums));
        System.out.println("Greedy:         Finished Running.");
        Log.endTimer("Greedy", getMin(chromNums));
    }

    private static void giveColor(int vertex)
    {
       // System.out.println("Greedy:         " + vertex);
        int[] vertices = ConnectedVertices.get(vertex);

        for (int i = 0; i < vertices.length; i++)
        {
            if(color.getColor(vertex) <= color.getColor(vertices[i]))
            {
                color.setColor(vertex, color.getColor(vertices[i]) + 1);
            }
        }
    }

    private static int colorGraph()
    {
        path = new int[n];

        // initial path consists of 1,2,3,4,..,n
        for(int i = 0; i < n; i++)
        {
            path[i] = (i+1);
        }

        //randomising the sequence
        makeRandomPath(path);


        for(int i = 0; i < n; i++)
        {
            giveColor(path[i]);
        }

        return color.chromNum();
    }

    private static void makeRandomPath(int arr[])
    {
        Random r = new Random();


        for (int i = n-1; i > 0; i--)
        {

            int j = r.nextInt(i+1);

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private static int getMin(int[] a)
    {

        int min = a[0];

        for(int i = 0; i < a.length; i++)
        {
            if(a[i] < min)
            {
                min = a[i];
            }
        }

        return min;

    }

    public static int getChrom()
    {
        return chromNum;
    }

}
