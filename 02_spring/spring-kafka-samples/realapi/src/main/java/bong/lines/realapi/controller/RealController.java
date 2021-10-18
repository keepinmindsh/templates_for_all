package bong.lines.realapi.controller;

import bong.lines.realapi.command.SampleCommand;
import bong.lines.realapi.model.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;


@Api(value="Real API")
@RestController
public class RealController {

    private final SampleCommand sampleCommand;
    private final EntityManager entityManager;

    public RealController(SampleCommand sampleCommand, EntityManager entityManager) {
        this.sampleCommand = sampleCommand;
        this.entityManager = entityManager;
    }

    @PostMapping(value = "/call")
    @ApiOperation(
            value="Real API"
            , httpMethod = "POST"
            , notes = "Real API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "VENDOR_ID", value = "Vendor 구분 번호", required = true, dataType = "string", paramType = "header", defaultValue = "" ),
            @ApiImplicitParam(name = "API-KEY", value = "API Key", required = true, dataType = "string", paramType = "header", defaultValue = "" )
    })
    public Object sample(@RequestBody Param param){
        return sampleCommand.execute(param, entityManager);
    }
}
