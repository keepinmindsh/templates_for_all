package bong.api.sample.domain.code;

import bong.comm.model.ResultGenerator;
import lombok.Getter;


@Getter
public enum ResultType {
    SUCCESS_2000(2000,"Success!");

    private final int value;
    private final String message;

    ResultType(int value, String message){
        this.value = value;
        this.message = message;
    }
}
