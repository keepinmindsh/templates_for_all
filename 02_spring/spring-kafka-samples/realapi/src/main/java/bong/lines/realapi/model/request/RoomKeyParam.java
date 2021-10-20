package bong.lines.realapi.model.request;

import bong.lines.realapi.code.INTF_TYPE;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class RoomKeyParam {
    private INTF_TYPE intfType;
    private String bsnsCode;
    private String propertyNo;
    private String vendorCode;
    private String keyIssueType;
    private String posNo;
    private String folioNo;
    private String etcValue01;
    private String etcValue02;
    private String etcValue03;
    private String etcValue04;
    private String etcValue05;
    private String userId;
    private String userIp;
    private String langType;
}
