console.log('singlePost.js open');

// 조회중인 pno 가져오기
const params = new URL( location.href ).searchParams;
const pno = params.get('pno');      console.log( pno );
const cno = params.get('cno');      console.log( cno );

let postMno;

// 게시물 개별조회
const findPost = async ( ) => {
    console.log('findPost func exe');
    try {
        // 1. fetch
        const option = { method : "GET" };
        const response = await fetch ( `/post/view?pno=${pno}`, option );
        const data = await response.json();
        console.log( data );
        postMno = data.mno;
        // 2. where
        const postHeader = document.querySelector('.postHeader');
        const pContent = document.querySelector('.pContent');
        // 3. what
        let header = `[${pno}] ${data.ptitle}<br> 작성일 : ${data.pdate}  조회수 : ${data.pview}<br>작성자 : ${data.mid} `;
        let content = `${data.pcontent}`;
        // 4. print
        postHeader.innerHTML = header;
        pContent.innerHTML = content;
        // 5. 내가 쓴 글이면, 삭제 수정 버튼 생성
        if ( data.host ){
            document.querySelector('.etcBox').innerHTML = `
                        <button type="button" onclick="onPostUpdate()">게시물 수정</button>
                        <button type="button" onclick="onPostDelete()">게시물 삭제</button>`
        }
    } catch ( error ) {
        console.log( error );
    } // try-catch end
} // func end
findPost();

// 게시물 수정
const onPostUpdate = async ( ) => {
    location.href = `postUpdate.jsp?pno=${pno}&cno=${cno}`;
} // func end

// 게시물 삭제
const onPostDelete = async ( ) => {
    console.log('onPostDelete func exe');
    // 1. fetch
    const option = { method : "DELETE" };
    const response = await fetch( `/post?pno=${pno}`, option );
    const data = await response.json();
    // 2. result
    if ( data == true ){
        alert('게시물 삭제 성공');
        location.href = `post.jsp?cno=${cno}`;
    } else {
        alert('게시물 삭제 실패')
    } // if end
} // func end

// 댓글 전체조회
const findAllReply = async ( ) => {
    console.log('findAllReply func exe');
    try{
        // 1. fetch
        const option = { method : "GET" };
        const response = await fetch( `/post/reply?pno=${pno}`, option );
        const data = await response.json();
        console.log( data );
        // 2. where
        const replyTboby = document.querySelector('.replyTbody');
        // 3. what
        let html = '';
        if ( data.length == 0 ){
            html += `<tr><td colspan="4"> 댓글이 없습니다. </td></tr>`
        } else {
            data.forEach( (reply) => {
                html += `<tr>
                            <td>${reply.rno}</td>
                            <td>${reply.rcontent}</td>
                            <td>${reply.mid}</td>
                            <td>${reply.rdate}</td>
                        </tr>`
            });
        } // if end
        // 4. print
        replyTboby.innerHTML = html;
    } catch ( error ) {
        console.log( error );
    } // try-catch end
} // func end
findAllReply();

// 댓글 등록
const onWriteReply = async ( ) => {
    console.log('onWriteReply func exe');
    try {
        // 1. Input value
        const rcontent = document.querySelector('.rcontent').value;
        // 2. obj
        const obj = { rcontent, pno };
        // 3. fetch
        const option = {
            method : "POST",
            headers : { "Content-Type" : "application/json" },
            body : JSON.stringify( obj )
        } // option end
        const response = await fetch( "/post/reply", option );
        const data = await response.json();
        // 4. print
        if ( data > 0 ){
            alert('댓글등록 성공');
            location.href = `singlePost.jsp?pno=${pno}`
        } else {
            alert('댓글등록 실패');
        } // if end
    } catch ( error ) {
        console.log( error );
    } // try-catch end
} // func end