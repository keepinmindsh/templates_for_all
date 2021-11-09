package bong.api.sample.domain.operation;

import bong.api.sample.domain.code.SampleSelectCusorMap;
import bong.api.sample.model.db.PackageResult;
import bong.api.sample.model.db.RefCursorObject;
import bong.api.sample.model.sample.DPTrnfHistResultDTO;
import bong.api.sample.model.sample.SampleDTO;
import bong.comm.operation.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class SampleSelectA implements Query<SampleDTO.SampleResponse> {

    private final SampleDTO.SampleRequest sampleRequest;
    private final EntityManager entityManager;

    @Override
    public SampleDTO.SampleResponse get() {
        return getDTTrnfHistSelect();
    }

    private SampleDTO.SampleResponse getDTTrnfHistSelect() {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("PG_IA03_SELECT.PR_IA_DPTRNF_HIST_SELECT")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(3, String.class, ParameterMode.OUT )
                .registerStoredProcedureParameter(4, String.class, ParameterMode.OUT )
                .registerStoredProcedureParameter(5, Object.class, ParameterMode.REF_CURSOR )

                .setParameter(1, sampleRequest.getDpsitPayNo())
                .setParameter(2, sampleRequest.getLangTypeCode());

        query.execute();

        PackageResult packageResult = new PackageResult();

        String outYn = (String)query.getOutputParameterValue(4);
        String outMSG = (String) query.getOutputParameterValue(5);

        if("Y".equals(outYn)){
            packageResult.setOutYN(outYn);
            packageResult.setOutMSG(outMSG);
            packageResult.setOutRefCursor(query.getResultList());

            log.info("PackageResult : {}", packageResult.toString());

            RefCursorObject refCursorObject = getQueryResultForDPTrnfHist(packageResult);

            return getResponseDataForTRNFHistory(sampleRequest, refCursorObject);
        }else {
            return SampleDTO.SampleResponse
                    .builder()
                    .dpTrnfHistResultDTO(null)
                    .build();
        }
    }

    private SampleDTO.SampleResponse getResponseDataForTRNFHistory(SampleDTO.SampleRequest sampleRequest, RefCursorObject refCursorObject) {
        return SampleDTO.SampleResponse
                .builder()
                .dpTrnfHistResultDTO(DPTrnfHistResultDTO.builder()
                        .seqNo(refCursorObject.getSEQ_NO())
                        .dpsitPayNo(refCursorObject.getDPSIT_PAY_NO())
                        .dpsitPayTrnfDate(refCursorObject.getDPSIT_PAY_TRNF_DATE())
                        .fromCustmNo(refCursorObject.getFROM_CUSTM_NO())
                        .fromCustmName(refCursorObject.getFROM_CUSTM_NAME())
                        .toCustmNo(refCursorObject.getTO_CUSTM_NO())
                        .fromCustmName(refCursorObject.getFROM_CUSTM_NAME())
                        .build()).build();
    }

    private RefCursorObject getQueryResultForDPTrnfHist(PackageResult response) {
        RefCursorObject refCursorObject = null;

        if(Optional.ofNullable(response.getOutRefCursor()).isPresent()){
            List queryList = response.getOutRefCursor();

            Object[] list = (Object[])queryList.get(0) ;

            refCursorObject = RefCursorObject.builder()
                    .SEQ_NO(String.valueOf(Optional.ofNullable(list[SampleSelectCusorMap.SEQ_NO.getValue()]).orElse("")))
                    .DPSIT_PAY_NO(String.valueOf(Optional.ofNullable(list[SampleSelectCusorMap.DPSIT_PAY_NO.getValue()]).orElse("")))
                    .DPSIT_PAY_TRNF_DATE(String.valueOf(Optional.ofNullable(list[SampleSelectCusorMap.DPSIT_PAY_TRNF_DATE.getValue()]).orElse("")))
                    .OPERATOR_NAME(String.valueOf(Optional.ofNullable(list[SampleSelectCusorMap.OPERATOR_NAME.getValue()]).orElse("")))
                    .FROM_CUSTM_NO(String.valueOf(Optional.ofNullable(list[SampleSelectCusorMap.FROM_CUSTM_NO.getValue()]).orElse("")))
                    .FROM_CUSTM_NAME(String.valueOf(Optional.ofNullable(list[SampleSelectCusorMap.FROM_CUSTM_NAME.getValue()]).orElse("")))
                    .TO_CUSTM_NO(String.valueOf(Optional.ofNullable(list[SampleSelectCusorMap.TO_CUSTM_NO.getValue()]).orElse("")))
                    .TO_CUSTM_NAME(String.valueOf(Optional.ofNullable(list[SampleSelectCusorMap.TO_CUSTM_NAME.getValue()]).orElse("")))
                    .COMT(String.valueOf(Optional.ofNullable(list[SampleSelectCusorMap.COMT.getValue()]).orElse("")))
                    .build();
        }

        return refCursorObject;
    }
}
