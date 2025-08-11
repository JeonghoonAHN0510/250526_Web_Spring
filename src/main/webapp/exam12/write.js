console.log('write.js open');

const boardWrite = async ( ) => {
    console.log('boardWrite func exe');
    // 1. 입력받은 값 가져오기
    let bcontentInput = document.querySelector('.bcontent');
    let bwriterInput = document.querySelector('.bwriter');
    let bcontent = bcontentInput.value;
    let bwriter = bwriterInput.value;
    // 2. 가져온 값 객체화하기
    let board = { bcontent, bwriter };
    // 3. fetch option
    const option = {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify( board )
    } // option end
    // 4. fetch response
    const response = await fetch( "/board", option )
    // 5. fetch data
    const data = response.json();
    if( data ){
        alert('등록 성공')
        location.href = '/exam12/list.jsp'
    } else {
        alert('등록 실패')
    } // if end
} // func end