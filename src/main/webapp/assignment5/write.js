console.log('write.js open');

// [1] 대기현황 등록
const waitingAdd = async ( ) => {
    console.log('waitingAdd func exe');
    // 1. Input Value
    let wphone = document.querySelector('.wphoneInput').value;
    let wcount = document.querySelector('.wcountInput').value;
    // 2. Object
    let waiting = { wphone, wcount };
    // 3. fetch option
    const option = {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify( waiting )
    } // option end
    // 4. fetch response
    const response = await fetch( "/waiting", option );
    // 5. fetch data
    const data = await response.json();
    // 6. data에 따른 출력
    if ( data == true ){
        alert('등록 성공')
        location.href = '/assignment5/list.jsp'
    } else {
        alert('등록 실패')
    } // if end
} // func end