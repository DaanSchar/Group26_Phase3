package graph;

/**
 * class that takes care of calculating the connected vertices
 */

public class ConnectedVertices
{

    private static int[][] connectMatrix;

    private static int n = Graph.getN();
    private static int m = Graph.getM();
    private static ColEdge[] e = Graph.getE();


    /**
     * makes a 2d array, containing a 1 for when the value of the row and the value of the column,
     * representing 2 vertices, are connected
     */
    public static void makeMatrix()
    {
        connectMatrix = new int[n][n];

        // sets the row of value u and the column of value v to 1 (this means they are connected) and vice versa
        // vertices are not considered to be connected to themselves.
        for(int i = 0; i < m;i++)
        {
            connectMatrix[e[i].u-1][e[i].v-1] = 1;
            connectMatrix[e[i].v-1][e[i].u-1] = 1;
        }
    }

    /**
     * returns an array containing all vertices that are connecte to vertex.
     */
    public static int[] get(int vertex)
    {
        vertex -= 1;

        int tempConnectedVertices[] = new int[100000];

        int j = 0;


        for (int i = 0; i < n; i++)
        {
            if (connectMatrix[vertex][i] == 1)
            {
                tempConnectedVertices[j] = i + 1;
                j++;
            }
        }

        int connectedVertices[] = new int[j];

        for(int i = 0; i < j;i++)
        {
            connectedVertices[i] = tempConnectedVertices[i];

        }

        return connectedVertices;

    }

    /**
     * prints the matrix showing all vertices and to which vertices they are
     * connected to.
     */
    public static void printMatrix()
    {
        for(int i = 0; i < n; i++)
        {
            System.out.println();
            for(int j = 0; j < n; j++)
            {
                System.out.print(connectMatrix[i][j] + " ");
            }
        }
    }
}
