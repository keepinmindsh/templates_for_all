package bong.lines.realapi.command;

import bong.lines.realapi.code.INTF_TYPE;
import bong.lines.realapi.command.factory.MachineProcessFactory;
import bong.lines.realapi.model.fatory.FactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Slf4j
@Component
public class MachineCommand {

    public Object execute(INTF_TYPE intfType, Object requestParam, EntityManager entityManager){
        return MachineProcessFactory.getProcess(intfType,
                FactoryBuilder.builder()
                        .requestParam(requestParam)
                        .entityManager(entityManager)
                        .build())
                            .execute();
    }
}
