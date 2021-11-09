package bong.api.sample.domain.factory;

import bong.api.sample.domain.code.SampleSelectType;
import bong.api.sample.domain.factory.builder.FactoryBuilder;
import bong.api.sample.domain.operation.SampleSelectA;
import bong.api.sample.domain.operation.SampleSelectB;
import bong.api.sample.model.sample.SampleDTO;
import bong.comm.operation.Query;

public class SampleFactory {
    public static Query get(SampleSelectType sampleSelectType, FactoryBuilder factoryBuilder) throws Exception{
        switch (sampleSelectType){
            case A:
                return new SampleSelectA((SampleDTO.SampleRequest)factoryBuilder.getRequestParam(), factoryBuilder.getEntityManager());
            case B:
                return new SampleSelectB();
            default:
                throw new Exception();
        }
    }
}
