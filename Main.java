import algorithms.Bipartite;
import algorithms.ColoringExample;
import graph.ConnectedVertices;
import graph.Graph;



public class Main
{
    public static void main(String[] args)
    {

        // data setup
        Graph.read(args[0]);
        ConnectedVertices.makeMatrix();

        // example algorithm for coloring a graph
        Bipartite.run();
    }
}
