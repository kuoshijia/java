package drawing_board;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Iterator;


//辅助类，避免一个类文件写得太长而分出来的另一个中间类（静态成员变量用于存储共享数据）
enum drawCommand {Line,Oval,Circle,Rectangle,Triangle,Characters,Null, startDragging,Dragging,Dragged};
enum MouseStatus {Pressed, Dragging,Released};
class Comm {
    static drawCommand cmd = drawCommand.Null;
    static double startX,startY,endX,endY,oldX1,oldX2,oldY1,oldY2;

    static MyShape currentMyShape;

    static Stroke stroke=new BasicStroke();
    static Color color = Color.BLACK;

    static JPanel panel;

    static MouseStatus mouseStatus = MouseStatus.Released;

    static String text = "";


//    static void panelMouseReleased(JPanel panel) {
//        panel.panelRepaint();
//    }

    static void panelMouseDragged() {
        //判断是创建新的图形，还是拖动图形
        switch (cmd) {
            case Null:
            case Dragged:
                break;
            case startDragging:
                MyShape.backup();
                cmd = drawCommand.Dragging;
            case Dragging:
                currentMyShape.x1 = oldX1 + endX - startX;
                currentMyShape.x2 = oldX2 + endX - startX;
                currentMyShape.y1 = oldY1 + endY - startY;
                currentMyShape.y2 = oldY2 + endY - startY;
                currentMyShape.rebuild();
                break;
            default: //构建图形
                MyShape myNewShape = new MyShape(stroke,color,startX,startY,endX,endY,cmd);
                switch (mouseStatus) {
                    case Pressed: //第一次建
                        MyShape.backup();
                        MyShape.myShapes.add(myNewShape);
                        currentMyShape = myNewShape;
                        break;
                    case Dragging: //第N次重建
                        for (int i = MyShape.myShapes.size()-1; i>=0; i--) {
                            if (MyShape.myShapes.get(i) == currentMyShape) {
                                MyShape.myShapes.set(i, myNewShape);
                                currentMyShape = myNewShape;
                                break;
                            }
                        }
                        break;
                }
                break;
        }
        mouseStatus = MouseStatus.Dragging;
        panel.repaint();
    }

    static void panelMousePressed() {
        mouseStatus = MouseStatus.Pressed;
        switch (cmd) {
            case Dragged:
            case Null: {
                //说明没有点击按钮,看一下要不要选中图形，以供日后拖动
                for (int i = MyShape.myShapes.size()-1; i>=0; i--) {
                    MyShape tmp = MyShape.myShapes.get(i);
                    if (tmp.shape.intersects(startX,startY,10,10)) {
                        currentMyShape = tmp;
                        MyShape.myShapes.remove(i);
                        MyShape.myShapes.add(tmp);
                        //保存图形的旧位置
                        oldX1 = tmp.x1;
                        oldX2 = tmp.x2;
                        oldY1 = tmp.y1;
                        oldY2 = tmp.y2;
                        //设置命令为拖动
                        cmd = drawCommand.startDragging;
                        //及时退出，只选择一个对象
                        break;
                    }
                }
                break;
            }
            case Characters:
                String input = JOptionPane.showInputDialog("请输入要显示的文本！");
                if (input.length()>0) {
                    MyShape myNewShape = new MyShape(new BasicStroke(), color, startX, startY, cmd, input);
                    MyShape.backup();
                    MyShape.myShapes.add(myNewShape);
                    panel.repaint();
                    cmd = drawCommand.Null;
                }
                    break;

        }
    }

    static void panelMouseReleased() {
        //为了测试设定的入口
        panelMouseDragged();
        mouseStatus = MouseStatus.Released;
        switch (cmd) {
            case Dragging:
                cmd = drawCommand.Dragged;
                break;
            default:
                cmd = drawCommand.Null;
        }
    }

    static void panelRepaint(Graphics2D g2d) {
        Iterator<MyShape> iterator = MyShape.myShapes.iterator();
        while(iterator.hasNext() && g2d != null) {
            MyShape next = iterator.next();
            AffineTransform x = new AffineTransform();
            if (next.type == drawCommand.Characters) {
                next.g2d = g2d;
                next.rebuild();
                x.translate(next.x1,next.y1);
            }
            g2d.setTransform(x);
            g2d.setStroke(next.stroke);
            g2d.setColor(next.color);
            //g2d.translate(next.offsetX,next.offsetY);
            g2d.draw(next.shape);
        }
    }

    static void keyTyped(char key) {
        if (currentMyShape != null) {
            switch (key) {
                case '-':
                    if(currentMyShape.strokeWidth>1) {
                        currentMyShape.strokeWidth--;
                        currentMyShape.rebuild();
                    }
                    break;
                case '+':
                    currentMyShape.strokeWidth++;
                    currentMyShape.rebuild();
                    break;
            }
            panel.repaint();
        }
    }


}
