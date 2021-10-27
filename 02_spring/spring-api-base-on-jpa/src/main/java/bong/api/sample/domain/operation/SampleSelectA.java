package bong.api.sample.domain.operation;

import bong.api.sample.model.SampleDTO;
import bong.comm.operation.Query;

public class SampleSelectA implements Query<SampleDTO.SampleResponse> {
    @Override
    public SampleDTO.SampleResponse get() {
        return null;
    }
}
