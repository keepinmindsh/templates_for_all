import { useRouter } from 'next/router'
import {useState, useEffect, useRef, ChangeEvent, Key} from 'react'
import {withRouter} from 'next/router';
import Link from 'next/link'
import axios from 'axios'
import {getCookie, loadLocalStorage} from "../../common/cookie/cookieHandle";
import Header from "../../common/components/elements/header";

// @ts-ignore
const Register = ({router: {query}}) => {

    const [businessType, setBusinessType] = useState<any>([]);
    const [requireType, setRequireType] = useState<any>([]);
    const [priorityType, setPriorityType] = useState<any>([]);
    const [customers, setCustomers] = useState<any>([]);
    const [links, setLinks] = useState<[string | null]>([null]);
    const [devLinks, setDevLinks] = useState<[string | null]>([null]);
    const [isLink, setIsLink] = useState(false);
    const [isShowCodeView, setIsShowCodeView] = useState(false)
    const [isShowCodeApprovalView, setIsShowCodeApprovalView] = useState(false)
    const [isForCustomer, setIsForCustomer] = useState(false)
    const [fileTags, setFileTags] = useState<[
            {
                id: number
                fileSize: number
                fileName: string
                filePath: string
            } | null
    ]>([null]);

    const nameInput = useRef();
    const customerDropInput = useRef();
    const refDevLinkInput = useRef();
    const fileInput = useRef();
    const statusInput = useRef();
    const refTitle = useRef();
    const refCause = useRef();
    const refHowToFix = useRef();
    const refResult = useRef();
    const refReceiptOpinion = useRef();
    const refDeveloperOpinion = useRef();
    const refReviewContent = useRef();
    const refReviewApprovalContent = useRef();
    const refReviewApprover = useRef();
    const refReviewApprovalDate = useRef();
    const router = useRouter()


    // @ts-ignore
    const logedInUserId = getCookie("LOGIN_INFO")? JSON.parse(getCookie("LOGIN_INFO"))["USER_ID"] : "";getCookie("LOGIN_INFO")
    const logedInCompanyId = loadLocalStorage('SESSION_COMPANY_ID');

    const [files, setFiles] = useState('');
    //state for checking file size
    const [fileSize, setFileSize] = useState(true);
    // for file upload progress message
    const [fileUploadProgress, setFileUploadProgress] = useState(false);
    //for displaying response message
    const [fileUploadResponse, setFileUploadResponse] = useState(null);

    // @ts-ignore
    const hostUrl:string = process.env.NEXT_PUBLIC_BACK_API_HOST

    const loginInfo = {
        userId : JSON.parse(getCookie("LOGIN_INFO"))["USER_ID"],
        userName : "",
        companyId : logedInCompanyId
    }

    //base end point url
    const FILE_UPLOAD_BASE_ENDPOINT = hostUrl + "/register/upload";

    const [procesStart, setProcessStart] = useState<boolean>(false)
    const [devProgressive, setDevProgressive] = useState<[
        {
            assignUser: string
            assignUserId: string
            devType: string
            timeStamp: string
            lastStep: boolean
            isDisable: boolean
            devTypeCode: string
        }
    ]>([ {assignUser: "", assignUserId: "", devType: "요청확인", timeStamp: "",lastStep : false, isDisable: false , devTypeCode: "REQUIREMENT_CHECK"},
        {assignUser: "", assignUserId: "", devType: "개발시작", timeStamp: "",lastStep : false, isDisable: false ,devTypeCode: "DEVELOPMENT_START"},
        {assignUser: "", assignUserId: "", devType: "개발빌드", timeStamp: "",lastStep : false, isDisable: false ,devTypeCode: "DEVELOPMENT_BUILD"},
        {assignUser: "", assignUserId: "", devType: "리뷰요청", timeStamp: "",lastStep : false, isDisable: false ,devTypeCode: "DEVELOPMENT_CODE_REVIEW"},
        {assignUser: "", assignUserId: "", devType: "리뷰승인", timeStamp: "",lastStep : false, isDisable: false ,devTypeCode: "DEVELOPMENT_CODE_APPROVAL"},
        {assignUser: "", assignUserId: "", devType: "개발배포요청", timeStamp: "",lastStep : false, isDisable: false ,devTypeCode: "DEVELOPMENT_DEVELOP_PUBLISH_REQUEST"},
        {assignUser: "", assignUserId: "", devType: "개발배포승인", timeStamp: "",lastStep : false, isDisable: false ,devTypeCode: "DEVELOPMENT_DEVELOP_PUBLISH_APPROVAL"},
        {assignUser: "", assignUserId: "", devType: "검증배포요청", timeStamp: "",lastStep : false, isDisable: false ,devTypeCode: "DEVELOPMENT_STAGE_PUBLISH_REQUEST"},
        {assignUser: "", assignUserId: "", devType: "검증배포승인", timeStamp: "",lastStep : false, isDisable: false ,devTypeCode: "DEVELOPMENT_STAGE_PUBLISH_APPROVAL"},
        {assignUser: "", assignUserId: "", devType: "운영배포요청", timeStamp: "",lastStep : false, isDisable: false ,devTypeCode: "DEVELOPMENT_REAL_PUBLISH_REQUEST"},
        {assignUser: "", assignUserId: "", devType: "운영배포승인", timeStamp: "",lastStep : false, isDisable: false ,devTypeCode: "DEVELOPMENT_REAL_PUBLISH_APPROVAL"}
    ])

    const [registerForm, setRegisterForm] = useState<{
        task: {
            businessType: string | null
            cause: string | null
            completeDate: string | null
            customNo: number | null
            developerOpinion: string | null
            expectedCompleteDate: string | null
            howToFix: string | null
            id: number | null
            priorityType: string | null
            receiptDate: string | null
            receiptNo: number | null
            receiptOpinion: string | null
            requestUserId: string | null
            requireType: string | null
            result: string | null
            stepType: string | null
            title: string | null
            reviewContent : string | null
            reviewApprovalContent : string | null
            reviewApprover : string | null
            reviewApprovalDate : string | null
        },
        progressives: [
            {
                assignUser: string
                assignUserId: string
                stepType: string
                timeStamp: string
                order: number
                lastStep: boolean
                disable: boolean
                stepTypeCode: string
            }
        ],
        fileAttacheds: [
                {
                    id: number
                    fileSize: number
                    fileName: string
                    filePath: string
                } | null
        ],
        links: [
            {
                linkId: number
                link: string
            }],
        devLinks: [
            {
                linkId: number
                link: string
            }
        ]
    }>({
        fileAttacheds: [null],
        progressives: [{assignUser: "", assignUserId: "", stepType: "", timeStamp: "", stepTypeCode: ""}],
        task: {
            businessType: null,
            cause: null,
            completeDate: null,
            customNo: null,
            developerOpinion: null,
            expectedCompleteDate: null,
            howToFix: null,
            id: null,
            priorityType: null,
            receiptDate: null,
            receiptNo: null,
            receiptOpinion: null,
            requestUserId: logedInUserId,
            requireType: null,
            result: null,
            stepType: null,
            title: null,
            reviewContent : null,
            reviewApprovalContent : null,
            reviewApprover :  null,
            reviewApprovalDate : null
        }
    });

    useEffect(() => {
        let tempTask = {};

        axios.get(hostUrl + '/codes?codeType=BUSINESS_TYPE')
            .then(res => {
                setBusinessType(res.data);

                if(res.data.length > 0){
                    tempTask = {
                        ...tempTask,
                        businessType : res.data[0].code
                    }
                }

                axios.get(hostUrl + '/codes?codeType=REQUIRE_TYPE')
                    .then(res => {
                        setRequireType(res.data);

                        if(res.data.length > 0){
                            tempTask = {
                                ...tempTask,
                                requireType : res.data[0].code
                            }
                        }

                        axios.get(hostUrl + '/codes?codeType=PRIORITY_TYPE')
                            .then(res => {
                                setPriorityType(res.data);
                                if(res.data.length > 0){
                                    tempTask = {
                                        ...tempTask,
                                        priorityType : res.data[0].code
                                    }
                                }

                                axios.get(hostUrl + '/customers?companyId=' + logedInCompanyId)
                                    .then(res => {
                                        setCustomers(res.data);

                                        if(res.data.length > 0){
                                            tempTask = {
                                                ...registerForm.task,
                                                ...tempTask,
                                                customNo : res.data[0].customerNo
                                            }
                                        }

                                        setInitializeData(tempTask);
                                    });
                            });
                    });
            });

        setIsLink(getParameterByName("screenType") == "LINK" ? true : false)
    }, [])

    const setInitializeData = (reviceTask) => {
        let tempRegister = {};

        if (( query.inputType || getParameterByName("inputType") ) && ( query.inputType != "NEW" || getParameterByName("inputType") != "NEW")) {
            axios.get(hostUrl + '/register/tasks?id=' + ( getParameterByName("id") ?  getParameterByName("id") : query.id ) +"&userId=" + loginInfo.userId )
                .then(res => {
                    setRegisterForm({
                        ...registerForm,
                        task: res.data.task,
                        progressives: res.data.progressives,
                        links: res.data.links,
                        devLinks : res.data.devLinks,
                        fileAttacheds: res.data.fileAttacheds
                    });

                    const list: [string | null] = [];
                    res.data.links.forEach(item => {
                        list.push(item.link)
                    })
                    setLinks(list)

                    const devList: [string | null] = [];
                    res.data.devLinks.forEach(item => {
                        devList.push(item.link)
                    })
                    setDevLinks(devList)

                    const fileList: [{
                        id: number
                        fileSize: number
                        fileName: string
                        filePath: string
                    } | null] = [];

                    res.data.fileAttacheds.forEach(item => {
                        fileList.push(item)
                    })
                    setFileTags([...fileList])

                    checkDevProcessStart(res.data.task.stepType);

                    axios.get(hostUrl + '/register/progressives/dev?id=' + getParameterByName("id") +"&userId=" + loginInfo.userId)
                        .then(res => {
                            setDevProgressive(res.data.devProgressives)

                            res.data.devProgressives.forEach((item, index) => {
                                if(item.timeStamp){
                                    setDevelopmentStatus(item.devTypeCode, true)
                                }
                            })
                        });

                    if(loginInfo.companyId != "SANHAIT"){
                        setIsForCustomer(true)
                    }
                });
        } else {
            axios.get(hostUrl + '/register/progressives?id=00' +"&userId=" + loginInfo.userId)
                .then(res => {
                    setRegisterForm({
                        ...registerForm
                        , task : reviceTask
                        , progressives: res.data.progressives
                    });
                });

            axios.get(hostUrl + '/register/progressives/dev?id=00'  +"&userId=" + loginInfo.userId)
                .then(res => {
                    setDevProgressive([...res.data.devProgressives])

                    res.data.devProgressives.forEach((item, index) => {
                        if(item.timeStamp){
                            setDevelopmentStatus(item.devTypeCode, true)
                        }
                    })
                });

            if(loginInfo.companyId != "SANHAIT"){
                setIsForCustomer(true)
            }
        }
    }

    const prePage = () => {
        return <button className="btn btn-primary" onClick={() => router.back()} type="button">뒤로가기</button>
    }

    const setDevelopmentStatus = (processCode : string, isOpen : boolean) => {
        switch (processCode) {
            case "DEVELOPMENT_BUILD" :
                if(loginInfo.companyId != "SANHAIT"){
                    setIsShowCodeView(false)
                }else{
                    setIsShowCodeView(isOpen)
                }
                break;
            case "DEVELOPMENT_CODE_APPROVAL" :
                if(loginInfo.companyId != "SANHAIT"){
                    setIsShowCodeApprovalView(false)
                }else{
                    setIsShowCodeApprovalView(isOpen)
                }
                break;
        }
    }

    const onRegisterFormHandler = (name: string, event: ChangeEvent) => {

        registerForm.task[name] = event.target.value;

        setRegisterForm({
            ...registerForm
        });
    }

    const checkDevProcessStart = (stepTypeCode: String | null) =>{
        if (stepTypeCode == "DEVELOPMENT_START" || stepTypeCode == "DEVELOPMENT_FINISH" || stepTypeCode == "CUSTOMER_APPLY") {
            setProcessStart(true)
        }
    }

    const onStartTask = (assignUserId: String | null, assignUserName: String, stepTypeCode: String | null, disable: boolean): void => {

        if(disable) return;

        axios.post(hostUrl + '/register/step', {
            taskId: registerForm.task.id,
            assignUserId: assignUserId,
            stepTypeCode: stepTypeCode
        }).then(res => {
            if (res.data == "Success") {
                statusInput.current.value = stepTypeCode

                axios.get(hostUrl + '/register/progressives?id=' + registerForm.task.id +"&userId=" + loginInfo.userId)
                    .then(res => {
                        setRegisterForm({...registerForm, progressives: res.data.progressives});
                    });
                checkDevProcessStart(stepTypeCode);
            }else if(res.data == "NotAll"){
                alert("모든 개발 단계가 완료되어여 다음단계를 진행할 수 있습니다.")
            }else if(res.data == "CheckNextStep"){
                alert("이전단계가 완료되어야 다음단계를 진행할 수 있습니다.")
            }
        });
    }

    const reviewApproval = () => {
        if(!refReviewApprover.current.value){
            axios.post(hostUrl + '/register/approval', {
                id: registerForm.task.id,
                userId : loginInfo.userId,
                reviewApprovalContent : registerForm.task.reviewApprovalContent
            }).then(res => {
                refReviewApprover.current.value = res.data.reviewApproval.reviewApprover;
                refReviewApprovalDate.current.value = res.data.reviewApproval.reviewApprovalDate;
            })
        }
    }

    const onStartDevTask = (assignUserId: String | null, assignUserName: String, devTypeCode: String | null, disable :boolean): void => {

        if(disable) return;

        if(devTypeCode == "DEVELOPMENT_DEVELOP_PUBLISH_REQUEST"){
            if(!refReviewApprover.current && !refReviewApprover.current ){
                alert("You cannot go to next step before approval code review")
                return;
            }

            if(!refReviewApprover.current.value && !refReviewApprovalDate.current.value){
                alert("You cannot go to next step before approval code review")
                return;
            }
        }

        if(devTypeCode == "DEVELOPMENT_CODE_REVIEW") {
            if (!registerForm.task.reviewContent) {
                alert("You cannot go to next step until register review content.")
                return;
            }else{
                onSaveTask(true);
            }
        }

        axios.post(hostUrl + '/register/step/dev', {
            taskId: registerForm.task.id,
            assignUserId: assignUserId,
            devTypeCode: devTypeCode
        }).then(res => {
            if (res.data == "Success") {
                axios.get(hostUrl + '/register/progressives/dev?id=' + registerForm.task.id +"&userId=" + loginInfo.userId)
                    .then(res => {
                        setDevProgressive(res.data.devProgressives)

                        res.data.devProgressives.forEach((item, index) => {
                            if (item.timeStamp) {
                                setDevelopmentStatus(item.devTypeCode, true)
                            }
                        })
                    });
            }else if(res.data == "CheckNextStep"){
                alert("Please check previous step before clicking next step")
            }
        });
    }

    const onNewTask = () => {
        setRegisterForm({
            ...registerForm,
            fileAttacheds: [null],
            progressives: [
                {assignUser: "", assignUserId: "", stepType: "고객접수", timeStamp: "", stepTypeCode: "CUSTOMER_RECEIPT"},
                {assignUser: "", assignUserId: "", stepType: "산하접수", timeStamp: "", stepTypeCode: "RECEIPT"},
                {assignUser: "", assignUserId: "", stepType: "개발담당", timeStamp: "", stepTypeCode: "DEVELOPMENT_ASSIGN"},
                {assignUser: "", assignUserId: "", stepType: "개발시작", timeStamp: "", stepTypeCode: "DEVELOPMENT_START"},
                {assignUser: "", assignUserId: "", stepType: "개발적용", timeStamp: "", stepTypeCode: "DEVELOPMENT_FINISH"},
                {assignUser: "", assignUserId: "", stepType: "고객적용", timeStamp: "", stepTypeCode: "CUSTOMER_APPLY"}
            ],
            task: {
                businessType: "RESERVATION",
                cause: "",
                completeDate: "",
                customNo: "",
                developerOpinion: "",
                expectedCompleteDate: "",
                howToFix: "",
                id: "",
                priorityType: "OPEN_READY",
                receiptDate: "",
                receiptNo: "",
                receiptOpinion: "",
                requestUserId: logedInUserId,
                requireType: "NEW",
                result: "",
                stepType: null,
                title: "",
                reviewContent : "",
                reviewApprovalContent : "",
                reviewApprover : "",
                reviewApprovalDate : ""
            }
        });

        refTitle.current.value = "";
        refCause.current.value = "";
        refHowToFix.current.value = "";
        refResult.current.value = "";
        refReceiptOpinion.current.value = "";
        refDeveloperOpinion.current.value = "";
        refReviewContent.current.value = "";
        refReviewApprovalContent.current.value =  "";
        refReviewApprover.current.value = "";
        refReviewApprovalDate.current.value = "";

        setProcessStart(false);
        setIsShowCodeView(false);
        setIsShowCodeApprovalView(false)
        setLinks([null]);
        setDevLinks([null]);
        setFileTags([null]);
    }

    const onSaveTask = (isAlertPassed : boolean | null) => {
        axios.post(hostUrl + '/register/task', {
            businessType: registerForm.task.businessType,
            cause: registerForm.task.cause,
            completeDate: registerForm.task.completeDate,
            customNo: registerForm.task.customNo,
            developerOpinion: registerForm.task.developerOpinion,
            expectedCompleteDate: registerForm.task.expectedCompleteDate,
            howToFix: registerForm.task.howToFix,
            id: registerForm.task.id ? registerForm.task.id : null,
            priorityType: registerForm.task.priorityType,
            receiptDate: registerForm.task.receiptDate,
            receiptNo: registerForm.task.receiptNo,
            receiptOpinion: registerForm.task.receiptOpinion,
            requestUserId: registerForm.task.requestUserId ? registerForm.task.requestUserId : logedInUserId,
            requireType: registerForm.task.requireType,
            result: registerForm.task.result,
            stepType: registerForm.task.stepType,
            title: registerForm.task.title,
            reviewContent :  registerForm.task.reviewContent,
            reviewApprovalContent : registerForm.task.reviewApprovalContent
        }).then(taskRes => {
            axios.get(hostUrl + '/register/progressives?id=' + taskRes.data.taskId + "&userId=" + loginInfo.userId)
                .then(res => {
                    setRegisterForm({
                        ...registerForm, progressives: res.data.progressives,
                        task: {
                            businessType: registerForm.task.businessType,
                            cause: registerForm.task.cause,
                            completeDate: registerForm.task.completeDate,
                            customNo: registerForm.task.customNo,
                            developerOpinion: registerForm.task.developerOpinion,
                            expectedCompleteDate: registerForm.task.expectedCompleteDate,
                            howToFix: registerForm.task.howToFix,
                            id: taskRes.data.taskId,
                            priorityType: registerForm.task.priorityType,
                            receiptDate: registerForm.task.receiptDate,
                            receiptNo: taskRes.data.receiptNo,
                            receiptOpinion: registerForm.task.receiptOpinion,
                            requestUserId: taskRes.data.requestUserId,
                            requireType: registerForm.task.requireType,
                            result: registerForm.task.result,
                            stepType: taskRes.data.stepType,
                            title: registerForm.task.title,
                            reviewContent :  registerForm.task.reviewContent,
                            reviewApprovalContent : registerForm.task.reviewApprovalContent
                        }
                    });

                    if(!isAlertPassed){
                        alert("Saved!")
                    }

                });
        });
    }

    const onAddRelatedLink = () => {

        axios
            .get(hostUrl + "/register/task/exist?id=" +  nameInput.current.value )
            .then(res => {
                if(res.data){
                    setLinks([...links, nameInput.current.value]);

                    axios.post(hostUrl + '/register/link', {
                        taskId: registerForm.task.id,
                        link: nameInput.current.value
                    }).then(res => {
                        nameInput.current.value = ""
                    });
                }else{
                    alert("존재하지 않는 Task ID 입니다.!")
                    nameInput.current.value = ""
                }
            })
    }

    const onAddRelatedDeveyeLink = () => {
        setDevLinks([...devLinks, refDevLinkInput.current.value]);

        axios.post(hostUrl + '/register/srlink', {
            taskId: registerForm.task.id,
            link: refDevLinkInput.current.value
        }).then(res => {
            refDevLinkInput.current.value = ""
        });
    }


    const onRemoveFile = (fileId: number) => {

        axios.delete(hostUrl + "/register/file?fileId=" + fileId)
            .then(res => {
                setFileTags([...fileTags.filter(item => item.id != fileId)])
            })
    }

    const uploadFileHandler = (event) => {
        setFiles(event.target.files);
    };

    const getParameterByName = (name, url = window.location.href) => {
        name = name.replace(/[\[\]]/g, '\\$&');
        var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }

    const fileSubmitHandler = (event) => {
        event.preventDefault();
        setFileSize(true);
        setFileUploadProgress(true);
        setFileUploadResponse(null);

        const formData = new FormData();

        for (let i = 0; i < files.length; i++) {
            formData.append(`files`, files[i])
            formData.append(`taskId`, registerForm.task.id)
        }

        const requestOptions = {
            method: 'POST',
            body: formData
        };

        fetch(FILE_UPLOAD_BASE_ENDPOINT, requestOptions)
            .then(async response => {

                console.log(response)

                const isJson = response.headers.get('content-type')?.includes('application/json');
                const data = isJson && await response.json();

                setFileTags(fileTags => [...fileTags, {
                    id: data.fileId,
                    fileSize: data.fileSize,
                    fileName: data.fileName,
                    filePath: data.filePath
                }])

                // check for error response
                if (!response.ok) {
                    // get error message
                    const error = (data && data.message) || response.status;
                    setFileUploadResponse(data.message);
                    return Promise.reject(error);
                }

                setFileUploadResponse(data.message);
                fileInput.current.value = null;
            })
            .catch(error => {
                console.error('Error while uploading file!', error);
            });

        setFileUploadProgress(false);
    };

    const onDelete = () => {
        axios.delete(hostUrl + '/register/task?id=' + query.id)
            .then(res => {
                if(res.data == "Success"){
                    alert("Deleted!")
                    router.back();
                }else {
                    alert("Delete not finished correctly!")
                }
            })
    }

    const onRefresh = () => {
        axios.get(hostUrl + '/register/tasks?id=' + query.id)
            .then(res => {
                setRegisterForm({
                    ...registerForm,
                    task: res.data.task,
                    progressives: res.data.progressives,
                    links: res.data.links,
                    fileAttacheds: res.data.fileAttacheds
                });

                const list: [string | null] = [];
                res.data.links.forEach(item => {
                    list.push(item.link)
                })
                setLinks(list)

                const fileList: [{
                    id: number
                    fileSize: number
                    fileName: string
                    filePath: string
                } | null] = [];

                res.data.fileAttacheds.forEach(item => {
                    fileList.push(item)
                })
                setFileTags([...fileList])

            });
    }

    return (
        <>
            <Header />
            <div className="card">
                <div className="card-body">
                    <div className="row">
                        {isLink ?
                            <></>
                            :
                            <div className="col d-grid gap-2 d-md-flex justify-content-md-start mb-2">
                                {prePage()}
                            </div>
                        }

                        <div className="col d-grid gap-2 d-md-flex justify-content-md-end mb-2">
                            <button className="btn btn-primary" onClick={onRefresh} type="button">Refresh</button>
                            <button className="btn btn-primary" onClick={onNewTask} type="button">New</button>
                            <button className="btn btn-primary" onClick={() => { onSaveTask(false) }} type="button">Save</button>
                            <button className="btn btn-primary" onClick={onDelete} type="button">Delete</button>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col">
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">* Customer</label>
                                <div className="col-sm-8">
                                    <select className="form-control" value={registerForm.task.customNo}
                                            onChange={(event) => {
                                                onRegisterFormHandler("customNo", event)
                                            }}>
                                        {
                                            customers.map((item: { customerNo: string; customerName: string; }, index: Key | null | undefined) => <option key={index}  value={item.customerNo} >{item.customerName}</option>)
                                        }
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">* Require Type</label>
                                <div className="col-sm-8">
                                    <select className="form-control" defaultValue={registerForm.task.requireType} value={registerForm.task.requireType}  onChange={(event) => { onRegisterFormHandler("requireType", event)}} >
                                        {
                                            requireType.map((item: { code: string ; value: string; }, index: Key | null | undefined) => <option key={index} value={item.code} >{item.value}</option>)
                                        }
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">Receipt Date</label>
                                <div className="col-sm-8">
                                    <input type="date" aria-label="First name" max="2099-12-31"  className="form-control" defaultValue={registerForm.task.receiptDate}  onChange={(event) => { onRegisterFormHandler("receiptDate", event)}} />
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">Request ID</label>
                                <div className="col-sm-8">
                                    <input type="text" className="form-control" defaultValue={registerForm.task.requestUserId}  onChange={(event) => { onRegisterFormHandler("requestUserId", event)}} readOnly />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row mt-1" >
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">* Business Type</label>
                                <div className="col-sm-8">
                                    <select className="form-control" defaultValue={registerForm.task.businessType}  value={registerForm.task.businessType} onChange={(event) => { onRegisterFormHandler("businessType", event)}} >
                                        {
                                            businessType.map((item: { code: string ; value: string; }, index: Key | null | undefined) => <option key={index} value={item.code} >{item.value}</option>)
                                        }
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">* Priority</label>
                                <div className="col-sm-8">
                                    <select className="form-control" defaultValue={registerForm.task.priorityType}  onChange={(event) => { onRegisterFormHandler("priorityType", event)}} >
                                        {
                                            priorityType.map((item: { code: string ; value: string; }, index: Key | null | undefined) => <option key={index} value={item.code} >{item.value}</option>)
                                        }
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">Expected</label>
                                <div className="col-sm-8">
                                    <input type="date" aria-label="First name" max="2099-12-31"  className="form-control" defaultValue={registerForm.task.expectedCompleteDate} onChange={(event) => { onRegisterFormHandler("expectedCompleteDate", event)}} />
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">Receipt No</label>
                                <div className="col-sm-8">
                                    <input type="text" className="form-control" defaultValue={registerForm.task.receiptNo} onChange={(event) => { onRegisterFormHandler("receiptNo", event)}} readOnly />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row mt-1" >
                        <div className="col-sm-6" >
                            <div className="input-group mb-3">
                                <button className="btn btn-outline-secondary" type="button" onClick={onAddRelatedLink} id="button-addon1" disabled={registerForm.task.id ? false : true } >
                                    Add Task Id Link
                                </button>
                                <input type="text" className="form-control" id="relatedLink"  ref={nameInput}  disabled={registerForm.task.id ? false : true }/>
                            </div>
                        </div>
                        <div className="col" >
                            {links?.map(item => {
                                if(item){
                                    return <Link  href={{
                                        pathname: '/register/register',
                                        query: {
                                            inputType: "UPDATE",
                                            id : item,
                                            assignUserId : loginInfo.userId ,
                                            assignUserName : loginInfo.userName,
                                            screenType : "LINK"
                                        }
                                    }} >
                                                <a className="btn btn-outline-secondary me-1" target="_blank" type="button" id="button-addon1">{item}</a>
                                            </Link>
                                }else{
                                    return ""
                                }
                            })}

                        </div>

                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">Status</label>
                                <div className="col-sm-8">
                                    <input type="text" className="form-control disabled" defaultValue={registerForm.task.stepType}  ref={statusInput} onChange={(event) => { onRegisterFormHandler("statusType", event)}} readOnly />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row mt-1" >
                        <div className="col-sm-6" >
                            <div className="input-group mb-3">
                                <button className="btn btn-outline-secondary" type="button" onClick={onAddRelatedDeveyeLink} id="button-addon1" disabled={registerForm.task.id ? false : true } >
                                    Add Dev Eye Link
                                </button>
                                <input type="text" className="form-control" id="relatedLink"  ref={refDevLinkInput}  disabled={registerForm.task.id ? false : true }/>
                            </div>
                        </div>
                        <div className="col" >
                            {devLinks?.map(item => {
                                if(item){
                                    return <a className="btn btn-outline-secondary me-1" rel="noopener noreferrer" href={"http://gw.sanhait.com:9005/csr/sr/SRDetailView.do?popup=true&SRID=" + item} target="_blank" type="button" id="button-addon1">{item}</a>
                                }else{
                                    return ""
                                }
                            })}
                        </div>
                    </div>
                    <div className="row mt-1 mb-1">
                        {
                            (registerForm.progressives !== null && registerForm.progressives !== undefined) ?
                                registerForm.progressives.sort((a, b) =>  a.order - b.order  )
                                    .map((item: {assignUser: string, stepType: string,  timeStamp: string, lastStep : boolean, disable : boolean, assignUserId : string, stepTypeCode : string}, index) => {
                                        return ( <div className="col-xl-2 col-md-2 p-1" key={index}>
                                                <div className="card bg-pattern">
                                                    {
                                                            <button type="button" onClick={() => {onStartTask(logedInUserId, item.assignUser, item.stepTypeCode, item.disable)}} className={item.lastStep ? "btn btn-warning" : "btn btn-light" } disabled={item.disable ? true : registerForm.task.id ? item.timeStamp ? true : false : true } >
                                                                <h6 className="text-muted mb-0 text-sm-center">{item.stepType} </h6>
                                                                <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center"> { item.assignUserId ? "[" + item.assignUserId + "]" : " " }</h6>
                                                                <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center">{item.timeStamp}</h6>
                                                            </button>
                                                    }

                                                </div>
                                            </div>
                                        )
                                }) : <></>
                        }
                    </div>
                    <div className="row mt-1 mb-1 justify-content-end">
                        {
                            procesStart && !isForCustomer ?
                            devProgressive.sort((a, b) =>  a.order - b.order  )
                                .map((item: {assignUser: string, devType: string,  timeStamp: string, lastStep : boolean, disable : boolean, assignUserId : string, devTypeCode : string}, index) => {
                                    return ( <div className="col-xl-1 col-md-1 p-1" key={index}>
                                            <div className="card bg-pattern">
                                                {
                                                        <button type="button"  onClick={() => {onStartDevTask(logedInUserId, item.assignUser, item.devTypeCode, item.disable)}} className={item.lastStep ? "btn btn-warning" : "btn btn-light" } disabled={item.disable ? true : registerForm.task.id ? item.timeStamp ? true : false : true } >
                                                            <h6 className="text-muted mb-0 text-sm-center">{item.devType} </h6>
                                                            <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center"> { item.assignUserId ? "[" + item.assignUserId + "]" : " " }</h6>
                                                            <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center">{item.timeStamp}</h6>
                                                        </button>
                                                }

                                            </div>
                                        </div>
                                    )
                                })
                                :
                                <></>
                        }
                    </div>
                    <div className="row" >
                        <div className="col-md-8" >
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">* Title</label>
                                <div className="col-sm-10">
                                    <textarea className="form-control" rows={1} id="title" ref={refTitle} defaultValue={registerForm.task.title}  onChange={(event) => { onRegisterFormHandler("title", event)}} />
                                </div>
                            </div>
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">* Cause</label>
                                <div className="col-sm-10">
                                    <textarea className="form-control" rows={6} id="cause" ref={refCause}  defaultValue={registerForm.task.cause}  onChange={(event) => { onRegisterFormHandler("cause", event)}} />
                                </div>
                            </div>
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">* How To Fix</label>
                                <div className="col-sm-10">
                                    <textarea className="form-control" rows={6} id="howToFix" ref={refHowToFix} defaultValue={registerForm.task.howToFix}  onChange={(event) => { onRegisterFormHandler("howToFix", event)}} />
                                </div>
                            </div>
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">Attached</label>
                                <div className="col-sm-10">
                                    <form onSubmit={fileSubmitHandler}>
                                        <div className="row" >
                                            <div className="col-sm-10">
                                                <input  className="form-control"  type="file" id="formFileMultiple" ref={fileInput}  multiple onChange={uploadFileHandler}  disabled={registerForm.task.id ? false : true } />
                                            </div>

                                            {!fileSize && <p style={{color:'red'}}>File size exceeded!!</p>}
                                            {fileUploadProgress && <p style={{color:'red'}}>Uploading File(s)</p>}
                                            {fileUploadResponse!=null && <p style={{color:'green'}}>{fileUploadResponse}</p>}
                                            <div className="col-sm-2">
                                                <button className="btn btn-light" type='submit'  disabled={registerForm.task.id ? false : true }  >Upload</button>
                                            </div>
                                        </div>
                                        <div className="row" >
                                            {fileTags?.map(item => {
                                                if(item){
                                                    if(item.fileName){
                                                        return <>
                                                            <div className="row" >
                                                                <div className="col-sm-6" >
                                                                    <a href={ hostUrl + "/register/files?fileId=" + item.id }  target="_blank" rel="noopener noreferrer" download>
                                                                        {item.fileName}
                                                                    </a>
                                                                </div>
                                                                <div className="col-sm-2" >
                                                                    <a onClick={() => onRemoveFile(item.id)} >Remove</a>
                                                                </div>
                                                            </div>
                                                        </>
                                                    }else{
                                                        return ""
                                                    }
                                                }else{
                                                    return ""
                                                }
                                            })}
                                        </div>

                                    </form>
                                </div>
                            </div>
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">Result</label>
                                <div className="col-sm-10">
                                    <textarea className="form-control" rows={7} id="result" ref={refResult} defaultValue={registerForm.task.result}  onChange={(event) => { onRegisterFormHandler("result", event)}} />
                                </div>
                            </div>
                        </div>
                        {
                            isForCustomer ?
                                <></>
                                :
                                <div className="col-md-4" >
                                    <div className="form-group mb-2">
                                        <label htmlFor="comment">* Receipt Opinion</label>
                                        <textarea className="form-control" rows={11} id="receiptOpinion" ref={refReceiptOpinion} defaultValue={registerForm.task.receiptOpinion}   onChange={(event) => { onRegisterFormHandler("receiptOpinion", event)}}  />
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="comment">* Develop Opinion</label>
                                        <textarea className="form-control" rows={11} id="developerOpinion" ref={refDeveloperOpinion} defaultValue={registerForm.task.developerOpinion} onChange={(event) => { onRegisterFormHandler("developerOpinion", event)}}   />
                                    </div>
                                </div>
                        }

                    </div>
                    {isShowCodeView ?
                        <div className="row" >
                            <div className="col-md-12" >
                                <label htmlFor="comment">* Code Review Content</label>
                                <textarea className="form-control" rows={11} id="reviewContent" ref={refReviewContent} defaultValue={registerForm.task.reviewContent}   onChange={(event) => { onRegisterFormHandler("reviewContent", event)}}  />
                            </div>
                        </div>
                        :
                        <></>
                    }
                    {
                        isShowCodeApprovalView ?
                        <div className="row" >
                            <div className="col-md-8" >
                                <label htmlFor="comment">* Code Review Approval</label>
                                <textarea className="form-control" rows={11} id="reviewApprovalContent" ref={refReviewApprovalContent} defaultValue={registerForm.task.reviewApprovalContent}   onChange={(event) => { onRegisterFormHandler("reviewApprovalContent", event)}}  />
                            </div>
                            <div className="col-md-4" >
                                <h6>Approval Result</h6>
                                <button type="button"  className="btn btn-warning" onClick={reviewApproval} >
                                    <h6 className="text-muted mb-0 text-sm-center">Code Review Approval</h6>
                                </button>
                                <div className="form-group row mb-2 mt-2">
                                    <label htmlFor="colFormLabelSm"
                                           className="col-sm-2 col-form-label col-form-label-sm text-end">Approval ID</label>
                                    <div className="col-sm-10">
                                        <textarea className="form-control" rows={1} id="title" ref={refReviewApprover} defaultValue={registerForm.task.reviewApprover} value={registerForm.task.reviewApprover}  disabled />
                                    </div>
                                </div>
                                <div className="form-group row mb-2 mt-2">
                                    <label htmlFor="colFormLabelSm"
                                           className="col-sm-2 col-form-label col-form-label-sm text-end">Approval Date</label>
                                    <div className="col-sm-10">
                                        <textarea className="form-control" rows={1} id="title" ref={refReviewApprovalDate} defaultValue={registerForm.task.reviewApprovalDate} value={registerForm.task.reviewApprovalDate} disabled />
                                    </div>
                                </div>
                            </div>
                        </div>
                        :
                        <></>
                    }
                </div>
            </div>
        </>
    )
}

export default withRouter(Register);
