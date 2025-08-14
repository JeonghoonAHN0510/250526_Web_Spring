console.log('find.js open')

// [1] 아이디 찾기
const findId = async ( ) => {
    console.log('findId func exe');
    try {
        // 1. Input value
        const mname = document.querySelector('.findIdmname').value;
        const mphone = document.querySelector('.findIdmphone').value;
        // 2. fetch option
        const option = { method : "GET" };
        // 3. fetch response
        const response = await fetch( `/member/findId?mname=${mname}&mphone=${mphone}`, option );
        // 4. fetch data
        const data = await response.json();
        // 5. result
        alert(`아이디 : ${data.mid}`);
    } catch ( error ) {
        console.log( error );
        alert('일치하지 않는 정보입니다.');
    } // try-catch end
} // func end

// [2] 비밀번호 찾기
const findPwd = async ( ) => {
    console.log('findPwd func exe');
    try { 
        // 1. Input value
        const mid = document.querySelector('.findPwdmid').value;
        const mphone = document.querySelector('.findPwdmphone').value;
        // 2. fetch option
        const option = { method : "GET" };
        // 3. fetch response
        const response = await fetch( `/member/findPwd?mid=${mid}&mphone=${mphone}`, option );
        // 4. fetch data
        const data = await response.json();
        // 5. result
        alert(`임시 비밀번호 : ${data.mpwd}`);
    } catch ( error ) {
        console.log( error );
        alert('일치하지 않는 정보입니다.')
    } // try-catch end
} // func end