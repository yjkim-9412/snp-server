import React from "react";


interface ChildLogin {
    setIsLoggedIn:  React.Dispatch<React.SetStateAction<Boolean>>
}


const Home:React.FC = () => {

return(
    <>
        <button> 로그인 상태 변경</button>
        <span>Home</span>;
    </>
)
}
export default Home;