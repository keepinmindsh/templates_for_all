package bong.lines.realapi.model.response.roomclean;

import bong.lines.realapi.model.response.ProcessData;
import bong.lines.realapi.model.response.roomclean.HeaderForRoomClean;
import bong.lines.realapi.model.response.roomclean.SettingDataForRoomClean;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ResponseForRoomCleanDTO {
    private final HeaderForRoomClean header;
    private final SettingDataForRoomClean settingData;
    private final ProcessData processData;
}
