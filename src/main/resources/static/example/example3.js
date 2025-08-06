console.log('example3.js connect');

const func1 = ( ) => {
    console.log('[1] fetch 전');
    // 비동기 fetch : fetch 실행되고 결과가 오기 전에 뒤에 있는 코드를 실행한다.
    let option = { method : "GET" };
    fetch( "/day06/exam1", option )
        .then( response => response.json() )
        .then( data => {
            console.log(data);
            console.log('[2] fetch 통신 결과')
        })
        .catch( error => { console.log(error) } )

    console.log('[3] fetch 후')
} // func end
// 코드 실행 결과 : [1] -> [3] -> [2] (비동기 fetch이기 때문에)

const func2 = ( ) => {
    console.log('func2 exe');


} // func end