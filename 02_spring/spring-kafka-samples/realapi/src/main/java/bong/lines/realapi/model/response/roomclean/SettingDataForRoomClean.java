package bong.lines.realapi.model.response.roomclean;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SettingDataForRoomClean {
    private String vendorCode;
    private String seperatorPropertyYn;
    private String propertyValue;
    private String seperatorZoneTypeYn;
    private String zoneTypeValue;
    private String seperatorFloorYn;
    private String floorValue;
    private String websocketIPAddress;
    private String websocketPortNo;
    private String interfaceMethod;
    private String setValue_01;
    private String setValue_02;
    private String setValue_03;
    private String setValue_04;
    private String setValue_05;
    private String setValue_06;
    private String setValue_07;
    private String setValue_08;
    private String setValue_09;
    private String setValue_10;
}
