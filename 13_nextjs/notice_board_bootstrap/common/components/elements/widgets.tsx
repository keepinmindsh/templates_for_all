const Widgets = () => {
    return (
        <>
            <br />
            <div className="row">
                <div className="col-xl-5" >
                    <div className="row mb-2" >
                        <div className="col" >
                            <div className="form-group">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-12 col-form-label col-form-label-sm">고객사</label>
                                <div className="col-sm-12">
                                    <select className="form-control">
                                        <option>Default select</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-12 col-form-label col-form-label-sm">요청타입</label>
                                <div className="col-sm-12">
                                    <select className="form-control">
                                        <option>Default select</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row" >
                        <div className="col" >
                            <div className="form-group">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-12 col-form-label col-form-label-sm">업무</label>
                                <div className="col-sm-12">
                                    <select className="form-control">
                                        <option>Default select</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className="col" >
                            <div className="form-group">
                                <label htmlFor="colFormLabelSm"
                                       className="col-sm-12 col-form-label col-form-label-sm">우선순위</label>
                                <div className="col-sm-12">
                                    <select className="form-control">
                                        <option>Default select</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-xl-7" >
                    <div className="row">
                        <div className="col-xl-2 col-md-2 p-0">
                            <div className="card bg-pattern">
                                <button type="button" className="btn btn-light">
                                    <h6 className="text-muted mb-0 text-sm-center">고객사접수</h6>
                                    <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center">[ 24 ]</h6>
                                </button>
                            </div>
                        </div>
                        <div className="col-xl-2 col-md-2 p-0">
                            <div className="card bg-pattern">
                                <button type="button" className="btn btn-warning">
                                    <h6 className="text-muted mb-0 text-sm-center">산하접수</h6>
                                    <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center">[ 18 ]</h6>
                                </button>
                            </div>
                        </div>
                        <div className="col-xl-2 col-md-2 p-0">
                            <div className="card bg-pattern">
                                <button type="button" className="btn btn-light">
                                    <h6 className="text-muted mb-0 text-sm-center">개발담당</h6>
                                    <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center">[ 06 ]</h6>
                                </button>
                            </div>
                        </div>
                        <div className="col-xl-2 col-md-2 p-0">
                            <div className="card bg-pattern">
                                <button type="button" className="btn btn-light">
                                    <h6 className="text-muted mb-0 text-sm-center">개발시작</h6>
                                    <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center">[ 06 ]</h6>
                                </button>
                            </div>
                        </div>
                        <div className="col-xl-2 col-md-2 p-0">
                            <div className="card bg-pattern">
                                <button type="button" className="btn btn-light">
                                    <h6 className="text-muted mb-0 text-sm-center">개발종료</h6>
                                    <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center">[ 06 ]</h6>
                                </button>
                            </div>
                        </div>
                        <div className="col-xl-2 col-md-2 p-0">
                            <div className="card bg-pattern">
                                <button type="button" className="btn btn-light">
                                    <h6 className="text-muted mb-0 text-sm-center">고객사적용</h6>
                                    <h6 className="font-size-16 mt-0 mb-0 pt-1 text-sm-center">[ 06 ]</h6>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br />
            <div  className="row" >
                <div className="card">
                    <div className="card-body">
                        <form>
                            <div className="form-group mb-0">
                                <div className="input-group mb-0">
                                    <input type="text" className="form-control" placeholder="Search..."
                                           aria-describedby="project-search-addon"/>
                                    <div className="input-group-append">
                                        <button className="btn btn-danger" type="button" id="project-search-addon"><i
                                            className="fa fa-search search-icon font-12"></i></button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    );
};

export default Widgets;