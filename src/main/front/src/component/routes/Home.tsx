import React from "react";


interface ChildLogin {
    sendLoginStatus: () => void
}
const Home:React.FC<ChildLogin> = ({sendLoginStatus}) =>
    <>
        <button onClick={sendLoginStatus}> 로그인 상태 변경</button>
        <span>Home</span>;
    </>
export default Home;