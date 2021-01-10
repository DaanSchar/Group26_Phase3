package algorithms;

/**
 * basic backtracking algorithm - not properly working yet
 */

import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;

import java.util.ArrayList;

public class BackTracking
{

    private static int n;
    private static int m;
    private static ColEdge[] e;

    private static int q;
    private static int l;
    private static int k;
    private static int[] L;
    private static ArrayList U;
    private static Color color;

    private static int count;
    private static int round;

    private static long starttime;


    public static void run() {

        Log.startTimer();
        starttime = System.currentTimeMillis();

        System.out.println("BackTracking:         Running BackTracking...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();
        color = new Color(n);

        // setup

        q = n;
        l = 1;
        k = 1; // (2nd index)
        L = new int[n]; //stores best solution for a vertex k in current partial solution
        L[0] = 1; // best solution for vertex 1 so far

        U = new ArrayList <ArrayList<Integer>> (n); //stores colors that are unused so far for vertex k

        for(int i = 0; i < n; i++)
        {
            U.add(new ArrayList<Integer> (n)); //create empty 2D ArrayList of n*n size
        }

        color.setColorBackTracking(0, 1);

        getPosColorSet(k);

    }


    /**performs the recursive call until vertex 1 is reached
     * @param k the vertex that is considered
     */
    private static void backtrack(int k)
    {
        if(k == 0) {
            //STOP
            end();

        } else {

            count += 1;
            System.out.println("backtracking: " + count);
            k -= 1;
            l = L[k];
            System.out.println("l updated: " + l);

            checkPosColorSet(k);
        }
    }


    /**
     * computes the set of assignable colors of vertex k
     * @param k the vertex that is considered
     */
    private static void getPosColorSet(int k){

        getPosCol(k);
        checkPosColorSet(k);
    }


    /**
     * returns array containing the possible colors this vertex can be assigned.
     * @param k the vertex that is considered
     */
    private static void getPosCol(int k)
    {
        int[] vertices = ConnectedVertices.get(k+1);
        ArrayList<Integer> posCol = new ArrayList();

        System.out.println("getPosCol: q = " + q);

        // makes an arraylist which contains all colors smaller to q
        for(int i = 0; i < q - 1; i++)
        {
            posCol.add(i+1);
        }

        System.out.println("possible colors of vertex " + (k+1) + ": " + posCol);

        // removes colors this vertex is adjacent to
        for(int i = 0; i< vertices.length; i++)
        {
            posCol.remove((Integer.valueOf(color.getColor(vertices[i]))));
        }

        System.out.println("removing own color: " + color.getColorBackTracking(k));
        //removes color this vertex is colored with
        posCol.remove(Integer.valueOf(color.getColorBackTracking(k)));

        //eliminate L(k)
        System.out.println("remove L(k): " + L[k]);
        posCol.remove((Integer.valueOf(L[k])));


        //inserts posCol into U(k) at index k
        U.set(k, (ArrayList)posCol);


        //System.out.println("k: " + (k+1));
        //System.out.println("U: " + U.get(k));
        System.out.println("possible colors of vertex " + (k+1) + "after removal: " + U.get(k));

    }

    /**
     * checks whether U(k) - the unused assignable set of colors -  is empty
     * @param k the vertex that is considered
     */
    private static void checkPosColorSet(int k) {

        //get U(k) - at position k
        ArrayList<Integer> newPosCol = (ArrayList)U.get(k);

        //System.out.println("new k: " + (k+1));
        //System.out.println("new U: " + U.get(k));

        int[] posColArr = new int[newPosCol.size()];

        // copies over from arraylist to array
        for(int i = 0; i < posColArr.length; i++)
        {
            posColArr[i] = (int)newPosCol.get(i);
        }

        // if the set of possible colors of vertex k is empty
        if(isEmpty(posColArr))
        {
            System.out.println("U of vertex " + (k+1) + " is empty");
            backtrack(k);

        } else {
            // sets the color of vertex k to the smallest color in U(array of all assignable colors)
            int smallestColor = getMin(posColArr);
            color.setColorBackTracking(k, smallestColor);

            //removes smallestColor from newPosCol
            newPosCol.remove(Integer.valueOf(color.getColorBackTracking(k)));

            //System.out.println("smallest color: " + smallestColor);
            //System.out.println("newPosCol after remove: " + newPosCol);

            //insert newPosCol back into U at position k
            U.set(k, (ArrayList)newPosCol);

            //System.out.println("U(k) after removal: " + U.get(k));

            check(k);

        }
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
     * performs some checks on whether the current coloring is promising to yield a better solution that has been
     * achieved so far
     * @param k the vertex that is considered
     */
    private static void check(int k)
    {
        //System.out.println("check: i >= q");
        //System.out.println("i = " + (color.getColorBackTracking(k)));
        //System.out.println("q = " + q);

        if((color.getColorBackTracking(k) >= q)) {
            backtrack(k);
        }
        else{
            //System.out.println("check i > l");
            //System.out.println("i = " + (color.getColorBackTracking(k)));
            //System.out.println("l = " + l);

            while((color.getColorBackTracking(k)) > l)
                {
                    l += 1;
                }

            //System.out.println("check: k < n");
            //System.out.println("k = " + (k+1));
            //System.out.println("n = " + n);

            if(k == n - 1) { //last vertex in the array
                System.out.println("STORE");
                store();
            }
            else{

                //L[k] = l;

                //updating L(k) to the best current solution of k
                if(L[k] == 0) {

                    L[k] = color.getColorBackTracking(k);
                }
                else if(color.getColorBackTracking(k) < L[k]){

                    L[k] = color.getColorBackTracking(k);
                }


                System.out.println("UPDATE L[k] = " + L[k]);
                k += 1;
                System.out.println("UPDATED k = " + (k+1) + " GO TO STEP 2");

                getPosColorSet(k);

            }
        }
    }

    /**
     * updates the values of the coloring
     */
    private static void store()
    {
        //System.out.println("stored: q = " + l);
        q = l;
        //System.out.println("updated: k = " + (color.getVertex(q) - 1));
        k = color.getVertex(q) - 1;
        l = q - 1;
        //System.out.println("updated: l = " + (q-1));

        round += 1;
        System.out.println("round: " + round);

        System.out.println("colors:");
        color.printColorList();

        System.out.println(color.chromNum() + " colors have been used.");
        System.out.println("q = " + q);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        //start new round
        System.out.println("starting new round...");
        System.out.println("starting at vertex: " + (k+1));

        checkPosColorSet(k);
    }


    public static void end()
    {
        int result = q;
        long runtime = System.currentTimeMillis() - starttime;

        System.out.println("BackTracking:         Finished running BackTracking");
        System.out.println("BackTracking:         " + result + " colors have been used" );
        System.out.println("BackTracking:         runtime: " + runtime);
        Log.endTimer("BackTracking", result);

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





