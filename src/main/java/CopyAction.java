import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;

import java.io.*;
public class CopyAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);

        assert editor != null;
        String selectedText = editor.getSelectionModel().getSelectedText();

        if (selectedText != null) {
            try(FileWriter writer = new FileWriter("C:\\C++\\copy_code.txt", false))
            {
                writer.write(selectedText);

                writer.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        } else {
            Messages.showMessageDialog("Selection is empty, could you please select something?", "Copy Action", Messages.getInformationIcon());
        }
    }

    @Override
    public boolean isDumbAware() {
        return false;
    }
}
