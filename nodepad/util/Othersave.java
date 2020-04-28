package nodepad.util;
import javax.swing.*;
import java.io.*;
public class Othersave {
    JTextArea TA;
    String title;
    
    // 获取当前内容

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
            JOptionPane.showMessageDialog(null,"没有输入名字！！！", "警告",JOptionPane.WARNING_MESSAGE);
        }
        try {
            String str=this.TA.getText();
            FileWriter out=new FileWriter(file);
            out.write(str);
            out.close();
            JOptionPane.showMessageDialog(null,"保存成功","另存为",JOptionPane.WARNING_MESSAGE);
        } catch (Exception eg) {
           System.out.println("写入失败");
           JOptionPane.showMessageDialog(null,"没有输入名字！！！", "警告",JOptionPane.WARNING_MESSAGE);
          
        }

    }
}
}