package nodepad.edit;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class Copy {
    JTextArea jTextArea;
    public String str="";
    
    public Copy(JTextArea j){
        jTextArea=j;
    }
    public void Clicked() {
        
        String str=jTextArea.getSelectedText();
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
            JOptionPane.showMessageDialog(null,"ÎÞÄÚÈÝ","¾¯¸æ!",JOptionPane.WARNING_MESSAGE);
        }
        
    }
    

}