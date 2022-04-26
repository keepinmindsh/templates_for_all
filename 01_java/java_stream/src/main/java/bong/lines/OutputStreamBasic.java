package bong.lines;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class OutputStreamBasic {
    public static void main(String[] args) {
        OutputStream outputStream = null;
        try {
             outputStream = new FileOutputStream("D:\\GIT\\templates_for_all\\01_java\\java_stream\\rc4.log");

            String content = "Value";

            outputStream.write(content.getBytes(StandardCharsets.UTF_8));

            outputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
