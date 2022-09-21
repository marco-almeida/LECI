////////////////////////////////////////////////////////////////////////////////
// Initialization

var _page;
goToHome();


////////////////////////////////////////////////////////////////////////////////
// Functions to load Html

function goToHome(elem) {
    // load html 
    LoadTemplate("heroHeader", "home_hero.html");
    document.getElementById("maincontainer").innerHTML = "";

    // set navbar about active
    if (elem) {
        document.getElementsByClassName("active")[0].classList.remove("active");
        elem.classList.add("active");
    }

    // set app page
    _page = "home";
}

function goToAbout(elem) {
    // load html 
    LoadTemplate("heroHeader", "about_hero.html");
    LoadTemplate("maincontainer", "about_container.html");

    // set navbar about active
    document.getElementsByClassName("active")[0].classList.remove("active");
    elem.classList.add("active");

    // set app page
    _page = "about";
}

function goToService(elem) {
    // load html 
    LoadTemplate("heroHeader", "service_hero.html");
    LoadTemplate("maincontainer", "service_container.html");

    // set navbar about active
    document.getElementsByClassName("active")[0].classList.remove("active");
    elem.classList.add("active");

    // set app page
    _page = "service";
}

async function goToMenu(elem) {
    // load html 
    await LoadTemplate("heroHeader", "menu_hero.html");
    await LoadTemplate("maincontainer", "menu_container.html");

    // set navbar about active
    document.getElementsByClassName("active")[0].classList.remove("active");
    elem.classList.add("active");

    // set app page
    _page = "menu";

    // load data
    LoadMenuData("breakfast.json");
}

// Set JSON data in Menu Html
// Load JSON data in Menu, given a Json file
// @file - file json data
async function LoadMenuData(file) {
    console.log("LoadMenuData");
    let data = await LoadData(file);
    for (i=0; i < data.length; i++) {
        document.getElementById("img"+(i+1)).setAttribute("src", "myimg/"+data[i].img);
        document.getElementById("title"+(i+1)).innerHTML = data[i].title;
        document.getElementById("price"+(i+1)).innerHTML = data[i].price;
        document.getElementById("description"+(i+1)).innerHTML = data[i].description;
    }
}




////////////////////////////////////////////////////////////////////////////////
// Load Files with Web Fetch API

// Load HTML container from Template file
// @dest - id from container where to load HTML
// @file - template filename
async function LoadTemplate(dest, file) {
    let url = "templates/" + file;
    let obj = await fetch(url);
    let txt = await obj.text();
    document.getElementById(dest).innerHTML = txt;
}

// Return JSON from file
// @file - data filename
async function LoadData(file) {
    let url = "data/" + file;
    let obj = await fetch(url);
    let data = await obj.json();
    return data;
}
