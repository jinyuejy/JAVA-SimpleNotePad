package nodepad.util;

import javax.swing.*;
import java.io.*;

public class SaveFiles {
    JTextArea TA;
    // JFrame JF;
    String path;

    // ��ȡ��ǰ����

    public SaveFiles(JTextArea T,String p) {
        this.TA = T;
        // JF = frame;
        path=p;
    }

    public void Clicked() {
        if (path==null){ 
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setSelectedFile(new File("�ҵ��ĵ�.txt"));
            int opt = jFileChooser.showSaveDialog(null);
            if (opt == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                String filename = file.getName();
                if (filename.trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "û���������֣�����", "����",JOptionPane.WARNING_MESSAGE);
                }
                try {
                    String str = this.TA.getText();
                    FileWriter out = new FileWriter(file);
                    out.write(str);
                    out.close();
                    JOptionPane.showMessageDialog(null, "����ɹ�","�� ʾ",JOptionPane.WARNING_MESSAGE);
                } catch (Exception eg) {
                    System.out.println("д��ʧ��");
                    JOptionPane.showMessageDialog(null, "û���������֣�����", "����",JOptionPane.WARNING_MESSAGE);

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
                JOptionPane.showMessageDialog(null, "����ɹ�","�� ʾ",JOptionPane.WARNING_MESSAGE);
            } catch (Exception eg) {
                System.out.println("д��ʧ��");
                JOptionPane.showMessageDialog(null, "û���������֣�����", "����",JOptionPane.WARNING_MESSAGE);

            }

        }

    }

}