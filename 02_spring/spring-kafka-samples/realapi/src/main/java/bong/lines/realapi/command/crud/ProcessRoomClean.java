package bong.lines.realapi.command.crud;

import bong.lines.realapi.command.inf.MachineProcess;
import bong.lines.realapi.model.db.PackageResult;
import bong.lines.realapi.model.db.RefCursorObject;
import bong.lines.realapi.model.request.RoomCleanParam;
import bong.lines.realapi.model.response.ProcessData;
import bong.lines.realapi.model.response.roomclean.HeaderForRoomClean;
import bong.lines.realapi.model.response.roomclean.ResponseForRoomCleanDTO;
import bong.lines.realapi.model.response.roomclean.SettingDataForRoomClean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ProcessRoomClean implements MachineProcess<Object> {

    private final RoomCleanParam roomCleanParam;
    private final EntityManager entityManager;

    @Override
    public Object execute() {
        return processForRoomClean();
    }


    private Object processForRoomClean() {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("PG_IF_ROOM_CLEAN_STD_V2.PR_SEND_DATA")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(6, String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(7, String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(8, String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(9, String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(10,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(11,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(12,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(13,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(14,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(15,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(16,String.class, ParameterMode.IN )
                .registerStoredProcedureParameter(17, String.class, ParameterMode.OUT )
                .registerStoredProcedureParameter(18, String.class, ParameterMode.OUT )
                .registerStoredProcedureParameter(19, RefCursorObject.class, ParameterMode.REF_CURSOR )

                .setParameter(1, roomCleanParam.getCompanyId())
                .setParameter(2, roomCleanParam.getBsnsCode())
                .setParameter(3, roomCleanParam.getPropertyNo())
                .setParameter(4, roomCleanParam.getVendorCode())
                .setParameter(5, roomCleanParam.getIntfType().getValue())
                .setParameter(6, roomCleanParam.getRoomNo())
                .setParameter(7, roomCleanParam.getFolioNo())
                .setParameter(8, roomCleanParam.getZoneTypeCode())
                .setParameter(9, roomCleanParam.getEtcValue01())
                .setParameter(10, roomCleanParam.getEtcValue02())
                .setParameter(11, roomCleanParam.getEtcValue03())
                .setParameter(12, roomCleanParam.getEtcValue04())
                .setParameter(13, roomCleanParam.getEtcValue05())
                .setParameter(14, roomCleanParam.getUserId())
                .setParameter(15, roomCleanParam.getUserIp())
                .setParameter(16, roomCleanParam.getLangType());

        query.execute();

        PackageResult packageResult = new PackageResult();

        String outYn = (String)query.getOutputParameterValue(17);
        String outMSG = (String) query.getOutputParameterValue(18);

        if("Y".equals(outYn)){
            packageResult.setOutYN(outYn);
            packageResult.setOutMSG(outMSG);
            packageResult.setOutRefCursor(query.getResultList());

            log.info("PackageResult : {}", packageResult.toString());

            RefCursorObject refCursorObject = getQueryResultForRoomClean(packageResult);

            return getResponseDataForRoomClean(roomCleanParam, refCursorObject);
        }else {
            return ResponseForRoomCleanDTO.builder()
                    .processData(null)
                    .header(null)
                    .settingData(null)
                    .build();
        }
    }

    private ResponseForRoomCleanDTO getResponseDataForRoomClean(RoomCleanParam roomCleanParam, RefCursorObject refCursorObject) {
        ResponseForRoomCleanDTO responseForRoomCleanDTO = ResponseForRoomCleanDTO.builder()
                .header(HeaderForRoomClean.builder()
                        .machineType(refCursorObject.getPC_CTRL_INTF_TYPE_CODE())
                        .bsnsCode(roomCleanParam.getBsnsCode())
                        .propertyNo(roomCleanParam.getPropertyNo())
                        .folioNo(roomCleanParam.getFolioNo())
                        .roomNo(roomCleanParam.getRoomNo())
                        .userId(roomCleanParam.getUserId())
                        .userIp(roomCleanParam.getUserIp())
                        .build())
                .settingData(SettingDataForRoomClean.builder()
                        .vendorCode(refCursorObject.getINTF_VENDOR_CODE())
                        .seperatorPropertyYn(refCursorObject.getSEPR_PROPERTY_YN())
                        .propertyValue(refCursorObject.getPROPERTY_VALUE())
                        .seperatorZoneTypeYn(refCursorObject.getSEPR_ZONE_YN())
                        .floorValue(refCursorObject.getFLOOR_VALUE())
                        .websocketIPAddress(refCursorObject.getINST_PC_IP_ADDR())
                        .websocketPortNo(refCursorObject.getINST_PC_POSR_NO())
                        .interfaceMethod(refCursorObject.getINTF_METHD_CODE())
                        .setValue_01(refCursorObject.getSET_VALUE_01())
                        .setValue_02(refCursorObject.getSET_VALUE_02())
                        .setValue_03(refCursorObject.getSET_VALUE_03())
                        .setValue_04(refCursorObject.getSET_VALUE_04())
                        .setValue_05(refCursorObject.getSET_VALUE_05())
                        .setValue_06(refCursorObject.getSET_VALUE_06())
                        .setValue_07(refCursorObject.getSET_VALUE_07())
                        .setValue_08(refCursorObject.getSET_VALUE_08())
                        .setValue_09(refCursorObject.getSET_VALUE_09())
                        .setValue_10(refCursorObject.getSET_VALUE_10())
                        .build())
                .processData(ProcessData.builder()
                        .processData01(refCursorObject.getREQUEST_DATA_01())
                        .processData01(refCursorObject.getREQUEST_DATA_02())
                        .processData01(refCursorObject.getREQUEST_DATA_03())

                        .build())
                .build();

        log.info("ResponseDTO : {}", responseForRoomCleanDTO.toString());

        return responseForRoomCleanDTO;
    }

    private RefCursorObject getQueryResultForRoomClean(PackageResult response) {
        RefCursorObject refCursorObject = null;

        if(Optional.ofNullable(response.getOutRefCursor()).isPresent()){
            List queryList = response.getOutRefCursor();

            Object[] list = (Object[])queryList.get(0) ;

            int array = 0;

            refCursorObject = RefCursorObject.builder()
                    .INTF_NO(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .PC_CTRL_INTF_TYPE_CODE(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .INTF_VENDOR_CODE(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .INTF_VENDOR_NAME(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .INTF_NAME(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .INTF_METHD_CODE(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .INTF_METHD_NAME(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SPEC_PC_YN(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SEQ_NO(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SEPR_PROPERTY_YN(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .PROPERTY_VALUE(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SEPR_ZONE_YN(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .ZONE_VALUE(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SEPR_FLOOR_YN(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .FLOOR_VALUE(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .INST_PC_IP_ADDR(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .INST_PC_POSR_NO(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SET_VALUE_01(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SET_VALUE_02(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SET_VALUE_03(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SET_VALUE_04(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SET_VALUE_05(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SET_VALUE_06(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SET_VALUE_07(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SET_VALUE_08(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SET_VALUE_09(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .SET_VALUE_10(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .REQUEST_DATA_01(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .REQUEST_DATA_02(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .REQUEST_DATA_03(String.valueOf(Optional.ofNullable(list[array++]).orElse("")))
                    .build();
        }

        return refCursorObject;
    }
}
