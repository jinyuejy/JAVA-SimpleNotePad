package nodepad.edit;

import javax.swing.JTextArea;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
public class Cut {
    JTextArea jTextArea;
    public String str="";
    public int start,end;

    public Cut(JTextArea j){
        jTextArea=j;
    }

    public void Clicked(){
        str=jTextArea.getSelectedText();
        Clipboard sysboard=Toolkit.getDefaultToolkit().getSystemClipboard();
        if(str!=null){
            try {
                Transferable Tf=new StringSelection(str);
                sysboard.setContents(Tf, null);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"×Ö·û´®Îª¿Õ","ÌáÊ¾£¡",JOptionPane.WARNING_MESSAGE);
        }
        
        start=jTextArea.getSelectionStart();
        end=jTextArea.getSelectionEnd();

        jTextArea.replaceRange("", start, end);


    }
}
