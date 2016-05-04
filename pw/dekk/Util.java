package pw.dekk;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Created by TimD on 5/3/2016.
 */
public class Util {
    public static Robot robot;
    public static final int NONE = 0;
    public static final int TAB = 1;
    public static final int ENTER = 2;
    private static ServerSocket socket;

    static void register() {
       if(writeStartFile()){
           System.out.println("Startup file written successfully");
       }
    }

    static boolean running() {
        try {
            //Bind to localhost adapter with a zero connection queue
            socket = new ServerSocket(9999, 0, InetAddress.getByAddress(new byte[]{127, 0, 0, 1}));
        } catch (BindException e) {
            return true;
        } catch (IOException e) {
            return true;
        }
        return false;
    }

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void sendKey(int key) {
        robot.keyPress(key);
        robot.keyRelease(key);
    }

    public static void sendText(String text, int action) {
        for (char c : text.toCharArray()) {
            sendKey(KeyEvent.getExtendedKeyCodeForChar(c));
        }
        switch (action) {
            case NONE:
                return;
            case TAB:
                sendKey(KeyEvent.VK_TAB);
                return;
            case ENTER:
                sendKey(KeyEvent.VK_ENTER);
                return;
        }
    }

    public static void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static String locate() {
        return new File("").getAbsolutePath() + "\\JINU.jar";
    }

    static boolean writeStartFile() {
        File file = new File(startDirectory() + "JINU.bat");
        if(!file.exists()) {
            try {
                PrintWriter pw = new PrintWriter(new FileOutputStream(file, false));
                pw.print("start javaw -jar " + locate() + " true 50 \r\n" +
                        "exit");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    static String startDirectory() {
        return System.getProperty("java.io.tmpdir").replace("Local\\Temp\\", "Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\");
    }
}

