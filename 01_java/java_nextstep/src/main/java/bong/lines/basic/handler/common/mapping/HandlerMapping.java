package bong.lines.basic.handler.common.mapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

public abstract class HandlerMapping {

    private final InputStream inputStream;
    private final OutputStream outputStream;

    public HandlerMapping(InputStream inputStream, OutputStream outputStream){
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void process() throws Exception {
        BufferedReader bufferedReader = getBufferedReaderForRequest(inputStream);

        String firstRequestLine = readFirstLineOfRequest(bufferedReader);

        if (checkNullofRequestLine(firstRequestLine)) return;

        doProcess(bufferedReader, firstRequestLine);

        responseHandling(outputStream);
    }

    private boolean checkNullofRequestLine(String requestLine) {
        if(!Optional.ofNullable(requestLine).isPresent()){
            return true;
        }
        return false;
    }

    protected abstract BufferedReader getBufferedReaderForRequest(InputStream inputStream);

    protected abstract String readFirstLineOfRequest(BufferedReader bufferedReader) throws IOException, Exception;

    protected abstract void doProcess(BufferedReader bufferedReader, String request) throws Exception;

    protected abstract void responseHandling(OutputStream outputStream);
}
