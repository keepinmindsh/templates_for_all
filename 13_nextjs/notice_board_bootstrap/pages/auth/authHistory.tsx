import { useRouter } from 'next/router'
import {useState, useEffect, useRef, ChangeEvent, Key} from 'react'
import {withRouter} from 'next/router';
import Link from 'next/link'
import axios from 'axios'
import {getCookie, loadLocalStorage} from "../../common/cookie/cookieHandle";
import Header from "../../common/components/elements/header";

const AuthHistory = ({router: {query}}) => {

    return (
        <>
            <Header />
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
                                    <select className="form-control" >
                                        <option value={""} >All</option>

                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group row">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-3 col-form-label col-form-label-sm text-end">우선순위</label>
                                <div className="col-sm-9">
                                    <select className="form-control" >
                                        <option value={""} >All</option>
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
                                        <input type="date" aria-label="First name" max="2099-12-31" className="form-control"  />
                                        <input type="date" aria-label="Last name" max="2099-12-31" className="form-control"  />
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
                                        <input type="date" aria-label="First name" className="form-control" max="2099-12-31" />
                                        <input type="date" aria-label="Last name" className="form-control" max="2099-12-31" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-4" >
                        </div>
                    </div>
                    <div className="row mt-2 mb-2" >
                    </div>
                </div>
                <div className="col-xl-4" >
                    <div className="row p-1">
                    </div>
                    <div className="row p-1">
                    </div>
                </div>
            </div>
            <div className="card">
                <div className="card-body">
                    <div className="table-responsive project-list">
                        <table className="table project-table table-centered table-nowrap">
                            <thead>
                            <tr>
                                <th scope="col">번호</th>
                                <th scope="col">고객사</th>
                                <th scope="col">제목</th>
                                <th scope="col">우선순위</th>
                                <th scope="col">우선순위</th>
                                <th scope="col">우선순위</th>
                                <th scope="col">우선순위</th>
                                <th scope="col">우선순위</th>
                                <th scope="col">우선순위</th>
                                <th scope="col">우선순위</th>
                            </tr>
                            </thead>
                            <tbody>
                            {/*{
                                        props.gridData &&
                                        props.gridData
                                            .map(item => {
                                                return  <tr>
                                                        <th scope="row">{item.receiptNo}</th>
                                                        <td>{item.custmName}</td>
                                                        <td>{item.title}</td>
                                                        <td>
                                                            {item.priorityType}
                                                        </td>
                                                        <td>
                                                            {item.requireType}
                                                        </td>
                                                        <td>
                                                            {item.receiptDate}
                                                        </td>
                                                        <td>
                                                            {item.finishedDate}
                                                        </td>
                                                        <td>
                                                            {item.stepType}
                                                        </td>
                                                    </tr>
                                            })
                                    }*/}

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