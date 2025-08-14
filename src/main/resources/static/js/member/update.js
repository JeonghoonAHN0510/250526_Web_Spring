console.log('update.js open');

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
        // 4. where + print
        document.querySelector('.mno').innerHTML = data.mno;
        document.querySelector('.mid').innerHTML = data.mid;
        document.querySelector('.mname').value = data.mname;
        document.querySelector('.mphone').value = data.mphone;
    } catch ( error ) {
        console.log( error );
        location.href = '/index.jsp';
    } // try-catch end
} // func end
info();

// [2] 회원정보수정
const onUpdate = async ( ) => {
    console.log('onUpdate func exe');
    try {
        // 1. Input value
        const mname = document.querySelector('.mname').value;
        const mphone = document.querySelector('.mphone').value;
        // * 길이 유효성 검사
        if ( mphone.length != 13 ){
            alert('- 포함한 13글자 연락처를 입력해주세요.');
            return;
        } // if end
        // 2. Object
        const obj = { mname, mphone };
        // 3. fetch option
        const option = {
            method : "PUT",
            headers : { "Content-Type" : "application/json" },
            body : JSON.stringify( obj )
        } // option end
        // 4. fetch response
        const response = await fetch ( "/member/update", option );
        // 5. fetch data
        const data = await response.json();
        // 6. result
        if ( data == true ){
            alert('회원정보 수정 성공');
            location.href = '/member/info.jsp';
        } else {
            alert('회원정보 수정 실패');
        } // if end
    } catch ( error ) {
        console.log( error );
    } //try-catch end 
} // func end