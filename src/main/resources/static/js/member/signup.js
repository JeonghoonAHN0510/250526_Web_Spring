console.log('signup.js open');

// *** 유효성 검사 체크리스트 ***
const signPass = [ false, false ];   // 초기값은 실패 [ 아이디체크, 연락처체크 ]

// [1] 회원가입 처리
const signup = async ( ) => {
    console.log('signup func exe');
    // 유효성 검사 체크리스트에 false가 존재하면, 회원가입 진행 불가능
    // .indexOf(A)  : 배열 내 A의 인덱스 값을 반환
    // .includes(A) : 배열 내 A의 존재 여부를 boolean으로 반환
    if ( signPass.includes(false) ){
        alert('올바른 정보를 입력해주세요.');
        return;
    } // if end

    try{
        // 1-1. 전송할 form 가져오기
        const signupForm = document.querySelector('#signupForm');
        // 1-2. 대용량 form으로 변환
        const signupFormData = new FormData( signupForm );
        // 2. fetch option
        const option = {
            method : "POST",
            body : signupFormData
        } // option end
        // 4. fetch response
        const response = await fetch( "/member/signup", option );
        // 5. fetch data
        const data = await response.json();
        // 6. result
        if ( data > 0 ){
            alert('회원가입 성공');
            location.href = '/member/login.jsp';
        } else {
            alert('회원가입 실패');
        } // if end
    } catch ( error ) {
        console.log( error );
    } // try-catch end
} // func end

// [2] 아이디 유효성 검사 : 입력할 때마다 검사
const idCheck = async ( ) => {
    console.log('idCheck func exe');
    try{
        // 1-1. 전송할 form 가져오기
        const signupForm = document.querySelector('#signupForm');
        // 1-2. 대용량 form으로 변환
        const signupFormData = new FormData( signupForm );
        console.log( signupFormData.get('mid') );

        // 1. Input value
        const mid = signupFormData.get('mid');
        // 2. where
        const idCheckBox = document.querySelector('.idCheck');
        // * 길이 유효성 검사
        if ( mid.length < 6 ){
            idCheckBox.innerHTML = '아이디는 6글자 이상부터 가능합니다.';
            signPass[0] = false
            return;
        } // if end
        // * 중복 유효성 검사
        // 3. fetch option
        const option = { method : "GET" };
        // 4. fetch response
        const response = await fetch( `/member/check?type=mid&data=${mid}`, option );
        // 5. fetch data
        const data = await response.json();
        // 6. result
        if ( data == true ){
            idCheckBox.innerHTML = '중복 아이디가 존재합니다.';
            signPass[0] = false;
        } else {
            idCheckBox.innerHTML = '사용 가능한 아이디입니다.';
            signPass[0] = true;
        } // if end
    } catch ( error ) {
        console.log( error );
    } // try-catch end
} // func end

// [3] 연락처 중복검사 : 입력할 때마다 검사
const phoneCheck = async ( ) => {
    console.log('phoneCheck func exe');
    try{
        // 1-1. 전송할 form 가져오기
        const signupForm = document.querySelector('#signupForm');
        // 1-2. 대용량 form으로 변환
        const signupFormData = new FormData( signupForm );
        console.log( signupFormData.get('mphone') );

        const mphone = signupFormData.get('mphone');
        // 2. where
        const phoneCheckBox = document.querySelector('.phoneCheck');
        // * 길이 유효성 검사
        if ( mphone.length != 13 ){
            phoneCheckBox.innerHTML = '- 포함한 13글자 연락처를 입력해주세요.';
            signPass[1] = false;
            return;
        } // if end
        // * 중복 유효성 검사
        // 3. fetch option
        const option = { method : "GET" };
        // 4. fetch response
        const response = await fetch( `/member/check?type=mphone&data=${mphone}`, option );
        // 5. fetch data
        const data = await response.json();
        // 6. result
        if ( data == true ){
            phoneCheckBox.innerHTML = '중복 연락처가 존재합니다.';
            signPass[1] = false;
        } else {
            phoneCheckBox.innerHTML = '사용 가능한 연락처입니다.';
            signPass[1] = true;
        } // if end
    } catch ( error ) {
        console.log( error );
    } // try-catch end
} // func end