console.log('view.js open')

// [1] 회원목록 조회
const memberPrint = async ( ) => {
    console.log('memberPrint func exe')
    // 1. fetch option
    const option = { method : "GET" };
    // 2. fetch response
    const response = await fetch( "/member", option );
    // 3. fetch data
    const data = await response.json();
    // 4. where
    const memberTbody = document.querySelector('#memberTbody');
    // 5. print
    let html = "";
    data.forEach( (member) => {
        let grade = member.grade;
        if ( grade == 'A' ){
            grade = 'VIP'
        } else if ( grade == 'B' ){
            grade = '일반'
        } else if ( grade == 'C' ){
            grade = '직원'
        } // if end

        html += `<tr>
                    <td><a href="/assessment/update.jsp?custno=${member.custno}">${member.custno}</a></td>
                    <td>${member.custname}</td>
                    <td>${member.phone}</td>
                    <td>${member.address}</td>
                    <td>${member.joindate}</td>
                    <td>${grade}</td>
                    <td>${member.city}</td>
                </tr>`
    });
    memberTbody.innerHTML = html;
} // func end
memberPrint();