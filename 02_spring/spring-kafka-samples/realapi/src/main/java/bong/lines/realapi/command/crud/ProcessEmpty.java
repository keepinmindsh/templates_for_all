package bong.lines.realapi.command.crud;

import bong.lines.realapi.command.inf.MachineProcess;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcessEmpty  implements MachineProcess {
    @Override
    public Object execute() {
        log.warn("INTF_TYPE do not match. please check your intfType parameter");

        return "INTF_TYPE do not match. please check your intfType parameter";
    }
}
