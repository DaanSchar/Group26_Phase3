import graph.ConnectedVertices;
import graph.Graph;



public class Main
{
    public static void main(String[] args)
    {

        // data setup
        Graph.read(args[0]);
        ConnectedVertices.makeMatrix();

        // run your algorithm here
    }
}
