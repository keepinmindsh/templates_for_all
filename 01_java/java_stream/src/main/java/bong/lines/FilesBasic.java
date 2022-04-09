package bong.lines;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FilesBasic {
    public static void main(String[] args) throws Exception {
//        createFileAndPathsWithFiles();

//        writeStringToFileWithFiles();

//        readAllStringWithFiles();

//        readAllStringWithFilesLines();

        writeStringToFileWithFilesSecondway();
    }

    private static void writeStringToFileWithFilesSecondway() throws IOException {
        String path = "C:\\Temp\\rc5.log";

        Files.writeString(Paths.get(path), "안녕하세요", StandardCharsets.UTF_8);
    }

    private static void readAllStringWithFilesLines() {
        String path = "C:\\Temp\\rc5.log";

        try (Stream<String> lines = Files.lines(Paths.get(path), StandardCharsets.UTF_8) ) {

            StringBuilder stringBuilder = new StringBuilder();
            lines.forEach(s -> stringBuilder.append(s).append("\n"));
            System.out.println("stringBuilder = " + stringBuilder);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void readAllStringWithFiles() {
        String path = "C:\\Temp\\rc5.log";

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {

            StringBuilder stringBuilder = new StringBuilder();
            String string = null;
            while ((string = bufferedReader.readLine()) != null) {
                stringBuilder.append(string).append("\n");
            }
            System.out.println("stringBuilder = " + stringBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
