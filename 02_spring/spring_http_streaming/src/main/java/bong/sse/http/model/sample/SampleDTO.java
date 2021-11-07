package bong.sse.http.model.sample;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SampleDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String website;
}