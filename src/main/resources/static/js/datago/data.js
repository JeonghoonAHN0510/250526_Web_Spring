/*
    - 활용목적 : 연구, 웹개발 테스트 중
    - 인증키 설정
        ApiKeyAuth  : 일반 인증키(Encoding) 입력
        ApiKeyAuth2 : 일반 인증키(Decoding) 입력
        -> [ OpenAPI 실행 준비 ] -> [ OpenAPI 호출 ]
*/
console.log('data.js open');

// [1] 인천 부평구 주유소 현황
const dataAPI1 = async ( ) => {
    console.log('dataAPI1 func exe');
    // 1. 요청한 URL 선언 : serviceKey와 URL은 합쳐서 사용해도 무방
    const serviceKey = '1gnf8NlFWQRKR8VMIP7cDXTlGjCo8QSffKkmq8FkUzQrgTtLs%2BYVKc4b6Qhk9bvA5ZdhezAlZfPOuzayxMjbvg%3D%3D';
    const URL = 'https://api.odcloud.kr/api/15102672/v1/uddi:d26dabc4-e094-463d-a4b1-cab3af66bb6d?page=1&perPage=38&serviceKey=';
    // perPage : 페이지당 가져올 데이터 수
    // -> URL의 perPage를 수정하여 한 번에 가져올 데이터량을 설정
    
    // 2. fetch를 활용한 공공데이터 API 요청
    const option = { method : "GET" };
    const response = await fetch( URL + serviceKey, option );
    const data = await response.json();
    console.log( data );        // 요청 결과값을 console에 출력하고 분석하여 사용
    console.log( data.data );   // data : 실질적인 데이터가 들어오는 속성명
    
    // 3. Print
    // 3-1. where
    const dataTbody = document.querySelector('#dataTbody');
    // 3-2. what
    let html = '';
    data.data.forEach( ( value ) => {
        html += `<tr>
                    <td>${value.연번}</td>
                    <td>${value.상호}</td>
                    <td>${value.업종}</td>
                    <td>${value.전화번호}</td>
                    <td>${value['주소']}</td>
                </tr>`
    });
        // 특수문자가 존재하는 경우, ['속성명']으로 표기
    // 3-3. print
    dataTbody.innerHTML = html;
} // func end
dataAPI1();

// [2] 사업자등록정보 상태조회 서비스
const dataAPI2 = async ( ) => {
    console.log('dataAPI2 func exe');
    // 1. 요청할 데이터 준비
    const b_no = document.querySelector('.b_no').value;
    const obj = { "b_no": [b_no] };   // '-'없는 사업자번호 입력
    const serviceKey = "1gnf8NlFWQRKR8VMIP7cDXTlGjCo8QSffKkmq8FkUzQrgTtLs%2BYVKc4b6Qhk9bvA5ZdhezAlZfPOuzayxMjbvg%3D%3D";
    const URL = "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=";

    // 2. fetch를 활용한 데이터 전송
    const option = {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify( obj )
    } // option end
    const response = await fetch ( URL + serviceKey, option );
    const data = await response.json();
    console.log( data );

    alert( data.data[0]['tax_type'] );
    // 속성명에 특수문자가 포함된 경우, ['속성명']을 이용

} // func end