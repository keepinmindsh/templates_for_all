package bong.lines.basic.handler.common.mapping;

import bong.lines.basic.handler.common.mapping.HandlerMapping;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;


@Slf4j
public class GetMapping extends HandlerMapping {

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
