package nodepad.edit;

import java.awt.*;
import java.awt.datatransfer.*;

import javax.swing.JTextArea;
public class Paste {
    JTextArea jTextArea;
    public String strs="";
    public int start,end;

    public Paste(JTextArea j){
        jTextArea=j;
    }

    public void Clicked(){
        String str="";
        Clipboard syscon=Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable Tf=syscon.getContents(null);

        if(Tf!=null){
            if(Tf.isDataFlavorSupported(DataFlavor.stringFlavor)){
                try {
                    start=jTextArea.getCaretPosition();
                    end=start+str.length();
                    str=(String)Tf.getTransferData(DataFlavor.stringFlavor);
                    jTextArea.insert(str,start);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        



    }

}