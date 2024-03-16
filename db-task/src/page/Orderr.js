import axios from 'axios';
import { useLocation,useNavigate } from 'react-router-dom';
import {  useEffect,useState } from 'react';


export default function Orderr(){

    const [orderrlist, setOrderrlist] = useState([]);
    const [loading, setLoading] = useState(true);

    const navigate = useNavigate();   // 값 보내기  아래 버튼
    
    const location = useLocation();   //값 받아오기
    const menuNum = location.state.menuNum;   //메뉴 번호
    const guestNum = location.state.guestNum;   //고객 번호
    const featureNum = location.state.featureNum; //코너 번호


            
    console.log("Orderr 메뉴 번호 : ",menuNum);
    console.log("Orderr 코너 번호 : ",featureNum);
    console.log("Orderr 고객 번호 : ",guestNum);
   

    const getFood =()=>{    // orderr 테이블  select 음식 주문 양식 보여줌 (고객이 시킨)
        const url = `/api1/order/getFood/${guestNum}`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                setOrderrlist(response.data);
                setLoading(false);
            });
    };

    const deleteOrderr = (orderNumber)=>{// 주문 목록 삭제
        const url = `/api1/order/${orderNumber}`;
        axios.delete(url)
        .then((response) => {
          console.log("응답객체:", response);
          setLoading(false);
    
          // 요청이 성공적으로 처리되었으므로 useEffect를 다시 호출합니다.
          getFood();
        })
        .catch((error) => {
          console.error("주문 삭제 실패:", error);
        });
    };
    
    

    useEffect(getFood,[]);  //시작시 화면 에 보여준 

    const Puls = (orderNumber,orderQuantity,menuNumber) =>{     //puls 버튼 클릭 시 숫자 증가 (input type="number" 가 안 먹혀서 버튼으로 대처 함) 
        const newQuantity = orderQuantity + 1;
        console.log("orderNumber = ",orderNumber);
        console.log("newQuantity = ",newQuantity);
        console.log("menuNumber = ",menuNumber);

        const updatedList = orderrlist.map((p) => {       
            if (p.orderNumber === orderNumber) {
              return { ...p, orderQuantity: newQuantity };
            }
            return p;
          });
        
          setOrderrlist(updatedList);

        const url = `/api1/order/${orderNumber}/${newQuantity}/${guestNum}/${menuNumber}`;   //버튼 클릭 후 orderr테이블 업데이트
        axios.put(url)
        .then((response) => {
        console.log("응답객체:", response);
      // 업데이트가 성공한 경우에 수행할 작업을 추가합니다.
      getFood();
    })
    .catch((error) => {
      console.error("데이터 업데이트 중 오류 발생:", error);
    });
    };

    const Mius = (orderNumber,orderQuantity,menuNumber) =>{ //Mius 버튼 클릭 시 숫자 감소 (input type="number" 가 안 먹혀서 버튼으로 대처 함) 
        const newQuantity = orderQuantity - 1;

        if (newQuantity < 1) {
          newQuantity = 1;
        }
      
        console.log("orderNumber = ",orderNumber);
        console.log("newQuantity = ",newQuantity);
        console.log("menuNumber = ",menuNumber);

        const updatedList = orderrlist.map((p) => {
            if (p.orderNumber === orderNumber) {
              return { ...p, orderQuantity: newQuantity };
            }
            return p;
          });
        
          setOrderrlist(updatedList);

        const url = `/api1/order/${orderNumber}/${newQuantity}/${guestNum}/${menuNumber}`;  //버튼 클릭 후 orderr테이블 업데이트
        axios.put(url)
        .then((response) => {
        console.log("응답객체:", response);
      // 업데이트가 성공한 경우에 수행할 작업을 추가합니다.
      getFood();
    })
    .catch((error) => {
      console.error("데이터 업데이트 중 오류 발생:", error);
    });

    };

    const PostShoppbag = (list)=>{                  //쇼핑백 테이블에 orderr 값들 을 추가 (이젠에 받아온 고객 번호와 메뉴 번호도 같이)
      console.log("-------SaveToSB---------");

      list.forEach((item) => {
          const {orderNumber,menuNumber} = item;
          console.log("orderNumber = >",orderNumber);
          console.log("menuNumber = >",menuNumber);
      const url = `/api1/shoppBag/${guestNum}/${orderNumber}/${menuNumber}`;

     axios.post(url)
    .then((response) => {
      console.log("응답객체:", response);
      setLoading(false);
    }).catch((error) => {
      console.error("데이터 저장 중 오류 발생:", error);
    });

  });
      
  }


    return(
        <div className='bag'>
  

            <hr/>
            <h1>주문 목록</h1>
            <ul>
            {
                loading ? <h1>Loading</h1> :
                orderrlist.map((p)=>(
                    <li key={p.orderNumber}> 
                         메뉴:{p.menuName}&nbsp;|&nbsp;
                         가격:{p.menuPrice}&nbsp;|&nbsp;
                         수량:{p.orderQuantity}&nbsp;|&nbsp;
                        <button onClick={()=>Puls(p.orderNumber,p.orderQuantity,p.menuNumber)} className='btnorder'>+</button>&nbsp;&nbsp;
                        <button onClick={()=>Mius(p.orderNumber,p.orderQuantity,p.menuNumber)} className='btnorder'>-</button>&nbsp;&nbsp;
                        <button onClick={()=>{deleteOrderr(p.orderNumber);}}>삭제</button>

                    </li>            
                ))
                
            }
              <div className='underbtn'>
                <button onClick={()=>{navigate(`/menu`,{state :{guestNum:guestNum,featureNum:featureNum} })}}>메뉴로</button>&nbsp;&nbsp;
                <button onClick={()=>{navigate(`/shoppBag`,{state :{guestNum:guestNum,menuNum:menuNum,featureNum:featureNum} });PostShoppbag(orderrlist);}}>결제하기</button>
              </div>
            </ul>
        </div>
    )
}