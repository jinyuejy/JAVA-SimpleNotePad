package nodepad.format;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Bgcolor {
    JTextArea jTextArea;
    private Color[] color = { Color.WHITE, Color.BLACK, Color.LIGHT_GRAY, Color.CYAN ,Color.LIGHT_GRAY,Color.GREEN};
    private String [] name={"白色","黑色","灰色","青色","浅灰","绿色"};

    public Bgcolor(JTextArea j){
        jTextArea=j;
    }

    public void Clicked(){
        JFrame frame =new JFrame("背景颜色选择");
        JButton button=new JButton("确认");
        button.setBounds(150,160,60,30);
       
        frame.setBounds(50,100,300,200);
        frame.setDefaultCloseOperation(2);
        JPanel panel=new JPanel();
        JLabel label=new JLabel("请选择：");
        JComboBox<String> comboBox=new JComboBox<>(name);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int select =comboBox.getSelectedIndex();
               jTextArea.setBackground(color[select]);
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