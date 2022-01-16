import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
public class MainProgram {
    public static void main(String[] args) {
        CreateJFrame notePad=new CreateJFrame();
    }
}
class CreateJFrame extends JFrame{
    private JMenuBar menuBar;
    private JMenu fileMenu=new JMenu("File");
    private JMenu editMenu=new JMenu("Edit");
    private JMenu formatMenu=new JMenu("Format");
    private JTextArea Display;
    private CreateJDialog fontDialog;
    private JDialogReplace replaceDialog;
    private JFileChooser SaveFile=new JFileChooser();
    private String Current=new String();
    public CreateJFrame(){
        super.setTitle("Untitled - Notepad");
        SaveFile.setCurrentDirectory(new File("."));
        super.setIconImage(new ImageIcon("notepad.png").getImage());
        Display=new JTextArea(16,20);
        Display.setFont(new Font("serif",Font.PLAIN,18));
        JScrollPane scrollPane=new JScrollPane(Display);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Display.setLineWrap(true);
        String[] Names={"File","Edit","Format"};
        menuBar=new CreateMenu().Menu(Names, fileMenu,editMenu,formatMenu);
        AddItem();

        super.setJMenuBar(menuBar);
        super.add(scrollPane);
        super.setSize(500,500);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
        super.setLocationRelativeTo(null);
    }
    public void AddItem(){
        //File menu
        JMenuItem New=new JMenuItem("New");
        New.addActionListener(event->{
            if(!Display.getText().equals(Current)){
                int choice=JOptionPane.showConfirmDialog(CreateJFrame.this, "Do you want to save change", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION);
                if(choice==JOptionPane.YES_OPTION){
                    //Save text
                }
                else{
                    Display.setText("");
                    super.setTitle("Untitled - Notepad");
                }
                Current="";
            }
        });
        New.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        JMenuItem Save=new JMenuItem("Save");
        Save.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        Save.addActionListener(event->{
            Current=Display.getText();
            int option=SaveFile.showSaveDialog(CreateJFrame.this);
            SaveText(SaveFile.getSelectedFile().getPath());
            super.setTitle(SaveFile.getSelectedFile().getName());
        });
        JMenuItem Open=new JMenuItem("Open");
        Open.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        Open.addActionListener(event->{
            //asking before open...
            
            SaveFile.showOpenDialog(CreateJFrame.this);
            ReadText(SaveFile.getSelectedFile().getPath());
            Current=Display.getText();
            super.setTitle(SaveFile.getSelectedFile().getName());
        });
        JMenuItem Exit=new JMenuItem("Exit");
        Exit.addActionListener(event->System.exit(0));
        fileMenu.add(New);
        fileMenu.add(Open);
        fileMenu.addSeparator();
        fileMenu.add(Save);
        fileMenu.addSeparator();
        fileMenu.add(Exit);

        //Edit
        JMenuItem Undo=new JMenuItem("Undo");
        JMenuItem Replace=new JMenuItem("Replace");
        JMenuItem Date=new JMenuItem("Date");
        Date.addActionListener(event->{
            String day=String.format("%1$tR %1$ta %1$tF",  new Date());
            Display.append(day.toString());
        });
        // Undo.addActionListener(event->{
        //     if(Display.getText().length()==0)
        //         return;
        //     StringBuilder Chain=new StringBuilder(Display.getText());
        //     Chain.deleteCharAt(Chain.length()-1);
        //     Display.setText(Chain.toString());
        // });
        Replace.addActionListener(event->{
            if(replaceDialog==null){
                replaceDialog=new JDialogReplace(CreateJFrame.this, Display);
            }
            replaceDialog.setVisible(true);
        });
        editMenu.add(Undo);
        editMenu.add(Replace);
        editMenu.add(Date);
        Undo.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
        Replace.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
        Date.setAccelerator(KeyStroke.getKeyStroke("F5"));
        //Font
        JMenuItem Font=new JMenuItem("Font...");
        Font.addActionListener(event->{
            if(fontDialog==null){
                fontDialog=new CreateJDialog(CreateJFrame.this, Display);
            }
            fontDialog.setVisible(true);
        });
        formatMenu.add(Font);
    }
    public void SaveText(String path){
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter(path));
            writer.write(Display.getText());
            writer.close();
        }
        catch(IOException ex){
            System.out.println("Can't Save this list");
        }
    }
    public void ReadText(String path){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String text=reader.lines().collect(Collectors.joining("\n"));
            Display.setText(text);
            reader.close();
        }
        catch(IOException ex){
            System.out.println("Can't open file");
        }
    }
}
