// 인텔리제이 무료버전에서는 JS 지원 X
// [1] 인텔리제이 유료버전(비권장)
// [2] 인텔리제이 플러그인에서 모든 언어 자동 AI 추천(비권장)
// [3] VSCode를 통해 코드치기 -> vscode에서 코드치고 실행은 인텔리제이에서

console.log( "JS 실행2" );

// 1. JS 자료/값
console.log( 3 );       console.log( 3.14 );        console.log( true );
console.log( null );    console.log( undefined );   console.log( "안녕1" );
console.log( '안녕2' ); console.log( `안녕3` );     console.log( [ 3, 3.14, true, '안녕4' ] );  // 리스트/배열
console.log( function 함수명(){} ); // 함수 표현
console.log( {"속성명1" : 3, "속성명2" : "안녕5" } );   // 객체 == JSON

// 2. 변수/상수 선언 키워드 : let, const
let var1 = 15;
const var2 = '유재석';

// 3. 함수 선언
function func1( a, b ){
    console.log( 'func1 exe' );
    let c = a + b;
    return c;
} // func end

// 4. JS에서 함수 실행 : 함수명( 값1, 값2 );
let result1 = func1( 10, 20 );
console.log( result1 );

// 5. HTML에서 함수 실행 : <마크업 onclick ='함수명(값1, 값2)'>

// 6. 익명 함수 선언 : 함수명이 없는 함수 -> 주로 변수/상수에 저장되어 사용된다.
const func2 = function( a, b ){
    console.log( 'func2 exe' );
}; // func end
func2( 10, 20 );    // 익명함수 실행

// 7. 람다식 함수 선언 : 일회성함수로 주로 사용 -> 여러번 사용하려면 변수/상수에 저장하여 사용
const func3 = ( a, b ) => {
    console.log( 'func3 exe' );
};
func3( 10, 20 );    // 람다식함수 실행