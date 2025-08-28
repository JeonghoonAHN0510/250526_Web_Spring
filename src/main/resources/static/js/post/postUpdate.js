console.log('postUpdate.js open');

// 조회중인 pno 가져오기
const params = new URL( location.href ).searchParams;
const pno = params.get('pno');      console.log( pno );
const Params_cno = params.get('cno');      console.log( Params_cno );

// [*] 썸머노트 실행
// $ : jquery( JS확장 라이브러리 ) 문법
$( document ).ready( function() {
    $( '#summernote' ).summernote({
        lang: 'ko-KR',      // default: 'en-US'
        minHeight : 300    // 최소 높이
    });
});

// 개별조회하여 기존 입력 출력
const getPost = async ( ) => {
    console.log('getPost func exe');
    // 1. fetch
    const option = { method : "GET" };
    const response = await fetch ( `/post/view?pno=${pno}`, option );
    const data = await response.json();
    // 2. print
    document.querySelector('.cno').value = data.cno;            // select 마크업도 .value로 가면 된다.
    document.querySelector('.ptitle').value = data.ptitle;
    document.querySelector('.note-editable').innerHTML = data.pcontent;
} // func enc
getPost();

// 게시물 수정
const onUpdate = async ( ) => {
    console.log('onUpdate func exe');
    try {
        // 1. Input value
        const cno = document.querySelector('.cno').value;
        const ptitle = document.querySelector('.ptitle').value;
        const pcontent = document.querySelector('.pcontent').value;
        // 2. fetch
        const obj = { pno, cno, ptitle, pcontent };
        const option = {
            method : "PUT",
            headers : { "Content-Type" : "application/json" },
            body : JSON.stringify( obj )
        } // option end
        const response = await fetch( "/post", option );
        const data = await response.json();
        if ( data > 0 ){
            alert('수정 완료');
            location.href = `/post/singlePost.jsp?pno=${pno}&cno=${cno}`;
        } else {
            alert('수정 실패');
        } // if end
    } catch ( error ) {
        console.log( error );
    } // try-catch end
} // func end