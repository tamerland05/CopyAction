import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

public class CopyAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);

        assert editor != null;
        String selectedText = editor.getSelectionModel().getSelectedText();

        if (selectedText != null) {
            JFileChooser fileChooser = new JFileChooser("copy_code.txt");
            int ret = fileChooser.showDialog(null, "Open Directory");
            String path = String.valueOf(fileChooser.getSelectedFile());

            try(FileWriter writer = new FileWriter(path, false)) {
                writer.write(selectedText);

                writer.flush();
            } catch(IOException ex){
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

