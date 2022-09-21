function calcular() {
    
    let res;
    let op1 = parseInt(document.getElementById("op1").value);
    let op2 = parseInt(document.getElementById("op2").value);
    let oper = document.getElementById("oper").value;
    switch (oper) {
        case "Sum":
            res = op1 + op2;
            break;
        case "Subtraction":
            res = op1 - op2;
            break;
        case "Multiplication":
            res = op1 * op2;
            break;
        case "Division":
            if (op2 == 0){
                alert("Invalid op2");
                break;
            }
            res = op1 / op2;
            break;
        default:
            res = undefined
            break;
    }
    document.getElementById("res").value = res;
    window.write("sup");
}