function validate() {

    var password = document.getElementById("password").value;
    var confirmarpassword = document.getElementById("confirmarpassword").value;
    var codigopostal = document.getElementById("codigopostal").value;
    var morada = document.getElementById("morada").value;
    var email = document.getElementById("email").value;
    var nome = document.getElementById("nome").value;

    const validateEmail = (email) => {
        return String(email)
            .toLowerCase()
            .match(
                /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            );
    };

    var mensagem = "";
    if (!validateEmail(email)) {
        mensagem += "Email inválido!<br>";
    }
    if (password != confirmarpassword) {
        mensagem += "As palavras-passe não são iguais!<br>";
    }
    if (password.length < 8) {
        mensagem += "Palavra-chave deve ter pelo menos 8 carateres!<br>";
    }
    if (morada.length == 0) {
        mensagem += "O campo morada deve ser preenchido!<br>";
    }
    if (nome.length == 0) {
        mensagem += "O campo nome deve ser preenchido!<br>";
    }
    if (!/^\d{4}(-\d{3})$/.test(codigopostal)) {
        mensagem += "Código postal inválido!<br>";
    }
    if (mensagem == "") {
        window.location = "index.html";
    }
    document.getElementById("message").innerHTML = mensagem;
}