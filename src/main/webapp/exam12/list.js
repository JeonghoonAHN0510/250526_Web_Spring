console.log('list.js open')

// 전체조회 메소드
const boardPrint = async ( ) => {
    console.log('boardPrint func exe');
    // 1. fetch option
    const option = { method : "GET" };
    // 2. fetch response
    const response = await fetch( "/board", option );
    // 3. fetch data
    const data = await response.json();
    // 4. .querySelector
    const boardTbody = document.querySelector('#boardTbody');
    // 5. html 순회
    let html = "";
    data.forEach( (board) => {  // 반복문을 이용하여 data 순회
        html += `<tr>
                    <td>${board.bno}</td>
                    <td><a href="/exam12/view.jsp?bno=${board.bno}">${board.bcontent}</a></td>
                    <td>${board.bwriter}</td>
                </tr>`
    });
    // 6. html 출력하기
    boardTbody.innerHTML = html;
} // func end
boardPrint();