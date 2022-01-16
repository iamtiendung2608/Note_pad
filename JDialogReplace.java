import javax.swing.JDialog;
import java.awt.*;

import javax.lang.model.util.Elements.Origin;
import javax.swing.*;
public class JDialogReplace extends JDialog {
    private JLabel Find =new JLabel("Find: ") ;
    private JLabel Replace =new JLabel("Replace: ") ;
    private JTextField FindInput =new JTextField(20);
    private JTextField replaceInput =new JTextField(20) ;
    private JButton Cancel =new JButton("Cancel");    
    private JButton ReplaceAll =new JButton("Replace All");
    private JButton ReplaceFirst =new JButton("Replace");

    public JDialogReplace(JFrame parent, JTextArea Display){
        super(parent,"Replace",true);
        JPanel Layout=new JPanel(new GridLayout(3,3));
        Find.setFont(Display.getFont());
        Replace.setFont(Display.getFont());
        FindInput.setFont(Display.getFont());
        replaceInput.setFont(Display.getFont());

        Layout.add(Find);
        Layout.add(FindInput,BorderLayout.EAST);
        Layout.add(Replace);
        Layout.add(replaceInput,BorderLayout.EAST);
        Cancel.addActionListener(event->{
            super.setVisible(false);
        });
        ReplaceAll.addActionListener(event->{
            String Text=Display.getText().replace(FindInput.getText(),replaceInput.getText());
            if(Text.equals(Display.getText())){
                JOptionPane.showMessageDialog(parent, "Cannot find "+FindInput.getText(), "Notepad", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Display.setText(Text);
            super.setVisible(false);
        });
        ReplaceFirst.addActionListener(event->{
            String Text=Display.getText().replaceFirst(FindInput.getText(),  replaceInput.getText());
            if(Text.equals(Display.getText())){
                JOptionPane.showMessageDialog(parent, "Cannot find "+FindInput.getText(), "Notepad", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Display.setText(Text);
        });
        super.add(AddButton(),BorderLayout.EAST);
        super.add(Layout);
        super.setSize(300, 150);
        super.setLocationRelativeTo(parent);
    }
    private JPanel AddButton(){
        JPanel Result=new JPanel(new GridLayout(3,1));
        Result.add(ReplaceFirst);
        Result.add(ReplaceAll);
        Result.add(Cancel);
        return Result;
    }
}
