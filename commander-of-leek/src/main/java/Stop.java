import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class Stop extends AnAction {

    public Main main;

    @Override
    public void actionPerformed(AnActionEvent e) {
        Leek.stop();
        System.out.println("Stopped");
    }
}
