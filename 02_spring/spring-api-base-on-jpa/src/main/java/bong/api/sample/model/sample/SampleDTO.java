package bong.api.sample.model.sample;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class SampleDTO {

    private final String dpsitPayNo;
    private final String langTypeCode;

    @Getter
    @Setter
    public static class SampleRequest {
        private String dpsitPayNo;
        private String langTypeCode;
    }

    @Getter
    @Builder
    public static class SampleResponse{
        private DPTrnfHistResultDTO dpTrnfHistResultDTO;
    }
}
