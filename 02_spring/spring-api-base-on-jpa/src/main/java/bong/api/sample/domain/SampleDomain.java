package bong.api.sample.domain;


import bong.api.sample.SampleVersionConfig;
import bong.api.sample.domain.code.SampleSelectType;
import bong.api.sample.domain.factory.SampleFactory;
import bong.api.sample.domain.factory.builder.FactoryBuilder;
import bong.api.sample.model.SampleDTO;
import bong.comm.model.ResultGenerator;
import bong.comm.operation.Query;
import bong.starter.SpringBootStarter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class SampleDomain {

    private final EntityManager entityManager;
    private final SampleVersionConfig apiConfig;

    public Object execute(SampleDTO.SampleRequest sampleRequest) throws Exception {
        Query<SampleDTO.SampleResponse> getMyData = SampleFactory.get(SampleSelectType.A, FactoryBuilder
                .builder()
                .requestParam(sampleRequest)
                .build());


        return ResultGenerator.getResponseCode(getMyData.get(), apiConfig.getInfo()) ;
    }
}
