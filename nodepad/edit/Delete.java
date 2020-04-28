package nodepad.edit;

import javax.swing.JTextArea;

public class Delete {

    JTextArea jTextArea;
    public int start,end;

    public String str;
    public Delete(JTextArea j){
        jTextArea=j;
    }

    public void Clicked() {
        str=jTextArea.getSelectedText();
        start=jTextArea.getSelectionStart();
        end=jTextArea.getSelectionEnd();

        jTextArea.replaceRange("", start, end);
        
    }

}