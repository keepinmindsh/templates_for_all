package bong.lines.real.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PostVO {

    @Getter
    @Setter
    @ToString
    public static class RequestVO {

        private String value1;
        private String value2;
    }
}
