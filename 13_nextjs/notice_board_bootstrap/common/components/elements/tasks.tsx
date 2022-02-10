import styles from "../../../styles/components/elements/Tasks.module.css";
import Link from 'next/link'

const Tasks = (props : { gridData:[ {
        taskNo : number,
        custmName : string,
        title : string,
        priorityType : string,
        requireType : string,
        errorType : string,
        receiptDate : string,
        finishedDate : string,
        statusType : string

    }]|null }) => {
    return (
        <div className="row">
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
                                <th scope="col">요청타입</th>
                                <th scope="col">오류타입</th>
                                <th scope="col">접수일자</th>
                                <th scope="col">완료예정일</th>
                                <th scope="col">상태</th>
                            </tr>
                            </thead>
                            <tbody>
                                    {
                                        props.gridData &&
                                        props.gridData
                                        .map(item => {
                                            return <Link href={{
                                                    pathname: '/register/register',
                                                    query: { inputType : "UPDATE", id : item.taskNo} // array라 문자화
                                                }} >
                                                <tr>
                                                    <th scope="row">{item.taskNo}</th>
                                                    <td>{item.custmName}</td>
                                                    <td>{item.title}</td>
                                                    <td>
                                                        {item.priorityType}
                                                    </td>
                                                    <td>
                                                        {item.requireType}
                                                    </td>
                                                    <td>
                                                        {item.errorType}
                                                    </td>
                                                    <td>
                                                        {item.receiptDate}
                                                    </td>
                                                    <td>
                                                        {item.finishedDate}
                                                    </td>
                                                    <td>
                                                        {item.statusType}
                                                    </td>
                                                </tr>
                                            </Link>
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
                            <li className="page-item"><a className="page-link" href="#">1</a></li>
                            <li className="page-item active"><a className="page-link" href="#">2</a></li>
                            <li className="page-item"><a className="page-link" href="#">3</a></li>
                            <li className="page-item">
                                <a className="page-link" href="#">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Tasks;