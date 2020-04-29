package nodepad.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.PrintJob;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.undo.UndoManager;

import nodepad.util.Clock;
import nodepad.util.MQFontChooser;
import nodepad.util.SystemParam;
import nodepad.util.TestLine;

import nodepad.util.OpenFiles;
import nodepad.util.Othersave;
import nodepad.util.SaveFiles;
import nodepad.util.NewFile;
import nodepad.util.Print_Doc;
import nodepad.edit.*;
import nodepad.format.*;
import nodepad.help.*;

public class NotepadMainFrame extends JFrame implements ActionListener{

    /**
     * ���к�
     */
    public JTextArea jTextArea;
    public String Title;
    private static final long serialVersionUID = 8585210209467333480L;
    private JPanel contentPane;
    private JTextArea textArea;
    private JMenuItem itemOpen;
    private JMenuItem itemSave;
    
    //1���½� 
    //2���޸Ĺ�
    //3���������
    int flag=0;

    //��ǰ�ļ���
    String currentFileName=null;
    
     PrintJob  p=null;//����һ��Ҫ��ӡ�Ķ���
     Graphics  g=null;//Ҫ��ӡ�Ķ���
    
    //��ǰ�ļ�·��
    String currentPath=null;
    String path=null;
    
    //������ɫ
    JColorChooser jcc1=null;
    Color color=Color.BLACK;
    
    //�ı�������������
    int linenum = 1;
    int columnnum = 1;
    
    //����������
    public UndoManager undoMgr = new UndoManager(); 
    
    //������
    public Clipboard clipboard = new Clipboard("ϵͳ���а�");
    public String str="";
    private JMenuItem itemSaveAs;
    private JMenuItem itemNew;
    private JMenuItem itemPage;
    private JSeparator separator;
    private JMenuItem itemPrint;
    private JMenuItem itemExit;
    private JSeparator separator_1;
    private JMenu itemEdit;
    private JMenu itFormat;
    private JMenu itemCheck;
    private JMenu itemHelp;
    private JMenuItem itemSearchForHelp;
    private JMenuItem itemAboutNotepad;
    private JMenuItem itemUndo;
    private JMenuItem itemCut;
    private JMenuItem itemCopy;
    private JMenuItem itemPaste;
    private JMenuItem itemDelete;
    private JMenuItem itemFind;
    private JMenuItem itemFindNext;
    private JMenuItem itemReplace;
    private JMenuItem itemTurnTo;
    private JMenuItem itemSelectAll;
    private JMenuItem itemTime;
    private JMenuItem itemFont;
    private JMenuItem itemColor;
    private JMenuItem itemFontColor;
    private JCheckBoxMenuItem itemNextLine;
    private JScrollPane scrollPane;
    private JCheckBoxMenuItem itemStatement;
    private JToolBar toolState;
    public static JLabel label1;
    private JLabel label2;
    private JLabel label3;
    int length=0;
    int sum=0;
    
    public int start=0,end=0;
    public boolean f=false;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NotepadMainFrame frame = new NotepadMainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    GregorianCalendar c=new GregorianCalendar();
    int hour=c.get(Calendar.HOUR_OF_DAY);
    int min=c.get(Calendar.MINUTE);
    int second=c.get(Calendar.SECOND);    
    private JPopupMenu popupMenu;
    private JMenuItem popM_Undo;
    private JMenuItem popM_Cut;
    private JMenuItem popM_Copy;
    private JMenuItem popM_Paste;
    private JMenuItem popM_Delete;
    private JMenuItem popM_SelectAll;
    private JMenuItem popM_toLeft;
    private JMenuItem popM_showUnicode;
    private JMenuItem popM_closeIMe;
    private JMenuItem popM_InsertUnicode;
    private JMenuItem popM_RestartSelect;
    private JSeparator separator_2;
    private JSeparator separator_3;
    private JSeparator separator_4;
    private JSeparator separator_5;
    private JMenuItem itemRedo;
    private JSeparator separator_6;
    private JSeparator separator_7;
    private JSeparator separator_8;
    private JMenuItem popM_Redo;

    /**
     * Create the frame.
     */
    public NotepadMainFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }
        setTitle("�ޱ���");    
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 521, 572);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu itemFile = new JMenu("�ļ�(F)");
        itemFile.setMnemonic('F');
        menuBar.add(itemFile);
        
        itemNew = new JMenuItem("�½�(N)",'N');
        itemNew.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,
                java.awt.Event.CTRL_MASK));  
       itemNew.addActionListener(this);
        itemFile.add(itemNew);
        
        itemOpen = new JMenuItem("��(O)",'O');
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O,
                            java.awt.Event.CTRL_MASK));  
       itemOpen.addActionListener(this);
        itemFile.add(itemOpen);
        
        itemSave = new JMenuItem("����(S)");
        itemSave.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
                java.awt.Event.CTRL_MASK));   
       itemSave.addActionListener(this);
        itemFile.add(itemSave);
        
        itemSaveAs = new JMenuItem("���Ϊ(A)");
       itemSaveAs.addActionListener(this);
        itemFile.add(itemSaveAs);
        
        separator = new JSeparator();
        itemFile.add(separator);
        
        itemPage = new JMenuItem("ҳ������(U)",'U');
       itemPage.addActionListener(this);
        itemFile.add(itemPage);
        
        itemPrint = new JMenuItem("��ӡ(P)...",'P');
        itemPrint.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P,
                java.awt.Event.CTRL_MASK));   
       itemPrint.addActionListener(this);
        itemFile.add(itemPrint);
        
        separator_1 = new JSeparator();
        itemFile.add(separator_1);
        
        itemExit = new JMenuItem("�˳�(X)",'X');
        itemExit.addActionListener(this);
        itemFile.add(itemExit);
        
        itemEdit = new JMenu("�༭(E)");
        itemEdit.setMnemonic('E');
        menuBar.add(itemEdit);
        
        itemUndo = new JMenuItem("����(U)",'U');
        itemUndo.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z,
                java.awt.Event.CTRL_MASK));
       itemUndo.addActionListener(this);
        itemEdit.add(itemUndo);
        
        itemRedo = new JMenuItem("�ָ�(R)");
        itemRedo.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R,
                java.awt.Event.CTRL_MASK));
//        itemRedo.addActionListener(this);
        itemEdit.add(itemRedo);
        
        itemCut = new JMenuItem("����(T)",'T');
        itemCut.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X,
                java.awt.Event.CTRL_MASK));
       itemCut.addActionListener(this);
        
        separator_6 = new JSeparator();
        itemEdit.add(separator_6);
        itemEdit.add(itemCut);
        
        itemCopy = new JMenuItem("����(C)",'C');
       itemCopy.addActionListener(this);
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C,
                java.awt.Event.CTRL_MASK));
        itemEdit.add(itemCopy);
        
        itemPaste = new JMenuItem("ճ��(P)",'P');
       itemPaste.addActionListener(this);
        itemPaste.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V,
                java.awt.Event.CTRL_MASK));
        itemEdit.add(itemPaste);
        
        itemDelete = new JMenuItem("ɾ��(L)",'L');
       itemDelete.addActionListener(this);
        itemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,  
                InputEvent.CTRL_MASK));  
        itemEdit.add(itemDelete);
        
        separator_7 = new JSeparator();
        itemEdit.add(separator_7);
        
        itemFind = new JMenuItem("����(F)",'F');
        itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                Event.CTRL_MASK));
       itemFind.addActionListener(this);
        itemEdit.add(itemFind);
        
        itemFindNext = new JMenuItem("������һ��(N)",'N');
        itemFindNext.setAccelerator(KeyStroke.getKeyStroke("F3"));
       itemFindNext.addActionListener(this);
        itemEdit.add(itemFindNext);
        
        itemReplace = new JMenuItem("�滻(R)",'R');
       itemReplace.addActionListener(this);
        itemReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
                Event.CTRL_MASK));
        itemEdit.add(itemReplace);
        
        itemTurnTo = new JMenuItem("ת��(G)",'G');
       itemTurnTo.addActionListener(this);
        itemTurnTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
                Event.CTRL_MASK));
        itemEdit.add(itemTurnTo);
        
        itemSelectAll = new JMenuItem("ȫѡ(A)",'A');
        itemSelectAll.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A,
                java.awt.Event.CTRL_MASK));
       itemSelectAll.addActionListener(this);
        
        separator_8 = new JSeparator();
        itemEdit.add(separator_8);
        itemEdit.add(itemSelectAll);
        
        itemTime = new JMenuItem("ʱ��/����(D)",'D');
       itemTime.addActionListener(this);
        itemTime.setAccelerator(KeyStroke.getKeyStroke("F5"));
        itemEdit.add(itemTime);
        
        itFormat = new JMenu("��ʽ(O)");
        itFormat.setMnemonic('O');
        menuBar.add(itFormat);
        
        itemNextLine = new JCheckBoxMenuItem("�Զ�����(W)");
       itemNextLine.addActionListener(this);
        itFormat.add(itemNextLine);
        
        itemFont = new JMenuItem("�����С(F)...");
       itemFont.addActionListener(this);
        itFormat.add(itemFont);
        
        itemColor = new JMenuItem("������ɫ(C)...");
       itemColor.addActionListener(this);
        itFormat.add(itemColor);
        
        itemFontColor = new JMenuItem("������ɫ(I)...");
       itemFontColor.addActionListener(this);
        itFormat.add(itemFontColor);
        
        itemCheck = new JMenu("�鿴(V)");
        itemCheck.setMnemonic('V');
        menuBar.add(itemCheck);
        
        itemStatement = new JCheckBoxMenuItem("״̬��(S)");
       itemStatement.addActionListener(this);
       itemStatement.setSelected(false);
        itemCheck.add(itemStatement);
        
        itemHelp = new JMenu("����(H)");
        itemHelp.setMnemonic('H');
        menuBar.add(itemHelp);
        
        itemSearchForHelp = new JMenuItem("�鿴����(H)",'H');
       itemSearchForHelp.addActionListener(this);
        itemHelp.add(itemSearchForHelp);
        
        itemAboutNotepad = new JMenuItem("���ڼ��±�(A)",'A');
       itemAboutNotepad.addActionListener(this);
        itemHelp.add(itemAboutNotepad);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //���ñ߿򲼾�
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        textArea = new JTextArea();
        textArea.setEditable(true);
        
        //VERTICAL��ֱ    HORIZONTALˮƽ
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        //contentPane2=new JPanel();
        //contentPane2.setSize(10,textArea.getSize().height);
        //contentPane.add(contentPane2, BorderLayout.WEST);
        TestLine view = new TestLine();
        scrollPane.setRowHeaderView(view);
        
        popupMenu = new JPopupMenu();
//        addPopup(textArea, popupMenu);
        
        popM_Undo = new JMenuItem("����(U)");
//        popM_Undo.addActionListener(this);
        popupMenu.add(popM_Undo);
        
        popM_Redo = new JMenuItem("�ָ�(R)");
//        popM_Redo.addActionListener(this);
        popupMenu.add(popM_Redo);
        
        separator_2 = new JSeparator();
        popupMenu.add(separator_2);
        
        popM_Cut = new JMenuItem("����(T)");
//        popM_Cut.addActionListener(this);
        popupMenu.add(popM_Cut);
        
        popM_Copy = new JMenuItem("����(C)");
//        popM_Copy.addActionListener(this);
        popupMenu.add(popM_Copy);
        
        popM_Paste = new JMenuItem("ճ��(P)");
//        popM_Paste.addActionListener(this);
        popupMenu.add(popM_Paste);
        
        popM_Delete = new JMenuItem("ɾ��(D)");
//        popM_Delete.addActionListener(this);
        popupMenu.add(popM_Delete);
        
        separator_3 = new JSeparator();
        popupMenu.add(separator_3);
        
        popM_SelectAll = new JMenuItem("ȫѡ(A)");
//        popM_SelectAll.addActionListener(this);
        popupMenu.add(popM_SelectAll);
        
        separator_4 = new JSeparator();
        popupMenu.add(separator_4);
        
        popM_toLeft = new JMenuItem("���ҵ�����Ķ�˳��(R)");
//        popM_toLeft.addActionListener(this);
        popupMenu.add(popM_toLeft);
        
        popM_showUnicode = new JMenuItem("��ʾUnicode�����ַ�(S)");
//        popM_showUnicode.addActionListener(this);
        popupMenu.add(popM_showUnicode);
        
        popM_InsertUnicode = new JMenuItem("����Unicode�����ַ�(I)");
//        popM_InsertUnicode.addActionListener(this);
        popupMenu.add(popM_InsertUnicode);
        
        separator_5 = new JSeparator();
        popupMenu.add(separator_5);
        
        popM_closeIMe = new JMenuItem("�ر�IME(L)");
//        popM_closeIMe.addActionListener(this);
        popupMenu.add(popM_closeIMe);
        
        popM_RestartSelect = new JMenuItem("������ѡ(R)");
//        popM_RestartSelect.addActionListener(this);
        popupMenu.add(popM_RestartSelect);
        //��ӵ�����С��м䡿
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        //��ӳ���������
        textArea.getDocument().addUndoableEditListener(undoMgr);

       
                
        
        toolState = new JToolBar();
        toolState.setSize(textArea.getSize().width, 10);//toolState.setLayout(new FlowLayout(FlowLayout.LEFT));
        label1 = new JLabel("    ��ǰϵͳʱ�䣺" + hour + ":" + min + ":" + second+" ");
        toolState.add(label1);
        toolState.addSeparator();
        label2 = new JLabel("    �� " + linenum + " ��, �� " + columnnum+" ��  ");
        toolState.add(label2);
        toolState.addSeparator();
        
        label3 = new JLabel("    һ�� " +length+" ��  ");
        toolState.add(label3);
        textArea.addCaretListener(new CaretListener() {        //��¼����������
            public void caretUpdate(CaretEvent e) {
                //sum=0;
                JTextArea editArea = (JTextArea)e.getSource();
 
                try {
                    int caretpos = editArea.getCaretPosition();
                    linenum = editArea.getLineOfOffset(caretpos);
                    columnnum = caretpos - textArea.getLineStartOffset(linenum);
                    linenum += 1;
                    label2.setText("    �� " + linenum + " ��, �� " + (columnnum+1)+" ��  ");
                    //sum+=columnnum+1;
                    //length+=sum;
                    length=NotepadMainFrame.this.textArea.getText().toString().length();
                    label3.setText("    һ�� " +length+" ��  ");
                }
                catch(Exception ex) { }
            }});
            jTextArea=textArea;
        contentPane.add(toolState, BorderLayout.SOUTH);
        itemStatement.addChangeListener(new ChangeListener(){
                
                @Override
                public void stateChanged(ChangeEvent e) {
                        f=itemStatement.isSelected();
                        if(f){
                                toolState.setVisible(true);
                        }
                        else{
                                toolState.setVisible(false);
                        }
                }
        });
        
        
        toolState.setFloatable(false);
        Clock clock=new Clock();
        clock.start();
        
        
        
        // ���������˵�
        final JPopupMenu jp=new JPopupMenu();    //��������ʽ�˵������������ǲ˵���
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3)//ֻ��Ӧ����Ҽ������¼�
                {
                    jp.show(e.getComponent(),e.getX(),e.getY());//�����λ����ʾ����ʽ�˵�
                }
            }
        });
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==itemOpen){            //��
//            openFile();
                OpenFiles openFiles =new OpenFiles(jTextArea, this);
                openFiles.Clicked();
                Title=openFiles.title;
                path=openFiles.path;
        }else if(e.getSource()==itemSave){        //����
            //������ļ��Ǵ򿪵ģ��Ϳ���ֱ�ӱ���
//            save(); 
                SaveFiles saveFiles=new SaveFiles(jTextArea,path);
                saveFiles.Clicked();
        }else if(e.getSource()==itemSaveAs){    //���Ϊ
//            saveAs();
                Othersave othOthersave=new Othersave(jTextArea, Title);
                othOthersave.Clicked();
        }else if(e.getSource()==itemNew){        //�½�
//            newFile();
                NewFile newFile =new NewFile(jTextArea, this);
                newFile.Clicked();
        }else if(e.getSource()==itemExit){        //�˳�
            exit();
        }else if(e.getSource()==itemPage){        //ҳ������
            ///ҳ�����ã��ٶȵ��ģ���֪��������÷�
            PageFormat pf = new PageFormat();
            PrinterJob.getPrinterJob().pageDialog(pf); 
        }else if(e.getSource()==itemPrint){        //��ӡ
            //��ӡ��
//            Print();
                Print_Doc print =new Print_Doc(path);
                print.Clicked();
        }else if(e.getSource()==itemUndo || e.getSource()==popM_Undo){        //����
            if(undoMgr.canUndo()){
               undoMgr.undo();
            }
            else{JOptionPane.showMessageDialog(null,"��������","��ʾ��", JOptionPane.WARNING_MESSAGE);}
        }else if(e.getSource()==itemRedo || e.getSource()==popM_Redo){        //�ָ�
            if(undoMgr.canRedo()){
               undoMgr.redo();
            }
            else{JOptionPane.showMessageDialog(null,"��������","��ʾ��", JOptionPane.WARNING_MESSAGE);}
        }else if(e.getSource()==itemCut || e.getSource()==popM_Cut){        //����
//            cut();
                Cut cut=new Cut(jTextArea);
                cut.Clicked();
                start=cut.start;
                end=cut.end;
                str=cut.str;
        }else if(e.getSource()==itemCopy || e.getSource()==popM_Copy){        //����
//            copy();
                Copy copy=new Copy(jTextArea);
                copy.Clicked();
                str=copy.str;

        }else if(e.getSource()==itemPaste || e.getSource()==popM_Paste){    //ճ��

//            paste();
                Paste p=new Paste(jTextArea);
                p.Clicked();
                str=p.strs;
                start=p.start;
                end=p.end;
        }else if(e.getSource()==itemDelete || e.getSource()==popM_Delete){    //ɾ��
        //     String tem=textArea.getText().toString();
        //     textArea.setText(tem.substring(0,textArea.getSelectionStart())); 
        Delete delete=new Delete(jTextArea);
        delete.Clicked();
        str=delete.str;
        start=delete.start;
        end=delete.end;

        }else if(e.getSource()==itemFind){        //����
//            mySearch();
                Mysearch mysearch =new Mysearch(jTextArea);
                mysearch.Clicked();
        }else if(e.getSource()==itemFindNext){    //������һ��
//            mySearch();
                Mysearch mysearch1=new Mysearch(jTextArea);
                mysearch1.Clicked();
        }else if(e.getSource()==itemReplace){    //�滻
//            mySearch();
                Mysearch mysearch2=new Mysearch(jTextArea);
                mysearch2.Clicked();
        }else if(e.getSource()==itemTurnTo){    //ת��
//            turnTo();
        TurnTo turnTo=new TurnTo(jTextArea);
        turnTo.To();
        }else if(e.getSource()==itemSelectAll || e.getSource()==popM_SelectAll){    //ѡ��ȫ��
//            textArea.selectAll();
                textArea.selectAll();
        }
        else if(e.getSource()==itemNextLine){
                textArea.setLineWrap(true);
        }
        else if(e.getSource()==itemColor){
               Bgcolor bgcolor=new Bgcolor(jTextArea);
               bgcolor.Clicked();
        }
        else if(e.getSource()==itemFont){
              FontSize fontSize=new FontSize(jTextArea);
              fontSize.Clicked();

        }
        else if(e.getSource()==itemFontColor){
                Fontcolor fontcolor=new Fontcolor(jTextArea);
                fontcolor.Clicked();

        }
        else if(e.getSource()==itemSearchForHelp){
                String url="http://www.baidu.com";
                try {
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+url);
                } catch (Exception ee) {
                        //TODO: handle exception
                }
               
        }
        else if(e.getSource()==itemAboutNotepad){
               Help help =new Help();
               help.Clicked();
        }
    }    
    
    /*===============================8====================================*/
    /**
     * �Ƴ���ť���ʹ��ڵĺ��ʵ��һ���Ĺ���
     */
    private void exit() {
        if(flag==2 && currentPath==null){
            //���ǵ���С����
            //1�������������±�Ϊ0�����½��ĵ�Ϊ1���������޸ĺ�
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "�Ƿ񽫸��ı��浽�ޱ���?", "���±�", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
//                NotepadMainFrame.this.saveAs();
            }else if(result==JOptionPane.NO_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }else if(flag==2 && currentPath!=null){
            //���ǵ���С����
            //1�������������±�Ϊ0�����½��ĵ�Ϊ1���������޸ĺ�
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "�Ƿ񽫸��ı��浽"+currentPath+"?", "���±�", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
//                NotepadMainFrame.this.save();
            }else if(result==JOptionPane.NO_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }else{
            //���ǵ���С����
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "ȷ���رգ�", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }
    }
    /*===================================================================*/
    
    
}