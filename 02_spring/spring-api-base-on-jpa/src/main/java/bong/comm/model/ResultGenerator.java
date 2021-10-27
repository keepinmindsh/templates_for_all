package bong.comm.model;

import bong.comm.code.ResponseType;
import bong.comm.code.ResultType;
import bong.comm.model.type.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ResultGenerator {

    private static final String ISO_8601_24H_FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static Object getResponseCode(Object data, String versionInfo) {

        Version version = new Version();
        version.setVersion(versionInfo);

        Success success = new Success();
        success.setCode(ResultType.SUCCESS_2000.value());
        success.setMessage(ResultType.SUCCESS_2000.getReasonPhrase());

        Response responseVO = getResponseVOWithData(data, success, version);

        return getResponseEntityWithVO(responseVO);
    }

    public static Object getResponseCodeWithStatus(Object data, UserStatus resultVO, String versionInfo) {

        Version version = new Version();
        version.setVersion(versionInfo);

        Response responseVO = getResponseVOWithData(data, resultVO, version);

        return getResponseEntityWithVO(responseVO);
    }

    private static Response getResponseVOWithData(Object resultData, UserStatus resultVO, Version versionVO){

        Response response = new Response();

        response.setServerStatus(ResponseType.SUCESS_200.value());

        Date date = new Date();

        SimpleDateFormat isoSf = new SimpleDateFormat(ISO_8601_24H_FULL_FORMAT);
        isoSf.setTimeZone(TimeZone.getTimeZone("UTC"));

        response.setTimeStamp(String.format("%s",isoSf.format(date)));
        response.setVersionInfo(versionVO);
        response.setResultData(resultData);
        response.setUserStatus(resultVO);

        return response;
    }

    private static Response getResponseEntityWithVO(Response responseVO) {
        return responseVO;
    }
}
