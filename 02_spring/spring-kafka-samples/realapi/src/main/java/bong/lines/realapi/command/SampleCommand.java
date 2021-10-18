package bong.lines.realapi.command;

import bong.lines.realapi.model.Param;
import bong.lines.realapi.model.Response;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Component
public class SampleCommand {

    public Object execute(Param param, EntityManager entityManager){
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("PG_IF_ROOM_KEY_STD_V2.PR_IF_ISSUE_DATA")
                .registerStoredProcedureParameter(1,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(2,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(3,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(4,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(5,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(6,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(7,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(8,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(9,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(10,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(11,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(12,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(13,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(14,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(15,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(16, String.class, ParameterMode.OUT )
                .registerStoredProcedureParameter(17, String.class, ParameterMode.OUT )
                .registerStoredProcedureParameter(18, Object.class, ParameterMode.REF_CURSOR )
                .setParameter(1, param.getBsnsCode())
                .setParameter(2, param.getPropertyNo())
                .setParameter(3, param.getVendorCode())
                .setParameter(4, param.getKeyIssueType())
                .setParameter(5, param.getPosNo())
                .setParameter(6, param.getFolioNo())
                .setParameter(7, param.getPreRoomNo())
                .setParameter(8, param.getEtcValue01())
                .setParameter(9, param.getEtcValue02())
                .setParameter(10, param.getEtcValue03())
                .setParameter(11, param.getEtcValue04())
                .setParameter(12, param.getEtcValue05())
                .setParameter(13, param.getUserId())
                .setParameter(14, param.getUserIp())
                .setParameter(15, param.getLangType());

        query.execute();

        Response response = new Response();

        response.setOutYN((String)query.getOutputParameterValue(1));
        response.setOutMSG((String) query.getOutputParameterValue(2));
        response.setOutRefCursor(query.getResultList());

        return response;
    }
}
