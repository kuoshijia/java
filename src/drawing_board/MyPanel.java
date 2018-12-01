package drawing_board;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

//提供画图环境的panel，就是画板类
public class MyPanel extends JPanel {
    //画图接口
    public Graphics2D g2d;
    Stroke stroke;

    public MyPanel() {
        setBackground(Color.white);
        //this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g2d = (Graphics2D) g;
        g2d = this.g2d;
        Stroke s = new BasicStroke(20, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2d.setStroke(s);
        Line2D line = new Line2D.Double(30,50,100,200);
        g2d.draw(line);
    }



}
