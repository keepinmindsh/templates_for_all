package bong.comm.code;

public enum ResponseType {
	SUCESS_200(200, "OK"),
	SUCESS_204(201, "No Content"),
	SUCESS_206(202, "Partial Content"),
	
	REDIRECTION_301(301, "Moved Permanently"),
	REDIRECTION_302(302, "Found"),
	REDIRECTION_303(303, "See Other"),
	REDIRECTION_304(304, "Not Modified"),
	REDIRECTION_307(307, "Temporary Redirect"),
	
	CLIENT_ERROR_400(400, "Bad Request"),
	CLIENT_ERROR_401(401, "Unauthorized"),
	CLIENT_ERROR_403(403, "Forbidden"),
	CLIENT_ERROR_404(404, "Not Found"),
	CLIENT_ERROR_405(405, "Method Not Allowed"),
	
	SERVER_ERROR_500(500, "Internel Server Error"),
	SERVER_ERROR_503(503, "Service Unablable"),
	SERVER_ERROR_504(504, "Gateway Timeout"),
	SERVER_ERROR_505(505, "HTTP Version Not Supported");
	
	private final int value;
	private final String reasonPhrase;


	ResponseType(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}


	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}
	

	/**
	 * Return the enum constant of this type with the specified numeric value.
	 * @param statusCode the numeric value of the enum to be returned
	 * @return the enum constant with the specified numeric value
	 * @throws IllegalArgumentException if this enum has no constant for the specified numeric value
	 */
	public static ResponseType valueOf(int statusCode) {
		for (ResponseType status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		
		throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}
}
