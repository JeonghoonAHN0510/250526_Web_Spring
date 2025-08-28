console.log('post.js open');

// [1] 사용자가 요청한 URL에서 카테고리 가져오기 - cno, page
const params = new URL( location.href ).searchParams;   console.log( params );
const cno = params.get('cno');                          console.log( cno );
// 만약에 page가 존재하지 않으면, 1을 대입
const page = params.get('page') || 1;                   console.log( page );
// key, keyword가 존재하지 않으면, 공백 
const key = params.get('key') || '';                    console.log( key );
const keyword = params.get('keyword') || '';            console.log( keyword );

// [2] 요쳥된 매개변수를 이용한 fetch 게시물 요청하기
const findAll = async ( ) => {
    console.log('findAll func exe');
    try {
        // 1. fetch
        const option = { method : "GET" };
        const URL = `/post?cno=${ cno }&page=${ page }&key=${ key }&keyword=${ keyword }`;
        const response = await fetch( URL, option );
        const data = await response.json();             console.log( data );
        // 2. where
        const postTbody = document.querySelector('.postTbody');
        // 3. what
        let html = '';
        // data.data 확인
        data.data.forEach( (post) => {
            html += `<tr>
                        <td>${post.pno}</td>
                        <td><a href="singlePost.jsp?pno=${post.pno}&cno=${cno}">${post.ptitle}</a></td>
                        <td>${post.mid}</td>
                        <td>${post.pdate}</td>
                        <td>${post.pview}</td>
                     </tr>`
        });
        // 4. print
        postTbody.innerHTML = html;
        // 5. 페이징 버튼 출력
        viewPageBtns( data );
    } catch(error) {
        console.log( error );
    } // try-catch end
} // func end

// 페이징 처리 시, 검색 유지
const searchURL = `&key=${ key }&keyword=${ keyword }`;

// [3] 페이징 버튼 출력 함수 -> findAll 함수 내에서 실행
const viewPageBtns = async ( data ) => {
    console.log('viewPageBtns func exe');
    // data로부터 정보 가져오기
    let currentPage = parseInt( data.currentPage );
    // parseInt( 자료 ) : 자료를 int 타입으로 변환 -> java에서 js로 넘어올 때, 정적 변수로 넘어오기에 '이전''다음'에서 +1을 하면 1 + 1 -> 11이 된다. 그래서 int로 변환 후 사용
    let totalPage = data.totalPage;
    let startBtn = data.startBtn;
    let endBtn = data.endBtn;

    // 1. where
    const pageBtnBox = document.querySelector('.pageBtnBox');
    // 2. what
    let html = '';
    // * 이전 버튼 생성
    // 1페이지에서 0페이지로 가는 것을 막기 위해서, 조건문 구현(유효성 검사)
    html += `<li><a href="post.jsp?cno=${cno}&page=${ currentPage == 1 ? 1 : currentPage - 1 }${searchURL}"> 이전 </a></li>`;
    // * 페이지 버튼 생성
    for ( let i = startBtn; i <= endBtn; i++ ){
        html += `<li><a href="post.jsp?cno=${cno}&page=${ i }${searchURL}" style="${ i == currentPage ? 'color:red' : ''}"> ${ i } </a></li>`
    } // for end
    // * 다음 버튼 생성
    // 다음 페이지가 전체 페이지보다 커지는 것을 막기 위해서, 조건문 구현(유효성 검사)
    html += `<li><a href="post.jsp?cno=${cno}&page=${ currentPage == totalPage ? totalPage : currentPage + 1 }${searchURL}"> 다음 </a></li>`;
    // 3. print
    pageBtnBox.innerHTML = html;
} // func end

// [4] 검색 기능 함수
const onSearch = async ( ) => {
    console.log('onSearch func exe');
    // 1. Input Value
    const keyValue = document.querySelector('.key').value;           // select value
    const keywordValue = document.querySelector('.keyword').value;   // input value
    console.log( key );     console.log( keyword );
    // 2. 페이지 이동 : 검색하면, 1페이지로 이동
    location.href = `post.jsp?cno=${ cno }&page=${ 1 }&key=${ keyValue }&keyword=${ keywordValue }`

} // func end

findAll();