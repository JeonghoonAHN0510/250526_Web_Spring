console.log('add.js open');

const add = async ( ) => {
    console.log('add func exe');

    // 1. 입력받은 값 가져오기
    const wphoneInput = document.querySelector('.wphone');
    const wphone = wphoneInput.value;
    const wcountInput = document.querySelector('.wcount');
    const wcount = wcountInput.value;
    // 2. 입력받은 값을 객체화 > 속성명과 속성값변수명이 같으면 생략 가능
    const waiting = { wphone, wcount };
    // 3. fetch option
    const option = {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify( waiting )
    } // option end
    // 4. fetch exe
    const response = await fetch( "/waiting/add", option );
    const data = await response.json();
    // 5. 결과에 따른 출력하기
    if ( data ){
        alert('등록 성공');
        location.href = '/waiting/print.jsp';
    } else {
        alert('등록 실패')
    } // if end
} // func end