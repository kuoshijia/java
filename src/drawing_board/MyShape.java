package drawing_board;

import drawing_board.shapes.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;

public class MyShape  implements Cloneable, Serializable {
    static ArrayList<MyShape> myShapes = new ArrayList<>();
    static ArrayList<MyShape> backupShapes = new ArrayList<>();
    transient Shape shape;
    transient Stroke stroke;
    Color color;
    double x1,y1,x2,y2;
    drawCommand type;
    float strokeWidth = 1;
    String text;
    Graphics2D g2d;


    public MyShape(Stroke stroke, Color color, double x1, double y1, double x2, double y2, drawCommand type) {
        this.stroke = stroke;
        this.color = color;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.type = type;
        rebuild();
    }

    public MyShape(Stroke stroke, Color color, double x1, double y1, drawCommand type, String text) {
        this.stroke = stroke;
        this.color = color;
        this.x1 = x1;
        this.y1 = y1;
        this.type = type;
        this.text = text;
        rebuild();
    }

    static void backup() {
        backupShapes = (ArrayList<MyShape>) myShapes.clone();
        for (int i=0;i<backupShapes.size();i++) {
            try {
                MyShape o = backupShapes.get(i).clone();
                backupShapes.set(i,o);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
    void rebuild() {
        stroke = new BasicStroke(strokeWidth);
        switch (type) {
            case Line:
                shape = new Line2D.Double(x1, y1, x2, y2);
                break;
            case Circle:
                double centerX = 0.5 * (x1 + x2);
                double centerY = 0.5 * (y1 + y2);
                double radius = 0.5 * Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                Rectangle2D rect = new Rectangle2D.Double(centerX - radius, centerY - radius,
                        radius * 2, radius * 2);
                shape = new Ellipse2D.Double();
                ((Ellipse2D.Double) shape).setFrame(rect);
                break;
            case Oval:
                Rectangle2D rect2 = new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2),
                        Math.abs(x2 - x1), Math.abs(y2 - y1));
                shape = new Ellipse2D.Double();
                ((Ellipse2D.Double) shape).setFrame(rect2);
                break;
            case Rectangle:
                shape = new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2),
                        Math.abs(x2 - x1), Math.abs(y2 - y1));
                break;
            case Characters:
                if (g2d != null) {
                    Font f = new Font("Helvetica",1,60);
                    FontRenderContext frc = g2d.getFontRenderContext();
                    TextLayout tl = new TextLayout(text,f,frc);
                    shape = tl.getOutline(null);
                }
                break;
            case Triangle:
                shape = new Triangle();
                myShapes.remove(this);
                break;
        }
    }

    @Override
    protected MyShape clone() throws CloneNotSupportedException{
        MyShape o = new MyShape(stroke,color,x1,y1,x2,y2,type);
        if (o==null)
            throw new CloneNotSupportedException();
        return o;
    }

}
