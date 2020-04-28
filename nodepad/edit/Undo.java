package nodepad.edit;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.undo.*;

public class Undo {
    JTextArea jTextArea;
    public String str="";
    String str_s;
    static UndoManager undoManager=new UndoManager();
    public Undo(JTextArea j,String s){
        jTextArea=j;
        str_s=s;
    }
    public void Clicked() {
        jTextArea.getDocument().addUndoableEditListener(undoManager);
        if(undoManager.canUndo()){
            undoManager.undo();
        }
        else{
            JOptionPane.showMessageDialog(null,"无需撤销","提示：",JOptionPane.WARNING_MESSAGE);
        }
        
    }

}