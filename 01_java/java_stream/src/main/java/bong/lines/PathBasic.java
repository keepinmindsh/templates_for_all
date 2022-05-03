package bong.lines;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathBasic {
    public static void main(String[] args) {
        Path path = Paths.get("D:\\GIT\\templates_for_all\\01_java\\java_stream\\rc4.log");

        System.out.println("path.toAbsolutePath() = " + path.toAbsolutePath());
    }
}
