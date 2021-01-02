package color;

/**
 * Class which stores the coloring of the vertices of the graph
 */

public class Color
{

    private int n;
    private int[] colorList;

    public Color(int n)
    {
        this.n = n;
        colorList = new int[n];
    }



    public void setColor(int vertex, int color)
    {
        colorList[vertex-1] = color;
    }

    public int getColor(int vertex)
    {
        return colorList[vertex-1];
    }



    public void printColorList()
    {
        for(int i = 0; i < n; i++)
        {
            System.out.println("Vertex " + (i + 1) + ":    " + colorList[i]);
        }
    }
}
