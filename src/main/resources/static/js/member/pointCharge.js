console.log('pointCharge.js open');



const payment = async ( ) => {
    const pointName = document.getElementsByName('point');
    console.log( pointName );
    let point = "";
    pointName.forEach( (element) => {
        if ( element.checked ){
            console.log( element.value );
            point = element.value;
        } // if end
    }) // for end

    const response = await PortOne.requestPayment({
        // Store ID 설정
        storeId: "store-0bfc00f1-b821-4bba-a568-ed66889fe1a4",
        // 채널 키 설정
        channelKey: "channel-key-74731e51-1d44-4044-b0b9-9e5975f97422",
        paymentId: `payment-${crypto.randomUUID()}`,
        orderName: "포인트 충전",
        totalAmount: point,
        currency: "CURRENCY_KRW",
        payMethod: "CARD",
    });
    console.log( response );

    if ( response.code !== undefined ){
        // 오류 발생 -> 지금 컨셉에선 결제 성공
        console.log('결제 성공');

        // 1. fetch
        const option = { method : "POST" };
        const response = await fetch( `/payment/add?point=${point}`, option );
        const data = await response.json();
        console.log( data );
        if ( data > 0 ){
            alert(`${point}원 결제 성공!`)
        } else {
            alert('결제 실패')
        } // if end
    } // if end
} // func end