package algorithms;

import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;

public class ShapeFinder
{

    private static int n;
    private static int m;
    private static ColEdge[] e;

    public static void run()
    {

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();

        for(int i = 1; i < n+1; i++)
        {
            int I = i;
            int verticesI[] = ConnectedVertices.get(I);

            for(int j = 0; j < verticesI.length; j++)
            {
                int J = verticesI[j];
                int[] verticesJ = ConnectedVertices.get(J);

                for(int k = 0; k < verticesI.length; k++)
                {
                    int K = verticesI[k];
                    int[] verticesK = ConnectedVertices.get(K);

                    if (contains(verticesJ, K))
                    {
                        //System.out.println("TRIANGLE");
                        //System.out.println(i + " " + verticesI[j] + " "+ verticesI[k]);
                        for(int l = 0; l < verticesI.length; l++)
                        {
                            int L = verticesI[l];
                            int[] verticesL = ConnectedVertices.get(L);

                            if(contains(verticesI, L) && contains(verticesJ, L) && contains(ConnectedVertices.get(verticesI[k]), L))
                            {

                                for(int m = 0; m < verticesI.length; m++)
                                {
                                    int M = verticesI[m];
                                    int[] verticesM = ConnectedVertices.get(M);

                                    if(contains(verticesI, M) && contains(verticesJ, M) && contains(verticesK, M) && contains(verticesL, M))
                                    {

                                        for(int o = 0; o < verticesI.length; o++)
                                        {
                                            int O = verticesI[o];
                                            int[] verticesO = ConnectedVertices.get(O);

                                            if(contains(verticesI, O) && contains(verticesJ, O) && contains(verticesK, O) && contains(verticesL, O) && contains(verticesM, O))
                                            {
                                                for(int p = 0; p < verticesI.length; p++)
                                                {
                                                    int P = verticesI[p];
                                                    int[] verticesP = ConnectedVertices.get(O);

                                                    if(contains(verticesI, P) && contains(verticesJ, P) && contains(verticesK, P) && contains(verticesL, P) && contains(verticesM, P) && contains(verticesO, P))
                                                    {

                                                    }
                                                }
                                            }
                                        }

                                    }

                                }

                            }
                        }


                    }
                }
            }

        }

    }

    private static boolean contains(int[] a, int value)
    {
        for(int i = 0; i < a.length; i++)
        {
            if(a[i] == value)
            {
                return true;
            }
        }
        return false;
    }


}
