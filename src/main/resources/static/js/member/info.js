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
    } // try-catch end
} // func end
info();

// [2] 회원 탈퇴
const onDelete = async ( ) => {
    console.log('onDelete func exe');
    


} // func end