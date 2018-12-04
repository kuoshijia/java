package drawing_board;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;


//辅助类，避免一个类文件写得太长而分出来的另一个中间类
enum btnCmd {Line,Circle,Rectangle,Triangle,Characters};

class Comm {
    static btnCmd buttonCommand;
    static int startX,startY,endX,endY;

    static Shape shape;
    static MyShape myShape;
    static final ArrayList<MyShape> myShapes = new ArrayList<>();

    static Graphics2D g2d;
    static Stroke stroke=new BasicStroke();
    static Color color = Color.BLACK;

    static JPanel panel;

    static MyShape getShape() {
        switch (buttonCommand) {
            case Line:
                shape = new Line2D.Double(startX, startY, endX, endY);
                break;
            case Circle:
                shape = new Line2D.Double(startX, startY, endX, endY);
                break;
        }
        return new MyShape(shape,stroke,color);
    }

//    static void panelMouseReleased(JPanel panel) {
//        panel.repaint();
//    }

    static void panelMouseDragged() {
        myShape = getShape();

    }

    static void panelMousePressed() {

    }

    static void repaint(Graphics2D g2d) {
        g2d.setStroke(stroke);
        g2d.setColor(color);
        Iterator<Shape> iterator = shapes.iterator();
        while(iterator.hasNext() && g2d != null) {
            Shape next = iterator.next();
            g2d.draw(next);
        }
    }

}
