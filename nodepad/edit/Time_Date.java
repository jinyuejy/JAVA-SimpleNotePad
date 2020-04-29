package nodepad.edit;


import java.util.Date;

import javax.swing.JTextArea;

import java.text.SimpleDateFormat; 

public class Time_Date {
    JTextArea jTextArea;
    public Time_Date(JTextArea j){
        jTextArea=j;
    }

    public void Clicked(){
        Date now = new Date(); 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


        String hehe = dateFormat.format( now ); 

        jTextArea.append(hehe);

} 

    }

