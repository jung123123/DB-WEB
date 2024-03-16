import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import {  useEffect,useState,useRef } from 'react';
export default function Guest(){

    const [guestlist, setGuestlist] = useState([]);
    const [loading, setLoading] = useState(true);
    const [gname, setGname] = useState('');

    const guestNameRef = useRef();       //input 초기화

    const navigate = useNavigate();     // 값 보내기  아래 버튼
    


    const getGuest =()=>{       //고객 테이블 select
        const url = `/api1/guest/getAll`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                setGuestlist(response.data);
                setLoading(false);
            });
    };

    useEffect(getGuest,[]);     // 시작 시 실행
   
    const eff = () => {     //추후 다시 작동을 위해 담음
        getGuest();
    }


    const PostGuest = ()=>{         // 고객 추가
        console.log("-------SaveToSB---------");
        // const url = "http://localhost:8080/world"
        //const url = "/api/product/getAll"
        const url = `/api1/guest/${gname}`;

       axios.post(url)
      .then((response) => {
        console.log("응답객체:", response);
        setLoading(false);
        eff(); 
      });
    }

    const deleteg = (guestNumber)=>{        // 고객 삭제
        const url = `/api1/guest/${guestNumber}`;
        axios.delete(url)
        .then((response) => {
          console.log("응답객체:", response);
          setLoading(false);
          eff();
        })
        .catch((error) => {
          console.error("주문 삭제 실패:", error);
        });
    };


    const name = (e) =>{        //고객 이름 받아오기
        setGname(e.target.value);
        console.log('gname=>',gname);
    }

    const num = () => {         //받아온 고객 이름 보내기
        setLoading(true);
        PostGuest();
        guestNameRef.current.value = '';    //input 초기화
    }



    return(
        <div className='bag'>
            이름 : <input type="text" ref={guestNameRef} onChange={name} placeholder='필수'/>&nbsp;&nbsp;
            <button onClick={num}>번호 뽑기</button>

            <hr/>
            <h1>번호표</h1>
            <ul>
            {
                loading ? <h1>Loading</h1> :
                guestlist.map((p)=>(
                    <li key={p.guestNumber}> 
                         이름:{p.guestName}&nbsp;&nbsp;
                        <button onClick={()=>{navigate(`/feature`,{state: p.guestNumber})}}>이동</button>&nbsp;&nbsp;
                        <button onClick={()=>{deleteg(p.guestNumber)}}>삭제</button>
                    </li>    
                ))
            }
            </ul>
        </div>
    )
}