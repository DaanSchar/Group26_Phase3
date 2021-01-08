package logging;

import graph.Graph;
import main.Main;

import java.io.*;
import java.util.Scanner;


/**
 * The Log class tracks everything we would like to know of the run and
 * stores it in a .csv file
 */
public class Log
{

    private static FileWriter fw;
    private static BufferedWriter bw;
    private static PrintWriter pw;
    private static String filePath = "logs/logs.csv";

    private static int runNumber;
    private static long start;
    private static long end;
    private static long totalTime;

    //order of how the algorithms have been applied
    private static String order;

    public static void init()
    {
        FileWriter fw = null;
        try {

            // reads the total run number
            File totalRunFile = new File("logs/totalruns.txt");
            Scanner fr = new Scanner(totalRunFile);
            runNumber = fr.nextInt();
            System.out.println("Log:            Run: " + (runNumber+1));
            fr.close();

            fw = new FileWriter(filePath,true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            // column headers
            if(runNumber == 0)
            {
                pw.println("\"Run\"," + "\"Graph\"," + "\"Vertices\"," + "\"Edges\"," + "\"Total Time\"," + "\"Order\"," );
            }

            // initial source data
            pw.print(++runNumber + "," + Main.getGraphName() + "," + Graph.getN() + "," + Graph.getM() + ",");

            //tracks the amount of runs
            FileWriter fwRun = new FileWriter(totalRunFile);
            fwRun.write("" + (runNumber));
            fwRun.close();


            // init order string
            order = "";
            totalTime = 0;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startTimer()
    {
        start = System.currentTimeMillis();
    }

    public static void endTimer(String name)
    {
        end = System.currentTimeMillis();
        totalTime += (end-start);

        order += name;
        order += "[" + (end-start)/1000+ " s]";
        order += ",";

    }

    public static void endTimer(String name, int n)
    {
        end = System.currentTimeMillis();
        totalTime += (end-start);

        order += name;
        order += "[" + (end-start)/1000 + " s]";
        order += "[chrom: " + n + "]";
        order += ",";


    }

    public static void endTimer(String name, boolean b)
    {
        end = System.currentTimeMillis();
        totalTime += (end-start);

        order += name;
        order += "[" + (end-start)/1000+ " s]";
        order += "[is: " + b + "]";
        order += ",";

    }

    public static void close()
    {
        System.out.println("Log:            closing");
        pw.print(totalTime+" ms,");
        pw.println(order);
        pw.close();
    }


}
