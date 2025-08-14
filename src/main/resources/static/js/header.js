console.log('header.js open');

// [1] 내정보 요청하여 메뉴 나누기
const info = async ( ) => {
    console.log('info func exe');
    // 0. where
    const logMenu = document.querySelector('#log-menu');
    let html = '';
    try{
        // 1. fetch option
        const option = { method : "GET" };
        // 2. fetch response
        const response = await fetch( "/member/info", option );
        // 3. fetch data
        const data = await response.json();
        // return 값이 null이면, json() 변환이 안되기 때문에 catch에 잡혀서 넘어간다.
        // 즉, data == null로 잡아서 비로그인 시, 출력문을 작성하면 안 된다.
        console.log( data );
        // 4. what
        html += `<li><span> ${data.mid}님 </span></li>
                <li><a href="/member/info.jsp"> 내정보 </a></li>
                <li><a href="#" onclick="logout()"> 로그아웃 </a></li>`
    } catch {
        // * 로그인 안했을 때, 비정상 통신 fetch
        // 4. what
        html += `<li><a href="/member/login.jsp"> 로그인 </a></li>
                <li><a href="/member/signup.jsp"> 회원가입 </a></li>`
    } // try-catch end
    // 5. print
    logMenu.innerHTML = html;
} // func end
info(); // header.jsp가 실행될 때마다, 최초 1번 실행

// [2] 로그아웃
const logout = async ( ) => {
    console.log('logout func exe');
    try{
        // 1. fetch option
        const option = { method : "GET" };
        // 2. fetch response
        const response = await fetch( "/member/logout", option );
        // 3. fetch data
        const data = await response.json();
        // 4. result
        if ( data == true ){
            alert('로그아웃 성공')
            // 로그아웃 성공 시, 메인페이지로 이동
            location.href = "/index.jsp"
        } else {
            alert('비정상 요청이므로 관리자에게 문의하세요.')
        } // if end
    } catch {

    } // try-catch end
} // func end
