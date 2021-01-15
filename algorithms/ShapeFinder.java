package algorithms;

import graph.ColEdge;
import graph.ConnectedVertices;
import graph.Graph;

public class ShapeFinder
{

    private static int n;
    private static int m;
    private static ColEdge[] e;


    private static int shapeE;
    private static int shapeW;


    public static void run() {

        n = Graph.getN();
        m = Graph.getM();
        e = Graph.getE();


        for(int i = 1; i < n + 1; i++)
        {

            int degree = 0;

            for(int j = 0; j < m; j++)
            {
                if(j == e[j].u)
                {
                    degree++;
                } else if(j == e[j].v){
                    degree++;
                }
            }

            loop(1);
        }

    }
    private static void loop(int startVertex)
    {
        for(int i = startVertex ; i < startVertex+1; i++)
        {

            int degree = 0;

            for(int j = 0; j < m; j++)
            {
                if(i == e[j].u)
                {
                    degree++;
                } else if(i == e[j].v){
                    degree++;
                }
            }

            System.out.println(degree);

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

                    if (contains(verticesJ, K) && degree > 2)
                    {
                        //System.out.println("TRIANGLE");
                        //System.out.println(i + " " + verticesI[j] + " "+ verticesI[k]);
                        for(int l = 0; l < verticesI.length; l++)
                        {
                            int L = verticesI[l];
                            int[] verticesL = ConnectedVertices.get(L);

                            if(contains(verticesI, L) && contains(verticesJ, L) && contains(ConnectedVertices.get(verticesI[k]), L) && degree > 3)
                            {

                                for(int m = 0; m < verticesI.length; m++)
                                {
                                    int M = verticesI[m];
                                    int[] verticesM = ConnectedVertices.get(M);

                                    if(contains(verticesI, M) && contains(verticesJ, M) && contains(verticesK, M) && contains(verticesL, M) && degree > 4)
                                    {

                                        for(int o = 0; o < verticesI.length; o++)
                                        {
                                            int O = verticesI[o];
                                            int[] verticesO = ConnectedVertices.get(O);

                                            if(contains(verticesI, O) && contains(verticesJ, O) && contains(verticesK, O) && contains(verticesL, O) && contains(verticesM, O) && degree > 5)
                                            {
                                                for(int p = 0; p < verticesI.length; p++)
                                                {
                                                    int P = verticesI[p];
                                                    int[] verticesP = ConnectedVertices.get(P);

                                                    if(contains(verticesI, P) && contains(verticesJ, P) && contains(verticesK, P) && contains(verticesL, P) && contains(verticesM, P) && contains(verticesO, P) && degree > 6)
                                                    {
                                                        for(int q = 0; q < verticesI.length; q++)
                                                        {

                                                         int Q = verticesI[q];
                                                         int[] verticesQ = ConnectedVertices.get(Q);

                                                            if(contains(verticesI, Q) && contains(verticesJ, Q) && contains(verticesK, Q) && contains(verticesL, Q) && contains(verticesM, Q) && contains(verticesO, Q) && contains(verticesP, Q) && degree > 7)
                                                            {
                                                                for(int w = 0; w < verticesI.length; w++) {

                                                                    int W = verticesI[w];
                                                                    int[] verticesW = ConnectedVertices.get(W);

                                                                    if(contains(verticesI, W) && contains(verticesJ, W) && contains(verticesK, W) && contains(verticesL, W) && contains(verticesM, W) && contains(verticesO, W) && contains(verticesP, W) && contains(verticesQ,W) && degree > 8)
                                                                    {
                                                                        shapeW++;
                                                                        if(shapeW > 100)
                                                                        {
                                                                            return;
                                                                        }
                                                                        System.out.println("contains shape-2 " + i);
                                                                        i++;

                                                                        for(int e = 0; e < verticesI.length; e++)
                                                                        {
                                                                            int E = verticesI[e];
                                                                            int[] verticesE = ConnectedVertices.get(E);

                                                                            if (contains(verticesI, E) && contains(verticesJ, E) && contains(verticesK, E) && contains(verticesL, E) && contains(verticesM, E) && contains(verticesO, E) && contains(verticesP, E) && contains(verticesQ, E) && contains(verticesW, E)&& degree > 9)
                                                                            {
                                                                                shapeE++;
                                                                                if(shapeE > 100)
                                                                                {
                                                                                    return;
                                                                                }

                                                                                i++;
                                                                                System.out.println("contains shape-1 " + i);

                                                                                for(int r = 0; r < verticesI.length; r++)
                                                                                {
                                                                                    int R = verticesI[r];
                                                                                    int[] verticesR = ConnectedVertices.get(R);

                                                                                    if (contains(verticesI, R) && contains(verticesJ, R) && contains(verticesK, R) && contains(verticesL, R) && contains(verticesM, R) && contains(verticesO, R) && contains(verticesP, R) && contains(verticesQ, R) && contains(verticesW, R) && contains(verticesE, R) && degree > 10)
                                                                                    {

                                                                                        System.out.println("contains shape " + i);
                                                                                        return;
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
