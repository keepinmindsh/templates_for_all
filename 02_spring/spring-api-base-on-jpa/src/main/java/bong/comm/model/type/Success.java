package bong.comm.model.type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Success implements UserStatus {
	
	private int code;
	private String message;
}
