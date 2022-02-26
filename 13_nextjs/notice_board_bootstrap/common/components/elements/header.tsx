import Link from "next/link";
import {getCookie} from "../../cookie/cookieHandle";
import {useEffect, useState} from "react";
import axios from "axios";


const Header = () => {

    const [ home, setHome ] = useState(false)
    const [ register, setRegister ] = useState(false)
    const [ isLink, setIsLink] = useState(false);
    const [ menus, setMenus ] = useState<[{
        menuId : string,
        menuName : string,
        useYN : string,
        authMenuId : number,
        userId : string
    }]>([
        { menuId : "000001", menuName : "신규 등록" , useYN : "Y", authMenuId : 0 , userId : "" },
        { menuId : "000002", menuName : "권한 설정" , useYN : "Y", authMenuId : 0 , userId : "" },
        { menuId : "000003", menuName : "권한 이력조회" , useYN : "Y", authMenuId : 0 , userId : "" }
    ])

    const srHostUrl:string = process.env.NEXT_PUBLIC_BACK_API_HOST

    // @ts-ignore
    const loginInfo = {
        userId : getCookie("LOGIN_INFO")? JSON.parse(getCookie("LOGIN_INFO"))["USER_ID"] : "",
        userName : ""
    }

    useEffect(() => {
       axios.get(srHostUrl + "/auth/user/menus?userId=" + loginInfo.userId)
            .then(res => {
                setIsLink(getParameterByName("screenType") == "LINK" ? true : false)
                setMenus([...res.data.authMenuByUser])
            });
    }, [])

    const onActive = (value: string) => {
        switch (value) {
            case "HOME" :
                setHome(true)
                setRegister(false)
                break;
            case "Register" :
                setHome(false)
                setRegister(true)
                break;
        }
    }

    const getParameterByName = (name, url = window.location.href) => {
        name = name.replace(/[\[\]]/g, '\\$&');
        var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }


    return (
        <>
            {isLink ?
                <></>
                :
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <div className="container-fluid">
                        <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                                aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
                        <div className="collapse navbar-collapse" id="navbarNav">
                            <ul className="navbar-nav">
                                <li className="nav-item">
                                    <Link href={{
                                        pathname: '/main/main' // array라 문자화
                                    }} >
                                        <a className={home ? "nav-link active" :  "nav-link"  }  onClick={() => onActive("Home")} href="#">홈으로</a>
                                    </Link>
                                </li>
                                {
                                    menus ?
                                        menus.map(
                                            ( item , index ) => {
                                                switch (item.menuId) {
                                                    case "0000001" :
                                                        if(item.useYN == "Y"){
                                                            return <li className="nav-item">
                                                                <Link href={{
                                                                    pathname: '/register/register',
                                                                    query: { inputType : "NEW",  assignUserId : loginInfo.userId } // array라 문자화
                                                                }} >
                                                                    <a className={register ? "nav-link active" :  "nav-link"  }  onClick={() => onActive("Register")} aria-current="page" href="#">신규 등록</a>
                                                                </Link>
                                                            </li>
                                                        }else{
                                                            return <></>
                                                        }
                                                    case "0000002" :
                                                        if(item.useYN == "Y") {
                                                            return <li className="nav-item">
                                                                <Link href={{
                                                                    pathname: '/auth/auth',
                                                                    query: {
                                                                        inputType: "NEW",
                                                                        assignUserId: loginInfo.userId
                                                                    } // array라 문자화
                                                                }}>
                                                                    <a className={register ? "nav-link active" : "nav-link"}
                                                                       onClick={() => onActive("Register")}
                                                                       aria-current="page" href="#">권한 설정</a>
                                                                </Link>
                                                            </li>
                                                        }else{
                                                            return <></>
                                                        }
                                                    case "0000003" :
                                                        if(item.useYN == "Y") {
                                                            return <li className="nav-item">
                                                                <Link href={{
                                                                    pathname: '/auth/authHistory',
                                                                    query: {
                                                                        inputType: "NEW",
                                                                        assignUserId: loginInfo.userId
                                                                    } // array라 문자화
                                                                }}>
                                                                    <a className={register ? "nav-link active" : "nav-link"}
                                                                       onClick={() => onActive("Register")}
                                                                       aria-current="page" href="#">권한 이력조회</a>
                                                                </Link>
                                                            </li>
                                                        }else{
                                                            return <></>
                                                        }
                                                    default :
                                                        return <></>
                                                }
                                            }
                                        )
                                        :<></>
                                }
                            </ul>
                        </div>
                        <Link href={{ pathname: '/' }} >
                            <button>
                                Log Out
                            </button>
                        </Link>

                    </div>
                </nav>
            }
        </>
    )

}

export default Header;