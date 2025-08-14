console.log('login.js open')

// [1] 로그인 기능
const login = async ( ) => {
    console.log('login func exe');
    try{
        // 0. Input value
        // document.querySelector('') : 지정한 선택자의 마크업을 DOM 객체로 가져오기
        // .value가 가능한 마크업 : input, textarea, select ···
        const mid = document.querySelector('.idInput').value;
        const mpwd = document.querySelector('.pwdInput').value;
        // * 아이디와 비밀번호에 대한 유효성 검사 진행

        // 1. Object, Java의 Dto와 멤버변수명이 같아야한다.
        const obj = { mid, mpwd };
        // 2. fetch option
        const option = {
            method : "POST",
            headers : { "Content-Type" : "application/json" },
            body : JSON.stringify( obj )
        } // option end
        // 3. fetch response
        const response = await fetch( "/member/login", option );
        // 4. fetch data
        const data = await response.json();
        // 5. result
        if ( data > 0 ){
            alert('로그인 성공');
            location.href = '/index.jsp';
        } else {
            alert('로그인 실패');
        } // if end
    } catch {

    } // try-catch end
} // func end