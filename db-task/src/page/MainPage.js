import {BrowserRouter, Routes, Route, Link} from "react-router-dom";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import {  useEffect,useState,useRef } from 'react';
import Guest from "./Guest";
import Start from "./Start";



export default function MainPage(){

    const [guestlist, setGuestlist] = useState([]);
    const [loading, setLoading] = useState(true);

    const navigate = useNavigate();
  
   


    const getMainGuest =()=>{               //고객 화면 select
        const url = `/api1/guest/getAll`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                setGuestlist(response.data);
                setLoading(false);
            });
    };

    useEffect(getMainGuest,[]);     //화면 시작시 
    return(
        <div className="bag">
        
        

            <div>메인 화면</div>
            <hr/>
            <h1>번호표</h1>
            <hr/>
            <ul>
            {
                loading ? <h1>Loading</h1> :                        
                guestlist.map((p)=>(
                    <li key={p.guestNumber}> 
                         이름:{p.guestName}&nbsp;&nbsp;
                    </li>    
                    
                ))
            }

            <button onClick={()=>{navigate(`/guest`,)}}>번호표 뽑기</button>&nbsp;&nbsp;
            </ul>


        </div>
    )

}