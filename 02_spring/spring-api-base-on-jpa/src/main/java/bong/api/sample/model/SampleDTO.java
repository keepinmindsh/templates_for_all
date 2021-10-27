package bong.api.sample.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class SampleDTO {

    private String value;

    @Getter
    @Setter
    public static class SampleRequest {
        private String value;
    }

    @Getter
    @Setter
    public static class SampleResponse{
        private Object result;
    }
}
