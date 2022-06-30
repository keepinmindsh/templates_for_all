package bong.lines.java.basic;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class StandardInput {

    public void standardInputTest() throws IOException {
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String bufferedContent = bufferedReader.readLine();

        log.info("Input Value : {}", bufferedContent);
    }
}
