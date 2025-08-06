console.log('example3.js connect');

// 1) 비동기 fetch : fetch 실행되고 결과가 오기 전에 뒤에 있는 코드를 실행한다.
const func1 = ( ) => {
    console.log('[1] fetch 전');
    // 비동기 fetch
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

// 2) 동기 fetch : fetch 실행되고 결과가 오는 것을 기다리고 다음 코드를 실행한다.
const func2 = async ( ) => {
    console.log('[1] fetch 전');
    // 동기 fetch
    const option = { method : "GET" };
    try{
    const response = await fetch( "/day06/exam1" , option );
    const data = await response.json();
    console.log( data );
    } catch ( error ){
        console.log( error );
    }
    console.log('[2] fetch 통신 결과')

    console.log('[3] fetch 후')
} // func end
// 코드 실행 결과 : [1] -> [2] -> [3] (비동기 fetch이기 때문에)