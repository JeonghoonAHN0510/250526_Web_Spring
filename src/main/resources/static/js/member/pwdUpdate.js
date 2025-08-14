console.log('pwdUpdate.js open');

// [1] 비밀번호 수정
const onPwdUpdate = async ( ) => {
    console.log('onPwdUpdate func exe');
    try {
        // 1. Input value
        const oldPwd = document.querySelector('.oldPwd').value;
        const newPwd = document.querySelector('.newPwd').value;
        // 2. Object
        const obj = { oldPwd, newPwd };
        // 3. fetch option
        const option = {
            method : "PUT",
            headers : { "Content-Type" : "application/json" },
            body : JSON.stringify( obj )
        } // option end
        // 4. fetch response
        const response = await fetch( "/member/update/password", option );
        // 5. fetch data
        const data = await response.json();
        // 6. result
        if ( data == true ){
            alert('비밀번호 수정 완료');
            location.href = '/member/info.jsp';
        } else {
            alert('비밀번호 수정 실패');
        } // if end
    } catch ( error ) {
        console.log( error );
    } // try-catch end
} // func end

// [1] 기존 회원정보 출력
const info = async ( ) => {
    console.log('info func exe');
    try {
        // 1. fetch option 
        const option = { method : "GET" };
        // 2. fetch response
        const response = await fetch( "/member/info", option );
        // 3. fetch data
        const data = await response.json();
    } catch ( error ) {
        console.log( error );
        location.href = '/member/login.jsp';
    } // try-catch end
} // func end
info();