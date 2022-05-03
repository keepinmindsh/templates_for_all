package bong.lines;

import java.io.*;

public class InputStreamBasic {

    private static byte[] readByte = new byte[100];

    public static void main(String[] args) {
        try {
            InputStream inputStream = new FileInputStream("D:\\GIT\\templates_for_all\\01_java\\java_stream\\rc4.log");

            int readInt;

            basicSkip(inputStream);

            basicAvailable(inputStream);

            basicReadByte(inputStream);

//            basicMarkReset(inputStream);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void basicSkip(InputStream inputStream) {
        try {
            inputStream.skip(1000);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void basicAvailable(InputStream inputStream) {
        try {
            int available = inputStream.available();

            System.out.println("available = " + available);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void basicMarkReset(InputStream inputStream) throws IOException {
        int readInt;
        // TODO - Mark, Reset을 지원하는 구현체를 사용해야함.
        inputStream.mark(1000);
        inputStream.reset();

        while ((readInt = inputStream.read(readByte)) != -1){
            String value = new String(readByte);

            System.out.print(value);
        }
    }

    private static void basicReadByte(InputStream inputStream) throws IOException {
        int readInt;
        while ((readInt = inputStream.read(readByte)) != -1) {
            String value = new String(readByte);

            System.out.print(value);
        }
    }
}
