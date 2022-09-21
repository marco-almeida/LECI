var map = new L.Map("sede", { center: [43.862338, -79.269333], zoom: 15 });
                    var osmUrl = "http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png";
                    var osmAttrib = "Map data OpenStreetMap contributors";
                    var osm = new L.TileLayer(osmUrl, { attribution: osmAttrib });
                    map.addLayer(osm);