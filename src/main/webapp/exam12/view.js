console.log('view.js open');

// [1] 개별 조회
const boardFind = async ( ) => {
    console.log('boardFind func exe');
    // 0. URL 상의 쿼리스트링 가져오기
    // 0.1) URL 상의 웹주소 가져오기
    const URL = new URLSearchParams( location.search );
    // 0.2) 웹주소에서 쿼리스트링 값 가져오기
    const bno = URL.get('bno');

    // 1. fetch option
    const option = { method : "GET" };
    // 2. fetch response
    const response = await fetch( `/board/find?bno=${bno}`, option );
    // 3. fetch data
    const data = await response.json();
    // 4. 어디에
    const bwriterBox = document.querySelector('.bwriterBox');
    const bcontentBox = document.querySelector('.bcontentBox');
    // 5. 무엇을
    const bcontent = data.bcontent;
    const bwriter = data.bwriter;
    // 6. 출력
    bwriterBox.innerHTML = bwriter;
    bcontentBox.innerHTML = bcontent;
} // func end
boardFind();

// [2] 삭제
const boardDelete = async ( ) => {
    console.log('boardDelete func exe');
    // 1. URL 상의 쿼리스르트링 가져오기
    const URL = new URLSearchParams( location.search );
    const bno = URL.get('bno');
    // * 확인 받기
    let check = confirm('정말 삭제하시겠습니까?');
    if ( check ){
    // 2. fetch option
        const option = { method : "DELETE" }
        // 3. fetch response
        const response = await fetch( `/board?bno=${bno}`, option )
        // 4. fetch data
        const data = await response.json();
        if ( data ){
            alert('삭제 성공')
            location.href = '/exam12/list.jsp'
        } else {
            alert('삭제 실패')
        } // if end
    } // if end
} // func end

// [3] 수정페이지 이동
const boardUpdateView = async ( ) => {
    console.log('boardUpdateView func exe');

    location.href = '/exam12/update.jsp'

} // func end