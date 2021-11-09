package bong.api.sample.model.db;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RefCursorObject {
    private final String SEQ_NO;
    private final String DPSIT_PAY_NO;
    private final String OPERATOR_NAME;
    private final String DPSIT_PAY_TRNF_DATE;
    private final String FROM_CUSTM_NO;
    private final String FROM_CUSTM_NAME;
    private final String TO_CUSTM_NO;
    private final String TO_CUSTM_NAME;
    private final String COMT;
}
