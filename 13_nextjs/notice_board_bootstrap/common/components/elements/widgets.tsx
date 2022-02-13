import {useState, useEffect, ChangeEvent} from 'react'
import axios from 'axios'
import Link from 'next/link'

const Widgets = (props : { applyFunc : (param:[number]) => void }) => {

    const [businessType, setBusinessType] = useState<any>([]);
    const [requireType, setRequireType] = useState<any>([]);
    const [priorityType, setPriorityType] = useState<any>([]);
    const [customers, setCustomers] = useState<any>([]);
    const [buttonStatus, setButtonStatus] = useState<String>("CUSTOMER_RECEIPT");
    const [steps, setSteps] = useState<any[]>([]);

    const loginInfo = {
        userId : "shjeong",
        userName : "seung hwa"
    }

    const [searchForm, setSearchForm ] = useState<{
            custmNo : string|null,
            title : string|null,
            priorityType : string|null,
            requireType : string|null,
            businessType : string|null,
            receiptStartDate : string|null,
            receiptEndDate : string|null,
            finishedStartDate : string|null,
            finishedEndDate : string|null,
            searchKey : string|null

        }>({
            custmNo : "",
            title : "",
            priorityType : "",
            requireType : "",
            businessType : "",
            receiptStartDate : "",
            receiptEndDate : "",
            finishedStartDate : "",
            finishedEndDate : "",
            searchKey : ""
        });

    const hostUrl:string = "http://localhost:9090"

    useEffect(() => {
        axios.get(hostUrl + '/codes?codeType=BUSINESS_TYPE')
            .then(res => {
                setBusinessType(res.data);
            });
        axios.get(hostUrl + '/codes?codeType=REQUIRE_TYPE')
            .then(res => {
                setRequireType(res.data);
            });
        axios.get(hostUrl + '/codes?codeType=PRIORITY_TYPE')
            .then(res => {
                setPriorityType(res.data);
            });1
        axios.get(hostUrl + '/customers')
            .then(res => {
                setCustomers(res.data);
            });
        axios.get(hostUrl + '/steps')
            .then(res => {
                setSteps(res.data);
            });
    }, []);

    const applyButtonStatus = (button : String) : void => {
        setButtonStatus(button)
    }

    const searchForTasks = (value:String|null) : void => {
        const queryString : string =
            "custmNo=" + searchForm.custmNo + "&"
            + "requireType=" + searchForm.requireType + "&"
            + "businessType=" + searchForm.businessType + "&"
            + "priorityType=" + searchForm.priorityType + "&"
            + "receiptStartDate=" + searchForm.receiptStartDate?.replaceAll(/-/g, "") + "&"
            + "receiptEndDate=" + searchForm.receiptEndDate?.replaceAll(/-/g, "") + "&"
            + "finishedStartDate=" + searchForm.finishedStartDate?.replaceAll(/-/g, "") + "&"
            + "finishedEndDate=" + searchForm.finishedEndDate?.replaceAll(/-/g, "") + "&"
            + "buttonStatus=" + buttonStatus + "&"
            + "searchKey=" + searchForm.searchKey;

        axios.get(hostUrl + '/tasks?' + queryString)
            .then(res => {
                props.applyFunc(res.data)
            });
    }

    const onSearchChangeHandler = ( name : string, event : ChangeEvent) => {
        setSearchForm({...searchForm, [name] : event.target.value});
    }

    return (
        <>
            <div className="row mt-2" >
                <div className="row mt-2" >
                    <div className="col-sm-8" >
                    </div>
                    <div className="col-sm-4" >
                        <Link href={{
                            pathname: '/register/register',
                            query: { inputType : "NEW", assignUserId : loginInfo.userId , assignUserName : loginInfo.userName } // array라 문자화
                        }}
                        >
                            <button className="btn btn-primary float-end" type="button">신규등록</button>
                        </Link>
                    </div>
                </div>
            </div>
            <div className="row">
                <div className="col-xl-8" >
                    <div className="row mt-2" >
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-3 col-form-label col-form-label-sm text-end">고객사</label>
                                <div className="col-sm-9">
                                    <select className="form-control" onChange={(event) => { onSearchChangeHandler("custmNo", event)}}>
                                        <option value={""} >All</option>
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
                                       className="col-sm-3 col-form-label col-form-label-sm text-end">요청타입</label>
                                <div className="col-sm-9">
                                    <select className="form-control"  onChange={(event) => { onSearchChangeHandler("requireType", event)}} >
                                        <option value={""} >All</option>
                                        {
                                            requireType.map((item: { code: string ; value: string; }) => <option value={item.code} >{item.value}</option>)
                                        }
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row mt-2" >
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-3 col-form-label col-form-label-sm text-end">업무</label>
                                <div className="col-sm-9">
                                    <select className="form-control" onChange={(event) => { onSearchChangeHandler("businessType", event)}}>
                                        <option value={""} >All</option>
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
                                       className="col-sm-3 col-form-label col-form-label-sm text-end">우선순위</label>
                                <div className="col-sm-9">
                                    <select className="form-control" onChange={(event) => { onSearchChangeHandler("priorityType", event)}}>
                                        <option value={""} >All</option>
                                        {
                                            priorityType.map((item: { code: string ; value: string; }) => <option value={item.code} >{item.value}</option>)
                                        }
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row mt-2" >
                        <div className="col-md-8" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-3 col-form-label col-form-label-sm text-end" style={{width: 95 + "px"}}>접수 기간</label>
                                <div className="col-sm-9">
                                    <div className="input-group">
                                        <input type="date" aria-label="First name" className="form-control"  onChange={(event) => { onSearchChangeHandler("receiptStartDate", event)}} />
                                        <input type="date" aria-label="Last name" className="form-control"  onChange={(event) => { onSearchChangeHandler("receiptEndDate", event)}} />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-4" >
                        </div>
                    </div>
                    <div className="row mt-2" >
                        <div className="col-md-8" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-3 col-form-label col-form-label-sm text-end" style={{width: 95 + "px"}}>완료 기간</label>
                                <div className="col-sm-9">
                                    <div className="input-group">
                                        <input type="date" aria-label="First name" className="form-control"  onChange={(event) => { onSearchChangeHandler("finishedStartDate", event)}} />
                                        <input type="date" aria-label="Last name" className="form-control"  onChange={(event) => { onSearchChangeHandler("finishedEndDate", event)}} />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-4" >
                        </div>
                    </div>
                    <div className="row mt-2 mb-2" >
                        <form>
                            <div className="form-group mb-0">
                                <div className="input-group mb-0">
                                    <input type="text" className="form-control" placeholder="Search..."
                                           aria-describedby="project-search-addon"  onChange={(event) => { onSearchChangeHandler("searchKey", event)}} />
                                    <div className="input-group-append">
                                        <button className="btn btn-danger" onClick={searchForTasks} type="button" id="project-search-addon" ><i className="fa fa-search search-icon font-12"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div className="col-xl-4" >
                    <div className="row p-1">
                        {
                            steps.map((item: { stepType: string ; stepName: string; counts: number; }, index) => {
                                if(index <= 2 ) {
                                    return (
                                        <div className="col p-1">
                                            <div className="card bg-pattern">
                                                <button type="button" onClick={() => {applyButtonStatus(item.stepType); searchForTasks(item.stepType);} } className={buttonStatus == item.stepType ? "btn btn-warning" : "btn btn-light"}>
                                                    <h6 className="text-muted mb-0 text-sm-center">{item.stepName}</h6>
                                                    <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center">[ {item.counts} ]</h6>
                                                </button>
                                            </div>
                                        </div>
                                    )
                                }
                            })
                        }
                    </div>
                    <div className="row p-1">
                        {
                            steps.map((item: { stepType: string ; stepName: string; counts: number; }, index) => {
                                if(index > 2 ) {
                                    return (
                                        <div className="col p-1">
                                            <div className="card bg-pattern">
                                                <button type="button" onClick={() => {applyButtonStatus(item.stepType); searchForTasks(item.stepType);} } className={buttonStatus == item.stepType ? "btn btn-warning" : "btn btn-light"}>
                                                    <h6 className="text-muted mb-0 text-sm-center">{item.stepName}</h6>
                                                    <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center">[ {item.counts} ]</h6>
                                                </button>
                                            </div>
                                        </div>
                                    )
                                }
                            })
                        }
                    </div>
                </div>
            </div>
        </>
    );
};

export default Widgets;