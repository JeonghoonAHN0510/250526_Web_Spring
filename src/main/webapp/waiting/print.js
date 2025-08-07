console.log('print.js open');

const print = async ( ) => {
    console.log('print func exe')
    // 1. 매개변수 준비
    // 2. fetch option
    const option = { method : "GET" };
    // 3. fetch exe
    const response = await fetch( "/waiting/print", option );
    const waitings = await response.json();
//===========================================================\\
    // 1. 어디에 : waitingTbody
    let waitingTbody = document.querySelector('#waitingTbody')
    // 2. 무엇을 : 받은 배열을
    let html = "";
    // for ( let i = 0; i < waitings.length; i++ ){
    //     let waiting = waitings[i];
    //     html += `<tr>
    //         <td>${waiting.wno}</td>
    //         <td>${waiting.wphone}</td>
    //         <td>${waiting.wcount}</td>
    //         <td>${waiting.wdate}</td>
    //     </tr>`
    // } // for end
    waitings.forEach( (waiting) => {
        html += `<tr>
                    <td>${waiting.wno}</td>
                    <td>${waiting.wphone}</td>
                    <td>${waiting.wcount}</td>
                    <td>${waiting.wdate}</td>
                </tr>`
    });
    // 3. 출력
    waitingTbody.innerHTML = html ;
}; // func end
print();