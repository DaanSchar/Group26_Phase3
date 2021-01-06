package logging;

import main.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * This class tracks the timer between when start() and end() get called.
 * basically, we use it to tracks the time an algorithm takes to run.
 * it also tracks some other stuff
 */
public class Log
{

    private static long start;
    private static long end;
    private static File file = new File("logs/log.txt");
    private static FileWriter fw;
    private static long totalTime;


    /**
     * initializes the timer, and creates a filewriter
     */
    public static void init()
    {
        try {
            fw = new FileWriter(file);
            fw.write("Graph:  " + Main.getGraphName() + "\n\n");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * sets the starting time position
     */
    public static void startTimer()
    {
        start = System.currentTimeMillis();
    }


    /**
     * sets the ending time position, and writes the total
     * time to the file "timer.txt"
     */
    public static void endTimer(String className)
    {
        end = System.currentTimeMillis();
        totalTime += (end-start);

        try {
            fw.write(className + "\nTotal time: " + (end - start) + "\n\n");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * same thing, just added a boolean to log if a graph
     * gets tests positive or negative to an algorithm
     */
    public static void endTimer(String className, boolean bool)
    {
        end = System.currentTimeMillis();
        totalTime += (end-start);

        try {
            fw.write(className + "\nTotal time: " + (end - start) + "\n");
            fw.write("is " + className + ": " + bool + "\n\n");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void endTimer(String className, int number)
    {
        end = System.currentTimeMillis();
        totalTime += (end-start);

        try {
            fw.write(className + "\nTotal time: " + (end - start) + "\n");
            fw.write("Chromatic number: " + number + "\n\n");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }


    /**
     * closes the filewriter
     */
    public static void close()
    {
        try {

            fw.write("Total run time: " + totalTime);

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}