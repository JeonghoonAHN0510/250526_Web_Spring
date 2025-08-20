console.log('list.js open');

// 제품리스트를 받을 전역변수 선언
let productList = [];

// [1] 모든 제품정보 조회
const getList = async ( ) => {
    console.log('onList func exe');

    // 1. fetch
    const option = { method : "GET" };
    const response = await fetch( "/product/list", option );
    const data = await response.json();
    console.log( data );
    // 제품리스트를 전역변수에 저장
    productList = data;

} // func end

// [2] 카카오지도로 마커클러스터 추가하기
const getMap = async ( ) => {
    console.log('getMap func exe');

    // 클라이언트의 위치 정보 가져오기
    const position = await myPosition();

    // 1. 지도 생성
    // 지도를 표시할 div
    var map = new kakao.maps.Map(document.getElementById('map'), {
        // 지도의 중심좌표
        center : new kakao.maps.LatLng( position.coords.latitude, position.coords.longitude ),
        // 지도의 확대 레벨
        level : 5
    });

    // 2. 마커 클러스터러를 생성합니다
    // 마커 클러스터러를 생성할 때 disableClickZoom 값을 true로 지정하지 않은 경우
    // 클러스터 마커를 클릭했을 때 클러스터 객체가 포함하는 마커들이 모두 잘 보이도록 지도의 레벨과 영역을 변경합니다
    // 이 예제에서는 disableClickZoom 값을 true로 설정하여 기본 클릭 동작을 막고
    // 클러스터 마커를 클릭했을 때 클릭된 클러스터 마커의 위치를 기준으로 지도를 1레벨씩 확대합니다
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 10, // 클러스터 할 최소 지도 레벨
        disableClickZoom: true // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
    });

    // 3. 전역변수로 선언한 제품리스트를 마커로 생성
    // 3-1. map 반복문을 이용하여 마커리스트 생성
    let markers = productList.map( ( product ) => {
        // 3-2. 마커 생성
        let marker = new kakao.maps.Marker({
            position : new kakao.maps.LatLng( product.plat, product.plng )
        })
        // 3-3. 마커 클릭 이벤트
        kakao.maps.event.addListener( marker, 'click', ( ) => {
            alert( `클릭한 제품명은 ${product.pname}입니다.`)
        })
        // 3-4. 마커 반환
        return marker;
    })
    // 3-3. 반복문 종료 후, 마커리스트를 클러스터러에 추가
    clusterer.addMarkers( markers );

    // 4. 마커 클러스터러에 클릭이벤트를 등록합니다
    // 마커 클러스터러를 생성할 때 disableClickZoom을 true로 설정하지 않은 경우
    // 이벤트 헨들러로 cluster 객체가 넘어오지 않을 수도 있습니다
    kakao.maps.event.addListener( clusterer, 'clusterclick', function(cluster) {
        // 현재 지도 레벨에서 1레벨 확대한 레벨
        var level = map.getLevel() - 1;
        // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
        map.setLevel(level, {anchor: cluster.getCenter()});
    });
} // func end

// [*] 함수 동기화
const init = async ( ) => {
    // 1. 모든 제품정보 조회 함수 먼저 실행
    await getList();
    // 2. 지도 마커 함수 2번째로 실행
    await getMap();
} // func end
init();

/*

    [ for 반복문을 이용한 마커 등록 ]
    // 3. 전역변수로 선언한 제품리스트를 마커로 생성
    let markerList = [];
    // 3-1. 반복문을 이용한 제품리스트 반복하기
    for( let i = 0; i < productList.length; i++ ){
        // 3-2. 제품리스트에서 i번째 제품객체 반환
        const product = productList[i];
        // 3-3. 마커 생성하기
        let marker = new kakao.maps.Marker( {
            position : new kakao.maps.LatLng( product.plat, product.plng )
        })
        // 3-4. 마커리스트에 마커 추가
        markerList.push( marker );
    } // for end
    // 3-5. 반복문 종료 후, 마커리스트를 클러스터러에 추가
    clusterer.addMarkers( markerList );

    [ map 반복문을 이용한 마커 등록 ]
    // 3-1. 전역변수로 선언한 제품리스트를 마커로 생성
    // 3-2. map 반복문을 이용하여 마커리스트 생성
    let markers = productList.map( (product) => {
        return new kakao.maps.Marker({
            position : new kakao.maps.LatLng( product.plat, product.plng )
        })
    })
    // 3-3. 반복문 종료 후, 마커리스트를 클러스터러에 추가
    clusterer.addMarkers( markers );
*/