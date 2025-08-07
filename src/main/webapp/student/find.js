console.log('find.js open');

// [1] 전체조회 fetch
const find = async ( ) => {
    // async ( ) => { } : 동기화 함수
    console.log('find func exe');
    // 1. 매개변수 준비
    // 2. fetch option
    const option = { method : "GET" };
    // 3. fetch 동기화
    const response = await fetch( "/student/find", option );
    // 4. 응답객체를 json으로 변환
    const data = await response.json();
//==========================================================================\\
    // 1) 어디에 : studentTbody
    const studentTbody = document.querySelector('#studentTbody');
    // 2) 무엇을 : fetch로부터 받은 리스트를 HTML로 구성하여
    let html = "";
    // 반복문을 이용한 목록 내 객체를 HTML <tr>로 구성하기
    for( let i = 0; i < data.length; i++ ){
        const student = data[i];
        html += `<tr>
                    <td>${student.sno}</td>
                    <td>${student.sname}</td>
                    <td>${student.skor}</td>
                    <td>${student.smath}</td>
                </tr>`
    } // for end
    // 3) 출력 : .innerHTML
    studentTbody.innerHTML = html;
}; // func end
find(); // JS 실행 시 최초 실행