package nodepad.util;
import javax.swing.*;
import java.io.*;
public class Othersave {
    JTextArea TA;
    String title;
    
    // ��ȡ��ǰ����

    public Othersave(JTextArea T,String t){
        this.TA=T;
        title=t;
    }

    public void Clicked() {
    
    JFileChooser jFileChooser=new JFileChooser();
    jFileChooser.setSelectedFile(new File(title));
    int opt=jFileChooser.showSaveDialog(null);
    if(opt== JFileChooser.APPROVE_OPTION) {
        File file=jFileChooser.getSelectedFile();
        String filename=file.getName();
        if(filename.trim().length()==0){
            JOptionPane.showMessageDialog(null,"û���������֣�����", "����",JOptionPane.WARNING_MESSAGE);
        }
        try {
            String str=this.TA.getText();
            FileWriter out=new FileWriter(file);
            out.write(str);
            out.close();
            JOptionPane.showMessageDialog(null,"����ɹ�","���Ϊ",JOptionPane.WARNING_MESSAGE);
        } catch (Exception eg) {
           System.out.println("д��ʧ��");
           JOptionPane.showMessageDialog(null,"û���������֣�����", "����",JOptionPane.WARNING_MESSAGE);
          
        }

    }
}
}