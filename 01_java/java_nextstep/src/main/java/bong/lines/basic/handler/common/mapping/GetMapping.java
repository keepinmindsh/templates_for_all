package bong.lines.basic.handler.common.mapping;

import bong.lines.basic.handler.common.mapping.HandlerMapping;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;


@Slf4j
public class GetMapping extends HandlerMapping {

    private final int KEY_ONLY = 1;
    private final int KEY_AND_VALUE = 2;
    private final int KEY = 0;
    private final int VALUE = 1;

    private final int HAS_QEURYSTRING = 1;
    private final int QEURYSTRING = 1;

    private final int URI = 1;

    public GetMapping(InputStream inputStream, OutputStream outputStream) {
        super(inputStream, outputStream);
    }

    @Override
    public BufferedReader getBufferedReaderForRequest(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    @Override
    public String readFirstLineOfRequest(BufferedReader bufferedReader) throws Exception
    {
        return bufferedReader.readLine();
    }

    @Override
    public void doProcess(String requestContent) throws Exception{
        log.debug("For Mapping Value : {}", requestContent);

        String[] request_args = requestContent.split(" ");

        if(!request_args[URI].contains("?"))
            return;

        String[] url_and_queryString = request_args[URI].split("\\?");

        if(url_and_queryString.length <= HAS_QEURYSTRING)
            return;

        if(!url_and_queryString[QEURYSTRING].contains("&"))
            return;

        String[] queryString = url_and_queryString[QEURYSTRING].split("&");

        for (String key_and_value : queryString) {

            String key = "";
            String value = "";


            if(key_and_value.split("=").length == KEY_ONLY){
                key = key_and_value.split("=")[KEY];
            }

            if(key_and_value.split("=").length == KEY_AND_VALUE){
                key = key_and_value.split("=")[KEY];
                value = key_and_value.split("=")[VALUE];
            }

            System.out.println("key = " + key);
            System.out.println("value = " + value);
        }
    }

    @Override
    public void responseHandling(OutputStream outputStream) {
        DataOutputStream dos = new DataOutputStream(outputStream);
        byte[] body = "Hello World".getBytes(StandardCharsets.UTF_8);
        response200Header(dos, body.length);
        responseBody(dos, body);
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent){
        try{
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8 \r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        }catch (Exception exception){
            log.error(exception.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body){
        try{
            dos.write(body, 0, body.length);
            dos.writeBytes("\r\n");
            dos.flush();
        }catch (Exception exception){
            exception.getMessage();
        }
    }
}
