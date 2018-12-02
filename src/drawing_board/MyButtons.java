package drawing_board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

//按钮类
public class MyButtons extends Container{
    private ArrayList<String, ActionListener> actionsMap;
    public JButton[] buttons;
    public MyButtons() {
        //设置自身布局
        this.setLayout(new GridLayout(0,1,100,10));
        //添加按钮
        this.setActionListenerMap();
        this.buttons = new JButton[actionsMap.size()];
        for(Iterator iterator = actionsMap.keySet().iterator())
        {
            this.buttons[i] = new JButton(sButtons[i]);
            this.add(buttons[i]);
        }

    }

    protected ActionListener setActionListenerMap()
    {
        String[] sButtons = {"Line","Circle","Rectangle","Triangle","Characters"};
        actionsMap = new HashMap<String, ActionListener>();
        actionsMap.put("Line", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
