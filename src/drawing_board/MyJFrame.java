package drawing_board;



import javax.swing.*;
import java.awt.*;
//相框类
public class MyJFrame extends JFrame {
    //按钮
    public MyButtons buttons;
    //画板
    public MyPanel panel;

    //构造函数
    public MyJFrame() {
        //自身属性设置
        this.setSelf();
        //添加组件
        this.addPanel();
        this.addJButtons();
        //开始显示画框本身
        this.setVisible(true);
    }
//自身设置
    private void setSelf()
    {
        this.setTitle("这是一个画板");
        this.setSize(800,600);
        this.setBackground(Color.white);
        //this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(MyJFrame.EXIT_ON_CLOSE);
    }
//添加画板
    private void addPanel() {
        panel = new MyPanel();
        //java 1.6以上支持直接添加组件（默认添加在ContentPane），不需要getContentPane之后再在ContentPane上面添加
        this.add(BorderLayout.CENTER,panel);
    }
//添加按钮
    private void addJButtons() {
        buttons = new MyButtons();
        this.add(BorderLayout.EAST,buttons);
    }

}
