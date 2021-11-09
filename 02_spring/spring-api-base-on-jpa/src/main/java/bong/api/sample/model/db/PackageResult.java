package bong.api.sample.model.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PackageResult {
    private String outYN;
    private String outMSG;
    private List<Object> outRefCursor;
}
