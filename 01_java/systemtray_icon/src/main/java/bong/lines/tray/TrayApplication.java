package bong.lines.tray;

import bong.lines.tray.handler.TrayIconHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrayApplication {

    public static void main(String[] args) {

        // src/main/resources/icon 폴더 밑에 computer.png 아이콘 파일을 넘겨주고, 마우스로 올려질 때 보여질 “Example”, 그리고 더블 클릭했을 때 종료되는 코드이다.
        TrayIconHandler.registerTrayIcon(
                Toolkit.getDefaultToolkit().getImage("src/main/resources/icon/trayicon.png"),
                "Example",
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );

        // 그리고 아래와 같이 addItem() 메소드를 호출하자
        TrayIconHandler.addItem("Check Today", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // 그리고 아래와 같이 addItem() 메소드를 호출하자
        TrayIconHandler.addItem("Exit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        TrayIconHandler.displayMessage("Silentsoft", "Benefit the world !", TrayIcon.MessageType.INFO);
    }
}
