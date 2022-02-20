
/**
 *
 * @author		: hoonui Jeong
 *
 * @param		: String
 * @description	: undefined, NULL 값 ''값으로 반환
 *
 */
export const nullToBlank = (message: string) => {
    message = message + '';

    if (message == 'undefined' || message == 'NULL' || message == 'null') {
        message = '';
    }

    return message.trim();
}