// HTML - JS 연동 확인
console.log('exam11.js exe');

// 함수 생성 : 람다식 함수 선언으로
const boardWrite = ( ) => {
    console.log('boardWrite func exe');
    // 함수 기능 구현 - fetch
    // 1. 보낼 데이터 객체 준비
    let data = { "bcontent" : "JS테스트", "bwriter" : "테스트" };
    // 2. fetch 옵션 -> method, headers, body
    let option = {
        // 1) method : POST/PUT/GET/DELETE
        method : "POST",
        // 2) headers
        headers : {"Content-Type" : "application/json"},
        // 3) body : JSON 형식으로 전송
        body : JSON.stringify( data )
    } // option end
    // 3. fetch( URL, option )
    //      .then( response => response.json() )    -> 응답자료 타입변환
    //      .then( data => {실행문} )                -> 타입변환된 자료
    //      .catch( error => {실행문} )              -> 오류 발생 시, 실행
    fetch( "/board", option )
        .then( response => response.json() )
        .then( data => { console.log( data ) } )
        .catch( error => { console.log( error ) } );
} // func end