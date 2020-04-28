package nodepad.util;

import java.awt.*;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.io.*;
import java.util.Scanner;



public class OpenFile_Main{
    int width,height;
    String title;

    OpenFile_Main(int w,int h){
        width=w;
        height=h;
    }
    JPanel tool(JFrame jFrame){
        JPanel panel=new JPanel(null);

        panel.setBounds(0,0,width,height);
        panel.setBackground(Color.GRAY);

        TextArea textArea=new TextArea("请编辑：");
        textArea.setEditable(true);
        Font txt=new Font("宋体",Font.BOLD,16);
        textArea.setFont(txt);
        textArea.setBounds(0,30,width-15,height-30);
        JLabel label2=new JLabel("打开文件",JLabel.CENTER);
        JButton button2=new JButton();
        button2.add(label2);
        OpenFile openFile=new OpenFile(textArea,jFrame);
        button2.addActionListener(openFile);
        button2.setOpaque(false);
        button2.setBackground(Color.GRAY);
        button2.setBounds(100,0,100,30);
        panel.add(button2);
        panel.add(textArea);
        return panel;
    }


    public static void main(String[] args) {
        OpenFile_Main notePad =new OpenFile_Main(1000,618);
        JFrame frame=new JFrame();
        JLayeredPane layeredPane=new JLayeredPane();
        frame.setBounds(500,50,notePad.width,notePad.height);
        frame.setDefaultCloseOperation(3);
        frame.setTitle("SimpleNotePad--无标题");
        frame.setResizable(false);
        layeredPane.add(notePad.tool(frame),100);
        frame.add(layeredPane);
        frame.setVisible(true);
    }
}


class OpenFile implements ActionListener{
    TextArea myarea;
    JFrame Jf;
    String title;

    OpenFile(TextArea s,JFrame j){
        myarea=s;
        Jf=j;

    }

    public void actionPerformed(ActionEvent e) {
        
       FileNameExtensionFilter filter=new FileNameExtensionFilter("*.txt","txt");  //筛选目标后缀文件
       JFileChooser jChooser=new JFileChooser();
       jChooser.setFileFilter(filter);                                            //设置筛选
       jChooser.setMultiSelectionEnabled(false);                                  // 不允许多选

       int res=jChooser.showOpenDialog(null);

       if(res==JFileChooser.APPROVE_OPTION) {
           File file=jChooser.getSelectedFile();
           this.title=file.getName();
           this.title=this.title.substring(0,title.length()-4);
           Jf.setTitle("SimpleNotePad---"+this.title);
           try {
            //    Desktop.getDesktop().open(file);
               Scanner txt =new Scanner(file);
               myarea.setText("");
               while(txt.hasNextLine()){
                   String str=txt.nextLine()+"\n";
                   myarea.append(str);
               }
           } catch (IOException ee) {
               System.out.println("文件损坏");
           }
       }
    }
    
} 