import { useRouter } from 'next/router'
import {useState, useEffect, useRef, ChangeEvent} from 'react'
import { withRouter } from 'next/router';
import Link from 'next/link'
import axios from 'axios'

const Register = ({ router: { query } }) => {

    const [businessType, setBusinessType] = useState<any>([]);
    const [requireType, setRequireType] = useState<any>([]);
    const [priorityType, setPriorityType] = useState<any>([]);
    const [customers, setCustomers] = useState<any>([]);
    const [links, setLinks]  = useState<[string|null]>([null]);

    const nameInput = useRef();

    const [files, setFiles] = useState('');
    //state for checking file size
    const [fileSize, setFileSize] = useState(true);
    // for file upload progress message
    const [fileUploadProgress, setFileUploadProgress] = useState(false);
    //for displaying response message
    const [fileUploadResponse, setFileUploadResponse] = useState(null);
    //base end point url
    const FILE_UPLOAD_BASE_ENDPOINT = "http://localhost:9090/register/upload";

    const [registerForm, setRegisterForm ] = useState<{
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
        },
        progressives : [
            {
                assignUser: string
                assignUserId : string
                stepType: string
                timeStamp: string
                order: number
                lastStep : boolean
                stepTypeCode : string
            }
        ],
        fileAttacheds : [
            any
        ],
        links : [
            {
                linkId : number
                link : string
            }
        ]
    }>({
        fileAttacheds: [null],
        progressives: [{assignUser: "", assignUserId: "", stepType: "", timeStamp: "", stepTypeCode: ""}],
        task: {
            businessType: "BIZ1",
            cause: null,
            completeDate: null,
            customNo: null,
            developerOpinion: null,
            expectedCompleteDate: null,
            howToFix: null,
            id: null,
            priorityType: "MIDDLE",
            receiptDate: null,
            receiptNo: null,
            receiptOpinion: null,
            requestUserId: null,
            requireType: "NEW",
            result: null,
            stepType: null,
            title: null
        }
    })

    let taskId = query.id;

    useEffect(() => {
        axios.get('http://localhost:9090/codes?codeType=BUSINESS_TYPE')
            .then(res => {
                setBusinessType(res.data);
            });
        axios.get('http://localhost:9090/codes?codeType=REQUIRE_TYPE')
            .then(res => {
                setRequireType(res.data);
            });
        axios.get('http://localhost:9090/codes?codeType=PRIORITY_TYPE')
            .then(res => {
                setPriorityType(res.data);
            });
        axios.get('http://localhost:9090/customers')
            .then(res => {
                setCustomers(res.data);
            });

        if(query.inputType && query.inputType != "NEW"){
            axios.get('http://localhost:9090/register/tasks?id=' + query.id)
                .then(res => {
                    setRegisterForm({...registerForm , task : res.data.task , progressives : res.data.progressives, links : res.data.links });

                    const list : [string|null] = [];
                    res.data.links.forEach(item =>{
                        list.push(item.link)
                    })

                    setLinks(list)
                });
        }else{
            setRegisterForm({...registerForm
                ,progressives : [
                    {assignUser: "", assignUserId: query.assignUserId, stepType: "고객접수", timeStamp: "", stepTypeCode: "CUSTOMER_RECEIPT"},
                    {assignUser: "", assignUserId: query.assignUserId, stepType: "산하접수", timeStamp: "", stepTypeCode: "RECEIPT"},
                    {assignUser: "", assignUserId: query.assignUserId, stepType: "개발담당", timeStamp: "", stepTypeCode: "DEVELOPMENT_ASSIGN"},
                    {assignUser: "", assignUserId: query.assignUserId, stepType: "개발시작", timeStamp: "", stepTypeCode: "DEVELOPMENT_START"},
                    {assignUser: "", assignUserId: query.assignUserId, stepType: "개발적용", timeStamp: "", stepTypeCode: "DEVELOPMENT_FINISH"},
                    {assignUser: "", assignUserId: query.assignUserId, stepType: "고객적용", timeStamp: "", stepTypeCode: "CUSTOMER_APPLY"}
                ]});
        }
    }, [])

    const prePage = () => {
        const router = useRouter()
        return <button className="btn btn-primary" onClick={() => router.back()} type="button">뒤로가기</button>
    }

    const onRegisterFormHandler = ( name : string, event : ChangeEvent) => {

        registerForm.task[name] = event.target.value;

        setRegisterForm(registerForm);
    }

    const onStartTask = (assignUserId:String|null, assignUserName:String, stepTypeCode:String|null) : void => {
        axios.post('http://localhost:9090/register/step',{
            taskId : registerForm.task.id,
            assignUserId: assignUserId,
            stepTypeCode: stepTypeCode
        }).then(res => {
            if(res.data == "Success"){
                axios.get('http://localhost:9090/register/progressives?id=' + registerForm.task.id)
                    .then(res => {
                        setRegisterForm({...registerForm , progressives : res.data.progressives });
                    });
            }
        });
    }

    const onSaveTask = () => {
        axios.post('http://localhost:9090/register/task',{
            businessType: registerForm.task.businessType,
            cause: registerForm.task.cause,
            completeDate: registerForm.task.completeDate,
            customNo: registerForm.task.customNo,
            developerOpinion: registerForm.task.developerOpinion,
            expectedCompleteDate: registerForm.task.expectedCompleteDate,
            howToFix: registerForm.task.howToFix,
            id: taskId ? taskId : null,
            priorityType: registerForm.task.priorityType,
            receiptDate: registerForm.task.receiptDate,
            receiptNo: registerForm.task.receiptNo,
            receiptOpinion: registerForm.task.receiptOpinion,
            requestUserId: registerForm.task.requestUserId,
            requireType: registerForm.task.requireType,
            result: registerForm.task.result,
            stepType: registerForm.task.stepType,
            title: registerForm.task.title
        }).then(taskRes => {
            axios.get('http://localhost:9090/register/progressives?id=' + taskRes.data.taskId)
                .then(res => {
                    setRegisterForm({...registerForm , progressives : res.data.progressives ,
                        task : {
                            businessType: registerForm.task.businessType,
                            cause: registerForm.task.cause,
                            completeDate: registerForm.task.completeDate,
                            customNo: taskRes.data.customNo,
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
                            title: registerForm.task.title
                        }
                    });

                    alert("Saved!")
                });
        });
    }

    const onAddRelatedLink = () => {
        setLinks([...links, nameInput.current.value]);

        axios.post('http://localhost:9090/register/link',{
            taskId : registerForm.task.id,
            link : nameInput.current.value
        }).then(res => {

        });
    }

    const uploadFileHandler = (event) => {
        setFiles(event.target.files);
    };

    const fileSubmitHandler = (event) => {
        event.preventDefault();
        setFileSize(true);
        setFileUploadProgress(true);
        setFileUploadResponse(null);

        const formData = new FormData();

        for (let i = 0; i < files.length; i++) {
            if (files[i].size > 1024){
                setFileSize(false);
                setFileUploadProgress(false);
                setFileUploadResponse(null);
                return;
            }

            formData.append(`files`, files[i])
            formData.append(`taskId`, registerForm.task.id)
        }

        const requestOptions = {
            method: 'POST',
            body: formData
        };

        fetch(FILE_UPLOAD_BASE_ENDPOINT, requestOptions)
            .then(async response => {
                const isJson = response.headers.get('content-type')?.includes('application/json');
                const data = isJson && await response.json();

                // check for error response
                if (!response.ok) {
                    // get error message
                    const error = (data && data.message) || response.status;
                    setFileUploadResponse(data.message);
                    return Promise.reject(error);
                }

                console.log(data.message);
                setFileUploadResponse(data.message);
            })
            .catch(error => {
                console.error('Error while uploading file!', error);
            });
        setFileUploadProgress(false);
    };

    return (
        <>
            <div className="card">
                <div className="card-body">
                    <div className="row">
                        <div className="col d-grid gap-2 d-md-flex justify-content-md-start mb-2">
                            {prePage()}
                        </div>
                        <div className="col d-grid gap-2 d-md-flex justify-content-md-end mb-2">
                            <button className="btn btn-primary" type="button">Refresh</button>
                            <button className="btn btn-primary" onClick={onSaveTask} type="button">Save</button>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col">
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">* 고객사</label>
                                <div className="col-sm-8">
                                    <select className="form-control" defaultValue={registerForm.task.customNo} onChange={(event) => { onRegisterFormHandler("customNo", event)}}>
                                        {
                                            customers.map((item: { customerNo : string ; customerName : string; }) => <option value={item.customerNo} >{item.customerName}</option>)
                                        }
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">* 업무</label>
                                <div className="col-sm-8">
                                    <select className="form-control" defaultValue={registerForm.task.businessType} onChange={(event) => { onRegisterFormHandler("businessType", event)}} >
                                        {
                                            businessType.map((item: { code: string ; value: string; }) => <option value={item.code} >{item.value}</option>)
                                        }
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">접수일시</label>
                                <div className="col-sm-8">
                                    <input type="date" aria-label="First name" className="form-control" defaultValue={registerForm.task.receiptDate}  onChange={(event) => { onRegisterFormHandler("receiptDate", event)}} />
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">요청자</label>
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
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">* 요청타입</label>
                                <div className="col-sm-8">
                                    <select className="form-control" defaultValue={registerForm.task.requireType}  onChange={(event) => { onRegisterFormHandler("requireType", event)}} >
                                        {
                                            requireType.map((item: { code: string ; value: string; }) => <option value={item.code} >{item.value}</option>)
                                        }
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">* 우선순위</label>
                                <div className="col-sm-8">
                                    <select className="form-control" defaultValue={registerForm.task.priorityType}  onChange={(event) => { onRegisterFormHandler("priorityType", event)}} >
                                        {
                                            priorityType.map((item: { code: string ; value: string; }) => <option value={item.code} >{item.value}</option>)
                                        }
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">완료예정</label>
                                <div className="col-sm-8">
                                    <input type="date" aria-label="First name" className="form-control" defaultValue={registerForm.task.expectedCompleteDate} onChange={(event) => { onRegisterFormHandler("expectedCompleteDate", event)}} />
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">접수번호</label>
                                <div className="col-sm-8">
                                    <input type="text" className="form-control" defaultValue={registerForm.task.receiptNo} onChange={(event) => { onRegisterFormHandler("receiptNo", event)}} readOnly />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row mt-1" >
                        <div className="col-sm-6" >
                            <div className="input-group mb-3">
                                <button className="btn btn-outline-secondary" type="button" onClick={onAddRelatedLink} id="button-addon1">
                                    연관링크 추가
                                </button>
                                <input type="text" className="form-control" id="relatedLink"  ref={nameInput}  />
                            </div>
                        </div>
                        <div className="col" >
                            {links?.map(item => {
                                if(item){
                                    return <Link href={item}><button className="btn btn-outline-secondary me-1" type="button" id="button-addon1">{item}</button></Link>
                                }else{
                                    return ""
                                }
                            })}

                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">상태</label>
                                <div className="col-sm-8">
                                    <input type="text" className="form-control disabled" defaultValue={registerForm.task.stepType}   onChange={(event) => { onRegisterFormHandler("statusType", event)}} readOnly />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row mt-1 mb-1">
                        {
                            registerForm.progressives.sort((a, b) =>  a.order - b.order  )
                                .map((item: {assignUser: string, stepType: string,  timeStamp: string, lastStep : boolean, assignUserId : string, stepTypeCode : string}, index) => {
                                    return (
                                        <div className="col-xl-2 col-md-2 p-1">
                                            <div className="card bg-pattern">
                                                <button type="button" onClick={() => {onStartTask(item.assignUserId, item.assignUser, item.stepTypeCode)}} className={item.lastStep ? "btn btn-warning" : "btn btn-light" } >
                                                    <h6 className="text-muted mb-0 text-sm-center">{item.stepType} </h6>
                                                    <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center"> { item.assignUser ? "[" + item.assignUser + "]" : " " }</h6>
                                                    <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center">{item.timeStamp}</h6>
                                                </button>
                                            </div>
                                        </div>
                                    )
                            })
                        }
                    </div>
                    <div className="row" >
                        <div className="col-md-8" >
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">* 제목</label>
                                <div className="col-sm-10">
                                    <textarea className="form-control" rows={1} id="title" defaultValue={registerForm.task.title}  onChange={(event) => { onRegisterFormHandler("title", event)}} />
                                </div>
                            </div>
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">* 현상</label>
                                <div className="col-sm-10">
                                    <textarea className="form-control" rows={6} id="cause"  defaultValue={registerForm.task.cause}  onChange={(event) => { onRegisterFormHandler("cause", event)}} />
                                </div>
                            </div>
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">* 개선방안</label>
                                <div className="col-sm-10">
                                    <textarea className="form-control" rows={6} id="howToFix" defaultValue={registerForm.task.howToFix}  onChange={(event) => { onRegisterFormHandler("howToFix", event)}} />
                                </div>
                            </div>
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">첨부파일</label>
                                <div className="col-sm-10">
                                    <form onSubmit={fileSubmitHandler}>
                                        <div className="row" >
                                            <div className="col-sm-10">
                                                <input  className="form-control"  type="file" id="formFileMultiple"   multiple onChange={uploadFileHandler} />
                                            </div>

                                            {!fileSize && <p style={{color:'red'}}>File size exceeded!!</p>}
                                            {fileUploadProgress && <p style={{color:'red'}}>Uploading File(s)</p>}
                                            {fileUploadResponse!=null && <p style={{color:'green'}}>{fileUploadResponse}</p>}
                                            <div className="col-sm-2">
                                                <button className="btn btn-light" type='submit'>Upload</button>
                                            </div>
                                        </div>

                                    </form>
                                </div>
                            </div>
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">처리결과</label>
                                <div className="col-sm-10">
                                    <textarea className="form-control" rows={7} id="result" defaultValue={registerForm.task.result}  onChange={(event) => { onRegisterFormHandler("result", event)}} />
                                </div>
                            </div>
                        </div>
                        <div className="col-md-4" >
                            <div className="form-group mb-2">
                                <label htmlFor="comment">* 접수자 의견</label>
                                <textarea className="form-control" rows={11} id="receiptOpinion" defaultValue={registerForm.task.receiptOpinion}   onChange={(event) => { onRegisterFormHandler("receiptOpinion", event)}}  />
                            </div>
                            <div className="form-group">
                                <label htmlFor="comment">* 개발자 의견</label>
                                <textarea className="form-control" rows={11} id="developerOpinion" defaultValue={registerForm.task.developerOpinion} onChange={(event) => { onRegisterFormHandler("developerOpinion", event)}}   />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default withRouter(Register);