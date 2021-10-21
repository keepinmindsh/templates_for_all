package bong.lines.realapi.command.factory;

import bong.lines.realapi.code.INTF_TYPE;
import bong.lines.realapi.command.crud.ProcessEmpty;
import bong.lines.realapi.command.crud.ProcessRoomClean;
import bong.lines.realapi.command.crud.ProcessRoomKey;
import bong.lines.realapi.command.inf.MachineProcess;
import bong.lines.realapi.model.fatory.FactoryBuilder;
import bong.lines.realapi.model.request.RoomCleanParam;
import bong.lines.realapi.model.request.RoomKeyParam;

public class MachineProcessFactory {
    public static MachineProcess getProcess(INTF_TYPE intfType, FactoryBuilder factoryBuilder){
        switch (intfType){
            case ROOM_CLEAN:
                return new ProcessRoomClean((RoomCleanParam) factoryBuilder.getRequestParam(), factoryBuilder.getEntityManager());
            case ROOM_KEY:
                return new ProcessRoomKey((RoomKeyParam) factoryBuilder.getRequestParam(), factoryBuilder.getEntityManager());
            default:
                return new ProcessEmpty();
        }
    }
}
