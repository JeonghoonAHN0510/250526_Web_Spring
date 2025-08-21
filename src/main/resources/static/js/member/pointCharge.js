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

    if (response.code !== undefined) {
        // 오류 발생
        console.log('결제 성공')
    } // if end


} // func end