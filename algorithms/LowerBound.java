package algorithms;

import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;

import java.util.ArrayList;

public class LowerBound
{

    private static int n;
    private static int m;
    private static ColEdge[] e;

    private static ArrayList<Integer> R;
    private static ArrayList<Integer> P;
    private static ArrayList<Integer> X;
    private static int maxSize;

    public static int get()
    {
        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        P = new ArrayList<Integer>();
        R = new ArrayList<Integer>();

        maxSize = 0;

        for(int vertex = 1; vertex < n + 1; vertex++)
        {
            R.add(vertex);
            addToP(vertex);

            for (int i = 1; i < n + 1; i++) {
                if (isConnectedToR(i)) {
                    R.add(i);
                }
            }
            maxSize();
            resetP();
            resetR();
        }
        return maxSize;
    }

    /**
     *  adds all vertices adjacent to vertex to P
     */
    private static void addToP(int vertex)
    {
        int[] vertices = ConnectedVertices.get(vertex);

        for(int i = 0;  i< vertices.length; i++)
        {
            P.add(vertices[i]);
        }
    }

    /**
     * checks if vertex is connected to all current nodes of the clique
     */
    private static boolean isConnectedToR(int vertex)
    {
        int[] vertices = ConnectedVertices.get(vertex);

        for(int i = 0; i < R.size(); i++)
        {
            // if R[i] is not adjacent with vertex
            if(!(contains(vertices, R.get(i))))
            {
                return false;
            }
        }
        return true;
    }

    private static void resetP()
    {
        P = new ArrayList<Integer>();
    }

    private static void resetR()
    {
        R = new ArrayList<Integer>();
    }

    /**
     * checks if an int array contains an int value
     */
    private static boolean contains(int[] a, int value)
    {
        for(int i = 0; i < a.length; i++)
        {
            if(a[i] == value)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * calculates the biggest size of R
     */
    private static void maxSize()
    {
        if(R.size() > maxSize)
        {
            maxSize = R.size();
        }
    }

}
