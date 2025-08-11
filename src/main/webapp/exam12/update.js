console.log('update.js open');

// [1] 개별 조회(수정 전, 내용 출력)
const boardFind = async ( ) => {
    // 1. bno 가져오기
    const bno = new URLSearchParams( location.search ).get('bno');
    // 2. fetch option
    const option = { method : "GET" };
    // 3. fetch response
    const response = await fetch( `/board/find?bno=${bno}`, option );
    // 4. fetch data
    const data = await response.json();
    // 5. 어디에
    const bcontent = document.querySelector('.bcontent');
    // 6. 출력하기
    bcontent.innerHTML = data.bcontent;
} // func end
boardFind();

// [2] 게시물 수정
const boardUpdate = async ( ) => {
    console.log('boardUpdate func exe')
    // 0. 수정할 bno 가져오기
    const bno = new URLSearchParams( location.search ).get('bno');
    // 1. 입력받은 값 가져오기
    const bcontent = document.querySelector('.bcontent').value;
    // 2. 가져온 값 객체화
    const board = { bno, bcontent }
    // 3. fetch option
    const option = {
        method : "PUT",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify( board )
    } // option end
    // 4. fetch response
    const response = await fetch ( "/board", option );
    // 5. fetch data
    const data = await response.json();
    if ( data == true ){
        alert('수정 성공');
        location.href = `/exam12/view.jsp?bno=${bno}`
    } else {
        alert('수정 실패')
    } // if end
} // func end