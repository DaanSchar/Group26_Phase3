package algorithms;

import color.Color;
import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;
import logging.Log;

import java.util.ArrayList;

public class BackTracking_try
{

    private static int n;
    private static int m;
    private static ColEdge[] e;

    private static int q;
    private static int l;
    private static int k;
    private static int[] sortedDegrees;
    private static int[] L;
    private static ArrayList U;
    private static Color color;

    private static int count;
    private static int round;

    private static long starttime;
    private static long runtime;



    public static void run() {

        Log.startTimer();
        starttime = System.currentTimeMillis();

        System.out.println("BackTracking:         Running BackTracking...");

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();
        color = new Color(n);

        // setup
        //degreeSort();
        //color.setColor(sortedDegrees[0], 1);

        color.setColorBackTracking(0, 1);

        q = n;
        l = 1;
        k = 1; // (2nd index)
        L = new int[n]; //storing number of colors for a vertex in current solution
        L[0] = 1; // number of colors used for vertex 1
        U = new ArrayList <ArrayList<Integer>> (n); //construct 2D ArrayList

        for(int i = 0; i < n; i++)
        {
            U.add(new ArrayList<Integer> (n)); //create 2D ArrayList of n*n size
        }

        //getPosColorSet(sortedDegrees[k]);
        getPosColorSet(k);

    }

    public static void degreeSort()
    {
        //make array that stores the degrees of each vertex
        int[] degrees = new int[n];

        for(int i = 1; i <= n; i++) {

            for(int j = 0; j < e.length; j++) {

                if(i == e[j].u) {

                    degrees[i - 1] = degrees[i - 1] + 1;
                }
                else if(i == e[j].v) {

                    degrees[i - 1] = degrees[i - 1] + 1;
                }
            }
        }

        //calculate maximum degree
        int maxDegree = 0;

        for(int i = 0; i < degrees.length; i++) {
            maxDegree = Math.max(maxDegree, degrees[i]);
        }

        //make array storing vertices by decreasing order of degrees
        sortedDegrees = new int[n];

        int position = 0;

        for(int i = maxDegree; i >= 0; i--) {
            for(int j = 0; j < degrees.length; j++) {
                if(degrees[j] == i) {
                    sortedDegrees[position] = j + 1;
                    position++;
                }
            }
        }
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
     * computes the set of assignable unused colors
     * @param k the vertex that is considered
     */
    private static void getPosColorSet(int k){

        getPosCol(k);
        checkPosColorSet(k);
    }

    /**
     * returns array containing the possible colors this vertex can be assigned.
     */
    private static void getPosCol(int k)
    {
        //int[] vertices = ConnectedVertices.get(sortedDegrees[k]);
        int[] vertices = ConnectedVertices.get(k);
        ArrayList<Integer> posCol = new ArrayList();

        // makes an arraylist which contains all colors smaller to q
        for(int i = 0; i < q; i++)
        {
            posCol.add(i+1);
        }

        // removes colors this vertex is adjacent to
        for(int i = 0; i< vertices.length; i++)
        {
            posCol.remove((Integer.valueOf(color.getColor(vertices[i]))));
        }

        //inserts posCol into U(k)
        U.set(k, (ArrayList)posCol);


        System.out.println("k: " + (k+1));
        System.out.println("U: " + U.get(k));

    }

    /**
     * computes the set of assignable unused colors and
     * checks whether the assignable set of colors is empty
     * @param k the vertex that is considered
     */
    private static void checkPosColorSet(int k) {

        //get U(k)
        ArrayList<Integer> newPosCol;

        newPosCol = (ArrayList)U.get(k);

        System.out.println("new k: " + (k+1));
        System.out.println("new U: " + U.get(k));

        int[] posColArr = new int[newPosCol.size()];

        // copies over from arraylist to array
        for(int i = 0; i < posColArr.length; i++)
        {
            posColArr[i] = (int)newPosCol.get(i);
        }

        // if the set of possible colors of vertex k is empty
        if(isEmpty(posColArr))
        {
            System.out.println("is empty");
            backtrack(k);

        } else {
            // sets the color of vertex k to the smallest color in U(array of all assignable colors)
            int smallestColor = getMin(posColArr);
            //color.setColor(sortedDegrees[k], smallestColor);
            color.setColorBackTracking(k, smallestColor);

            //removes smallestColor from newPosCol
            newPosCol.remove(Integer.valueOf(color.getColorBackTracking(k)));

            System.out.println("smallest color: " + smallestColor);
            System.out.println("newPosCol after remove: " + newPosCol);

            //insert newPosCol back into U
            U.set(k, (ArrayList)newPosCol);

            System.out.println("U(k) after removal: " + U.get(k));

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

    //check()
    //checks whether smallestColor >= q
    //if yes backtrack(k)
    //if no check whether smallestColor > l
        //if yes l += 1
        //if no check whether k=n
            //if yes store()
            //if no color(k) = l and k += 1

    /**
     * performs some checks on whether the coloring is promising
     * @param k the vertex that is considered
     */
    private static void check(int k)
    {
        System.out.println("check: i >= q");
        //System.out.println("i = " + (color.getColor(sortedDegrees[k])));
        System.out.println("i = " + (color.getColorBackTracking(k)));
        System.out.println("q = " + q);

        //if((color.getColor(sortedDegrees[k])) >= q) {
        if((color.getColorBackTracking(k) >= q)) {
            backtrack(k);
        }
        else{
            System.out.println("check i > l");
            //System.out.println("i = " + (color.getColor(sortedDegrees[k])));
            System.out.println("i = " + (color.getColorBackTracking(k)));
            System.out.println("l = " + l);
            //if((color.getColor(sortedDegrees[k])) > l)
            if((color.getColorBackTracking(k)) > l)
            {
                //while((color.getColor(sortedDegrees[k])) > l)
                while((color.getColorBackTracking(k)) > l)
                {
                    l += 1;
                }
            }
            System.out.println("check: k < n");
            System.out.println("k = " + (k+1));
            System.out.println("n = " + n);
            if(k == n - 1){//last vertex in the array
                System.out.println("STORE");
                store();
            }
            else{

                L[k] = l;
                k += 1;
                System.out.println("UPDATED k = " + (k+1) + " GO TO STEP 2");

                getPosColorSet(k);

            }
        }
    }

    //store()
    //q=l
    //j=1
    //getVertex(largestColor)
    //set k = j-1 and l = q-1

    /**
     * updates the values of the coloring
     */
    private static void store()
    {
        System.out.println("stored: q = " + l);
        q = l;
        System.out.println("updated: k = " + (color.getVertex(q) - 1));
        k = color.getVertex(q) - 2;
        l = q - 1;
        System.out.println("updated: l = " + (q-1));

        round += 1;
        System.out.println("round: " + round);

        System.out.println("colors:");
        color.printColorList();

        System.out.println(color.chromNum() + " colors have been used.");

        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");

        //start new round
        System.out.println("starting new round...");
        System.out.println("starting at vertex: " + (k+1));

        checkPosColorSet(k);
    }


    public static void end()
    {
        int result = q;
        runtime = System.currentTimeMillis() - starttime;

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





