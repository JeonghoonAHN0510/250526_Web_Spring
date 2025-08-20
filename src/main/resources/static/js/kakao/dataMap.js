console.log('dataMap.js open');

// [1] 공공데이터 - 인천시 동구 약국현황
const dataAPI = async ( ) => {
    console.log('dataAPI func exe');
    // 1. 공공데이터 준비
    const serviceKey = '1gnf8NlFWQRKR8VMIP7cDXTlGjCo8QSffKkmq8FkUzQrgTtLs%2BYVKc4b6Qhk9bvA5ZdhezAlZfPOuzayxMjbvg%3D%3D';
    const URL = 'https://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=34&serviceKey=';
    // 2. fetch를 이용한 공공데이터 API 요청
    const option = { method : "GET" };
    const response = await fetch( URL + serviceKey, option );
    const data = await response.json();
    console.log( data );
    // 3. 사이드바에 정보 출력하기
    // 3-1. where
    const sidebar = document.querySelector('#sidebar');
    // 3-2. what
    let html = '';
    data.data.forEach( (value) => {
        html += `<div id="store">
                    <div>${value.약국명}</div>
                    <div>${value.전화번호}</div>
                    <div>${value.소재지도로명주소}</div>
                 </div>`
    });
    // 3-3. print
    sidebar.innerHTML = html;
} // func end
dataAPI();

// [2] 카카오맵 사용하기, https://apis.map.kakao.com/web/sample/addClustererClickEvent/
const kakaoMap = async ( ) => {
    console.log('kakaoMap func exe');

    // 클라이언트의 위치 정보 가져오기
    const position = await myPosition();

    // 1. 지도를 표시할 div
    var map = new kakao.maps.Map(document.getElementById('map'), {
        center : new kakao.maps.LatLng( position.coords.latitude, position.coords.longitude ), // 지도의 중심좌표
        level : 12 // 지도의 확대 레벨
    });

    // 2. 마커 클러스터러를 생성합니다
    // -> 여러개의 마커가 겹칠 때, 도형으로 마커수를 표현하는 방법
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

    // 3. fetch를 이용한 공공데이터 자료 활용
    // 3-1. 공공데이터 준비
    const serviceKey = '1gnf8NlFWQRKR8VMIP7cDXTlGjCo8QSffKkmq8FkUzQrgTtLs%2BYVKc4b6Qhk9bvA5ZdhezAlZfPOuzayxMjbvg%3D%3D';
    const URL = 'https://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=34&serviceKey=';
    // 3-2. fetch를 이용한 공공데이터 API 요청
    const option = { method : "GET" };
    const response = await fetch( URL + serviceKey, option );
    const data = await response.json();
    console.log( data );
    // 4. map 반복문을 이용하여 마커를 하나씩 생성하여 markers 변수에 등록
    // forEach 반복문은 return이 없어서, return이 있는 map 반복문을 사용한다.
    let markers = data.data.map( (value) => {
        // 4-1. 마커 생성
        let marker = new kakao.maps.Marker({
            position : new kakao.maps.LatLng( value.위도, value.경도 )
            // LatLng : 위도/경도
        });
        // 4-2. 마커 클릭 이벤트
        kakao.maps.event.addListener( marker, 'click', ( ) => {
            // 특정 마커를 클릭하면, 사이드바에 특정 정보 출력하기        
            const sidebar = document.querySelector('#sidebar');
            let html = `<button type="button" onclick="dataAPI()"> 전체보기 </button>
                        <div id="store">
                            <div>${value.약국명}</div>
                            <div>${value.전화번호}</div>
                            <div>${value.소재지도로명주소}</div>
                        </div>`;
            sidebar.innerHTML = html;
        });
        // 4-3. 마커 리턴
        return marker;
    })
    // 5. 여러개의 마커를 가진 markers 변수를 클러스터러에 등록
    clusterer.addMarkers( markers );
    // 마커 클러스터러에 클릭이벤트를 등록합니다
    // 마커 클러스터러를 생성할 때 disableClickZoom을 true로 설정하지 않은 경우
    // 이벤트 헨들러로 cluster 객체가 넘어오지 않을 수도 있습니다
    kakao.maps.event.addListener( clusterer, 'clusterclick', function(cluster) {
        // 현재 지도 레벨에서 1레벨 확대한 레벨
        var level = map.getLevel() - 1;
        // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
        map.setLevel( level, { anchor : cluster.getCenter() } );
    });
}; // func end
kakaoMap();