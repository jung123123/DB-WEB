import {BrowserRouter, Routes, Route, Link} from "react-router-dom";
import Feature from "./page/Feature";
import Menu from "./page/Menu";
import Guest from "./page/Guest";
import Orderr from "./page/Orderr";
import Shoppbag from "./page/Shoppbag";
import Start from "./page/Start";
import MainPage from "./page/MainPage";




function App() {    
  return (
    <div >
      {/*  시작 페이지  */}
      <BrowserRouter>

        <div className='title'>

             <Link to = "/">메인화면</Link>&nbsp;|&nbsp;      {/*  화면 우측 상단에 고정  */}
            <Link to = "/start">관리자화면</Link>

            {/* 
            <Link to = "/guest">번호표</Link>&nbsp;|&nbsp;
            <Link to = "/start">관리자화면</Link> */}
            {/* <Link to = "/shoppbag">쇼핑백</Link><br/> 
             <Link to = "/feature">키오스크</Link><br/> 
            <Link to = "/menu">메뉴</Link><br/>
            <Link to = "/orderr">주문</Link>  */}
            

        </div>
        <Routes>
          <Route path="/" element={<MainPage/>}/>   {/*  각 파일 주소  */}
          <Route path="/guest" element={<Guest/>}/>
          <Route path="/feature" element={<Feature/>}/>
          <Route path="/menu" element={<Menu/>}/>
          <Route path="/orderr" element={<Orderr/>}/>
          <Route path="/shoppbag" element={<Shoppbag/>}/>
          <Route path="/start" element={<Start/>}/>

        </Routes>
        <div className="appbottom">
        <h1>1812037 박정현</h1>
        <h6>과제용</h6>
        </div>
   

      </BrowserRouter>
            
    </div>

  );
}

export default App;
