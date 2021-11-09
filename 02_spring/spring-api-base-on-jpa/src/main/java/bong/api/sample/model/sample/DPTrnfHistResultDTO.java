package bong.api.sample.model.sample;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DPTrnfHistResultDTO {
    private final String seqNo;
    private final String dpsitPayNo;
    private final String operatorName;
    private final String dpsitPayTrnfDate;
    private final String fromCustmNo;
    private final String fromCustmName;
    private final String toCustmNo;
    private final String toCustmName;
    private final String comt;
}
