package bong.comm.model;

import bong.comm.model.type.UserStatus;
import bong.comm.model.type.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Response {
	
	private String echoToken;
	private String TimeStamp;
	private Version versionInfo;
	private int serverStatus;	
	private UserStatus userStatus;
	private Object resultData;
}
