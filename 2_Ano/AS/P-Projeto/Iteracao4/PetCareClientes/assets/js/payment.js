function validate() {

    var cc = document.getElementById("cc").value;
    var validade = document.getElementById("validade").value;
    var cvv = document.getElementById("cvv").value;
    var paymentMethod = document.getElementById("paymentMethod").value;
    var localization = document.getElementById("localization").value;

    var mensagem = "";

    if (paymentMethod != 1) {
        mensagem += "Por favor escolher o método de pagamento!<br>";
    }
    if (localization != 1) {
        mensagem += "Por favor indicar a localização!<br>";
    }
    if (cc.length != 16) {
        mensagem += "Número de cartão de crédito deve conter 16 digitos!<br>";
    }
    if (!/^\d+$/.test(cc) && cc.length != 0) {
        mensagem += "Número de cartão de crédito deve apenas conter algarismos!<br>";
    }
    if (!/^\d{2}(-\d{4})$/.test(validade)) {
        mensagem += "Validade errada. Usar formato MM-AAAA!<br>";
    } else {
        if (validade.split("-")[0] < 1 || validade.split("-")[0] > 12) {
            mensagem += "Data de validade inválida!<br>";
        }
    }
    if (!/^\d+$/.test(cvv) || cvv.length != 3) {
        mensagem += "CVV inválido! Usar apenas 3 digitos.<br>";
    }
    if (mensagem == "") {
        window.location = "afterpayment.html";
    }
    document.getElementById("message").innerHTML = mensagem;
}