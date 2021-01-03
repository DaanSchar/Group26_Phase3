package matrix;

import matrix.exceptions.InvalidMatrixSizeException;

import java.util.Arrays;

public class Matrix
{

    /**
     * multiplies 2 matrices and returns the result
     *
     * throws InvalidMatrixMultiplicationSizeException
     */
    public static int[][] multiply(int[][] a, int[][] b) {

        int[][] c = new int[a.length][b[0].length];

        // exception handling
        try {
            if (a[0].length != b.length)
            {
                throw new InvalidMatrixSizeException("Cannot multiply matrices: Invalid Size");
            }

            // both matrices are same square
            if (a.length == b[0].length && a.length == a[0].length)
            {
                for (int i = 0; i < a.length; i++)
                {
                    for (int j = 0; j < a.length; j++)
                    {
                        for (int k = 0; k < a.length; k++)
                        {
                            c[i][j] += a[i][k] * b[k][j];
                        }
                    }
                }
                return c;
            }

            // when a is taller than b
            if (a.length > b.length)
            {
                for (int i = 0; i < a.length; i++)
                {
                    for (int j = 0; j < b[0].length; j++)
                    {
                        for (int k = 0; k < a[0].length; k++)
                        {
                            c[i][j] += a[i][k] * b[k][j];
                        }
                    }
                }
                return c;
            } else {

                for (int i = 0; i < a.length; i++)
                {
                    for (int j = 0; j < b[0].length; j++)
                    {
                        for (int k = 0; k < a[0].length; k++)
                        {
                            c[i][j] += a[i][k] * b[k][j];
                        }
                    }
                }
                return c;
            }
        } catch (InvalidMatrixSizeException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }






















    /**
     * still testing this out. might be usefull later
     * @param a
     */
    public static void getDeterminant(int[][] a)
    {

        int size = a.length;
        int[][] tempMatrix = new int[size][size];

        for(int skip = 0; skip < a.length; skip++) {
            tempMatrix = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (j != skip && i != 0) {
                        tempMatrix[i][j] = a[i][j];
                    }
                }
            }
            for(int i = 0; i < a.length; i++)
            {
            System.out.println(Arrays.toString(tempMatrix[i]));
            }
            System.out.println();


            int[][] matrix = new int[size - 1][size - 1];
            int k = 0;

            for(int i = 1; i < size; i++)
            {
                for(int j = 0; j < size; j++)
                {
                    if(j != skip)
                    {
                        if (tempMatrix[i][j] != 0)
                        {
                            if(j >= matrix.length)
                            {
                                matrix[i - 1][j-1] = tempMatrix[i][j];
                            } else {
                                matrix[i - 1][j] = tempMatrix[i][j];
                            }
                        }
                    }
                }
            }

            for(int i = 0; i < matrix.length; i++)
            {
                System.out.println(Arrays.toString(matrix[i]));
            }
            System.out.println();


        }

    }


}
