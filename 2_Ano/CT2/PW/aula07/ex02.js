var persons = '[ \
    { "fname": "Joao", "lname": "Azevedo", "email": "jazevedo@mail.pt" }, \
    { "fname": "Manuel", "lname": "Pinto", "email": "mpinto@mail.pt" }, \
    { "fname": "Gustavo", "lname": "Soares", "email": "gsoares@mail.pt" } \
]';

function fill() {
    let data = JSON.parse(persons);
    let htm = ""
    for (i = 0; i < data.length; i++) {
        htm += "<tr>";
        htm += "<td>" + data[i].fname + "</td>";
        htm += "<td>" + data[i].lname + "</td>";
        htm += "<td>" + data[i].email + "</td>";
        htm += "</tr>";
    }
    document.getElementById("pessoal").innerHTML = htm;
}