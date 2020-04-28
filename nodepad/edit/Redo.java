package nodepad.edit;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.undo.*;
public class Redo {
    JTextArea jTextArea;
    static UndoManager undoManager=new UndoManager();

    public Redo(JTextArea j){
        jTextArea=j;
    }

    public void Clicked(){
        jTextArea.getDocument().addUndoableEditListener(undoManager);
        if(undoManager.canRedo()){
            undoManager.redo();
        }
        else{
            JOptionPane.showMessageDialog(null,"��������","��ʾ��",JOptionPane.WARNING_MESSAGE);
        }

    }

}