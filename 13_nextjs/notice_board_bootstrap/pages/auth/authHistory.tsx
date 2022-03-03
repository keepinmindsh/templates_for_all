import {withRouter} from 'next/router'
import {ChangeEvent, useState} from 'react'
import axios from 'axios'
import Header from "../../common/components/elements/header";

const AuthHistory = ({ router: { query } }) => {

    const [authType, setAuthType] = useState('DEV');

    const hostUrl: string = process.env.NEXT_PUBLIC_BACK_API_HOST;

    const [historyListData, setHistoryListData] = useState<[{
        userId: string
        type: string
        authChangeDesc: string
        authChangeDate: string
    }]>([{
        userId: "",
        type: "",
        authChangeDesc: "",
        authChangeDate: "",
    }]);

    const onAuthTypeHandler = (event: ChangeEvent) => {
        setAuthType(event.target.value);
    }

    const onSearchBtn = () => {
        axios.get(hostUrl + '/auth/historys/' + authType)
            .then(res => {
                setHistoryListData(res.data.authHists);
            });
    }

    return (
        <>
            <Header />

            <div className="row">
                <div className="col-xl-12" >
                    <div className="row mt-2" >
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                    className="col-sm-3 col-form-label col-form-label-sm text-end">권한 구분</label>
                                <div className="col-sm-7">
                                    <select className="form-control" onChange={(event) => { onAuthTypeHandler(event) }} >
                                        <option value={"DEV"} >개발</option>
                                        <option value={"STEP"} >고객</option>
                                        <option value={"MENU"} >메뉴</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            {/* <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                    className="col-sm-3 col-form-label col-form-label-sm text-end" >기간</label>
                                <div className="col-sm-7">
                                    <div className="input-group">
                                        <input type="date" aria-label="First name" max="2099-12-31" className="form-control" />
                                        <input type="date" aria-label="Last name" max="2099-12-31" className="form-control" />
                                    </div>
                                </div>
                            </div> */}
                        </div>

                    </div>

                    <div className="row mt-2" >

                        <div className="col" >
                            <div className="form-group row">
                                <div className="col-sm-11 col d-grid gap-2 d-md-flex justify-content-md-end mb-2">
                                    <button className="btn btn-primary" onClick={onSearchBtn} type="button">Search</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="row mt-2 mb-2" >
                    </div>
                </div>
            </div>
            <div className="card">
                <div className="card-body">
                    <div className="table-responsive project-list">
                        <table className="table project-table table-centered table-nowrap">
                            <thead>
                                <tr>
                                    <th scope="col">사용자 ID</th>
                                    <th scope="col">구분</th>
                                    <th scope="col">내경 내용</th>
                                    <th scope="col">내경 일자</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    historyListData &&
                                    historyListData
                                        .map(item => {
                                            return <tr key={item.userId}>
                                                <th scope="row">{item.userId}</th>
                                                <td>{item.type}</td>
                                                <td>{item.authChangeDesc}</td>
                                                <td>{item.authChangeDate}</td>
                                            </tr>
                                        })
                                }

                            </tbody>
                        </table>
                    </div>
                    <div className="pt-3">
                        <ul className="pagination justify-content-end mb-0">
                            <li className="page-item disabled">
                                <a className="page-link" href="#" tabIndex={-1} aria-disabled="true">Previous</a>
                            </li>
                            <li className="page-item active"><a className="page-link" href="#">1</a></li>
                            <li className="page-item"><a className="page-link" href="#">2</a></li>
                            <li className="page-item"><a className="page-link" href="#">3</a></li>
                            <li className="page-item">
                                <a className="page-link" href="#">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </>
    )
}


export default withRouter(AuthHistory);
