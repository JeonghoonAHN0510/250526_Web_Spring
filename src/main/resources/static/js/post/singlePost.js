console.log('singlePost.js open');

// 조회중인 pno 가져오기
const params = new URL( location.href ).searchParams;
const pno = params.get('pno');      console.log( pno );

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
        data.forEach( (reply) => {
            html += `<tr>
                        <td>${reply.rno}</td>
                        <td>${reply.rcontent}</td>
                        <td>${reply.mid}</td>
                        <td>${reply.rdate}</td>
                    </tr>`
        });
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



} // func end