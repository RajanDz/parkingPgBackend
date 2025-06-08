var map = L.map('map').setView([42.443232987991586, 19.26844343486122], 14);
L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);

var firstMarker = L.marker([42.444098, 19.263560]).addTo(map);
firstMarker.bindPopup("<b>Parking centar</b> <br>0.50$/h</br>").openPopup();
var secondMarker = L.marker([42.43389 , 19.25222]).addTo(map);
secondMarker.bindPopup("<b>Parking centar</b> <br>0.50$/h</br>").openPopup();

var thirdMarker = L.marker([42.4305 , 19.2489]).addTo(map);
thirdMarker.bindPopup("<b>Parking centar</b> <br>0.50$/h</br>").openPopup();

var fourthMarker = L.marker([42.4310, 19.2590]).addTo(map);
fourthMarker.bindPopup("<b>Parking centar</b> <br>0.50$/h</br>").openPopup();

function showMenu(){
    const sideMenu = document.querySelector('.side-menu');
    sideMenu.classList.add("visible")
}

function hideMenu(){
const sideMenu = document.querySelector('.side-menu');
sideMenu.classList.remove("visible");
}

function manageProfile(){
    const profile = document.querySelector(".user-container");
    profile.classList.add('visible')
    const sideMenu = document.querySelector('.side-menu');
    sideMenu.classList.remove("visible");
}