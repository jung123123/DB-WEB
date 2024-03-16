import axios from 'axios';
import { useLocation,useNavigate } from 'react-router-dom';
import {  useEffect,useState } from 'react';
import '../css/main.css'

export default function Shoppbag(){

    const [shoppbaglist, setShoppbaglist] = useState([]);
    const [loading, setLoading] = useState(true);



    const navigate = useNavigate();     // 값 보내기  아래 버튼  (뒤로가기 버튼)
    const location = useLocation();     // 값 받아오기

    const menuNum = location.state.menuNum;     // 메뉴 번호
    const guestNum = location.state.guestNum;     //고객 번호
    const featureNum = location.state.featureNum;   // 코너 번호 
    console.log("Shoppbag 고객 번호 : ",guestNum);
    console.log("Shoppbag 메뉴 번호 : ",menuNum);
    console.log("featureNum 코너 번호 : ",featureNum);

    const getSum =()=>{         //고객에 따른 주문 메뉴 총합 보여줌 쇼핑백 테이블 select
        const url = `/api1/shoppBag/getSum/${guestNum}`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                setShoppbaglist(response.data);
                setLoading(false);
            });
    };


    const deleteShoppbag = () =>{       // 쇼핑백 테이블 삭제
        const url = `/api1/shoppBag/guest/${guestNum}`;
        axios.delete(url)
        .then((response) => {
          console.log("응답객체:", response);
          setLoading(false);
        })
        .catch((error) => {
          console.error("Shoppbag 삭제 실패:", error);
        });
    };

    
    const deleteOrder = () =>{          //orderr 테이블 삭제
        const url = `/api1/order/guest/${guestNum}`;

        setTimeout(() => {          //deleteShoppbag 먼저 실행 되야 작동 가능
            axios.delete(url)
            .then((response) => {
            console.log("응답객체:", response);
            setLoading(false);
            })
            .catch((error) => {
            console.error("Order 삭제 실패:", error);
            });
        }, 1000);
    };


    const deleteGuest = () =>{      // guest 테이블 삭제
        const url = `/api1/guest/${guestNum}`;

        setTimeout(() => {          //deleteShoppbag 먼저 실행 되야 작동 가능
            axios.delete(url)
            .then((response) => {
            console.log("응답객체:", response);
            setLoading(false);
            })
            .catch((error) => {
            console.error("Order 삭제 실패:", error);
            });
        }, 2000);
    };


    useEffect(getSum,[]);       // 화면 시작 시 작동

    const end = (shoppbaglist) => {     //  결제 후 알림 화면 띄우기 
        let totalCoin = 0;

        shoppbaglist.forEach((item) => {
            const {sum} = item;
            totalCoin = sum;
            console.log("sum =>",sum);
            console.log("totalCoin =>",totalCoin);
        })
        alert("총액 ="+totalCoin + "원" + "\n"+ "구입 완료"+"\n"+"10초 뒤에 다시 이용해 주세요" );

    }



    return(
        <div className='bag'>
            
            <hr/>
            <h1>결제 목록</h1>
            <ul>
            {
                loading ? <h1>Loading</h1> :
                shoppbaglist.map((p)=>(
                    <li key={p.guestNumber}> 
                         메뉴:{p.menuName}&nbsp;|&nbsp;
                         가격:{p.menuPrice}&nbsp;|&nbsp;
                         수량:{p.orderQuantity}&nbsp;|&nbsp;
                         총합 가격:{p.sum}
                      
                    </li>            
                ))
                
            }

            <div className='underbtn'>
                <button onClick={()=>{navigate(`/orderr`,{state :{guestNum:guestNum,menuNum:menuNum,featureNum:featureNum} });deleteShoppbag();}}>뒤로가기</button>&nbsp;&nbsp;
                <button onClick={()=>{navigate(`/`);end(shoppbaglist);deleteShoppbag();deleteOrder();deleteGuest();}}>결제 완료</button>
             </div>

           </ul>
        </div>
    )
}