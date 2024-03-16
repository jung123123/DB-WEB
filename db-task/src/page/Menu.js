import { useLocation,useNavigate } from "react-router-dom";
import axios from 'axios';
import {  useEffect,useState } from 'react';

export default function Menu(){

    const [menulist, setMenulist] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();     // 값 보내기  아래 버튼

    const location = useLocation();
    const featureNum = location.state.featureNum;       // 코너 번호 받아오기
    const guestNum = location.state.guestNum;           // 고객 번호 받아오기
    
  
        
    console.log("Menu 코너 번호 : ",featureNum);
    console.log("Menu 고객 번호 : ",guestNum);
    

    const Postorder = (menuNum)=>{          // 주문 테이블에 추가
        console.log("-------SaveToSB---------");
        const url = `/api1/order/${guestNum}/${menuNum}`;

       axios.post(url)
      .then((response) => {
        console.log("응답객체:", response);
        setLoading(false);
      });
    }

    useEffect (()=>{                // 시작시 코너에 맞는 메뉴 테이블 select
        const url = `/api1/menu/getFeature/${featureNum}`;
        console.log("axios 내부 코너 번호 : ",featureNum);
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                setMenulist(response.data);
                setLoading(false);
            });
    },[]);

   
    return(
        <div className='bag'>
           <hr/>
            <h1>메뉴 종류</h1>
            <ul>
            {
                loading ? <h1>Loading</h1> :
                menulist.map((p)=>(
                    <li key={p.menuNumber}> 
                        메뉴 이름 : {p.menuName},
                        메뉴 가격 : {p.menuPrice}&nbsp;&nbsp;
                        <button onClick={()=>{navigate(`/orderr`,{state :{menuNum: p.menuNumber,guestNum:guestNum,featureNum:featureNum} });Postorder(p.menuNumber);}}>담기</button>

                    </li>    
                ))
            }
            </ul>
        </div>
    )





}