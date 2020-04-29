package nodepad.format;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import nodepad.view.NotepadMainFrame;
public class FontSize {

    JTextArea jTextArea;
    private String [] ziti ={"宋体","微软雅黑","华文行楷","楷体","等线","华文隶书","隶书"};
    private String [] zihao={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22"};

    public  FontSize(JTextArea j) {
        jTextArea=j;
        
    }

    public void Clicked(){

        JFrame frame =new JFrame("字体大小框");
        JPanel panel =new JPanel(null);
        JButton button=new JButton("确认");
        button.setBounds(180,160,60,30);
        frame.setBounds(50,100,400,300);
        panel.setBounds(0, 0, 400, 300);
        frame.setDefaultCloseOperation(2);
        JComboBox<String> comboBox1=new JComboBox<>(ziti);
        comboBox1.setBounds(220,20,80,20);
        JComboBox<String> comboBox2 =new JComboBox<>(zihao);
        comboBox2.setBounds(220,80,80,20);
        JLabel label1=new JLabel("字体选择:");
        label1.setBounds(120,20,100,20);
        JLabel label2 =new JLabel("字号选择:");
        label2.setBounds(120,80,100,20);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int zt=comboBox1.getSelectedIndex();
                int zh=comboBox2.getSelectedIndex();
                Font font=new Font(ziti[zt],Font.BOLD,zh);
                NotepadMainFrame.font=font;
                jTextArea.setFont(font);
                frame.setVisible(false);
            }
        });

        panel.add(label1);
        panel.add(comboBox1);
        panel.add(label2);
        panel.add(comboBox2);
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);


    }
}