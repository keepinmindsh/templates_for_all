package com.sanha.lines;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Sample2 {
    private Sheets service;
    private final String APPLICATION_NAME = "Google Sheets API Java YenaWorld";
    private final JsonFactory JSON_FACTORY = null; //JacksonFactory.getDefaultInstance();
    private HttpTransport HTTP_TRANSPORT;
    private final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);

    {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            service = getSheetsService();
        } catch (Throwable t) {
            //log.error("## googleHttpTransportInit Throwable: {}", t);
            System.exit(1);
        }
    }


    private Credential authorize() throws IOException {
        GoogleCredential googleCredential = GoogleCredential
                .fromStream(Sample2.class.getResourceAsStream("/credential.json"))
    .createScoped(SCOPES);
        // className 에 해당 class 명 삽입
        // resources/credential/credential.json 에 인증키 삽입

        //log.info("## init credential");

        return googleCredential;
    }


    private Sheets getSheetsService() throws IOException {
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }


    public Boolean importSheet() {
        final String sheetId = "11ipWOYAFJn49u1Mjfg6vw4ebjg3N-gnqY_k84Lx-nYc"; // 구글 시트 url 에서 /d/ 와 /edit 사이 문자
        final String sheetName = "sample"; // 시트 이름 (시트 아래 이름)
        final String sheetRange= "B2"; // 시트 읽을 위치 ex) A1:S

        try{
            ValueRange response = service.spreadsheets().values()
                    .get(sheetId , sheetName  + "!" + sheetRange).execute();

            List<List<Object>> values = response.getValues();
            if (values == null || values.size() == 0) {
                //log.info("## importSheet No data found.");
            } else {
                for (List row : values) {
                    String text = row.get(0).toString();
                    // row index data read 로직
                    System.out.println("text = " + text);

                }
            }
        } catch (Exception e){
            e.printStackTrace();
            //log.error("## importSheet exception: {}", e);
            return false;
        }

        return true;
    }
}
