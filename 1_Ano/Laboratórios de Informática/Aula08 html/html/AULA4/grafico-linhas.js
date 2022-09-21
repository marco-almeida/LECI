function desenhaGrafico() {
    $("#grafico-linhas").highcharts({
        title: {
            text: "Média de temperaturas",
        },
        xAxis: {
            categories: ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun",
                "Jul", "Ago", "Set", "Out", "Nov", "Dez"]
        },
        series: [{
            name: "Borba de Godim",
            data: [9.3, 10.4, 11.9, 13.2, 15.2, 18.3, 20.2, 20.1, 18.9, 16, 12.6, 10.6]
        }, {
            name: "Aveiro",
            data: [8, 9, 11, 12, 14, 17, 19, 19, 18, 15, 11, 9]
        }]
    });
};

function graficoColumns() {
    $("#grafico-colunas").highcharts({
        chart: {
            type: "column"
        },
        title: {
            text: "Média de temperaturas",
        },
        series: [{
            name: "Borba de Godim",
            data: [9.3, 10.4, 11.9, 13.2, 15.2, 18.3, 20.2, 20.1, 18.9, 16, 12.6, 10.6]
        }, {
            name: "Aveiro",
            data: [8, 9, 11, 12, 14, 17, 19, 19, 18, 15, 11, 9]
        }]
    });
};

function graficoPie() {
    $("#grafico-pie").highcharts({
        chart: {
            type: "pie"
        },
        title: {
            text: "Média de temperaturas",
        },
        series: [{
            name: "Borba de Godim",
            data: [9.3, 10.4, 11.9, 13.2, 15.2, 18.3, 20.2, 20.1, 18.9, 16, 12.6, 10.6]
        }, {
            name: "Aveiro",
            data: [8, 9, 11, 12, 14, 17, 19, 19, 18, 15, 11, 9]
        }]
    });
};

function graficoArea() {
    $("#grafico-area").highcharts({
        chart: {
            type: "area"
        },
        title: {
            text: "Média de temperaturas",
        },
        series: [{
            name: "Borba de Godim",
            data: [9.3, 10.4, 11.9, 13.2, 15.2, 18.3, 20.2, 20.1, 18.9, 16, 12.6, 10.6]
        }, {
            name: "Aveiro",
            data: [8, 9, 11, 12, 14, 17, 19, 19, 18, 15, 11, 9]
        }]
    });
};



var map = new L.Map("borba", { center: [41.318323, -8.141478], zoom: 15 });
var osmUrl = "http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png";
var osmAttrib = "Map data OpenStreetMap contributors";
var osm = new L.TileLayer(osmUrl, { attribution: osmAttrib });
map.addLayer(osm);
