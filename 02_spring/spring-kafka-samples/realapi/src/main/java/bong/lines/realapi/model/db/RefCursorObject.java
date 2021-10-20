package bong.lines.realapi.model.db;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RefCursorObject {
    private final String INTF_NO;
    private final String PC_CTRL_INTF_TYPE_CODE;
    private final String INTF_VENDOR_CODE;
    private final String INTF_VENDOR_NAME;
    private final String INTF_NAME;
    private final String INTF_METHD_CODE;
    private final String INTF_METHD_NAME;
    private final String SPEC_PC_YN;
    private final String SEQ_NO;
    private final String SEPR_PROPERTY_YN;
    private final String PROPERTY_VALUE;
    private final String SEPR_ZONE_YN;
    private final String ZONE_VALUE;
    private final String SEPR_FLOOR_YN;
    private final String FLOOR_VALUE;
    private final String INST_PC_IP_ADDR;
    private final String INST_PC_POSR_NO;
    private final String SET_VALUE_01;
    private final String SET_VALUE_02;
    private final String SET_VALUE_03;
    private final String SET_VALUE_04;
    private final String SET_VALUE_05;
    private final String SET_VALUE_06;
    private final String SET_VALUE_07;
    private final String SET_VALUE_08;
    private final String SET_VALUE_09;
    private final String SET_VALUE_10;
    private final String REQUEST_DATA_01;
    private final String REQUEST_DATA_02;
    private final String REQUEST_DATA_03;
}
