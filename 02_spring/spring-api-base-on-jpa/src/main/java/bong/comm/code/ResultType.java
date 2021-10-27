package bong.comm.code;

// API 응답 코드 관리
public enum ResultType {
	SUCCESS(0000, "정상 처리 되었습니다."),	
	
	// 파라미터 에러코드
	COMM_PARMATER_FAIL(1000, "공통 파라미터가 누락되었습니다."),
	
	// 권한 에러코드
	URL_ACCESS_AUTH_FAIL(1300, "해당 URL 접근 권한이 없습니다."),						// Client에서 API-KEY 미 전송
	AUTH_FAIL(1400, "권한 인증 실패"),													// Client에서 API-KEY 미 전송
	AUTH_API_KEY_CLIENT_MISSING(1401, "권한 인증 실패"),								// Client에서 API-KEY 미 전송
	AUTH_API_KEY_SERVER_ERROR(1402, "권한 인증 실패"),								// Server에서 API-KEY 없음
	AUTH_API_NOT_EQUAL(1403, "권한 인증 실패"),										// Client와 Server에 등록된 API-KEY가 다름 

	SUCCESS_2000(2000, "OK"),
	USER_WARING_CODE_3000(3000, "User Exception Error"),						// 각 모듈별 기본 사용자 예외 코드 
	PARAMETER_ALERT_CODE_4000(4000, "Paramter Not Matched"),						// 각 모듈별 파라미터 예외 코드 
	INTERNAL_ALERT_5000(5000, "Internel Server Error"),							// 각 모둘별 내부 서버 예외 코드
	AUTH_ALERT_6000(6000, "Authentication Alert"),								// 각 모둘별 권한 서버 예외 코드
	DB_ALERT_7000(7000, "Data Base Alert"),										// 각 모둘별 DataBase 서버 예외 코드
	
	// Exception 공통 에러 코드
	DEFAULT_EXCEPTION_ERROR(9000, "Error during process"),					// Exception 오류 기본 오류 포맷
	NO_SEARCH_FOUND_DATA_ERROR(9001, "No Data Found"),					// 검색된 데이터가 없습니다.
	FILE_NOT_FOUND_ERROR(9002, "File Not Found");							// 파일을 찾을 수 없습니다.
			
	
	private final int value;
	private final String reasonPhrase;


	ResultType(int value, String reasonPhrase) {
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
	public static ResultType valueOf(int statusCode) {
		for (ResultType status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		
		throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}
}
