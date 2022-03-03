const USER_INFO_KEY = 'LOGIN_INFO';
const SESSION_TIME = 720;
const COOKIE_CHANGE_KEY = 'COOKIE_CHANGE_TIME';
export const LOGOUT_TYPE_KEY = 'LOGOUT_TYPE_KEY';

/**
 *
 * @author		: hoonui Jeong
 *
 * @param		: String (변경할 날짜형식)
 * @description	: dateFormat
 *
 */
Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";

    let weekName = ['일', '월', '화', '수', '목', '금', '토'];
    let d = this;
    let h;

    return f.replace(/(YYYY|YY|yyyy|yy|MM|DD|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "YYYY": case "yyyy": return d.getFullYear();
            case "YY": case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "DD": case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};

String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

/**
 *
 * @author		: hoonui Jeong
 *
 * @param		: String
 * @description	: Cookie 사용자 정보 저장
 *
 */
export const setCookieUserInfo = (value) => {

    let date = new Date();
    date.setMinutes(date.getMinutes() + SESSION_TIME);

    if (typeof document !== 'undefined') {
        //date.setTime(date.getTime() + 1000 * 60 * 30);
        document.cookie = USER_INFO_KEY + '=' + encodeURIComponent(value) + ';expires=' + date.toUTCString() + ';path=/';
    }

    let changeTime = new Date().format('YYYYMMDDHHmmss');

    saveLocalStorage(COOKIE_CHANGE_KEY, changeTime);
    removeLocalStorage(LOGOUT_TYPE_KEY);

    return changeTime;
};


/**
 *
 * @author		: hoonui Jeong
 *
 * @param		: String
 * @description	: Cookie 정보 가져오기 (Decode)
 *
 */
export const getCookie = (name) => {
    let value;

    if (typeof document !== 'undefined') {
        value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    }

    return value? decodeURIComponent(value[2]) : "{}";
};




/**
 *
 * @author		: hoonui Jeong
 *
 * @param		: String
 * @description	: Cookie 정보 가져오기
 *
 */
export const getBasicCookie = (name) => {
    let value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');

    return value? value[2] : null;
};

/**
 *
 * @author		: hoonui Jeong
 *
 * @param		: String
 * @description	: Cookie 저장되어있는 사용자 정보 제거
 *
 */
export const removeUserCookie = (userLogOut) => {
    let date = new Date();

    if (typeof document !== 'undefined') {
        //date.setTime(date.getTime() + 1000 * 60 * 30);
        document.cookie = USER_INFO_KEY + "= " + "; expires=" + date.toUTCString() + "; path=/";
    }

    removeLocalStorage(COOKIE_CHANGE_KEY);

    if(userLogOut === 'Y'){
        saveLocalStorage(LOGOUT_TYPE_KEY, 'Y');
    }
}

/**
 *
 * @author		: hoonui Jeong
 *
 * @param		: String, String
 * @description	: 로컬 스토리지 저장 함수
 *
 */
export const saveLocalStorage = (key, value) => {
    localStorage.setItem(key, value);
}

/**
 *
 * @author		: hoonui Jeong
 *
 * @param		: String, String
 * @description	: 로컬 스토리지 삭제 함수
 *
 */
export const removeLocalStorage = (key) => {
    localStorage.removeItem(key);
}

/**
 *
 * @author		: hoonui Jeong
 *
 * @param		: String
 * @description	: 로컬 스토리지 값 가져오기
 *
 */
export const loadLocalStorage = (key) => {
    if (typeof localStorage !== 'undefined') {
        return localStorage.getItem(key);
    }else{
        return "";
    }
}