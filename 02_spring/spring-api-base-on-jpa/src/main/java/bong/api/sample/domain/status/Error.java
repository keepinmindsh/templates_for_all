package bong.api.sample.domain.status;

import bong.comm.model.type.UserStatus;

public class Error implements UserStatus {
    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void setCode(int code) {

    }

    @Override
    public void setMessage(String message) {

    }
}
