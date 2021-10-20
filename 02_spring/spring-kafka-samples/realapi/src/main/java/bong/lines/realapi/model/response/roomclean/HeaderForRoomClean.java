package bong.lines.realapi.model.response.roomclean;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HeaderForRoomClean {
    private String machineType;
    private String companyId;
    private String bsnsCode;
    private String propertyNo;
    private String folioNo;
    private String roomNo;
    private String userId;
    private String userIp;
}
