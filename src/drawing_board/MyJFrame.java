package drawing_board;


import javax.swing.*;
import java.awt.*;

public class MyJFrame extends JFrame {
    public MyJFrame() {
        super("这是一个画板");
        this.setSize(800,600);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(MyJFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
