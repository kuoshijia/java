package drawing_board;

import javax.swing.*;
import java.awt.*;

//提供画图环境的panel
public class MyPanel extends JPanel {
    public Graphics2D brush;
    Stroke stroke;

    public MyPanel() {
        brush = (Graphics2D) this.getGraphics();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
