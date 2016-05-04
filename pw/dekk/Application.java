package pw.dekk;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import pw.dekk.model.Response;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TimD on 5/3/2016.
 */
public class Application implements org.jnativehook.keyboard.NativeKeyListener {

    private static List<Response<NativeKeyEvent>> responses;
    private static boolean AUTO_ENTER = true;
    private static int SLEEP_TIME = 50;
    private static final int PORT = 9999;

    static {
        responses = new ArrayList<>();
    }

    public static void main(String[] args) {
        try {
            if (Util.running()) {
                JOptionPane.showConfirmDialog(null, "JINU is alreay running on this system. Exiting", "Instance Conflict", JOptionPane.DEFAULT_OPTION);
                System.exit(1);
            }
            if (args != null && args.length == 2) {
                AUTO_ENTER = args[0].equals("true");
                SLEEP_TIME = Integer.parseInt(args[1]);
                System.out.println(AUTO_ENTER + " " + SLEEP_TIME);
            }
            Util.register();
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new Application());
        branch();
    }

    /**
     * Decides on whether or not to automatically login or wait for F12 key
     */
    private static void branch() {
        if (AUTO_ENTER) {
            Runnable scan = () -> {
                while (true) {
                    WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "Login");
                    if (hwnd != null) {
                        User32.INSTANCE.ShowWindow(hwnd, 9);        // SW_RESTORE
                        User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front
                        new F12().react(null);
                    }
                    Util.sleep(SLEEP_TIME);
                }
            };
            new Thread(scan).start();
        } else {
            responses.add(new F12());
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        responses.stream().filter(response -> response.accept(e)).forEach(response -> response.react(e));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {

    }
}
