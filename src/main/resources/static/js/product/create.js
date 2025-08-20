console.log('create.js open');

// 위도경도를 전역변수로 선언
let latlng = null;

// [1] 카카오지도로 위도·경도 받아오기
const getMap = async ( ) => {
    console.log('getMap func exe');

    // 지도를 표시할 div
    var mapContainer = document.getElementById('map'),
        mapOption = {
            // 지도의 중심좌표
            center: new kakao.maps.LatLng(37.48942383081153, 126.72433796566986),
            // 지도의 확대 레벨
            level: 3
        };
    // 지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);
    // 지도를 클릭한 위치에 표출할 마커입니다
    var marker = new kakao.maps.Marker({
        // 지도 중심좌표에 마커를 생성합니다 
        position: map.getCenter()
    }); 
    // 지도에 마커를 표시합니다
    marker.setMap(map);

    // 지도에 클릭 이벤트를 등록합니다
    // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
    kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
        // 클릭한 위도, 경도 정보를 가져옵니다.
        // 전역변수로 선언한 latlng에 대입하기.
        latlng = mouseEvent.latLng;
        // 마커 위치를 클릭한 위치로 옮깁니다.
        marker.setPosition(latlng);
        var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
        message += '경도는 ' + latlng.getLng() + ' 입니다';
        var resultDiv = document.getElementById('clickLatlng');
        resultDiv.innerHTML = message;
    });
} // func end
getMap();
 
// [2] 제품 등록
const onCreate = async ( ) => {
    console.log('onCreate func exe');

    // 입력값을 하나씩 가져오는 것이 아닌 form 전체를 가져오는 방식
    // 주의할 점 : form 안의 속성명은 name으로 설정하고, 자바 Dto의 속성명과 일치시킨다.
    // <input name="pname"> ---> ProductDto{ String pname; }

    // 1-1. 전송할 form 가져오기
    const productForm = document.querySelector('#productForm');
    // 1-2. 대용량 첨부파일 form 전환
    const productFormData = new FormData( productForm );
    // 1-3. 위도경도는 직접 form에 넣기, .append("속성명", 값)
    // [1] 에서 구한 위도경도 변수에서 꺼내와 form에 넣기
    productFormData.append( "plat", latlng.getLat() );
    productFormData.append( "plng", latlng.getLng() );

    // 2. fetch, headers 생략 가능
    const option = {
        method : "POST",
        body : productFormData
    } // option end
    const response = await fetch( "/product/create", option );
    const data = await response.json();

    // 3. 결과에 따른 출력
    if ( data > 0 ){
        alert('등록 성공');
    } else {
        alert('등록 실패');
    } // if end
} // func end

/*

    [ multipart/form-data ]
    // 1-1. 전송할 form 가져오기
    const productForm = document.querySelector('#productForm');
    // 1-2. 대용량 첨부파일 form 전환
    const productFormData = new FormData( productForm );
    // 2. fetch
    const option = {
        method : "POST",
        body : productFormData
    } // option end
    const response = await fetch( "/product/create", option );
    const data = await response.json();

    [ application/x-www-form-urlencoded ]
    // 1. 전송할 form 가져오기
    const productForm = document.querySelector('#productForm');
    // 2. fetch
    const option = {
        method : "POST",
        body : productForm
    } // option end
    const response = await fetch( "/product/create", option );
    const data = await response.json();

*/