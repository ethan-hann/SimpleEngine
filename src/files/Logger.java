package files;

import java.io.*;
import java.util.Calendar;

/**
 * Logger class to handle writing to a log file
 * Writes a string to Log file; most objects can be represented as a String
 */
public class Logger
{
    private static Logger instance;
    private final File LOG_FILE = new File("./res/logs/log.txt");

    private FileOutputStream fos;
    private OutputStreamWriter osw;
    private BufferedWriter bw;

    private boolean created = false;

    public static Logger getInstance()
    {
        if (instance == null)
        {
            instance = new Logger();
        }
        return instance;
    }

    public void write(String msg)
    {
        if (LOG_FILE.exists() || created)
        {
            try
            {
                fos = new FileOutputStream(LOG_FILE, true);
                osw = new OutputStreamWriter(fos);
                bw = new BufferedWriter(osw);

                String logStr = Calendar.getInstance().getTime().toString() +
                        ":\t" +
                        msg;
                bw.write(logStr);
                System.out.println(logStr);
                bw.newLine();

                closeLog();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        } else
        {
            try
            {
                created = LOG_FILE.createNewFile();
                write(msg);
            } catch (IOException e)
            {
                System.err.println("Log file could not be created!");
                e.printStackTrace();
            }
        }
    }

    private void closeLog()
    {
        try
        {
            bw.close();
            osw.close();
            fos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
