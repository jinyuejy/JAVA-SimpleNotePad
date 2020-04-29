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
    private Color[] color = { Color.WHITE, Color.BLACK, Color.LIGHT_GRAY, Color.CYAN,Color.LIGHT_GRAY };
    private String [] name={"��ɫ","��ɫ","��ɫ","��ɫ","ǳ��"};
    
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
        JComboBox<String> comboBox=new JComboBox<>(name);

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int select=comboBox.getSelectedIndex();

                Font font =new Font("����",Font.BOLD,14);
                jTextArea.setFont(font);
                jTextArea.setForeground(color[select]);
                frame.setVisible(false);
               
            }

        });
        panel.add(label);
        panel.add(comboBox);
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);
        
    }

}