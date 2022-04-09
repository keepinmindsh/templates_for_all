package bong.lines;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FilesBasic {
    public static void main(String[] args) throws Exception {
//        createFileAndPathsWithFiles();

        writeStringToFileWithFiles();
    }

    private static void writeStringToFileWithFiles() throws IOException {
        Path path = Paths.get("C:\\Temp\\rc5.log");

        String value1 = "Test Code1";
        String value2 = "Test Code2";
        String value3 = "Test Code3";

        List<String> list = Arrays.asList(value1, value2, value3);

        Files.write(path, list);

        List<String> findList = Files.readAllLines(path);

        System.out.println("findList = " + findList);
    }

    private static void createFileAndPathsWithFiles() throws IOException {
        Path fp = Paths.get("C:\\Temp\\rc5.log");

        fp = Files.createFile(fp);

        Path dp1 = Paths.get("C:\\Temp\\sample");

        dp1 = Files.createDirectory(dp1);

        Path dp2 = Paths.get("C:\\Temp\\sample2");

        dp2 = Files.createDirectory(dp2);
    }


}
