import javax.swing.*;

public class painting_board {
    private JPanel panel1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("painting_board");
        frame.setContentPane(new painting_board().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton button1;
    private JButton button2;
    private JButton button3;
}
