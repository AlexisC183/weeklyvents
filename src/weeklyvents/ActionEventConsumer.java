import clojure.lang.IFn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ActionEventConsumer implements ActionListener {
    private final IFn function;

    public ActionEventConsumer(IFn function) {
        if (function == null)
            throw new NullPointerException();

        this.function = function;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        function.invoke(e);
    }
}