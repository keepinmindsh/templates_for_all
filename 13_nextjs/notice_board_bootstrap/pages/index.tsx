import type { NextPage } from 'next'
import {useEffect, useState} from "react"
import Head from 'next/head'
import {useRouter} from 'next/router'

import {loadLocalStorage, removeLocalStorage, saveLocalStorage, setCookieUserInfo} from '../common/cookie/cookieHandle'
import { nullToBlank } from '../common/util/utils'
import {api_call, convertFormdata} from "../common/util/commAxios";

const Home: NextPage = () => {
    const router = useRouter()
    const [idSave, setIdSave] = useState(true);

    const [loginInfo, setLoginInfo] = useState({
        'COMPANY_ID'    : '',
        'USER_ID'       : '',
        'PASSWORD'      : '',

    });

    useEffect(() => {
        if(loadLocalStorage('ID_SAVE') === 'Y'){
            setIdSave(true);
            setLoginInfo({
                ...loginInfo,
                'USER_ID' : loadLocalStorage('USER_ID'),
                'COMPANY_ID' : loadLocalStorage('COMPANY_ID')
            })
        }else{
            setIdSave(false);
        }

    }, []);

    const changeHandler = (ele) => {
        const value = ele.target.value;
        const name = ele.target.name;

        setLoginInfo({
            ...loginInfo,
            [name] : value
        })
    }


    const loginHandler = () => {
        if(nullToBlank(loginInfo.USER_ID) === '' || nullToBlank(loginInfo.PASSWORD) === '') {
            alert("로그인정보를 입력하세요.");
        }else {

            const loginHeader = {
                method: 'POST',
                url: '/login',
                type: 'login',
                headers: {
                    'Content-Type': 'application/json',
                    'API-KEY': '165f439f72ef3febda5ea2f59a4642d6a5a93d48e9df7a9971bb969cfcc8f95c',
                    'VENDOR_ID': ''
                }
            };

            let loginParams = {
                systemId: 'WINGSINTEGRATION',
                companyId: loginInfo.COMPANY_ID,
                bsnsCode: '11',
                propertyNo: '11',
                userId: loginInfo.USER_ID,
                password: loginInfo.PASSWORD,
                langTypeCode : 'KR'
            }

            api_call(loginHeader, convertFormdata(loginParams), (result, request) => {
                if(idSave){
                    saveLocalStorage("ID_SAVE", 'Y');
                    saveLocalStorage("USER_ID", loginInfo.USER_ID);
                    saveLocalStorage("COMPANY_ID", loginInfo.COMPANY_ID);
                }else{
                    removeLocalStorage('ID_SAVE');
                    removeLocalStorage('COMPANY_ID');
                    removeLocalStorage('USER_ID');
                }

                setCookieUserInfo(JSON.stringify({
                    'USER_ID'    : loginInfo.USER_ID,
                    ...result
                }));

                router.push('/main/main');
            }, (error) => {
                console.error(error)
                alert("로그인 정보가 잘못되었습니다.");
            });
        }
    }

    const handleKeyDown = (ele) => {
        if(ele.key === 'Enter'){
            loginHandler();
        }
    }

    const checkboxHandler = () => {
        setIdSave(!idSave);
    }

    return (
        <>
            <Head>
                <title>Working Progressive</title>
                <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" />
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <div className="card border-0 mt-md-5">
                <div className="container-fluid h-custom">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-md-9 col-lg-6 col-xl-5">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp" className="img-fluid" alt="Sample image" />
                        </div>
                        <div className="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                            <h1 className="login-h1 mb-4 fw-bold">SR System</h1>
                            <form>
                                <div className="form-outline mb-4">
                                    <input type="text" id="form3Example3" className="form-control form-control-lg"  value={loginInfo.COMPANY_ID} name="COMPANY_ID" onChange={changeHandler} placeholder="Company ID"/>
                                </div>
                                <div className="form-outline mb-4">
                                    <input type="email" id="form3Example3" className="form-control form-control-lg"  value={loginInfo.USER_ID} name="USER_ID"  onChange={changeHandler} autoComplete={"off"} placeholder="Email Address"/>
                                </div>

                                <div className="form-outline mb-3">
                                    <input type="password" id="form3Example4" className="form-control form-control-lg"  name="PASSWORD" value={loginInfo.PASSWORD} onChange={changeHandler} autoComplete={"off"} onKeyDown={handleKeyDown} placeholder="password"/>
                                </div>

                                <div className="d-flex justify-content-between align-items-center">
                                    <div className="form-check mb-0">
                                        <input className="form-check-input me-2" type="checkbox" value="" checked={idSave} onClick={checkboxHandler}  id="form2Example3"/>
                                        <label className="form-check-label" htmlFor="form2Example3">
                                            Remember me
                                        </label>
                                    </div>

                                </div>
                                <div className="text-center text-lg-start mt-4 pt-2">
                                    <button type="button" className="btn btn-primary btn-lg" onClick={loginHandler}  >Login
                                    </button>
                                    {/*<p className="small fw-bold mt-2 pt-1 mb-0">{"Don't have an account?"} <a href="#!" className="link-danger">Register</a>*/}
                                    {/*</p>*/}
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Home
