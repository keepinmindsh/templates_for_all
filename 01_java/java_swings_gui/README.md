# Java Swing 프로젝트 시작하기

##### New Project 생성하기

- 기본 자바 프로젝트 생성 ( gradle 및 자바 프로젝트 기반 생성 )

##### Swing Gui Form 추가 

![Crazy Java 001](https://github.com/keepinmindsh/templates_for_all/blob/main/01_java/java_swings_gui/assets/0001_crazy_java.png)

##### Main 코드 생성 

```java
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
```

> [Swing IntelliJ Coding](https://examples.javacodegeeks.com/desktop-java/ide/intellij-idea/intellij-idea-gui-designer-tutorial/)    
> [Swing IDEA with GUI](https://javawithus.com/en/how-to-create-gui-forms-in-idea-correctly/)  
> [Java Swing Sample](https://blog.naver.com/PostView.naver?blogId=codingspecialist&logNo=221658740670&categoryNo=14&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView)