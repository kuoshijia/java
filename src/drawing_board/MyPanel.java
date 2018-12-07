package drawing_board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//提供画图环境的panel，就是画板类
class MyPanel extends JPanel {

    public MyPanel() {
        Comm.panel = this;
        setEvents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        Comm.panelRepaint(g2d);
        //设置窗体为焦点，以便接收键盘事件
        requestFocus();
    }

    //事件处理注册
    protected void setEvents() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Comm.startX = e.getX();
                Comm.startY = e.getY();
                Comm.panelMousePressed();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Comm.endX = e.getX();
                Comm.endY = e.getY();
                Comm.panelMouseReleased();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Comm.endX = e.getX();
                Comm.endY = e.getY();
                Comm.panelMouseDragged();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Comm.keyTyped(e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //Comm.keyTyped(e.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }



}
