package bong.lines;

import javax.swing.*;

public class App {
    private JButton button_msg;
    private JPanel panelMain;

    public App() {
        button_msg = new JButton("Button");

        panelMain = new JPanel();

        panelMain.add(button_msg);

        button_msg.addActionListener(e -> JOptionPane.showMessageDialog(null, "Hello World"));
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("App");

        jFrame.setContentPane(new App().panelMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
