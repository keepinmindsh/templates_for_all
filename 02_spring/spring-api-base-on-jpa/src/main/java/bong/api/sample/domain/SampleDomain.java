package bong.api.sample.domain;


import bong.api.sample.SampleVersionConfig;
import bong.api.sample.domain.code.ResultType;
import bong.api.sample.domain.code.SampleSelectType;
import bong.api.sample.domain.factory.SampleFactory;
import bong.api.sample.domain.factory.builder.FactoryBuilder;
import bong.api.sample.domain.status.Error;
import bong.api.sample.model.sample.SampleDTO;
import bong.comm.model.ResultGenerator;
import bong.comm.model.type.UserStatus;
import bong.comm.operation.Query;
import bong.db.config.DBSelector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class SampleDomain {

    private final EntityManager entityManager;
    private final SampleVersionConfig apiConfig;

    public Object execute(String systemId, SampleDTO.SampleRequest sampleRequest) throws Exception {

        DBSelector.set(systemId);

        Query<SampleDTO.SampleResponse> getMyData = SampleFactory.get(SampleSelectType.A, FactoryBuilder
                .builder()
                        .entityManager(entityManager)
                        .requestParam(sampleRequest)
                .build());

        UserStatus userStatus = new Error();

        userStatus.setCode(ResultType.SUCCESS_2000.getValue());
        userStatus.setMessage(ResultType.SUCCESS_2000.getMessage());

        return ResultGenerator.getResponseCodeWithStatus(getMyData.get(), userStatus, apiConfig.getInfo());

        //return ResultGenerator.getResponseCode(getMyData.get(), apiConfig.getInfo()) ;
    }
}
