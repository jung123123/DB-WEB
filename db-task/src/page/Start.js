import axios from 'axios';
import {  useEffect,useState, useRef } from 'react';
import { useLocation,useNavigate } from 'react-router-dom';
import '../css/main.css'

export default function Start(){

  //관리자 페이지

    const [loading, setLoading] = useState(true);
    const [featurelist, setFeaturelist] = useState([]);
    const [menulist, setMenulist] = useState([]);
    const [mname, setMname] = useState();
    const [mprice, setMprice] = useState();
    const [mfnum, setMfnum] = useState();
    const [fname, setFname] = useState();

    const featureNameRef = useRef();     //input 초기화
    const menuNameRef = useRef();        //input 초기화
    const menuPriceRef = useRef();      //input 초기화
    const featureNumberRef = useRef();  //input 초기화

    
    const PostFeature = (featureData)=>{      // 코너 생성
        console.log("-------SaveToSB---------");
        return new Promise((resolve, reject) => {       //    버그인지는 모르겠으나 const a = [{a},{b},{c}]; 로 할 시  반복 적으로 실해하면 0 = a, 1 = b, 2 = c가 아님 랜덤하게 저장 되어 이러한 방식을 사용 
            setTimeout(async () => {      // 버튼 클릭시 삭제후 생성되는 작업이라 삭제가 먼저 실행되도록 딜레이
              try {
                for (const data of featureData) {             
                  const { featureName } = data;
                  const url = `/api1/feature/${featureName}`;
        
                  const response = await axios.post(url);
                  console.log("응답객체:", response);
                  setLoading(false);
                }
                resolve(); // 모든 요청이 완료되면 resolve 호출
              } catch (error) {
                reject(error); // 오류 발생 시 reject 호출
              }
            }, 2000);
          });
    };

    const featureData = [     //코너 의 기본 값
        {
            featureName: '한식'
          },
          {
            featureName: '중식'
          },
          {
            featureName: '분식'
          }
    ];
    

    const PostMenu = (MenuData)=>{      //메뉴 생성
        console.log("-------SaveToSB---------");
        return new Promise((resolve, reject) => {
            setTimeout(async () => {     // 버튼 클릭시 삭제후 생성되는 작업이라 삭제가 먼저 실행되도록 딜레이 (코너가 먼저 생성되야 외래키를 받으므로 코너 보다 더 딜레이)
                try {
            for (const data of MenuData) {
                const { menuName, menuPrice,feature } = data;
                const url = `/api1/menu/${menuName}/${menuPrice}/${feature}`;

                const response = await axios.post(url);
                console.log("응답객체:", response);
                setLoading(false);
              }
              resolve(); // 모든 요청이 완료되면 resolve 호출
            } catch (error) {
              reject(error); // 오류 발생 시 reject 호출
            }
            eff();
          }, 3000);
         
        });
    }

    const eff = () => {   //2개의 파일을 담기위해 생성
        getFeature();
        getMenu();
    }

    const MenuData = [    // 메뉴의 기본 값
        {
            menuName: '비빔밥',
            menuPrice:4000,
            feature:1
          },
          {
            menuName: '불고기',
            menuPrice:6000,
            feature:1
          },
          {
            menuName: '김치찌개',
            menuPrice:4500,
            feature:1
          },
          {
            menuName: '자장면',
            menuPrice:4000,
            feature:2
          },
          {
            menuName: '짬뽕',
            menuPrice:4500,
            feature:2
          },
          {
            menuName: '탕수육',
            menuPrice:7000,
            feature:2
          },
          {
            menuName: '떡볶이',
            menuPrice:3000,
            feature:3
          },
          {
            menuName: '순대',
            menuPrice:3500,
            feature:3
          },
          {
            menuName: '튀김',
            menuPrice:2500,
            feature:3
          }
       
    ];

    const deleteFeature = () =>{    //코너 전체 삭제
        setTimeout(() => {        // 외래키 때문에 메뉴 먼저 삭제를 위해 딜레이
            const url = `/api1/feature/`;
            axios.delete(url)
            .then((response) => {
            console.log("응답객체:", response);
            setLoading(false);
            })
            .catch((error) => {
            console.error("Feature 삭제 실패:", error);
            });
        }, 1000);
    };
    const deleteMenu = () =>{   //메뉴 전체 삭제
        
            const url = `/api1/menu/`;
            axios.delete(url)
            .then((response) => {
            console.log("응답객체:", response);
            setLoading(false);
            })
            .catch((error) => {
            console.error("Menu 삭제 실패:", error);
            });

    };

    const deleteOrder = () =>{   //메뉴 전체 삭제
        
      const url = `/api1/order/`;
      axios.delete(url)
      .then((response) => {
      console.log("응답객체:", response);
      setLoading(false);
      })
      .catch((error) => {
      console.error("Order 삭제 실패:", error);
      });

};


    
    const handleClick = () => {   // 기본값 버튼   데이터 전체 삭제 후  데이터 추가
        deleteOrder();
        deleteFeature();
        deleteMenu();
        PostFeature(featureData);
        PostMenu(MenuData);
    }

    const getFeature =()=>{   // 코너 select
        const url = `/api1/feature/getAll`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                setFeaturelist(response.data);
                setLoading(false);
            });
    };

    const getMenu =()=>{    //메뉴 select
        const url = `/api1/menu/getAll`;
        axios.get(url)  //axios는 json 보내줌
            .then((response)=>{
                console.log("응답객체:",response);
                setMenulist(response.data);
                setLoading(false);
            });
    };

    useEffect(getFeature,[]); //시작 시 코너 테이블 보여줌
    useEffect(getMenu,[]);  //시작 시 메뉴 테이블 보여줌


    const FPosts = ()=>{    //코너 추가 직접 하나
        console.log("-------SaveToSB---------");
        // const url = "http://localhost:8080/world"
        //const url = "/api/product/getAll"
        const url = `/api1/feature/${fname}`;

       axios.post(url)
      .then((response) => {
        console.log("응답객체:", response);
        setLoading(false);
        eff();
      });
    }

    const MPosts = ()=>{    //메뉴 추가 직접 하나
        console.log("-------SaveToSB---------");
        // const url = "http://localhost:8080/world"
        //const url = "/api/product/getAll"
        const url = `/api1/menu/${mname}/${mprice}/${mfnum}`;

       axios.post(url)
      .then((response) => {
        console.log("응답객체:", response);
        setLoading(false);
        eff();
      });
    }


    const deletef = (featureNumber)=>{    //코너 삭제 직접 하나
        const url = `/api1/feature/${featureNumber}`;
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

    const deletem = (menuNumber)=>{    //메뉴 삭제 직접 하나
        const url = `/api1/menu/${menuNumber}`;
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


    const namef = (e) => {    //추가할 코너 이름 받아오기 
        setFname(e.target.value);
    }

    const featurepost = () => { //추가할 코너 이름 보내기 
        FPosts();
        // input 값 초기화
        featureNameRef.current.value = '';  

    }

    const namem = (e) => {  //추가할 메뉴 이름 받아오기 
        setMname(e.target.value);
    }
    const pricem = (e) => { //추가할 메뉴 가격 받아오기 
        setMprice(e.target.value);
    }
    const nummf = (e) => {  //추가할 메뉴 코너 받아오기 
        setMfnum(e.target.value);
    }

    const menupost = () => {    //추가할 메뉴 이름 ,가격, 코너 보내기
        MPosts();
         // input 값 초기화
        menuNameRef.current.value = '';
        menuPriceRef.current.value = '';
        featureNumberRef.current.value = '';
    }

    const updatef = (featureNumber) =>{     //updatef 버튼 클릭 시  
      
      console.log("featureNumber = ",featureNumber);
      console.log("fname = ",fname);

        const url = `/api1/feature/${featureNumber}/${fname}`;   //버튼 클릭 후 orderr테이블 업데이트
        axios.put(url)
        .then((response) => {
            console.log("응답객체:", response);
          // 업데이트가 성공한 경우에 수행할 작업을 추가합니다.
          featureNameRef.current.value = ''; 
          getFeature();
        })
        .catch((error) => {
          console.error("데이터 업데이트 중 오류 발생:", error);
        });
    };

    const updatem = (menuNumber) =>{     //updatef 버튼 클릭 시  
      
      console.log("menuNumber = ",menuNumber);     
      console.log("mname = ",mname);
      console.log("mprice = ",mprice);
      console.log("mfnum = ",mfnum);

        const url = `/api1/menu/${menuNumber}/${mname}/${mprice}/${mfnum}`;   //버튼 클릭 후 orderr테이블 업데이트
        axios.put(url)
        .then((response) => {
            console.log("응답객체:", response);
          // 업데이트가 성공한 경우에 수행할 작업을 추가합니다.
          menuNameRef.current.value = '';
          menuPriceRef.current.value = '';
          featureNumberRef.current.value = ''; 
          getMenu();
        })
        .catch((error) => {
          console.error("데이터 업데이트 중 오류 발생:", error);
        });
  };




    return(
    
        <div className='bag'>
          
          <div >
            
            <button onClick={handleClick}>기본 값 초기화</button><br/>

            코너 이름 : <input type="text" ref={featureNameRef} onChange={namef} placeholder='필수'/>&nbsp;&nbsp;
            <button onClick={featurepost}>추가</button>
            <br/>

            메뉴 이름 : <input type="text" ref={menuNameRef} onChange={namem} placeholder='필수'/>&nbsp;&nbsp;
            메뉴 가격 : <input type="number" ref={menuPriceRef} onChange={pricem} placeholder='필수'/>&nbsp;&nbsp;
            판매 코너 번호 : <input type="number" ref={featureNumberRef} onChange={nummf} placeholder='필수'/>&nbsp;&nbsp;
            <button onClick={menupost}>추가</button>

          </div>

          <div className='gridlist'>
            <div className="scroll-container">
              <hr/>
              <h1>코너</h1>
              <ul>
              {
                  loading ? <h1>Loading</h1> :
                  featurelist.map((p)=>(
                      <li key={p.featureNumber} > 
                          코너 번호:{p.featureNumber}&nbsp;&nbsp;
                          코너 이름:{p.featureName}&nbsp;&nbsp;
                          <button onClick={()=>{deletef(p.featureNumber)}}>삭제</button>&nbsp;&nbsp;
                          <button onClick={()=>{updatef(p.featureNumber)}}>수정</button>


                      </li>            
                  ))
                  
              }
              </ul>
            </div>

            <div className="scroll-container">
              <hr/>
              <h1>메뉴</h1>
              <ul>
              {
                  loading ? <h1>Loading</h1> :
                  menulist.map((p)=>(
                      
                      <li key={p.menuNumber} className='sp'> 
                          <span>메뉴 번호:{p.menuNumber}</span>
                          <span >메뉴:{p.menuName}</span>
                          <span>가격:{p.menuPrice}</span>
                          <span>코너 번호:{p.feature.featureNumber}</span>
                          <button onClick={()=>{deletem(p.menuNumber)}}>삭제</button>&nbsp;&nbsp;
                          <button onClick={()=>{updatem(p.menuNumber)}}>수정</button>
                          <br/> <br/> 
                      </li> 
                                
                  ))
                  
              } 
                  
              </ul>
            </div>  
          </div>
        </div>
    )
}