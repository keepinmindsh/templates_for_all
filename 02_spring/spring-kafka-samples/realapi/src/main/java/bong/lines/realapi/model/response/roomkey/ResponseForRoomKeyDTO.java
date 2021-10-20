package bong.lines.realapi.model.response.roomkey;

import bong.lines.realapi.model.response.ProcessData;
import bong.lines.realapi.model.response.roomclean.HeaderForRoomClean;
import bong.lines.realapi.model.response.roomclean.SettingDataForRoomClean;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ResponseForRoomKeyDTO {
    private final HeaderForRoomKey header;
    private final SettingDataForRoomKey settingData;
    private final ProcessData processData;
}
