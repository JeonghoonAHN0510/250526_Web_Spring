console.log( 'example2.js open' );   // HTML에 JS 파일 연동 확인

/*
[ FETCH ] 라이브러리 함수
- 외부 주소로부터 요청/응답을 *비동기 통신* 지원하는 함수
    비통기통신 : fetch(JS내장), ajax(JQuery), axios(React)
- 사용법
    fetch( URL, option )
        .then( response => response.json() )
        .then( data => { 실행문 } )
        .catch( error => { 실행문 } )
    1) URL : 통신할 URL 주소
    2) 통신할 option 정보 -> headers/body는 POST/PUT
        { 
            method : "GET/POST/PUT/DELETE",
            headers : { "Content-Type" : "application/json" },
            body : JSON.stringify( {전송할 객체} ) 
        }
    3) then( response => response.json() )
        -> 응답자료를 JSON 타입으로 변환
    4) then( data => {실행문} )
        -> 변환된 자료를 매개변수로 받아 실행문 처리
    5) catch( error => {실행문} )
        -> 통신 간 오류 발생시 실행문 처리


*/

// TalendAPI 대신 실제로 통신함수 만들어보기
// [1] GET 방식
const func1 = ( ) => {
    console.log('func1 exe');
    // fetch를 이용한 Spring Controller와 통신하기, GET은 생략 가능
    fetch("/day04/method1", { method : "GET" } );
} // func end

// [2] POST 방식
const func2 = ( ) => {
    console.log('func2 exe');
    fetch("/day04/method2", { method : "POST" } );
} // func end

// [3] PUT 방식
const func3 = ( ) => {
    console.log('func3 exe');
    // baseURL은 생략 가능
    fetch("/day04/method3", { method : "PUT" } );
} // func end

// [4] DELETE 방식
const func4 = ( ) => {
    console.log('func4 exe');
    fetch("/day04/method4", { method : "DELETE" } );
} // func end

// [5] GET 방식 + 쿼리스트링
const func5 = ( ) => {
    console.log('func5 exe');
    const name = '유재석';  // 샘플 데이터
    const age = 20;         // 샘플 데이터
    // fetch 함수를 이용한 쿼리스트링 방식 -> 백틱 사용
    fetch(`/day04/method5?name=${name}&age=${age}`, { method : "GET" } )
        .then( response => response.json() )
        .then( data => {console.log( data )} )
        .catch( error => { console.log( error )} );
} // func end

// [6] POST 방식 + BODY
const func6 = ( ) => {
    console.log('func6 exe');
    const name = '유재석';  // 샘플 데이터
    const age = 20;         // 샘플 데이터
    // fetch 함수를 이용한 Body 방식
    const option = {
        method : "POST",
        // HTTP BODY 타입 설정
        headers : { "Content-Type" : "application/json" },
        // JSON 타입으로 변환
        // JSON.stringify() : JSON -> 문자열타입
        // JSON.parse() : 문자열타입 -> JSON
        body : JSON.stringify( { name : name, age : age } )
    } // option end
    fetch("/day04/method6", option)
        .then( response => response.json() )
        .then( data => {console.log( data )} )
        .catch( error => {console.log( error )} );
} // func end

// [7] PUT 방식 + BODY
const func7 = ( ) => {
    console.log('func7 exe');
    let sample = { name : '유재석', age : 20 };
    let option = {
        method : "PUT",
        headers : { "Content-Type" : "application/json" },
        body : JSON.stringify( sample )
    } // option end
    fetch("/day04/method7", option )
        .then( response => response.json() )
        .then( data => {console.log( data )} )
        .catch( error => {console.log( error )} );
} // func end

// [8] DELETE 방식 + 쿼리스트링
const func8 = ( ) => {
    console.log('func8 exe');
    const name = '유재석';
    const age = 40;
    fetch(`/day04/method8?name=${name}&age=${age}`, {method : "DELETE"} )
        .then( response => response.json() )
        .then( data => {console.log( data )} )
        .catch( error => {console.log( error )} );
} // func end