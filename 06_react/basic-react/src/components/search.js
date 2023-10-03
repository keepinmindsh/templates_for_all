const Search = (props) => {
    return (
        <div>
            <input value={props.filtered} onChange={props.onEnteredFilteredText} ></input>
            <label for="display-div-tables" >Display Check Box</label><input id="display-div-tables" type="checkbox" checked={props.isChecked} onChange={props.onCheckedDisplayDiv} />
        </div>
    )
}

export default Search
