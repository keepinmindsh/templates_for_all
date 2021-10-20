package bong.lines.realapi.model.response.roomkey;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HeaderForRoomKey {
    private String machineType;
    private String companyId;
    private String bsnsCode;
    private String propertyNo;
    private String folioNo;
    private String roomNo;
    private String userId;
    private String userIp;
}
