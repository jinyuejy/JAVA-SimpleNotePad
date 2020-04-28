package nodepad.help;

import java.awt.*;
import javax.swing.*;

/**
 * Help
 */
public class Help {

    public void Clicked() {

        JFrame frame=new JFrame("关于记事本");
        frame.setDefaultCloseOperation(2);
        frame.setBounds(100,200,600,500);
        JPanel panel =new JPanel(new GridLayout(3,1));

        Font font=new Font("华文行楷",Font.BOLD,20);

        JLabel label=new JLabel("Simple Note Pad");
        label.setFont(font);

        Font font1=new Font("楷体",Font.BOLD,14);
        JLabel label2=new JLabel("日期-----2020年4月28日");
        label2.setFont(font1);

        Font font2=new Font("宋体",Font.BOLD,10);
        JLabel label3=new JLabel("谢谢使用");
        label3.setFont(font2);

        panel.add(label);
        panel.add(label2);
        panel.add(label3);

        frame.add(panel);
        frame.setVisible(true);

        
    }
}