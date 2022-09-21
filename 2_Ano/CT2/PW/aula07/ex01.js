var person = '{ \
                "name": "Joao Manuel Azevedo", \
                "home": {"street":"Avenida Calouste Gulbenkian", "number" : 10}, \
                "phone": "+351 234 372 956" \
            }';

function fill() {
    let data = JSON.parse(person);
    document.getElementById("name").value = data.name;
    document.getElementById("home").value = data.home.street + ", " + data.home.number;
    document.getElementById("phone").value = data.phone;
}