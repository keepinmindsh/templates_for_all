package bong.lines.producer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KeyDTO {

    @Setter
    @Getter
    @ToString
    public static class Request {
        private String machineType;
        private String companyId;
        private String bsnsCode;
        private String propertyNo;
        private String folioNo;
        private String roomNo;
        private String posNo;
        private String vendorId;
        private String jobType;
    }
}
