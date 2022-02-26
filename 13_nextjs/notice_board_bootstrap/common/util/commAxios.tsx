import axios from 'axios';
import {nullToBlank} from './utils';

const API_HOST = process.env.NEXT_PUBLIC_API_HOST;
const API_CONTEXT = process.env.NEXT_PUBLIC_API_CONTEXT;
const API_SUB_CONTEXT = process.env.NEXT_PUBLIC_API_SUB_CONTEXT;
const CONNECTION_TIMEOUT = 90000;

const cancelTokenSource = axios.CancelToken.source();

const options = {
    method : 'POST',
    timeout : CONNECTION_TIMEOUT,
    responseType : 'JSON',
    responseEncoding : 'utf8',
}

export const api_call = (options, params, callback, errorCallback) => {
    let method = 'GET';

    if(nullToBlank(options.method) !== ''){
        method = options.method;
    }

    const sendOptions = initHeader(method, options, params);

    axios(
        sendOptions
    ).then((response) => {
        let result = response.data;

        if(result.serverStatus === 200) {
            if (nullToBlank(result.userStatus) !== "") {
                if (result.userStatus.code === 2000) {
                    if (typeof callback === "function") {
                        callback(result.resultData, result);
                    }
                } else {
                    if(typeof errorCallback === "function"){
                        errorCallback(result);
                    }
                }
            } else {
                if(typeof errorCallback === "function"){
                    errorCallback(result);
                }
            }
        }else{
            if(typeof errorCallback === "function"){
                errorCallback(result);
            }
        }
    }).catch((error) => {
        if(typeof errorCallback === "function") {
            errorCallback(error);
        }
    }).finally(() => {

    });
}

const initHeader = (method, opt, params) => {
    let systemId = '/';
    if(nullToBlank(params.systemId) !== ''){
        systemId = '/' + params.systemId;
    }

    if(opt.type === 'login') {
        options.url = 'https://wingsapi.sanhait.com/is/v1/is' + opt.url;
        //options.url = 'http://localhost:11900/v1/is' + opt.url;
    }else{
        options.url = API_HOST + API_CONTEXT + '/' + API_SUB_CONTEXT + systemId + opt.url;
    }

    options.method = method;
    options.data = params;
    options.withCredentials = true;

    if(nullToBlank(opt.responseType) != ""){
        options.responseType = opt.responseType;
    }else{
        options.responseType = 'JSON';
    }

    if(nullToBlank(opt.headers) !== ""){
        options.headers = opt.headers;
    }

    if(opt.timeout != null && opt.timeout != 'undefined' && opt.timeout != "") {
        options.timeout = opt.timeout;
    }

    options.cancelToken = cancelTokenSource.token;

    return options;
}


export function convertFormData(object, formData){
    if(formData == null || formData == '' || formData == 'undefined') {
        formData = new FormData();
    }

    Object.keys(object).forEach(key => {
        if(!object[key] && key){
            formData.append(key, '');
        }else{
            formData.append(key, object[key]);
        }
    });

    return formData;
}

export function paramSerialize(params){
    //return JSON.stringify(params).replace(/,/g, String.fromCharCode(20));
    return JSON.stringify(params);
}
