package bong.api.sample.domain.code;

import lombok.Getter;

@Getter
public enum SampleSelectCusorMap {
    SEQ_NO(0),
    DPSIT_PAY_NO(1),
    OPERATOR_NAME(2),
    DPSIT_PAY_TRNF_DATE(3),
    FROM_CUSTM_NO(4),
    FROM_CUSTM_NAME(5),
    TO_CUSTM_NO(6),
    TO_CUSTM_NAME(7),
    COMT(8);

    private final int value;

    SampleSelectCusorMap(int value){
        this.value = value;
    }

}
