package pw.dekk;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by TimD on 5/3/2016.
 */
public class Util {
    public static Robot robot;
    public static final int NONE = 0;
    public static final int TAB = 1;
    public static final int ENTER = 2;

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
}

