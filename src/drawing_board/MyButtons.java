package drawing_board;

import javax.swing.*;
import java.awt.*;

public class MyButtons extends Container{
    String[] sButtons = {"Line","Circle","Rectangle","Triangle","Characters"};
    JButton[] buttons;
    public MyButtons() {
        //设置自身布局
        this.setLayout(new GridLayout(0,1,100,10));
        //添加按钮
        this.buttons = new JButton[sButtons.length];
        for(int i=0; i<sButtons.length;i++)
        {
            this.buttons[i] = new JButton(sButtons[i]);
            this.add(buttons[i]);
        }

    }
}
