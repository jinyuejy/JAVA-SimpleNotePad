package nodepad.util;
import javax.swing.*;


public class NewFile {
    JTextArea textArea;
    JFrame frame;

    public NewFile(JTextArea j,JFrame jf){
        textArea=j;
        frame=jf;
    }

    public void Clicked(){
        frame.setTitle("simplenotepad--Œﬁ±ÍÃ‚");
        textArea.setText("");
    }
}