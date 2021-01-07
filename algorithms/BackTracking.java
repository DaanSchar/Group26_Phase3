package algorithms;

import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;

public class BackTracking
{

    private static int n;
    private static int m;
    private static ColEdge[] e;

    private static int q;
    private static int l;
    private static int k;
    private static int[] U;
    private static Color color;



    public static void run()
    {
        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();
        color = new Color(n);

        // setup
        q = n;
        l = 1;
        k = 1; // (2nd index)
        color.setColor(1,1);

        // if the set of possible colors of vertex k is empty
        if(isEmpty(getPosCol(k)))
        {
            if(k == 1)
            {
                return;
            } else{
                k -= 1;
                l = color.getColor(k);
            }
        } else {
            // sets the color of vertex k to the smallest color in U(array of all assignable colors)

            int smallestColor =  getMin(getPosCol(k));

            color.setColor(k, smallestColor);
        }




    }


    /**
     * returns array containing the possible colors this vertex can be assigned.
     */
    private static int[] getPosCol(int vertex)
    {

        int[] vertices = ConnectedVertices.get(vertex);
        ArrayList posCol = new ArrayList();

        // makes an arraylist which contains all colors smaller and equal to q
        for(int i = 0; i < q; i++)
        {
            posCol.add(i+1);
        }

        // removes colors this vertex is adjacent to.
        for(int i = 0; i< vertices.length; i++)
        {
            posCol.remove((Integer.valueOf(color.getColor(vertices[i]))));
        }

        // removes current color of vertex from possible colors
        posCol.remove(Integer.valueOf(color.getColor(vertex)));

        int[] posColArr = new int[posCol.size()];

        // copies over from arraylist to array
        for(int i = 0; i < posColArr.length; i++)
        {
            posColArr[i] = (int)posCol.get(i);
        }

        return posColArr;
    }


    private static boolean isEmpty(int[] a)
    {
        if(a.length == 0)
        {
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns smallest value of an array
     */
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

}





