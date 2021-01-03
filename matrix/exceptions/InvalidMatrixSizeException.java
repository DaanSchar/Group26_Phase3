package matrix.exceptions;

public class InvalidMatrixSizeException extends Exception
{

    public InvalidMatrixSizeException()
    {
        super("Cannot process Matrix: Invalid Size");
    }

    public InvalidMatrixSizeException(String message)
    {
        super(message);
    }

}
