package weeklyvents;

import clojure.lang.IFn;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class WindowOpenedConsumer extends WindowAdapter {
    private final IFn function;

    public WindowOpenedConsumer(IFn function) {
        if (function == null)
            throw new NullPointerException();

        this.function = function;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        function.invoke(e);
    }
}