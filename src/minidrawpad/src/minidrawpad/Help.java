package minidrawpad;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
//�����˵����ܵ�������
public class Help extends JFrame {
	private DrawPad  drawpad = null;
	Help(DrawPad dp)
	{
		drawpad = dp;
	}
	
	public void MainHeip()
	  {
	  	JOptionPane.showMessageDialog(this,"СС��ͼ������ĵ���","СС��ͼ��",JOptionPane.WARNING_MESSAGE);
	  } 
	 public void AboutBook()
	  {
	  	JOptionPane.showMessageDialog(drawpad,"СС��ͼ��"+"\n"+"    �汾: 1.2.3"+"\n"
	  	   +"    ����:  Anonymous"+"\n"
	  	   +"    ʱ��:  2018/01/13","СС��ͼ��",JOptionPane.WARNING_MESSAGE);
	  }
}
