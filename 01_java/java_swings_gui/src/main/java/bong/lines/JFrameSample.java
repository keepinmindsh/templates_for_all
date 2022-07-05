package bong.lines;

import javax.swing.*;
import java.awt.*;

public class JFrameSample {

    public static void main(String[] args) {
        JFrame fr = new JFrame("This 프레임");
        JPanel pn = new JPanel();
        JButton[] bt = new JButton[4];

        GridLayout gl = new GridLayout(2, 2);
        pn.setLayout(gl);

        for (int i = 0; i < bt.length; i++) {
            bt[i] = new JButton("Button" + i);
            pn.add(bt[i]);
        }

        pn.add(bt[0]);
        pn.add(bt[1]);
        pn.add(bt[2]);
        pn.add(bt[3]);

        fr.setContentPane(pn);

        fr.setSize(400, 300);
        fr.setVisible(true);
    }
}