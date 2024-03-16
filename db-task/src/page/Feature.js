import axios from 'axios';
import { useNavigate, useLocation } from 'react-router-dom';
import {  useEffect,useState } from 'react';
export default function Feature(){

    const [featurelist, setFeaturelist] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();     // 값 보내기  아래 버튼

    const {state} = useLocation();      //값 받아오기 (고객 번호)
        
    console.log("고객 번호 : ",state);

    useEffect (()=>{            //시작시 코너 테이블 select
        const url = `/api1/feature/getAll`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                setFeaturelist(response.data);
                setLoading(false);
            });
    },[]);

    return(
        <div className='bag'>
            <hr/>
            <h1>코너 종류</h1>
            <ul>
            {
                loading ? <h1>Loading</h1> :
                featurelist.map((p)=>(
                    <li key={p.featureNumber}> 
                        코너 명:{p.featureName}&nbsp;&nbsp;
                        <button onClick={()=>{navigate(`/menu`, {state :{featureNum: p.featureNumber,guestNum:state}})}}>이동</button>

                    </li>    
                ))
            }
            </ul>
        </div>
    )
}