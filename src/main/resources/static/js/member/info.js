console.log('info.js open');

// [1] 내정보 조회
const info = async ( ) => {
    console.log('info func exe');
    try {
        // 1. fetch option
        const option = { method : "GET" };
        // 2. fetch response
        const response = await fetch( "/member/info", option );
        // 3. fetch data
        const data = await response.json();
        // 4. where
        const mnoBox = document.querySelector('.mnoBox');
        const midBox = document.querySelector('.midBox');
        const mnameBox = document.querySelector('.mnameBox');
        const mphoneBox = document.querySelector('.mphoneBox');
        const mdateBox = document.querySelector('.mdateBox');
        // 5. print
        mnoBox.innerHTML = `회원번호 : ${data.mno}`;
        midBox.innerHTML = `아이디 : ${data.mid}`;
        mnameBox.innerHTML = `이름 : ${data.mname}`;
        mphoneBox.innerHTML = `연락처 : ${data.mphone}`;
        mdateBox.innerHTML = `가입일 : ${data.mdate}`;
    } catch ( error ) {
        console.log( error );
        // 비로그인 상태 시, 로그인 페이지로 이동
        location.href = '/member/login.jsp';
    } // try-catch end
} // func end
info();

// [2] 회원 탈퇴
// alert    : 알람창
// prompt   : 입력상자 알람창
// confirm  : 확인/취소 알람창 -> true / false
const onDelete = async ( ) => {
    console.log('onDelete func exe');
    // 0. 탈퇴 확인받기
    let check = confirm('정말 탈퇴하시겠습니까?');
    if ( check == false ) return;
    try {
        // 1. 현재 비밀번호 입력받기
        let pwdInput = prompt('현재 비밀번호를 입력하세요.');
        // 2. fetch option
        const option = { method : "DELETE" };
        // 3. fetch response
        const response = await fetch( `/member/delete?mpwd=${pwdInput}`, option );
        // 4. fetch data
        const data = await response.json();
        // 5. result
        if ( data == true ){
            alert('회원탈퇴가 완료되었습니다.');
            location.href = '/index.jsp';
        } else {
            alert('비밀번호가 일치하지 않습니다.');
        } // if end
    } catch ( error ) {
        console.log( error );
    } // try-catch end
} // func end