console.log('add.js open');

// [1] 회원번호 자동생성
const custnoPrint = async ( ) => {
    console.log('custnoPrint func exe');
    // 1. fetch option
    const option = { method : "GET" };
    // 2. fetch response 
    const response = await fetch( "/member/custno", option );
    // 3. fetch data
    const data = await response.json();
    // 4. where
    const custnoInput = document.querySelector('.custnoInput');
    // 5. print
    custnoInput.value = data + 1;
} // func end
custnoPrint();

// [2] 회원 등록
const addMember = async ( ) => {
    console.log('addMember func exe');
    // 1. Input Value
    let custno = document.querySelector('.custnoInput').value;
    let custname = document.querySelector('.custnameInput').value;
    let phone = document.querySelector('.phoneInput').value;
    let address = document.querySelector('.addressInput').value;
    let joindate = document.querySelector('.joindateInput').value;
    let grade = document.querySelector('.gradeInput').value;
    let city = document.querySelector('.cityInput').value;
    // 2. Object
    const member = { custno, custname, phone, address, joindate, grade, city };
    // 3. fetch option
    const option = {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify( member )
    } // option end
    // 4. fetch response
    const response = await fetch( "/member", option );
    // 5. fetch data
    const data = await response.json();
    // 6. data에 따른 출력
    // 0 : 성공, 1 : 등록실패, 2 : 성명 입력 X, 3: 전화 입력 X, 4 : 주소 입력 X
    // 5 : 가입일자 입력 X, 6 : 등급 입력 X, 7 : 도시코드 입력 X, 8 : 회원번호 입력 X
    if ( data == 0 ){
        alert('등록 성공');
    } else if ( data == 1 ){
        alert('등록 실패')
    } else if ( data == 2 ){
        alert('회원성명이 입력되지 않았습니다.')
    } else if ( data == 3 ){
        alert('회원전화가 입력되지 않았습니다.')
    } else if ( data == 4 ){
        alert('회원주소가 입력되지 않았습니다.')
    } else if ( data == 5 ){
        alert('가입일자가 입력되지 않았습니다.')
    } else if ( data == 6 ){
        alert('고객등급이 입력되지 않았습니다.')
    } else if ( data == 7 ){
        alert('도시코드가 입력되지 않았습니다.')
    } else if ( data == 8 ){
        alert('회원번호가 입력되지 않았습니다.')
    } // if end
} // func end