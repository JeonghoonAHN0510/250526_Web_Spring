console.log('update.js open');

// [1] 선택 조회 출력
const waitingPrint = async ( ) => {
    console.log('waitingPrint func exe');
    // 0. URL 상 wno 가져오기
    const wno = new URLSearchParams( location.search ).get('wno');
    // 1. fetch option
    const option = { method : "GET" };
    // 2. fetch response
    const response = await fetch( `/waiting/find?wno=${wno}`, option );
    // 3. fetch data
    const data = await response.json();
    // 4. where
    let wnoBox = document.querySelector('.wnoBox');
    let wphoneInput = document.querySelector('.wphoneInput');
    let wcountInput = document.querySelector('.wcountInput');
    // 5. print
    wnoBox.innerHTML = data.wno;
    wphoneInput.value = data.wphone;
    wcountInput.value = data.wcount;
} // func end
waitingPrint();

// [2] 대기수정 기능
const waitingUpdate = async ( ) => {
    console.log('waitingUpdate func exe');
    // 0. URL 상 wno 가져오기
    const wno = new URLSearchParams( location.search ).get('wno');
    // 1. Input Value
    const wphone = document.querySelector('.wphoneInput').value;
    const wcount = document.querySelector('.wcountInput').value;
    // 2. Object
    const waiting = { wno, wphone, wcount };
    // 3. fetch option
    const option = {
        method : "PUT",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify( waiting )
    } // option end
    // 4. fetch response
    const response = await fetch( "/waiting", option );
    // 5. fetch data
    const data = await response.json();
    // 6. data에 따른 출력
    if ( data == true ){
        alert('수정 성공')
        location.href = `/assignment5/view.jsp?wno=${wno}`
    } else {
        alert('수정 실패')
    } // if end
} // func end