console.log('position.js open');

// [1] 컴퓨터 IP기반 위치 조회
const myPosition = async ( ) => {
    console.log('myPosition func exe');

    const position = await new Promise( ( resolve, reject ) => {
        // new Promise : 비동기 객체
        // resolve : 성공했을 때, reject : 실패했을 때

        // 현재 IP기반의 위도·경도 조회: navigator.geolocation.getCurrentPosition( 성공객체, 실패객체, {옵션} )
        navigator.geolocation.getCurrentPosition( resolve, reject, {
            enableHighAccuracy : true,  // 가능한 정확한 위치 조회
            timeout : 5000,             // 5초 안에 못 가져오면, 실패(reject) 반환
            maximumAge : 0              // 캐시 정보는 사용 안함
        }); // navigator.geolocation end
    }); // promise end
    console.log( `위치정보 : ${position}` );
    console.log( `위도 : ${position.coords.latitude}` );
    console.log( `경도 : ${position.coords.longitude}` );
    return position;
} // func end
myPosition();