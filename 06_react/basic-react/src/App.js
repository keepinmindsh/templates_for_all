import {useState} from "react";
import Search from './components/search';
import Table from './components/tables';
import './App.css';

const App = () => {
    const [filtered, setFilteredText] = useState("")
    const [isChecked, setIsChecked] = useState(false)

    const onEnteredFilteredText = (event) => {
        setFilteredText(event.target.value)
    }

    const onCheckedDisplayDiv = (event) => {
        setIsChecked(event.target.checked)
    }

    return (
    <div className="App">
      <header className="App-header">
        <Search filtered={filtered} isChecked={isChecked} onEnteredFilteredText={onEnteredFilteredText} onCheckedDisplayDiv={onCheckedDisplayDiv} />
        <Table filtered={filtered} isChecked={isChecked} />
      </header>
    </div>
    );
}

export default App;
