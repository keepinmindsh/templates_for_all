const Table = (props) => {

    const listOfTables = [
        {title : "Lines 1", id : 1 },
        {title : "Jeong 2", id : 2 },
        {title : "Seung 3", id : 3 },
        {title : "Bong 4", id : 4 },
        {title : "Good 5", id : 5 },
        {title : "BongBong 6", id : 6 },
    ]

    const displayTables = () => {
        let displayTable;
        if(props.isChecked) {
            displayTable = <>
                {
                    listOfTables
                        .filter(item => {
                            return props.filtered ? item.title.indexOf(props.filtered) > -1 : true
                        })
                        .map(item => {
                            return <div id={item.id} ><label>{item.title}</label></div>
                        })
                }
            </>
        }else {
            displayTable = <></>
        }

        return displayTable
    }

    return (
        <div>
            {displayTables()}
        </div>
    )
}

export default Table
