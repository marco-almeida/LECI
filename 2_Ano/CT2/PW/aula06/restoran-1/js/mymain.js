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
