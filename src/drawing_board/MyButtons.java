package drawing_board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

//按钮类 {"Line","Circle","Rectangle","Triangle","Characters"}
class MyButtons extends Container{
    public JButton[] buttons;

    //类入口初始化
    MyButtons() {
        //设置自身布局
        this.setLayout(new GridLayout(0,1,100,10));
        //添加按钮
        buttons = new JButton[buttonActions.length];
        for(int i=0; i<buttonActions.length;i++)
        {
            String title = (String) buttonActions[i][0];
            ActionListener actionListener = (ActionListener) buttonActions[i][1];
            this.buttons[i] = new JButton(title);
            this.buttons[i].addActionListener(actionListener);
            this.add(buttons[i]);
        }

    }

    //按钮注册
    private final Object[][] buttonActions = {
            {"Line",new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Comm.cmd = drawCommand.Line;
                }
            }},
            {"Circle",new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Comm.cmd = drawCommand.Circle;
                }
            }},
            {"Oval",new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Comm.cmd = drawCommand.Oval;
                }
            }},
            {"Rectangle",new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Comm.cmd = drawCommand.Rectangle;
                }
            }},
            {"Triangle",new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Comm.cmd = drawCommand.Triangle;
                }
            }},
            {"Color",new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Comm.color = JColorChooser.showDialog(Comm.panel,"颜色选取器",Color.BLACK);
                    if (Comm.cmd == drawCommand.Dragged) {
                        MyShape.backup();
                        Comm.currentMyShape.color = Comm.color;
                        Comm.currentMyShape.rebuild();
                        Comm.panel.repaint();
                    }
                }
            }},
            {"Clear",new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyShape.backup();
                    MyShape.myShapes.clear();
                    Comm.panel.repaint();
                }
            }},
            {"Undo",new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<MyShape> tmp = MyShape.backupShapes;
                    MyShape.backupShapes = MyShape.myShapes;
                    MyShape.myShapes = tmp;
                    Comm.panel.repaint();
                }
            }},
            {"Load",new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser jfc = new JFileChooser();
                    int option = jfc.showOpenDialog(null);
                    if (option == JFileChooser.APPROVE_OPTION) {
                        try {
                            File file = jfc.getSelectedFile();
                            FileInputStream fis = new FileInputStream(file);
                            ObjectInputStream ois = new ObjectInputStream(fis);
                            MyShape.backup();
                            MyShape.myShapes = new ArrayList<>();
                            MyShape tmp;
                            try {
                                while (true) {
                                    tmp = (MyShape) ois.readObject();
                                    tmp.rebuild();
                                    MyShape.myShapes.add(tmp);
                                }
                            } catch (EOFException err) {
                                Comm.panel.repaint();
                            } finally {
                                ois.close();
                                fis.close();
                            }
                        } catch (IOException |ClassNotFoundException err) {
                            err.printStackTrace();
                        }
                    }
                }
            }},
            {"Save",new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser jfc = new JFileChooser();
                    int option = jfc.showSaveDialog(null);
                    if (option == JFileChooser.APPROVE_OPTION) {
                        try {
                            File file = jfc.getSelectedFile();
                            FileOutputStream fos = new FileOutputStream(file);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            for (int i=0;i<MyShape.myShapes.size();i++) {
                                MyShape o = MyShape.myShapes.get(i);
                                oos.writeObject(o);
                            }
                            oos.close();
                            fos.close();
                        } catch (IOException err) {
                            err.printStackTrace();
                        }

                    }
                }
            }},
            {"Characters",new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        Comm.cmd = drawCommand.Characters;
                }
            }},
    };



}
