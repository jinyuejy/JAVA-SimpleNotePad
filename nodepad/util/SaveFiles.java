package nodepad.util;

import javax.swing.*;
import java.io.*;

public class SaveFiles {
    JTextArea TA;
    // JFrame JF;
    String path;

    // 获取当前内容

    public SaveFiles(JTextArea T,String p) {
        this.TA = T;
        // JF = frame;
        path=p;
    }

    public void Clicked() {
        if (path==null){ 
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setSelectedFile(new File("我的文档.txt"));
            int opt = jFileChooser.showSaveDialog(null);
            if (opt == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                String filename = file.getName();
                if (filename.trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "没有输入名字！！！", "警告",JOptionPane.WARNING_MESSAGE);
                }
                try {
                    String str = this.TA.getText();
                    FileWriter out = new FileWriter(file);
                    out.write(str);
                    out.close();
                    JOptionPane.showMessageDialog(null, "保存成功","提 示",JOptionPane.WARNING_MESSAGE);
                } catch (Exception eg) {
                    System.out.println("写入失败");
                    JOptionPane.showMessageDialog(null, "没有输入名字！！！", "警告",JOptionPane.WARNING_MESSAGE);

                }
            }
        }
        else{
            File file=new File(path);
            try {
                String str = this.TA.getText();
                FileWriter out = new FileWriter(file);
                out.write(str);
                out.close();
                JOptionPane.showMessageDialog(null, "保存成功","提 示",JOptionPane.WARNING_MESSAGE);
            } catch (Exception eg) {
                System.out.println("写入失败");
                JOptionPane.showMessageDialog(null, "没有输入名字！！！", "警告",JOptionPane.WARNING_MESSAGE);

            }

        }

    }

}