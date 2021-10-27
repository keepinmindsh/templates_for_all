package bong.api.sample.domain.factory;

import bong.api.sample.domain.code.SampleSelectType;
import bong.api.sample.domain.factory.builder.FactoryBuilder;
import bong.api.sample.domain.operation.SampleSelectA;
import bong.api.sample.domain.operation.SampleSelectB;
import bong.comm.operation.Query;

public class SampleFactory {
    public static Query get(SampleSelectType sampleSelectType, FactoryBuilder factoryBuilder) throws Exception{
        switch (sampleSelectType){
            case A:
                return new SampleSelectA();
            case B:
                return new SampleSelectB();
            default:
                throw new Exception();
        }
    }
}
