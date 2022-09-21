var temp = null;
function diminuirVertical(elemento){
    if(temp == null){
    var elemento = event.target;
    temp = setInterval("diminuirVertical("+elemento.id+")",10);
}
var altura = parseInt(elemento.style.height) - 10 ;
elemento.style.height = altura+"px";
    if(altura == 0){
        window.clearInterval(temporizador);
        temp = null;
    }
}

var op = "+"; //Deverá estar no topo do ficheiro.
function operacao(elemento) {
var elementoSeleccionado = elemento.options[elemento.selectedIndex];
op = elementoSeleccionado.value;
console.log("Operação: "+op);
}

function mover(elemento){
    var e = document.getElementById(elemento);
    e.style.position = "absolute";
    e.style.top = (Math.random() * window.innerHeight)+"px";
    e.style.left = (Math.random() * window.innerWidth)+"px";
    }
    

function calcular(){
    var x = document.getElementById("op1");
    var y = document.getElementById("op2");
    var z = document.getElementById("res");
    console.log(parseFloat(x.value));
    console.log(parseFloat(y.value));
    if (op == "+"){
    document.getElementById( "res" ).value = parseFloat(x.value) + parseFloat(y.value);
    } else if (op == "-"){
    document.getElementById( "res" ).value = parseFloat(x.value) - parseFloat(y.value);
    } else if (op == "/"){
        document.getElementById( "res" ).value = parseFloat(x.value) / parseFloat(y.value);
    } else if (op == "*"){
        document.getElementById( "res" ).value = parseFloat(x.value) * parseFloat(y.value);
    }
    console.log(parseFloat(z.value));
    }
window.onload = calcular;



