import {useEffect} from "react";

const Table = (props) => {
    const displayTables = () => {
        let displayTable;
        if(props.isChecked) {
            displayTable = <>
                <div>table1</div>
                <div>table2</div>
                <div>table3</div>
                <div>table4</div>
                <div>table5</div>
            </>
        }else {
            displayTable = <></>
        }

        return displayTable
    }

    return (
        <div>
            {
                props.filtered ? <div> filtered text </div> : <></>
            }
            {displayTables()}
        </div>
    )
}

export default Table
