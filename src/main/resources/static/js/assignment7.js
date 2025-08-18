console.log('assignment7.js open');

// [1] 공공데이터 모두 출력하기
const dataAPI = async ( ) => {
    console.log('dataAPI func exe');
    // 1. 공공데이터 가져오기
    const serviceKey = '1gnf8NlFWQRKR8VMIP7cDXTlGjCo8QSffKkmq8FkUzQrgTtLs%2BYVKc4b6Qhk9bvA5ZdhezAlZfPOuzayxMjbvg%3D%3D';
    const URL = 'https://api.odcloud.kr/api/15039731/v1/uddi:1fcb72a0-ba75-4c97-a045-9ef7e3ef43c0?page=1&perPage=72&serviceKey=';
    // 2. fetch
    const option = { method : "GET" };
    const response = await fetch( URL + serviceKey, option );
    const data = await response.json();
    console.log( data );
    // 3. 가져온 데이터 출력하기
    // 3-1. where
    const sidebar = document.querySelector('#sidebar');
    // 3-2. what
    let html = '';
    data.data.forEach( (value) => {
        html += `<div id="store">
                    <div>학교명 : ${value.학교명}</div>
                    <div>구분 : ${value.구분}</div>
                    <div>학생수 : ${value.학생수}명</div>
                    <div>주소 : ${value.주소}</div>
                    <div>교무실 : ${value.교무실}</div>
                    <div>행정실 : ${value.행정실}</div>
                 </div>`
    });
    // 3-3. print
    sidebar.innerHTML = html;
} // func end
dataAPI();

// [2] kakao 맵 가져오기
const kakaoMap = async ( ) => {
    console.log('kakaoMap func exe');

    // 1. 지도를 표시할 div
    var map = new kakao.maps.Map(document.getElementById('map'), {
        center : new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표
        level : 12 // 지도의 확대 레벨
    });

    // 마커 클러스터러를 생성합니다
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

    // 2. 공공데이터 가져오기
    const serviceKey = '1gnf8NlFWQRKR8VMIP7cDXTlGjCo8QSffKkmq8FkUzQrgTtLs%2BYVKc4b6Qhk9bvA5ZdhezAlZfPOuzayxMjbvg%3D%3D';
    const URL = 'https://api.odcloud.kr/api/15039731/v1/uddi:1fcb72a0-ba75-4c97-a045-9ef7e3ef43c0?page=1&perPage=72&serviceKey=';
    const option = { method : "GET" };
    const response = await fetch( URL + serviceKey, option );
    const data = await response.json();

    // 3. 반복문을 통해 markers 배열 만들기
    let markers = data.data.map( (value) => {
        // 3-1. markers에 넣을 marker 만들기
        let marker = new kakao.maps.Marker({
                position : new kakao.maps.LatLng( value.위도, value.경도 )
        });
        // 3-2. marker에 따라 사이드바에 출력하기
        // 지도에 클릭 이벤트를 등록합니다
        // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
        kakao.maps.event.addListener( marker, 'click', ( ) => {        
            // * 특정 marker를 클릭하면, 함수 실행
            const sidebar = document.querySelector('#sidebar');
            let html = `<button type = "button" onclick ="dataAPI()"> 전체리스트 </button>
                        <div id="store">
                            <div>학교명 : ${value.학교명}</div>
                            <div>교무실 : ${value.교무실}</div>
                            <div>행정실 : ${value.행정실}</div>
                            <div>주소 : ${value.주소}</div>
                        </div>`
            sidebar.innerHTML = html;
        });
        // 3-3. marker를 반환하여, markers 배열에 넣기
        return marker;
    })
    clusterer.addMarkers(markers);

    // 마커 클러스터러에 클릭이벤트를 등록합니다
    // 마커 클러스터러를 생성할 때 disableClickZoom을 true로 설정하지 않은 경우
    // 이벤트 헨들러로 cluster 객체가 넘어오지 않을 수도 있습니다
    kakao.maps.event.addListener(clusterer, 'clusterclick', function(cluster) {
        // 현재 지도 레벨에서 1레벨 확대한 레벨
        var level = map.getLevel() - 1;
        // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
        map.setLevel(level, {anchor: cluster.getCenter()});
    });
} // func end
kakaoMap();