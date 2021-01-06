package algorithms;

import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;

/**
 * ordered coloring of vertices with the lowest possible color
 * such that no clash is induced
 * oder is determined dynamically by choosing vertex with largest
 * number of distinct colors assigned to adjacent vertices
 *
 *
 *
 *
 * 1.arrange vertices by decreasing order of degrees
 * 2.Color vertex maxDeg with color 1
 * 3.Choose vertex with max saturation degree- if there is equality choose vertex of maxDeg in uncoloured subgraph
 * 4.Color chosen vertex with lowest possible color
 * 5.If all vertices are colored, stop, otherwise return to 3
 *
 * @author Leo
 */

public class DSatur {

    private static int n;
    private static int m;
    private static ColEdge[] e;

    private static int[][] connectedVertices;
    private static int[] colors;
    private static int[] degrees;
    private static int[] sortedDegrees;

    private static int maxDegreeVertex;
    private static int maxSatVertex;

    private static Color color;

    private static long starttime;
    private static long runtime;


    public static void run()
    {
        starttime = System.currentTimeMillis();
        System.out.println("DSatur:         Running DSatur...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        degreeSort();
        firstColoring();
        coloring();

    }


    //step 1
    public static void degreeSort()
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

        /*System.out.println("List of all vertices with their degrees");

        for(int i = 0; i < degrees.length; i++)
        {
            System.out.println("Vertex: " + (i + 1) + " degree: " + degrees[i]);
        }*/


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

        /*System.out.println("Vertices arranged by decreasing order of degree");

        for(int i = 0; i < sortedDegrees.length; i++)
        {
            System.out.println("Vertex: " + sortedDegrees[i] + " degree: " + degrees[sortedDegrees[i]-1]);
        }*/

        maxDegreeVertex = sortedDegrees[0];

        /*//loop through all vertices of sortedDegrees
        for(int i = 0; i < sortedDegrees.length; i++)
        {
            //show connectedVertices
            System.out.println("vertex: " + sortedDegrees[i] + " connected to :" + Arrays.toString(ConnectedVertices.get(sortedDegrees[i])));
        }*/

    }

    //step 2
    public static void firstColoring()
    {
        //colors = new int[n];
        color = new Color(n);

        color.setColor(maxDegreeVertex, 1);

        //color.printColorList();

    }

    //step 3
    public static int saturation()
    {
        int maxSaturation = 0;

        int [] saturationArray = new int[n];

        //loop through all vertices of sortedDegrees
        for(int i = 0; i < sortedDegrees.length; i++)
        {
            if(color.getColor(sortedDegrees[i]) == 0)
            {
                int saturation = 0;

                //get connected vertices
                int [] connVertices = ConnectedVertices.get(sortedDegrees[i]);

                //loop through connected vertices
                for(int j = 0; j < connVertices.length; j++)
                {
                    if(color.getColor(connVertices[j]) != 0 && color.getColor(connVertices[j]) != saturationArray[color.getColor(connVertices[j])])
                    {
                        saturationArray[color.getColor(connVertices[j])] = color.getColor(connVertices[j]);
                        saturation++;
                    }

                }

                if(saturation > maxSaturation)
                {
                    maxSaturation = saturation;
                    maxSatVertex = sortedDegrees[i];
                }
            }

        }

        //System.out.println("vertex " + maxSatVertex + " has maximal saturation: " + maxSaturation);

        if(maxSaturation == 0)
        {
            for(int i = 0; i < sortedDegrees.length; i++)
            {
                if(color.getColor(sortedDegrees[i]) == 0)
                {
                    maxDegreeVertex = sortedDegrees[i];
                }
            }

            //System.out.println("returning maxDegreeVertex");
            return maxDegreeVertex;
        }

        //System.out.println("returning maxSatVertex");
        return maxSatVertex;

    }

    //step 4
    public static void condition()
    {
        if(color.allColored() == true)
        {
            //System.out.println("end");
            end();
        }
        else
        {
            coloring();
        }
    }

    public static void coloring()
    {
        int vertex = saturation();

        color.setColor(vertex, 1);

        condition();
    }

    //step 5
    public static void end()
    {
        runtime = System.currentTimeMillis() - starttime;

        int result = color.chromNum();
        System.out.println("DSatur:         " + result + " colors have been used" );
        System.out.println("DSatur:         Finished running DSatur");
        Log.endTimer("DSatur", result);



        //color.printColorList();
    }

}
