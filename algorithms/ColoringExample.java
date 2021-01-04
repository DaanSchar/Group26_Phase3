package algorithms;

import color.Color;
import graph.ColEdge;
import graph.Graph;

import java.util.Arrays;

public class ColoringExample
{
    private static int n;
    private static int m;
    private static ColEdge[] e;
    private static Color color;

    public static void run()
    {

        System.out.println("Running Coloring Example...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        color = new Color(n);

        for(int i = 0; i < n; i++)
        {
            color.setColor(i + 1, i + 1);
        }

        System.out.println("Finished running Coloring Example.");

        color.printColorList();

    }


}
