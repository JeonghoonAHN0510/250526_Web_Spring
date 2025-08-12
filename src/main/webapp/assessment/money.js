console.log('money.js open')

// [1] 회원매출조회
const moneyPrint = async ( ) => {
    console.log('moneyPrint func exe');
    // 1. fetch option
    const option = { method : "GET" };
    // 2. fetch response
    const response = await fetch( "/money", option );
    // 3. fetch data
    const data = await response.json();
    // 4. where
    const moneyTbody = document.querySelector('#moneyTbody');
    // 5. print
    let html = "";
    data.forEach( (element) => {
        let grade = element.grade;
        if ( grade == 'A' ){
            grade = 'VIP'
        } else if ( grade == 'B' ){
            grade = '일반'
        } else if ( grade == 'C' ){
            grade = '직원'
        } // if end

        html += `<tr>
                    <td>${element.custno}</td>
                    <td>${element.custname}</td>
                    <td>${grade}</td>
                    <td>${element.totalPrice}</td>
                </tr>`
    });
    moneyTbody.innerHTML = html;
} // func end
moneyPrint();