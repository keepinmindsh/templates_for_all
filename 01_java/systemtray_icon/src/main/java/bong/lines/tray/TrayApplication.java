package bong.lines.tray;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrayApplication {

    public static void main(String[] args) {
        TrayIconHandler.registerTrayIcon(
                Toolkit.getDefaultToolkit().getImage("src/main/resources/icon/computer.png"),
                "Example",
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );
    }
}
