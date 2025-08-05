console.log('assignment3.js exe')

const waitingRegis = ( ) => {
    console.log('waitingRegis func exe');
    // 1. 보낼 데이터 준비
    let data = {
        "wphone" : "010-1111-1111",
        "wcount" : 1111
    }
    // 2. fetch option -> method, headers, body
    let option = {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(data)
    } // option end
    // 3. fetch( URL, option )
    fetch( "/waiting", option )
        .then( response => response.json() )
        .then( data => { console.log( data ) } )
        .catch( error => { console.log( error ) } )
} // func end

const waitingList = ( ) => {
    console.log('waitingList func exe');
    // 1. 보낼 데이터 준비
    // 2. fetch option
    let option = { method : "GET" }
    // 3. fetch( URL, option )
    fetch( "/waiting", option )
        .then( response => response.json() )
        .then( data => { console.log( data ) } )
        .catch( error => { console.log( error ) } )
} // func end

const waitingDelete = ( ) => {
    console.log('waitingDelete func exe');
    // 1. 보낼 데이터 준비
    let wno = 2;
    // 2. fetch option
    let option = { method : "DELETE" };
    // 3. fetch( URL, option )
    fetch( `/waiting?wno=${wno}`, option )
        .then( response => response.json() )
        .then( data => { console.log( data ) } )
        .catch( error => { console.log( error ) } )
} // func end

const waitingUpdate = ( ) => {
    console.log('waitingUpdate func exe')
    // 1. 보낼 데이터 준비
    let data = {
        "wno" : 3,
        "wcount" : 1111
    } // data end
    // 2. fetch option
    let option = {
        method : "PUT",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify( data )
    } // option end
    // 3. fetch( URL, option )
    fetch( "/waiting", option )
        .then( response => response.json() )
        .then( data => { console.log( data ) } )
        .catch( error => { console.log( error ) } )
} // func end