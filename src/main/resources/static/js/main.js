var scroller = document.querySelector('#scroller');
var anchor = document.querySelector('#anchor');

var stompClient = null;

var payments;

$(function () {
    function connect() {

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }

    $.ajax({
        url: '/api/payments',
        type: 'GET',
        dataType: 'html',
        success: function (payload) {
            payments = JSON.parse(payload)

            alertLoop(0);
        }
    });

    var alertLoop = function (i) {
        if (payments[i]) {
            append(payments[i]);
            setTimeout(function () {
                alertLoop(i + 1);
            }, 90);
        }
    }

    function append(payment) {
        console.log(payment);
        var msg = document.createElement('div');
        msg.className = 'message';

        var trxClass = "ODL Transaction spotted";

        if (!payment.confirmed) {
            trxClass = "Uncofirmed ODL Transaction"
        }

        var spottedAt = payment.destination;

        if (payment.spottedAt && payment.spottedAt == "SOURCE") {
            spottedAt = payment.source;
        }

        msg.innerText = payment.dateTime + ", " + trxClass  + " : " + payment.amount.toFixed(6).replace(/\d(?=(\d{3})+\.)/g, '$&,') + " XRP from " + payment.source + " to " + payment.destination + ", TrxHash : " + payment.transactionHash +  ", Destination Fiat : " + payment.destinationCurrencry + ", " + spottedAt + " TradeIds : (" + payment.tradeIds + ")";

        scroller.insertBefore(msg, anchor);
    }

    function onError(event) {
    }

    function onConnected() {
        stompClient.subscribe('/topic/payments', onMessageReceived);

    }

    function onMessageReceived(payload) {
        var message = JSON.parse(payload.body);
        console.log(message);

        var msg = document.createElement('div');
        msg.className = 'message';
        var trxClass = "ODL Transaction spotted";

        if (!message.confirmed) {
            trxClass = "Uncofirmed ODL Transaction"
        }

        var spottedAt = message.destination;

        if (message.spottedAt && message.spottedAt == "SOURCE") {
            spottedAt = message.source;
        }

        msg.innerText = message.dateTime + ", " + trxClass  + " : " + message.amount.toFixed(6).replace(/\d(?=(\d{3})+\.)/g, '$&,') + " XRP from " + message.source + " to " + message.destination + ", TrxHash : " + message.transactionHash +  ", Destination Fiat : " + message.destinationCurrencry + ", " + spottedAt + " TradeIds : (" + message.tradeIds + ")";

        scroller.insertBefore(msg, anchor);
    }

    connect();
});
