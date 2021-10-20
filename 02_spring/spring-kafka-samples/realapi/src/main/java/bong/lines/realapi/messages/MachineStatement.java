package bong.lines.realapi.messages;

import bong.lines.realapi.code.INTF_TYPE;
import bong.lines.realapi.command.SampleCommand;
import bong.lines.realapi.model.request.RoomCleanParam;
import bong.lines.realapi.model.request.RoomKeyParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;


@Api(value="Real API")
@RestController
@CrossOrigin(origins = "http://localhost:9090/") // 컨트롤러에서 설정
public class MachineStatement {

    private final SampleCommand sampleCommand;
    private final EntityManager entityManager;

    public MachineStatement(SampleCommand sampleCommand, EntityManager entityManager) {
        this.sampleCommand = sampleCommand;
        this.entityManager = entityManager;
    }

    @PostMapping(value = "/roomclean/statement")
    @ApiOperation(
            value="Real API"
            , httpMethod = "POST"
            , notes = "Real API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "VENDOR_ID", value = "Vendor 구분 번호", required = true, dataType = "string", paramType = "header", defaultValue = "" ),
            @ApiImplicitParam(name = "API-KEY", value = "API Key", required = true, dataType = "string", paramType = "header", defaultValue = "" )
    })
    public Object sample(@RequestBody RoomCleanParam roomCleanParam){
        return sampleCommand.execute(INTF_TYPE.ROOM_CLEAN, roomCleanParam, entityManager);
    }


    @PostMapping(value = "/roomkey/statement")
    @ApiOperation(
            value="Real API"
            , httpMethod = "POST"
            , notes = "Real API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "VENDOR_ID", value = "Vendor 구분 번호", required = true, dataType = "string", paramType = "header", defaultValue = "" ),
            @ApiImplicitParam(name = "API-KEY", value = "API Key", required = true, dataType = "string", paramType = "header", defaultValue = "" )
    })
    public Object sample(@RequestBody RoomKeyParam roomKeyParam){
        return sampleCommand.execute(INTF_TYPE.ROOM_KEY, roomKeyParam, entityManager);
    }

}
