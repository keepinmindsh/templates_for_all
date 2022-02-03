import styles from "../../../styles/components/elements/Tasks.module.css";
import Link from 'next/link'

const Tasks = () => {
    return (
        <div className="row">
            <div className="card">
                <div className="card-body">
                    <div className="table-responsive project-list">
                        <table className="table project-table table-centered table-nowrap">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Projects</th>
                                <th scope="col">Start Date</th>
                                <th scope="col">Status</th>
                                <th scope="col">Team</th>
                                <th scope="col">Progress</th>
                                <th scope="col">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th scope="row">1</th>
                                <td>New admin Design</td>
                                <td>02/5/2019</td>
                                <td>
                                    <span className="text-success font-12"><i className="mdi mdi-checkbox-blank-circle mr-1"></i> Completed</span>
                                </td>
                                <td>
                                    <div className="team">
                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Roger Drake">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar6.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>

                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Reggie James">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar7.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>

                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Gerald Mayberry">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar8.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>
                                    </div>
                                </td>
                                <td>
                                    <p className="mb-0">Progress<span className="float-right">100%</span></p>

                                    <div className="progress mt-2" style={{height: 5 + 'px'}}>
                                        <div className="progress-bar bg-success" role="progressbar" style={{width: 100 + '%'}} aria-valuenow={100} aria-valuemin={0} aria-valuemax={100} />
                                    </div>
                                </td>

                                <td>
                                    <div className="action">
                                        <Link href="/register/register" >
                                            <a href="#" className="text-success mr-4" data-toggle="tooltip" data-placement="top" title="" data-original-title="Edit"> <i className="fa fa-pencil h5 m-0"></i></a>
                                        </Link>
                                        <a href="#" className="text-danger" data-toggle="tooltip" data-placement="top" title=""
                                           data-original-title="Close"> <i className="fa fa-remove h5 m-0"></i></a>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <th scope="row">2</th>
                                <td>Landing page Design</td>
                                <td>04/6/2019</td>
                                <td>
                                    <span className="text-primary font-12"><i className="mdi mdi-checkbox-blank-circle mr-1"></i> Pending</span>
                                </td>
                                <td>
                                    <div className="team">

                                    </div>
                                </td>
                                <td>
                                    <p className="mb-0">Progress<span className="float-right">78%</span></p>

                                    <div className="progress mt-2" style={{height: 5 + 'px'}}>
                                        <div className="progress-bar bg-primary" role="progressbar" style={{width: 78 + '%'}} aria-valuenow="78" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </td>

                                <td>
                                    <div className="action">
                                        <a href="#" className="text-success mr-4" data-toggle="tooltip" data-placement="top"
                                           title="" data-original-title="Edit"> <i className="fa fa-pencil h5 m-0"></i></a>
                                        <a href="#" className="text-danger" data-toggle="tooltip" data-placement="top" title=""
                                           data-original-title="Close"> <i className="fa fa fa-remove h5 m-0"></i></a>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <th scope="row">3</th>
                                <td>Multipurpose Landing Template</td>
                                <td>06/6/2019</td>
                                <td>
                                    <span className="text-success font-12"><i className="mdi mdi-checkbox-blank-circle mr-1"></i> Completed</span>
                                </td>
                                <td>
                                    <div className="team">
                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Neil Wing">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar3.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>

                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Stanley Barber">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar4.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>
                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Roger Drake">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar5.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>

                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Jack Krier">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar6.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>
                                    </div>
                                </td>
                                <td>
                                    <p className="mb-0">Progress<span className="float-right">100%</span></p>

                                    <div className="progress mt-2" style={{height: 5 + 'px'}}>
                                        <div className="progress-bar bg-success" role="progressbar" style={{width: 100 + '%'}}
                                             aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </td>

                                <td>
                                    <div className="action">
                                        <a href="#" className="text-success mr-4" data-toggle="tooltip" data-placement="top"
                                           title="" data-original-title="Edit"> <i className="fa fa-pencil h5 m-0"></i></a>
                                        <a href="#" className="text-danger" data-toggle="tooltip" data-placement="top" title=""
                                           data-original-title="Close"> <i className="fa fa fa-remove h5 m-0"></i></a>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">4</th>
                                <td>Blog Template Design</td>
                                <td>07/5/2019</td>
                                <td>
                                    <span className="text-success font-12"><i className="mdi mdi-checkbox-blank-circle mr-1"></i> Completed</span>
                                </td>
                                <td>
                                    <div className="team">
                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Roger Drake">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar7.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>

                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Reggie James">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar8.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>

                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Gerald Mayberry">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>
                                    </div>
                                </td>
                                <td>
                                    <p className="mb-0">Progress<span className="float-right">100%</span></p>

                                    <div className="progress mt-2" style={{height: 5 + 'px'}}>
                                        <div className="progress-bar bg-success" role="progressbar" style={{width: 100 + '%'}}
                                             aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </td>

                                <td>
                                    <div className="action">
                                        <a href="#" className="text-success mr-4" data-toggle="tooltip" data-placement="top"
                                           title="" data-original-title="Edit"> <i className="fa fa-pencil h5 m-0"></i></a>
                                        <a href="#" className="text-danger" data-toggle="tooltip" data-placement="top" title=""
                                           data-original-title="Close"> <i className="fa fa fa-remove h5 m-0"></i></a>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <th scope="row">5</th>
                                <td>Brand logo design</td>
                                <td>08/6/2019</td>
                                <td>
                                    <span className="text-primary font-12"><i className="mdi mdi-checkbox-blank-circle mr-1"></i> Pending</span>
                                </td>
                                <td>
                                    <div className="team">
                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Deborah Mixon">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>

                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Scott Jessie">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar2.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>
                                    </div>
                                </td>
                                <td>
                                    <p className="mb-0">Progress<span className="float-right">54%</span></p>

                                    <div className="progress mt-2" style={{height: 5 + 'px'}}>
                                        <div className="progress-bar bg-primary" role="progressbar" style={{width: 100 + '%'}}
                                             aria-valuenow="54" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </td>

                                <td>
                                    <div className="action">
                                        <a href="#" className="text-success mr-4" data-toggle="tooltip" data-placement="top"
                                           title="" data-original-title="Edit"> <i className="fa fa-pencil h5 m-0"></i></a>
                                        <a href="#" className="text-danger" data-toggle="tooltip" data-placement="top" title=""
                                           data-original-title="Close"> <i className="fa fa fa-remove h5 m-0"></i></a>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <th scope="row">6</th>
                                <td>Redesign - Landing page</td>
                                <td>10/6/2019</td>
                                <td>
                                    <span className="text-primary font-12"><i className="mdi mdi-checkbox-blank-circle mr-1"></i> Pending</span>
                                </td>
                                <td>
                                    <div className="team">
                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Neil Wing">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar6.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>

                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Stanley Barber">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar5.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>
                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Roger Drake">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar4.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>

                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Jack Krier">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar3.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>
                                    </div>
                                </td>
                                <td>
                                    <p className="mb-0">Progress<span className="float-right">41%</span></p>

                                    <div className="progress mt-2" style={{height: 5 + 'px'}}>
                                        <div className="progress-bar bg-primary" role="progressbar" style={{width: 41 + '%'}}
                                             aria-valuenow="41" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </td>

                                <td>
                                    <div className="action">
                                        <a href="#" className="text-success mr-4" data-toggle="tooltip" data-placement="top"
                                           title="" data-original-title="Edit"> <i className="fa fa-pencil h5 m-0"></i></a>
                                        <a href="#" className="text-danger" data-toggle="tooltip" data-placement="top" title=""
                                           data-original-title="Close"> <i className="fa fa fa-remove h5 m-0"></i></a>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">7</th>
                                <td>Redesign - Dashboard</td>
                                <td>12/5/2019</td>
                                <td>
                                    <span className="text-success font-12"><i className="mdi mdi-checkbox-blank-circle mr-1"></i> Completed</span>
                                </td>
                                <td>
                                    <div className="team">
                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Roger Drake">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar6.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>

                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Reggie James">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar7.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>
                                    </div>
                                </td>
                                <td>
                                    <p className="mb-0">Progress<span className="float-right">100%</span></p>
                                    <div className="progress mt-2" style={{height: 5 + 'px'}}>
                                        <div className="progress-bar bg-success" role="progressbar" style={{width: 100 + '%'}}
                                             aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </td>

                                <td>
                                    <div className="action">
                                        <a href="#" className="text-success mr-4" data-toggle="tooltip" data-placement="top"
                                           title="" data-original-title="Edit"> <i className="fa fa-pencil h5 m-0"></i></a>
                                        <a href="#" className="text-danger" data-toggle="tooltip" data-placement="top" title=""
                                           data-original-title="Close"> <i className="fa fa fa-remove h5 m-0"></i></a>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <th scope="row">8</th>
                                <td>Landing page Design</td>
                                <td>13/6/2019</td>
                                <td>
                                    <span className="text-primary font-12"><i className="mdi mdi-checkbox-blank-circle mr-1"></i> Pending</span>
                                </td>
                                <td>
                                    <div className="team">
                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Deborah Mixon">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar2.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>

                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Scott Jessie">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>
                                    </div>
                                </td>
                                <td>
                                    <p className="mb-0">Progress<span className="float-right">84%</span></p>

                                    <div className="progress mt-2" style={{height: 5 + 'px'}}>
                                        <div className="progress-bar bg-primary" role="progressbar" style={{width: 84 + '%'}}
                                             aria-valuenow="84" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </td>

                                <td>
                                    <div className="action">
                                        <a href="#" className="text-success mr-4" data-toggle="tooltip" data-placement="top"
                                           title="" data-original-title="Edit"> <i className="fa fa-pencil h5 m-0"></i></a>
                                        <a href="#" className="text-danger" data-toggle="tooltip" data-placement="top" title=""
                                           data-original-title="Close"> <i className="fa fa fa-remove h5 m-0"></i></a>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <th scope="row">9</th>
                                <td>Multipurpose Landing Template</td>
                                <td>15/6/2019</td>
                                <td>
                                    <span className="text-success font-12"><i className="mdi mdi-checkbox-blank-circle mr-1"></i> Completed</span>
                                </td>
                                <td>
                                    <div className="team">
                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Neil Wing">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar4.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>

                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Stanley Barber">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar3.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>
                                        <a href="javascript: void(0);" className="team-member" data-toggle="tooltip"
                                           data-placement="top" title="" data-original-title="Roger Drake">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar5.png"
                                                 className={styles.avatar} alt=""/>
                                        </a>
                                    </div>
                                </td>
                                <td>
                                    <p className="mb-0">Progress<span className="float-right">100%</span></p>

                                    <div className="progress mt-2" style={{height: 5 + 'px'}}>
                                        <div className="progress-bar bg-success" role="progressbar" style={{width: 100 + '%'}}
                                             aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </td>

                                <td>
                                    <div className="action">
                                        <a href="#" className="text-success mr-4" data-toggle="tooltip" data-placement="top"
                                           title="" data-original-title="Edit"> <i className="fa fa-pencil h5 m-0"></i></a>
                                        <a href="#" className="text-danger" data-toggle="tooltip" data-placement="top" title=""
                                           data-original-title="Close"> <i className="fa fa fa-remove h5 m-0"></i></a>
                                    </div>
                                </td>
                            </tr>
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