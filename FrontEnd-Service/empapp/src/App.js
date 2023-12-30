import logo from './logo.svg';
import './App.css';
import { useState } from 'react';
import Student from './Student';
import PropAndState from './PropAndState';

function App() {
const [data,setData] = useState(0)
function clickMe(){
  setData(data+1);
}

const[name,setName] = useState('Appu')

  return (
    <div className="App">
    <h1>{data}</h1>
    <button onClick={clickMe}>click me</button>
      <Student name={name}/>
      <button onClick={()=>{setName("Prashik")}}>Change Name</button>

      <PropAndState/>
    </div>
    
  );
}

export default App;
