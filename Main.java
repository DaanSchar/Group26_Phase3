import algorithms.Bipartite;
import algorithms.ColoringExample;
import algorithms.IsolatedVertex;
import graph.ConnectedVertices;
import graph.Graph;



public class Main
{
    public static void main(String[] args)
    {

        // data setup
        Graph.read(args[0]);
        ConnectedVertices.makeMatrix();

        IsolatedVertex.hasIsolated();
        Bipartite.run();
    }
}
