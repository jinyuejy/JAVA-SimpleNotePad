package nodepad.util;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;

public class OpenFiles{
    JTextArea myarea;
    JFrame Jf;
    public String title;
    public String path;

    public OpenFiles(JTextArea s,JFrame j){
        myarea=s;
        Jf=j;

    }

    public void Clicked() {
        
       FileNameExtensionFilter filter=new FileNameExtensionFilter("*.txt","txt");  
       JFileChooser jChooser=new JFileChooser();
       jChooser.setFileFilter(filter);                                            
       jChooser.setMultiSelectionEnabled(false);                                  

       int res=jChooser.showOpenDialog(null);

       if(res==JFileChooser.APPROVE_OPTION) {
           File file=jChooser.getSelectedFile();
           this.title=file.getName();
           this.path=file.getPath();
           Jf.setTitle("SimpleNotePad---"+this.title.substring(0,title.length()-4));
           try {
            //    Desktop.getDesktop().open(file);
            //    Scanner txt =new Scanner(file);
            //    System.out.println("i am here");
            BufferedReader txt=new BufferedReader(new FileReader(file));
            String line;
               myarea.setText("");
               while((line=txt.readLine())!=null){
                   String str=line+"\n";
                   myarea.append(str);
               }
               txt.close();
           } catch (IOException e) {
               System.out.println("File damaged");
           }
       }
    }
    
} 