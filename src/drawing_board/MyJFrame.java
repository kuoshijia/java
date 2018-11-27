package drawing_board;



import javax.swing.*;
import java.awt.*;

public class MyJFrame extends JFrame {
    public JButton[] buttons;
    public Container pane;
    public MyJFrame() {
        super("这是一个画板");
        this.setSize(800,600);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(MyJFrame.EXIT_ON_CLOSE);
        pane = getContentPane();
        initButtons();
        this.setVisible(true);
    }

    private void initButtons() {
        String[] sButtons = {"Line","Circle","Rectangle","Triangle","Characters"};
        this.buttons = new JButton[sButtons.length];
        Container paneButtons = new Container();
        paneButtons.setLayout(new GridLayout(0,1,100,10));
        pane.add(BorderLayout.EAST,paneButtons);
        for(int i=0; i<sButtons.length;i++)
        {
            this.buttons[i] = new JButton(sButtons[i]);
            paneButtons.add(buttons[i]);
        }
        //return this.buttons;
    }
}
