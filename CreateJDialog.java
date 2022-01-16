import javax.swing.*;
import java.awt.*;
public class CreateJDialog extends JDialog {
    private JPanel Layout;
    private JComboBox<String>FontFamily=new JComboBox<>(new String[]{
        "sanserif","serif","monospaced","Dialog","DialogInput",
        "Lucida Console","Verdana","Trebuchet MS"
    });    
    private JComboBox<Integer>FontSize=new JComboBox<>(new Integer[]{
        12,16,18,20,26,28,32,36,48
    });
    private JCheckBox Bold = new JCheckBox("Bold");   
    private JCheckBox Italic = new JCheckBox("Italic");
    private JCheckBox Plain = new JCheckBox("Plain");
    private JButton OK=new JButton("OK");    
    private JButton Cancel=new JButton("Cancel");
    public CreateJDialog(JFrame parent, JTextArea Display){
        super(parent,"Font",true);
        super.setSize(200,200);
        Layout=new JPanel(new GridLayout(2,2));
        Layout.add(new JLabel("font: "));
        Layout.add(FontFamily);
        FontFamily.setSelectedItem("serif");
        Layout.add(new JLabel("size: "));
        Layout.add(FontSize);
        FontSize.setSelectedItem(18);
        Cancel.addActionListener(event->{
            super.setVisible(false);
        });
        OK.addActionListener(event->{
            //Take general font type
            int Mode=0;
            if(Bold.isSelected())
                Mode+=Font.BOLD;
            if(Italic.isSelected())
                Mode+=Font.ITALIC;
            if(Plain.isSelected())
                Mode+=Font.PLAIN;
            Display.setFont(new Font((String)FontFamily.getSelectedItem(),Mode,(int)FontSize.getSelectedItem()));
            super.setVisible(false);
        });
        super.setSize(200, 200);
        JPanel ButtonLayout=new JPanel();
        ButtonLayout.add(OK);
        ButtonLayout.add(Cancel);
        Plain.setSelected(true);
        super.add(Layout,BorderLayout.NORTH);
        super.add(CheckBoxPanel(),BorderLayout.CENTER);
        super.add(ButtonLayout,BorderLayout.SOUTH);
        super.setLocationRelativeTo(parent);
    }
    //new GridLayout(3,1)
    public JPanel CheckBoxPanel(){
        JPanel Result=new JPanel(new GridLayout(1,3));
        Result.add(Bold);
        Result.add(Italic);
        Result.add(Plain);
        return Result;
    }
    
}
