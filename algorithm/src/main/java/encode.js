// TODO: Modify this function
function generateShortCode(storeId, transactionId) {
    // Logic goes here
    const current = new Date();

    const year = current.getFullYear();
    let month = current.getMonth() + 1;
    if (month.toString().length === 1) {
        month = '0' + month.toString();
    }
    let day = current.getDate();
    if (day.toString().length === 1) {
        day = '0' + day.toString();
    }


    const encodeDate = base10_to_base64(year) + base10_to_base64(month.toString() + day.toString());
    let encodeStoreId = base10_to_base64(storeId.toString());
    if (storeId < 64) {
        encodeStoreId = '.' + encodeStoreId;
    }
    const encodeTransactionId = base10_to_base64(transactionId);


    return encodeDate + encodeStoreId + encodeTransactionId;
}

// TODO: Modify this function
function decodeShortCode(shortCode) {
    // Logic goes here

    let year = shortCode.slice(0, 2);
    year = base64_to_base10(year);
    let date = shortCode.slice(2, 4);
    date = base64_to_base10(date);
    if (date.toString().length !== 4) {
        date = '0' + date.toString();
    }
    const month = date.slice(0, 2);
    const day = date.slice(2);
    const shopDate = new Date(year + '-' + month + '-' + day);
    let store = shortCode.slice(4, 6);
    if (store.charAt(0) === '.') {
        store = store.substr(1);
    }
    const storeId = base64_to_base10(store);

    const transaction = shortCode.slice(6);
    const transactionId = base64_to_base10(transaction);
    return {
        storeId: storeId, // store id goes here,
        shopDate: shopDate, // the date the customer shopped,
        transactionId: transactionId, // transaction id goes here
    };
}

function base10_to_base64(num) {
    const order = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    const base = order.length;
    let str = "", r;
    while (num) {
        r = num % base
        num -= r;
        num /= base;
        str = order.charAt(r) + str;
    }
    return str;
}

function base64_to_base10(str) {
    const order = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    const base = order.length;
    let num = 0, r;
    while (str.length) {
        r = order.indexOf(str.charAt(0));
        str = str.substr(1);
        num *= base;
        num += r;
    }
    return num;
}

const a = generateShortCode(10, 12);
console.log(a);
const b = decodeShortCode(a);
console.log(b)