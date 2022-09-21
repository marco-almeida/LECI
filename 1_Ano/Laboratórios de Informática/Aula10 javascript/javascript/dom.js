var x = document.getElementById( "op1" );
var y = document.getElementById( "op2" );
var z = document.getElementById( "res" );
console.log( parseFloat(x.value) );
console.log( parseFloat(y.value) );
(document.getElementById( "res" ).value) = parseFloat(x.value) + parseFloat(y.value);
console.log( parseFloat(z.value) );
