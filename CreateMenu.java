import java.awt.*;
import javax.swing.*;
public class CreateMenu {
    public JMenuBar Menu(String []names,JMenu...Item){
        JMenuBar menuBar=new JMenuBar();
        for(int i=0;i<Item.length;i++){
            Item[i].setMnemonic(names[i].charAt(1));
            menuBar.add(Item[i]);
        }
        return menuBar;
    }
}
