package algorithms;

import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;

import java.util.Arrays;

public class Greedy {

    private static int n;
    private static int m;
    private static ColEdge[] e;
    private static Color color;

    public static void run()
    {

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();
        color = new Color(n);

        for(int i = 1; i < n+1; i++)
        {
            giveColor(i);
        }


        color.printColorList();
        System.out.println(Arrays.toString(ConnectedVertices.get(2)));

        System.out.println(Check.isCorrect(color));
        System.out.println(color.chromNum());
    }

    private static void giveColor(int vertex)
    {
        int[] vertices = ConnectedVertices.get(vertex);

        int clr = 1;

        for (int i = 0; i < vertices.length; i++)
        {
            if(clr == color.getColor(vertices[i]))
            {
                clr++;
                i = 0;
            }
            System.out.println(clr);
        }
        color.setColor(vertex,clr);
    }

}
