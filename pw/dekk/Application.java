package pw.dekk;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import pw.dekk.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TimD on 5/3/2016.
 */
public class Application implements org.jnativehook.keyboard.NativeKeyListener {

   private static List<Response<NativeKeyEvent>> responses;

    static {
        responses = new ArrayList<>();
        responses.add(new F12());
    }

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new Application());
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
