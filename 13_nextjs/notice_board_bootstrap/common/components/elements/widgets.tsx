import { useState, useEffect} from 'react'
import axios from 'axios'

const Widgets = () => {

    const [businessType, setBusinessType] = useState<any>([]);
    const [requireType, setRequireType] = useState<any>([]);
    const [priorityType, setPriorityType] = useState<any>([]);
    const [customers, setCustomers] = useState<any>([]);
    const [buttonStatus, setButtonStatus] = useState<String>("CUSTOMER_RECEIPT");
    const [steps, setSteps] = useState<any[]>([]);

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
            });1
        axios.get('http://localhost:9090/customers')
            .then(res => {
                setCustomers(res.data);
            });
        axios.get('http://localhost:9090/steps')
            .then(res => {
                setSteps(res.data);
            });
    }, []);

    const applyButtonStatus = (button : String) : void => {
        setButtonStatus(button)
    }


    return (
        <>
            <div className="row">
                <div className="col-xl-8" >
                    <div className="row mt-2" >
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-3 col-form-label col-form-label-sm text-end">고객사</label>
                                <div className="col-sm-9">
                                    <select className="form-control">
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
                                    <select className="form-control">
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
                                    <select className="form-control">
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
                                    <select className="form-control">
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
                        <form>
                            <div className="form-group mb-0">
                                <div className="input-group mb-0">
                                    <input type="text" className="form-control" placeholder="Search..."
                                           aria-describedby="project-search-addon"/>
                                    <div className="input-group-append">
                                        <button className="btn btn-danger" type="button" id="project-search-addon"><i
                                            className="fa fa-search search-icon font-12"></i>
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
                                                <button type="button" onClick={() => applyButtonStatus(item.stepType) } className={buttonStatus == item.stepType ? "btn btn-warning" : "btn btn-light"}>
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
                                                <button type="button" onClick={() => applyButtonStatus(item.stepType) } className={buttonStatus == item.stepType ? "btn btn-warning" : "btn btn-light"}>
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