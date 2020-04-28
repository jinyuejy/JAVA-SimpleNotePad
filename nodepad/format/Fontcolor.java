package nodepad.format;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
public class Fontcolor {
    JTextArea jTextArea;
    private Color[] color = { Color.WHITE, Color.BLACK, Color.LIGHT_GRAY, Color.CYAN };
    private String [] ziti ={"����","΢���ź�","�����п�","����"};
    public Fontcolor(JTextArea j){
        jTextArea=j;
    }

    public void Clicked(){
        JFrame frame =new JFrame("����ѡ���");
        JButton button=new JButton("ȷ��");
        button.setBounds(200,160,60,30);
        frame.setBounds(50,100,400,300);
        frame.setDefaultCloseOperation(2);
        JPanel panel=new JPanel();
        JLabel label=new JLabel("��ɫ��");
        JComboBox<Color> comboBox=new JComboBox<>(color);
        JLabel label1=new JLabel("����");

        JComboBox<String> comboBox2=new JComboBox<>(ziti);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Color colors=(Color)comboBox.getSelectedItem();
                String fonts=(String)comboBox2.getSelectedItem();

                Font font =new Font(fonts,Font.BOLD,14);
                jTextArea.setFont(font);
                jTextArea.setForeground(colors);
               
            }

        });
        panel.add(label);
        panel.add(comboBox);
        panel.add(label1);
        panel.add(comboBox2);
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);
        
    }

}