package pw.dekk.model;

import org.jnativehook.mouse.NativeMouseEvent;

/**
 * Created by TimD on 5/3/2016.
 */
public class MouseResponse extends Response<NativeMouseEvent> {

    //TODO figure out what to do with this class

    @Override
    public void react(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public boolean accept(NativeMouseEvent nativeMouseEvent) {
        return false;
    }
}
