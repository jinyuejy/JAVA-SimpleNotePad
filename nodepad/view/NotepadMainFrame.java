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
     * 序列号
     */
    public JTextArea jTextArea;
    public String Title;
    private static final long serialVersionUID = 8585210209467333480L;
    private JPanel contentPane;
    private JTextArea textArea;
    private JMenuItem itemOpen;
    private JMenuItem itemSave;
    
    //1：新建 
    //2：修改过
    //3：保存过的
    int flag=0;

    //当前文件名
    String currentFileName=null;
    
     PrintJob  p=null;//声明一个要打印的对象
     Graphics  g=null;//要打印的对象
    
    //当前文件路径
    String currentPath=null;
    String path=null;
    
    //背景颜色
    JColorChooser jcc1=null;
    Color color=Color.BLACK;
    
    //文本的行数和列数
    int linenum = 1;
    int columnnum = 1;
    
    //撤销管理器
    public UndoManager undoMgr = new UndoManager(); 
    
    //剪贴板
    public Clipboard clipboard = new Clipboard("系统剪切板");
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
        setTitle("无标题");    
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 521, 572);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu itemFile = new JMenu("文件(F)");
        itemFile.setMnemonic('F');
        menuBar.add(itemFile);
        
        itemNew = new JMenuItem("新建(N)",'N');
        itemNew.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,
                java.awt.Event.CTRL_MASK));  
       itemNew.addActionListener(this);
        itemFile.add(itemNew);
        
        itemOpen = new JMenuItem("打开(O)",'O');
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O,
                            java.awt.Event.CTRL_MASK));  
       itemOpen.addActionListener(this);
        itemFile.add(itemOpen);
        
        itemSave = new JMenuItem("保存(S)");
        itemSave.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
                java.awt.Event.CTRL_MASK));   
       itemSave.addActionListener(this);
        itemFile.add(itemSave);
        
        itemSaveAs = new JMenuItem("另存为(A)");
       itemSaveAs.addActionListener(this);
        itemFile.add(itemSaveAs);
        
        separator = new JSeparator();
        itemFile.add(separator);
        
        itemPage = new JMenuItem("页面设置(U)",'U');
       itemPage.addActionListener(this);
        itemFile.add(itemPage);
        
        itemPrint = new JMenuItem("打印(P)...",'P');
        itemPrint.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P,
                java.awt.Event.CTRL_MASK));   
       itemPrint.addActionListener(this);
        itemFile.add(itemPrint);
        
        separator_1 = new JSeparator();
        itemFile.add(separator_1);
        
        itemExit = new JMenuItem("退出(X)",'X');
        itemExit.addActionListener(this);
        itemFile.add(itemExit);
        
        itemEdit = new JMenu("编辑(E)");
        itemEdit.setMnemonic('E');
        menuBar.add(itemEdit);
        
        itemUndo = new JMenuItem("撤销(U)",'U');
        itemUndo.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z,
                java.awt.Event.CTRL_MASK));
       itemUndo.addActionListener(this);
        itemEdit.add(itemUndo);
        
        itemRedo = new JMenuItem("恢复(R)");
        itemRedo.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R,
                java.awt.Event.CTRL_MASK));
//        itemRedo.addActionListener(this);
        itemEdit.add(itemRedo);
        
        itemCut = new JMenuItem("剪切(T)",'T');
        itemCut.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X,
                java.awt.Event.CTRL_MASK));
       itemCut.addActionListener(this);
        
        separator_6 = new JSeparator();
        itemEdit.add(separator_6);
        itemEdit.add(itemCut);
        
        itemCopy = new JMenuItem("复制(C)",'C');
       itemCopy.addActionListener(this);
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C,
                java.awt.Event.CTRL_MASK));
        itemEdit.add(itemCopy);
        
        itemPaste = new JMenuItem("粘贴(P)",'P');
       itemPaste.addActionListener(this);
        itemPaste.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V,
                java.awt.Event.CTRL_MASK));
        itemEdit.add(itemPaste);
        
        itemDelete = new JMenuItem("删除(L)",'L');
       itemDelete.addActionListener(this);
        itemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,  
                InputEvent.CTRL_MASK));  
        itemEdit.add(itemDelete);
        
        separator_7 = new JSeparator();
        itemEdit.add(separator_7);
        
        itemFind = new JMenuItem("查找(F)",'F');
        itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                Event.CTRL_MASK));
       itemFind.addActionListener(this);
        itemEdit.add(itemFind);
        
        itemFindNext = new JMenuItem("查找下一个(N)",'N');
        itemFindNext.setAccelerator(KeyStroke.getKeyStroke("F3"));
       itemFindNext.addActionListener(this);
        itemEdit.add(itemFindNext);
        
        itemReplace = new JMenuItem("替换(R)",'R');
       itemReplace.addActionListener(this);
        itemReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
                Event.CTRL_MASK));
        itemEdit.add(itemReplace);
        
        itemTurnTo = new JMenuItem("转到(G)",'G');
       itemTurnTo.addActionListener(this);
        itemTurnTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
                Event.CTRL_MASK));
        itemEdit.add(itemTurnTo);
        
        itemSelectAll = new JMenuItem("全选(A)",'A');
        itemSelectAll.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A,
                java.awt.Event.CTRL_MASK));
       itemSelectAll.addActionListener(this);
        
        separator_8 = new JSeparator();
        itemEdit.add(separator_8);
        itemEdit.add(itemSelectAll);
        
        itemTime = new JMenuItem("时间/日期(D)",'D');
       itemTime.addActionListener(this);
        itemTime.setAccelerator(KeyStroke.getKeyStroke("F5"));
        itemEdit.add(itemTime);
        
        itFormat = new JMenu("格式(O)");
        itFormat.setMnemonic('O');
        menuBar.add(itFormat);
        
        itemNextLine = new JCheckBoxMenuItem("自动换行(W)");
       itemNextLine.addActionListener(this);
        itFormat.add(itemNextLine);
        
        itemFont = new JMenuItem("字体大小(F)...");
       itemFont.addActionListener(this);
        itFormat.add(itemFont);
        
        itemColor = new JMenuItem("背景颜色(C)...");
       itemColor.addActionListener(this);
        itFormat.add(itemColor);
        
        itemFontColor = new JMenuItem("字体颜色(I)...");
       itemFontColor.addActionListener(this);
        itFormat.add(itemFontColor);
        
        itemCheck = new JMenu("查看(V)");
        itemCheck.setMnemonic('V');
        menuBar.add(itemCheck);
        
        itemStatement = new JCheckBoxMenuItem("状态栏(S)");
       itemStatement.addActionListener(this);
       itemStatement.setSelected(false);
        itemCheck.add(itemStatement);
        
        itemHelp = new JMenu("帮助(H)");
        itemHelp.setMnemonic('H');
        menuBar.add(itemHelp);
        
        itemSearchForHelp = new JMenuItem("查看帮助(H)",'H');
       itemSearchForHelp.addActionListener(this);
        itemHelp.add(itemSearchForHelp);
        
        itemAboutNotepad = new JMenuItem("关于记事本(A)",'A');
       itemAboutNotepad.addActionListener(this);
        itemHelp.add(itemAboutNotepad);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //设置边框布局
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        textArea = new JTextArea();
        textArea.setEditable(true);
        
        //VERTICAL垂直    HORIZONTAL水平
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        //contentPane2=new JPanel();
        //contentPane2.setSize(10,textArea.getSize().height);
        //contentPane.add(contentPane2, BorderLayout.WEST);
        TestLine view = new TestLine();
        scrollPane.setRowHeaderView(view);
        
        popupMenu = new JPopupMenu();
//        addPopup(textArea, popupMenu);
        
        popM_Undo = new JMenuItem("撤销(U)");
//        popM_Undo.addActionListener(this);
        popupMenu.add(popM_Undo);
        
        popM_Redo = new JMenuItem("恢复(R)");
//        popM_Redo.addActionListener(this);
        popupMenu.add(popM_Redo);
        
        separator_2 = new JSeparator();
        popupMenu.add(separator_2);
        
        popM_Cut = new JMenuItem("剪切(T)");
//        popM_Cut.addActionListener(this);
        popupMenu.add(popM_Cut);
        
        popM_Copy = new JMenuItem("复制(C)");
//        popM_Copy.addActionListener(this);
        popupMenu.add(popM_Copy);
        
        popM_Paste = new JMenuItem("粘贴(P)");
//        popM_Paste.addActionListener(this);
        popupMenu.add(popM_Paste);
        
        popM_Delete = new JMenuItem("删除(D)");
//        popM_Delete.addActionListener(this);
        popupMenu.add(popM_Delete);
        
        separator_3 = new JSeparator();
        popupMenu.add(separator_3);
        
        popM_SelectAll = new JMenuItem("全选(A)");
//        popM_SelectAll.addActionListener(this);
        popupMenu.add(popM_SelectAll);
        
        separator_4 = new JSeparator();
        popupMenu.add(separator_4);
        
        popM_toLeft = new JMenuItem("从右到左的阅读顺序(R)");
//        popM_toLeft.addActionListener(this);
        popupMenu.add(popM_toLeft);
        
        popM_showUnicode = new JMenuItem("显示Unicode控制字符(S)");
//        popM_showUnicode.addActionListener(this);
        popupMenu.add(popM_showUnicode);
        
        popM_InsertUnicode = new JMenuItem("插入Unicode控制字符(I)");
//        popM_InsertUnicode.addActionListener(this);
        popupMenu.add(popM_InsertUnicode);
        
        separator_5 = new JSeparator();
        popupMenu.add(separator_5);
        
        popM_closeIMe = new JMenuItem("关闭IME(L)");
//        popM_closeIMe.addActionListener(this);
        popupMenu.add(popM_closeIMe);
        
        popM_RestartSelect = new JMenuItem("汉字重选(R)");
//        popM_RestartSelect.addActionListener(this);
        popupMenu.add(popM_RestartSelect);
        //添加到面板中【中间】
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        //添加撤销管理器
        textArea.getDocument().addUndoableEditListener(undoMgr);

       
                
        
        toolState = new JToolBar();
        toolState.setSize(textArea.getSize().width, 10);//toolState.setLayout(new FlowLayout(FlowLayout.LEFT));
        label1 = new JLabel("    当前系统时间：" + hour + ":" + min + ":" + second+" ");
        toolState.add(label1);
        toolState.addSeparator();
        label2 = new JLabel("    第 " + linenum + " 行, 第 " + columnnum+" 列  ");
        toolState.add(label2);
        toolState.addSeparator();
        
        label3 = new JLabel("    一共 " +length+" 字  ");
        toolState.add(label3);
        textArea.addCaretListener(new CaretListener() {        //记录行数和列数
            public void caretUpdate(CaretEvent e) {
                //sum=0;
                JTextArea editArea = (JTextArea)e.getSource();
 
                try {
                    int caretpos = editArea.getCaretPosition();
                    linenum = editArea.getLineOfOffset(caretpos);
                    columnnum = caretpos - textArea.getLineStartOffset(linenum);
                    linenum += 1;
                    label2.setText("    第 " + linenum + " 行, 第 " + (columnnum+1)+" 列  ");
                    //sum+=columnnum+1;
                    //length+=sum;
                    length=NotepadMainFrame.this.textArea.getText().toString().length();
                    label3.setText("    一共 " +length+" 字  ");
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
        
        
        
        // 创建弹出菜单
        final JPopupMenu jp=new JPopupMenu();    //创建弹出式菜单，下面三项是菜单项
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3)//只响应鼠标右键单击事件
                {
                    jp.show(e.getComponent(),e.getX(),e.getY());//在鼠标位置显示弹出式菜单
                }
            }
        });
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==itemOpen){            //打开
//            openFile();
                OpenFiles openFiles =new OpenFiles(jTextArea, this);
                openFiles.Clicked();
                Title=openFiles.title;
                path=openFiles.path;
        }else if(e.getSource()==itemSave){        //保存
            //如果该文件是打开的，就可以直接保存
//            save(); 
                SaveFiles saveFiles=new SaveFiles(jTextArea,path);
                saveFiles.Clicked();
        }else if(e.getSource()==itemSaveAs){    //另存为
//            saveAs();
                Othersave othOthersave=new Othersave(jTextArea, Title);
                othOthersave.Clicked();
        }else if(e.getSource()==itemNew){        //新建
//            newFile();
                NewFile newFile =new NewFile(jTextArea, this);
                newFile.Clicked();
        }else if(e.getSource()==itemExit){        //退出
            exit();
        }else if(e.getSource()==itemPage){        //页面设置
            ///页面设置，百度到的，不知道具体的用法
            PageFormat pf = new PageFormat();
            PrinterJob.getPrinterJob().pageDialog(pf); 
        }else if(e.getSource()==itemPrint){        //打印
            //打印机
//            Print();
                Print_Doc print =new Print_Doc(path);
                print.Clicked();
        }else if(e.getSource()==itemUndo || e.getSource()==popM_Undo){        //撤销
            if(undoMgr.canUndo()){
               undoMgr.undo();
            }
            else{JOptionPane.showMessageDialog(null,"无需重做","提示：", JOptionPane.WARNING_MESSAGE);}
        }else if(e.getSource()==itemRedo || e.getSource()==popM_Redo){        //恢复
            if(undoMgr.canRedo()){
               undoMgr.redo();
            }
            else{JOptionPane.showMessageDialog(null,"无需重做","提示：", JOptionPane.WARNING_MESSAGE);}
        }else if(e.getSource()==itemCut || e.getSource()==popM_Cut){        //剪切
//            cut();
                Cut cut=new Cut(jTextArea);
                cut.Clicked();
                start=cut.start;
                end=cut.end;
                str=cut.str;
        }else if(e.getSource()==itemCopy || e.getSource()==popM_Copy){        //复制
//            copy();
                Copy copy=new Copy(jTextArea);
                copy.Clicked();
                str=copy.str;

        }else if(e.getSource()==itemPaste || e.getSource()==popM_Paste){    //粘贴

//            paste();
                Paste p=new Paste(jTextArea);
                p.Clicked();
                str=p.strs;
                start=p.start;
                end=p.end;
        }else if(e.getSource()==itemDelete || e.getSource()==popM_Delete){    //删除
        //     String tem=textArea.getText().toString();
        //     textArea.setText(tem.substring(0,textArea.getSelectionStart())); 
        Delete delete=new Delete(jTextArea);
        delete.Clicked();
        str=delete.str;
        start=delete.start;
        end=delete.end;

        }else if(e.getSource()==itemFind){        //查找
//            mySearch();
                Mysearch mysearch =new Mysearch(jTextArea);
                mysearch.Clicked();
        }else if(e.getSource()==itemFindNext){    //查找下一个
//            mySearch();
                Mysearch mysearch1=new Mysearch(jTextArea);
                mysearch1.Clicked();
        }else if(e.getSource()==itemReplace){    //替换
//            mySearch();
                Mysearch mysearch2=new Mysearch(jTextArea);
                mysearch2.Clicked();
        }else if(e.getSource()==itemTurnTo){    //转到
//            turnTo();
        TurnTo turnTo=new TurnTo(jTextArea);
        turnTo.To();
        }else if(e.getSource()==itemSelectAll || e.getSource()==popM_SelectAll){    //选择全部
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
     * 推出按钮，和窗口的红叉实现一样的功能
     */
    private void exit() {
        if(flag==2 && currentPath==null){
            //这是弹出小窗口
            //1、（刚启动记事本为0，刚新建文档为1）条件下修改后
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "是否将更改保存到无标题?", "记事本", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
//                NotepadMainFrame.this.saveAs();
            }else if(result==JOptionPane.NO_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }else if(flag==2 && currentPath!=null){
            //这是弹出小窗口
            //1、（刚启动记事本为0，刚新建文档为1）条件下修改后
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "是否将更改保存到"+currentPath+"?", "记事本", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
//                NotepadMainFrame.this.save();
            }else if(result==JOptionPane.NO_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }else{
            //这是弹出小窗口
            int result=JOptionPane.showConfirmDialog(NotepadMainFrame.this, "确定关闭？", "系统提示", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                NotepadMainFrame.this.dispose();
                NotepadMainFrame.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }
    }
    /*===================================================================*/
    
    
}