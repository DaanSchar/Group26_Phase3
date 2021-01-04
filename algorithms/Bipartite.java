package algorithms;

import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;

import java.util.Arrays;

public class Bipartite
{
    private static int n;
    private static int m;
    private static ColEdge[] e;
    private static Color color;

    public static void run()
    {

        System.out.println("Running Bipartite...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();
        color = new Color(n);

        for(int startVertex = 1; startVertex < n+1; startVertex++)
        {
            color.setColor(startVertex, 1);

            for (int vertex = 1; vertex < n + 1; vertex++) {
                System.out.println(vertex);

                int[] vertices = ConnectedVertices.get(vertex);
                System.out.println(Arrays.toString(vertices));

                for (int i = 0; i < vertices.length; i++) {
                    giveColor(vertices[i]);
                }
            }
        }

        color.printColorList();

        System.out.println("Finished running Bipartite");
    }

    /**
     * sets the colors of the adjacent vertices of the input vertex to -1
     * and the input vertex itself to 1
     */
    private static void giveColor(int vertex)
    {
        if(color.getColor(vertex) == 1)
        {
            try {
                int[] vertices = ConnectedVertices.get(vertex);

                for (int i = 0; i < vertices.length; i++) {
                    if (color.getColor(vertices[i]) == 1) {
                        throw new BipartiteInvalidException();
                    }

                    // colors all adjacent vertices 1
                    color.setColor(vertices[i], -1);
                }
            }
            catch (BipartiteInvalidException e)
            {
                System.out.println(e.getMessage());
            }
        }



        if(color.getColor(vertex) == -1)
        {
            try {
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
            catch (BipartiteInvalidException e)
            {
                System.out.println(e.getMessage());
            }
        }


    }


    /**
     * sets the colors of the adjacent vertices of the input vertex to 1
     * and the input vertex itself to -1
     */
    private static void setNegative(int vertex)
    {
        try {
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

            //sets the input vertex to -1
            color.setColor(vertex, -1);

        } catch (BipartiteInvalidException e){
            System.out.println(e.getMessage());
        }
    }
}
