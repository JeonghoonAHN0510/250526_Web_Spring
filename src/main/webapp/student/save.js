console.log('save.js open');

// [1] 등록 fetch
const save = async ( ) => {
    console.log('save func exe');
    // 1. 매개변수 준비 > 입력받은 값 가져오기
    const snameInput = document.querySelector('.sname');
    const sname = snameInput.value;
    const skorInput = document.querySelector('.skor');
    const skor = skorInput.value;
    const smathInput = document.querySelector('.smath');
    const smath = smathInput.value;
    // 2. 입력받은 값을 객체화 > 속성명과 속성값변수명이 같으면 생략 가능
    // const data = { sname : sname, skor : skor, smath : smath };
    const student = { sname, skor, smath };
    // 3. fetch option
    const option = {
        method : "POST",
        headers : { "Content-Type" : "application/json" },
        body : JSON.stringify( student )
    } // option end
    // 4. fetch exe
    const response = await fetch( "/student/save", option );
    const data = await response.json();
    // 5. 결과에 따른 출력하기
    if ( data == true ){
        alert('등록 성공');
        location.href = "/student/find.jsp";    // js 페이지 전환
    }else {
        alert('등록 실패');
    } // if end
} // func end