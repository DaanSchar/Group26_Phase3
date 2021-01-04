package algorithms;

public class BipartiteInvalidException extends Exception
{
    public BipartiteInvalidException()
    {
        super("Bipartite:  Cannot give vertex appropriate color");
    }

    public BipartiteInvalidException(String message)
    {
        super(message);
    }
}

