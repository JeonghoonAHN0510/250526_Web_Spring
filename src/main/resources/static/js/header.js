console.log('header.js open');

let mno;

// [1] 내정보 요청하여 메뉴 나누기
const infoMenu = async ( ) => {
    console.log('infoMenu func exe');
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
        mno = data.mno;
        // * 특정 회원의 포인트 합계 가져오기 -> await를 사용하여 promise가 끝나는 것을 기다린다.
        let point = await memberPoint();
        console.log( point );
        point = point.toLocaleString('ko-KR');
        console.log( data.mimgname );
        // 출력할 이미지의 주소 생성
        let mimgURL = `/upload/${data.mimgname}`
        // 만약, 프로필 이미지가 없다면
        if ( data.mimgname == null ){
            // 기본 이미지 출력
            mimgURL = 'https://placehold.co/50x50';
        } // if end

        // 4. what
        html += `<li><img src="${mimgURL}"></li>
                 <li><span> ${data.mid}님 (현재 포인트 : ${point}점) </span></li>
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
infoMenu(); // header.jsp가 실행될 때마다, 최초 1번 실행

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

// [3] 특정 회원의 포인트 총합 반환 메소드
const memberPoint = async ( ) => {
    console.log('memberPoint func exe');
    // 1. fetch option
    const option = { method : "GET" };
    // 2. fetch response
    const response = await fetch( "/point/gettotal", option );
    // 3. fetch data
    const data = await response.json();
    console.log( data );
    // 4. return
    return data;
} // func end