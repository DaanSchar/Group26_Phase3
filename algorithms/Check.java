
package algorithms;

import color.Color;
import graph.ColEdge;
import graph.Graph;


/**
 * this class checks if a coloring of the graph is correct, aka
 * there are no infringing colorings.
 */
public class Check
{

    private static int n;
    private static int m;
    private static ColEdge[] e;

    public static boolean isCorrect(Color color)
    {
        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        for(int i = 0; i < m; i++)
        {
            if(color.getColor(e[i].u) == color.getColor(e[i].v))
            {
                System.out.println(e[i].u + " = " + e[i].v);
                return false;
            }
            if(color.getColor(e[i].u) == 0 || color.getColor(e[i].v) == 0)
            {
                return false;
            }
        }
        return true;
    }
}