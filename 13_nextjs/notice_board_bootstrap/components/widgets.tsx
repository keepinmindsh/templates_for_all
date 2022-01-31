

const Widgets = () => {
    return (
        <>
            <br />
            <div className="row">
                <div className="col-xl-3 col-md-3">
                    <div className="card bg-pattern">
                        <div className="card-body">
                            <h5 className="font-size-20 mt-0 pt-1">24</h5>
                            <p className="text-muted mb-0">Total Projects</p>
                        </div>
                    </div>
                </div>
                <div className="col-xl-3 col-md-3">
                    <div className="card bg-pattern">
                        <div className="card-body">
                            <h5 className="font-size-20 mt-0 pt-1">18</h5>
                            <p className="text-muted mb-0">Completed Projects</p>
                        </div>
                    </div>
                </div>
                <div className="col-xl-3 col-md-3">
                    <div className="card bg-pattern">
                        <div className="card-body">
                            <h5 className="font-size-20 mt-0 pt-1">06</h5>
                            <p className="text-muted mb-0">Pending Projects</p>
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