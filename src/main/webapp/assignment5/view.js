console.log('view.js open');

// [1] 대기현황 선택 조회
const waitingPrint = async ( ) => {
    console.log('waitingPrint func exe');
    // 0. URL 상 wno 가져오기
    const wno = new URLSearchParams( location.search ).get('wno');
    // console.log( wno );
    // 1. fetch option
    const option = { method : "GET" }
    // 2. fetch response
    const response = await fetch( `/waiting/find?wno=${wno}`, option );
    // 3. fetch data
    const data = await response.json();
    // 4. where
    const wnoBox = document.querySelector('.wnoBox');
    const wphoneBox = document.querySelector('.wphoneBox');
    const wcountBox = document.querySelector('.wcountBox');
    const wdateBox = document.querySelector('.wdateBox');
    // 5. print
    wnoBox.innerHTML = data.wno;
    wphoneBox.innerHTML = data.wphone;
    wcountBox.innerHTML = data.wcount;
    wdateBox.innerHTML = data.wdate;
} // func end
waitingPrint();

// [2] 대기수정 페이지 이동
const updateTrans = ( ) => {
    console.log('updateTrans func exe');

    // 0. URL 상 wno 가져오기
    const wno = new URLSearchParams( location.search ).get('wno');

    location.href = `/assignment5/update.jsp?wno=${wno}`
} // func end


// [3] 대기현황 삭제
const waitingDelete = async ( ) => {
    console.log('waitingDelete func exe');
    // 0. URL 상 wno 가져오기
    const wno = new URLSearchParams( location.search ).get('wno');
    // * 확인받기
    let check = confirm('정말로 삭제하시겠습니까?');
    if ( check == true ){
        // 1. fetch option
        const option = { method : "DELETE" };
        // 2. fetch response
        const response = await fetch( `/waiting?wno=${wno}`, option );
        // 3. fetch data
        const data = await response.json();
        // 4. data에 따른 출력
        if ( data == true ){
            alert('삭제 성공');
            location.href = '/assignment5/list.jsp'
        } else {
            alert('삭제 실패')
        } // if end
    } // if end
} // func end
