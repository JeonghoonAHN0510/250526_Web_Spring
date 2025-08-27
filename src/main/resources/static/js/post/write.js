console.log('write.js open');

// [*] 썸머노트 실행
$( document ).ready( function(){
    $('#summernote').summernote();
});

// [1] 첨부파일이 없는 게시물 등록
const onWrite = async ( ) => {
    console.log('onWrite func exe');
    try {
        // 1. Input value
        const cno = document.querySelector('.cno').value;
        const ptitle = document.querySelector('.ptitle').value;
        const pcontent = document.querySelector('.pcontent').value;
        // 2. fetch
        const obj = { cno, ptitle, pcontent };
        const option = {
            method : "POST",
            headers : { "Content-Type" : "application/json" },
            body : JSON.stringify( obj )
        } // option end
        const response = await fetch( "/post", option );
        const data = await response.json();
        if ( data > 0 ){
            alert('등록 완료');
            location.href = `/post/post.jsp?cno=${cno}`;
        } else {
            alert('등록 실패');
        } // if end
    } catch ( error ) {
        console.log( error );
    } // try-catch end
} // func end

// [2] 첨부파일이 있는 게시물 or 내용에 텍스트 + 이미지 포함
// https://ckeditor.com/ -> 내용에 텍스트 + 이미지 포함
// https://summernote.org/