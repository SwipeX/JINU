package pw.dekk.model;

import org.jnativehook.keyboard.NativeKeyEvent;

/**
 * Created by TimD on 5/3/2016.
 */
public class KeyResponse extends Response<NativeKeyEvent> {

    private int keyCode = -1;

    public KeyResponse(int keyCode){
        this.keyCode = keyCode;
    }

    @Override
    public boolean accept(NativeKeyEvent event) {
        return event.getKeyCode() == this.keyCode;
    }

    @Override
    public void react(NativeKeyEvent nativeKeyEvent) {
        //to be overridden
    }

}
