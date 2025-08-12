console.log('list.js open')

// [1] 대기현황 전체출력
const waitingPrintAll = async ( ) => {
    console.log('waitingPrintAll func exe');
    // 1. fetch option
    const option = { method : "GET" };
    // 2. fetch response
    const response = await fetch( "/waiting", option );
    // 3. fetch data
    const data = await response.json();
    // 4. where
    let waitingTbody = document.querySelector('#waitingTbody');
    // 5. html
    let html = '';
    data.forEach( (waiting) => {
        html += `<tr>
                    <td>${waiting.wno}</td>
                    <td><a href="/assignment5/view.jsp?wno=${waiting.wno}">${waiting.wphone}</a></td>
                    <td>${waiting.wcount}</td>
                </tr>`
    });
    // 6. print
    waitingTbody.innerHTML = html;
} // func end
waitingPrintAll();

// [2] 대기등록 페이지 이동
const writeTrans = ( ) => {
    console.log('writeTrans func exe');

    location.href = '/assignment5/write.jsp'
} // func end