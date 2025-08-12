console.log('update.js open');

// [1] 선택 회원정보 출력
const memberPrintOne = async ( ) => {
    console.log('memberPrintOne func exe');
    // 0. custno 가져오기
    const custno = new URLSearchParams( location.search ).get('custno');
    // 1. fetch option
    const option = { method : "GET" };
    // 2. fetch response
    const response = await fetch( `/member/one?custno=${custno}`, option );
    // 3. fetch data
    const data = await response.json();
    // 4. where
    let custnoBox = document.querySelector('.custnoInput');
    let custnameBox = document.querySelector('.custnameInput');
    let phoneBox = document.querySelector('.phoneInput');
    let addressBox = document.querySelector('.addressInput');
    let joindateBox = document.querySelector('.joindateInput');
    let gradeBox = document.querySelector('.gradeInput');
    let cityBox = document.querySelector('.cityInput');
    // 5. print
    custnoBox.value = data.custno;
    custnameBox.value = data.custname;
    phoneBox.value = data.phone;
    addressBox.value = data.address;
    joindateBox.value = data.joindate;
    gradeBox.value = data.grade;
    cityBox.value = data.city;
} // func end
memberPrintOne();

// [2] 회원정보 수정
const updateMember = async ( ) => {
    console.log('updateMember func exe');
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
        method : "PUT",
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
        alert('수정 성공');
    } else if ( data == 1 ){
        alert('수정 실패')
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

// [3] 조회 페이지 이동
const viewTrans = ( ) => {
    console.log('viewTrans func exe');

    location.href = '/assessment/view.jsp'
} // func end