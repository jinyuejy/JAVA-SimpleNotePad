package nodepad.util;

import java.io.File;
import java.io.FileInputStream;

import javax.print.*;
import javax.print.attribute.*;
import javax.swing.JOptionPane;

public class Print_Doc {
    String path;
    public Print_Doc(String p){
        path=p;
    }


    public void Clicked(){
        
        HashPrintRequestAttributeSet pars = new HashPrintRequestAttributeSet();
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        PrintService services[]=PrintServiceLookup.lookupPrintServices(flavor, pars);

        PrintService defaluPrintService=PrintServiceLookup.lookupDefaultPrintService();
        if(path!=null){
            PrintService service=ServiceUI.printDialog(null,50,200, services, defaluPrintService, flavor, pars);
        

            if(service!=null){
                File file=new File(path);
                try {
                    DocPrintJob job=service.createPrintJob();
                    FileInputStream stream=new FileInputStream(file);
                    DocAttributeSet das=new HashDocAttributeSet();
                    Doc doc=new SimpleDoc(stream, flavor, das);
                    job.print(doc, pars);
                    
                } catch (Exception e) {
                   System.out.println("错误");
                }
            }
        }
       
        else{
            JOptionPane.showMessageDialog(null, "无内容，打印失败","提示：",JOptionPane.WARNING_MESSAGE);
        }

    }

}