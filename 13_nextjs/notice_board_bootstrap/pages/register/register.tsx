import { useRouter } from 'next/router'
import {useState, useEffect, ChangeEvent} from 'react'
import axios from 'axios'

const Register = () => {

    const [businessType, setBusinessType] = useState<any>([]);
    const [requireType, setRequireType] = useState<any>([]);
    const [priorityType, setPriorityType] = useState<any>([]);
    const [customers, setCustomers] = useState<any>([]);

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
            statusType: string | null
            title: string | null
        },
        progressives : [
            {
                assignUser: string
                stepType: string
                timeStamp: string
                order: number
                lastStep : boolean
            }
        ],
        fileAttacheds : [
            any
        ]
    }>({
        fileAttacheds: [null],
        progressives: [{assignUser: "", stepType: "", timeStamp: ""}],
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
            requestUserId: null,
            requireType: null,
            result: null,
            statusType: null,
            title: null
        }
    })

    useEffect(() => {
        // TODO - 정상동작 안함
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
        axios.get('http://localhost:9090/register/tasks')
            .then(res => {
                setRegisterForm({...registerForm , task : res.data.task , progressives : res.data.progressives });
            });
    }, [])

    const prePage = () => {
        const router = useRouter()
        return <button className="btn btn-primary" onClick={() => router.back()} type="button">뒤로가기</button>
    }

    const onRegisterFormHandler = ( name : string, event : ChangeEvent) => {
        setRegisterForm({...registerForm, [name] : event.target.value});
    }

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
                            <button className="btn btn-primary" type="button">Save</button>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col">
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">* 고객사</label>
                                <div className="col-sm-8">
                                    <select className="form-control" value={registerForm.task.customNo} >
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
                                    <select className="form-control" value={registerForm.task.businessType} >
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
                                    <input type="date" aria-label="First name" className="form-control" value={registerForm.task.receiptDate}  onChange={(event) => { onRegisterFormHandler("finishedStartDate", event)}} />
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">요청자</label>
                                <div className="col-sm-8">
                                    <input type="text" className="form-control" value={registerForm.task.requestUserId}  onChange={(event) => { onRegisterFormHandler("searchKey", event)}} readOnly />
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
                                    <select className="form-control" value={registerForm.task.receiptNo}  onChange={(event) => { onRegisterFormHandler("finishedStartDate", event)}} >
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
                                    <select className="form-control" value={registerForm.task.priorityType}  onChange={(event) => { onRegisterFormHandler("finishedStartDate", event)}} >
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
                                    <input type="date" aria-label="First name" className="form-control" value={registerForm.task.completeDate} onChange={(event) => { onRegisterFormHandler("finishedStartDate", event)}} />
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">접수번호</label>
                                <div className="col-sm-8">
                                    <input type="text" className="form-control" value={registerForm.task.receiptNo} onChange={(event) => { onRegisterFormHandler("searchKey", event)}} readOnly />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row mt-1" >
                        <div className="col-sm-9" >
                            <div className="input-group mb-3">
                                <button className="btn btn-outline-secondary" type="button" id="button-addon1">
                                    연관링크 추가
                                </button>
                                <input type="text" className="form-control" placeholder=""  aria-label="Example text with button addon" aria-describedby="button-addon1" />
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-4 col-form-label col-form-label-sm text-end">상태</label>
                                <div className="col-sm-8">
                                    <input type="text" className="form-control disabled" value={registerForm.task.statusType}   onChange={(event) => { onRegisterFormHandler("searchKey", event)}} readOnly />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row mt-1 mb-1">
                        {
                            registerForm.progressives.sort((a, b) =>  a.order - b.order  )
                                .map((item: {assignUser: string, stepType: string,  timeStamp: string, lastStep : boolean}, index) => {
                                    return (
                                        <div className="col-xl-2 col-md-2 p-1">
                                            <div className="card bg-pattern">
                                                <button type="button" className={item.lastStep ? "btn btn-warning" : "btn btn-light" } >
                                                    <h6 className="text-muted mb-0 text-sm-center">{item.stepType} [{item.assignUser}]</h6>
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
                                    <textarea className="form-control" rows={1} id="title" value={registerForm.task.title}/>
                                </div>
                            </div>
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">* 현상</label>
                                <div className="col-sm-10">
                                    <textarea className="form-control" rows={6} id="cause"  value={registerForm.task.cause} />
                                </div>
                            </div>
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">* 개선방안</label>
                                <div className="col-sm-10">
                                    <textarea className="form-control" rows={6} id="howToFix" value={registerForm.task.howToFix} />
                                </div>
                            </div>
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">첨부파일</label>
                                <div className="col-sm-10">
                                    <input className="form-control" type="file" id="formFileMultiple" multiple />
                                </div>
                            </div>
                            <div className="form-group row mb-2">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-2 col-form-label col-form-label-sm text-end">처리결과</label>
                                <div className="col-sm-10">
                                    <textarea className="form-control" rows={7} id="result" value={registerForm.task.result} />
                                </div>
                            </div>
                        </div>
                        <div className="col-md-4" >
                            <div className="form-group mb-2">
                                <label htmlFor="comment">* 접수자 의견</label>
                                <textarea className="form-control" rows={11} id="receiptOpinion" value={registerForm.task.receiptOpinion}   />
                            </div>
                            <div className="form-group">
                                <label htmlFor="comment">* 개발자 의견</label>
                                <textarea className="form-control" rows={11} id="developerOpinion" value={registerForm.task.developerOpinion}  />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Register;