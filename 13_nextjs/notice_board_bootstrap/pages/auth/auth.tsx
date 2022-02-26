import {useState, useEffect, useRef, ChangeEvent, Key} from 'react'
import {withRouter} from 'next/router';
import Link from 'next/link'
import axios from 'axios'
import {getCookie, loadLocalStorage} from "../../common/cookie/cookieHandle";
import Header from "../../common/components/elements/header";
import {string} from "prop-types";

const Auth = ({router: {query}}) => {

    // @ts-ignore
    const hostUrl:string = process.env.NEXT_PUBLIC_API_HOST
    const srHostUrl:string = process.env.NEXT_PUBLIC_BACK_API_HOST

    const [ selectedRow , setSelectedRow ] = useState<number>(-1);
    const [ selectedItem, setSelectedItem] = useState<{
        USER_ID : string
        USER_NAME : string
        COMPANY_ID : string
        EMAIL : string
    }>()

    /**
     *  <th scope="row">{item.COMPANY_ID}</th>
     <td>{item.USER_ID}</td>
     <td>{item.USER_NAME}</td>
     <td> {item.EMAIL}</td>
     */

        // @ts-ignore
    const authList = {
        height: "80vh",
        overflowY : "scroll"
    };

    const [ devTypeList, setDevTypeList ] = useState<[{
        devType : string
        devName : string
        useYN : string
    }]>([
        { devType : "REQUIREMENT_CHECK", devName : "요청확인", useYN : "N" },
        { devType : "DEVELOPMENT_START", devName : "개발시작", useYN : "N" },
        { devType : "DEVELOPMENT_BUILD", devName : "개발빌드", useYN : "N" },
        { devType : "DEVELOPMENT_CODE_REVIEW", devName : "리뷰요청", useYN : "N" },
        { devType : "DEVELOPMENT_CODE_APPROVAL", devName : "리뷰승인", useYN : "N" },
        { devType : "DEVELOPMENT_DEVELOP_PUBLISH_REQUEST", devName : "개발배포요청", useYN : "N" },
        { devType : "DEVELOPMENT_DEVELOP_PUBLISH_APPROVAL", devName : "개발배포승인", useYN : "N" },
        { devType : "DEVELOPMENT_STAGE_PUBLISH_REQUEST", devName : "검증배포요청", useYN : "N" },
        { devType : "DEVELOPMENT_STAGE_PUBLISH_APPROVAL", devName : "검증배포승인", useYN : "N" },
        { devType : "DEVELOPMENT_REAL_PUBLISH_REQUEST", devName : "운영배포요청", useYN : "N" },
        { devType : "DEVELOPMENT_REAL_PUBLISH_APPROVAL", devName : "운영배포승인", useYN : "N" }
    ])

    const [ stepTypeList, setStepTypeList ] = useState<[{
        stepType : string
        stepName : string
        useYN : string
    }]>([
        { stepType : "CUSTOMER_RECEIPT", stepName : "고객접수", useYN : "N" },
        { stepType : "RECEIPT", stepName : "산하접수", useYN : "N"},
        { stepType : "DEVELOPMENT_ASSIGN", stepName : "개발담당", useYN : "N"},
        { stepType : "DEVELOPMENT_START", stepName : "개발시작", useYN : "N" },
        { stepType : "DEVELOPMENT_FINISH", stepName : "개발적용", useYN : "N" },
        { stepType : "CUSTOMER_APPLY", stepName : "고객적용", useYN : "N" }
    ])

    const [ menuList, setMenuList ] = useState<[{
        menuId : string
        menuName : string
        useYN : string
    }]>([
        { menuId : "0000001", menuName : "신규등록", useYN : "N" },
        { menuId : "0000002", menuName : "권한설정", useYN : "N" },
        { menuId : "0000003", menuName : "권한이력조회", useYN : "N" }
    ])


    const [ userList, setUserListData ] = useState<[{
        USER_ID : string
        COMPANY_ID : string
        USER_NAME : string
        USER_POS_CODE : string
        USER_PWD : string
        FAIL_TIMES : number
        MOBILE_NO : string
        EMAIL : string
        PWD_CHNG_DATE : string
        USER_START_DATE : string
        USER_END_DATE : string
        USER_EXPIRED_DATE : string
        LOCK_YN : string
        TWO_FACTOR_AUTH_YN : string
        USE_YN : string
    }]>([{
            USER_ID : "",
            COMPANY_ID : "",
            USER_NAME : "",
            USER_POS_CODE : "",
            USER_PWD : "",
            FAIL_TIMES : 0,
            MOBILE_NO : "",
            EMAIL : "",
            PWD_CHNG_DATE : "",
            USER_START_DATE : "",
            USER_END_DATE : "",
            USER_EXPIRED_DATE : "",
            LOCK_YN : '',
            TWO_FACTOR_AUTH_YN : '',
            USE_YN : '',
        }]);

    useEffect(() => {

        axios.get(hostUrl + 'is/v1/outer/users?bsnsCode=11&companyId=&langTypeCode=ENG&operationKey=SELECT&propertyNo=11&systemId=WINGSINTEGRATION&useYN=Y')
            .then(res => {
                setUserListData(res.data.resultData)
            });


    }, [])

    const onClickRow = (item : {
        USER_ID : string
        USER_NAME : string
        COMPANY_ID : string
        EMAIL : string },
        index : number) => {

        setSelectedRow(index);

        setSelectedItem({
            USER_ID : item.USER_ID,
            USER_NAME : item.USER_NAME,
            EMAIL : item.EMAIL,
            COMPANY_ID : item.COMPANY_ID
        })

        axios.get(srHostUrl +'/auth/user/all?userId=' + item.USER_ID)
            .then(res => {
                if(res.data) {
                    setDevTypeList(res.data.authDevByUser)
                    setStepTypeList(res.data.authStepByUser)
                    setMenuList(res.data.authMenuByUser)
                }
            });
    }

    const updateAuth = (event : MouseEvent, item: any, index: number, type : string, isClicked : boolean) => {
        if(type == "DEV"){
            axios.post(srHostUrl + '/auth/user/dev', {
                userId: selectedItem.USER_ID,
                devType: item.devType,
                useYN : isClicked ? "Y" : "N"
            }).then(resDev => {
                axios.get(srHostUrl +'/auth/user/dev?userId=' + selectedItem.USER_ID )
                    .then(res => {
                        setDevTypeList(res.data.authDevByUser)


                    });
            });
        }else if(type == "MENU"){
            axios.post(srHostUrl + '/auth/user/menu', {
                userId: selectedItem.USER_ID,
                menuId : item.menuId,
                useYN: isClicked ? "Y" : "N"
            }).then(resMenu => {
                axios.get(srHostUrl +'/auth/user/menu?userId=' + selectedItem.USER_ID )
                    .then(res => {
                        setMenuList(res.data.authMenuByUser)
                    });
            });
        }else{
            axios.post(srHostUrl + '/auth/user/step', {
                userId: selectedItem.USER_ID,
                stepType: item.stepType,
                useYN: isClicked ? "Y" : "N"
            }).then(resStep => {
                axios.get(srHostUrl +'/auth/user/step?userId=' + selectedItem.USER_ID )
                    .then(res => {
                        setStepTypeList(res.data.authStepByUser)
                    });
            });
        }
    }

    return (
        <>
            <Header />
            <div className="row mt-2 p-2">
                <div className="col-sm-6" >
                    <div className="card" style={authList} >
                        <div className="card-body">
                            <div className="table-responsive project-list">
                                <table className="table project-table table-centered table-nowrap">
                                    <thead>
                                    <tr>
                                        <th scope="col">Company ID</th>
                                        <th scope="col">사용자 아이디</th>
                                        <th scope="col">사용자명</th>
                                        <th scope="col">이메일 아이디</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            userList &&
                                            userList
                                                .map( (item, index ) => {
                                                    return <tr className={selectedRow === index ? "bg-light" : "" } onClick={() => onClickRow(item, index)} key={index}>
                                                            <th scope="row">{item.COMPANY_ID}</th>
                                                            <td>{item.USER_ID}</td>
                                                            <td>{item.USER_NAME}</td>
                                                            <td> {item.EMAIL}</td>
                                                        </tr>
                                                })
                                        }
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-sm-1" >
                    <h3>개발 프로세스</h3>
                    {devTypeList &&
                    devTypeList.map( (item, index) => {
                        return  <div className="row mt-2 mb-2" key={index} >
                            <button type="button" onClick={(event : MouseEvent) => updateAuth(event, item, index, "DEV", item.useYN == "N" ? true : false)}  className={item.useYN == "N" ? "btn btn-warning" : "btn btn-light"} >
                                <h6 className="text-muted mb-0 text-sm-center"  >{item.devName}</h6>
                            </button>
                        </div>
                    })}
                </div>
                <div className="col-sm-1" >
                </div>
                <div className="col-sm-1" >
                    <h3>고객 프로세스</h3>
                    {stepTypeList &&
                    stepTypeList.map( (item, index) => {
                        return  <div className="row mt-2 mb-2" key={index} >
                            <button type="button" onClick={(event : MouseEvent)  => updateAuth(event, item, index, "STEP", item.useYN == "N" ? true : false)}  className={item.useYN == "N" ? "btn btn-warning" : "btn btn-light"}  >
                                <h6 className="text-muted mb-0 text-sm-center" style={{color : "black"}}>{item.stepName}</h6>
                            </button>
                        </div>
                    })}
                </div>
                <div className="col-sm-1" >
                </div>
                <div className="col-sm-1" >
                    <h3>메뉴 권한</h3>
                    {menuList &&
                    menuList.map( (item, index) => {
                        return  <div className="row mt-2 mb-2" key={index} >
                            <button type="button" onClick={(event : MouseEvent)  => updateAuth(event, item, index, "MENU", item.useYN == "N" ? true : false)}  className={item.useYN == "N" ? "btn btn-warning" : "btn btn-light"}  >
                                <h6 className="text-muted mb-0 text-sm-center" style={{color : "black"}}>{item.menuName}</h6>
                            </button>
                        </div>
                    })}
                </div>

            </div>
        </>
    )
}

export default withRouter(Auth);
