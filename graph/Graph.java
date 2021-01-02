package graph; /**
 * Class that takes care of reading the graph files
 */

import java.io.*;

public class Graph
{

    public final static boolean DEBUG = false;
    public final static String COMMENT = "//";

    private static int n;
    private static int m;
    private static ColEdge[] e;


    /**
     * reads the graph file
     * @param file
     */
    public static void read(String file)
    {
        if( file.length() < 1 )
        {
            System.out.println("Error! No filename specified.");
            System.exit(0);
        }


        String inputfile = file;

        boolean seen[] = null;

        n = -1;

        m = -1;

        e = null;

        try 	{
            FileReader fr = new FileReader(inputfile);
            BufferedReader br = new BufferedReader(fr);

            String record = new String();


            while ((record = br.readLine()) != null)
            {
                if( record.startsWith("//") ) continue;
                break;
            }

            if( record.startsWith("VERTICES = ") )
            {
                n = Integer.parseInt( record.substring(11) );
                if(DEBUG) System.out.println(COMMENT + " Number of vertices = "+n);
            }

            seen = new boolean[n+1];

            record = br.readLine();

            if( record.startsWith("EDGES = ") )
            {
                m = Integer.parseInt( record.substring(8) );
                if(DEBUG) System.out.println(COMMENT + " Expected number of edges = "+m);
            }

            e = new ColEdge[m];

            for( int d=0; d<m; d++)
            {
                if(DEBUG) System.out.println(COMMENT + " Reading edge "+(d+1));
                record = br.readLine();
                String data[] = record.split(" ");
                if( data.length != 2 )
                {
                    System.out.println("Error! Malformed edge line: "+record);
                    System.exit(0);
                }
                e[d] = new ColEdge();

                e[d].u = Integer.parseInt(data[0]);
                e[d].v = Integer.parseInt(data[1]);

                seen[ e[d].u ] = true;
                seen[ e[d].v ] = true;

                if(DEBUG) System.out.println(COMMENT + " Edge: "+ e[d].u +" "+e[d].v);

            }

            String surplus = br.readLine();
            if( surplus != null )
            {
                if( surplus.length() >= 2 ) if(DEBUG) System.out.println(COMMENT + " Warning: there appeared to be data in your file after the last edge: '"+surplus+"'");
            }

        }
        catch (IOException ex)
        {
            System.out.println("Error! Problem reading file "+inputfile);
            System.exit(0);
        }

        for( int x=1; x<=n; x++ )
        {
            if( seen[x] == false )
            {
                if(DEBUG) System.out.println(COMMENT + " Warning: vertex "+x+" didn't appear in any edge : it will be considered a disconnected vertex on its own.");
            }
        }
    }

    public static int getN()
    {
        return n;
    }

    public static int getM()
    {
        return m;
    }

    public static ColEdge[] getE()
    {
        return e;
    }

}
