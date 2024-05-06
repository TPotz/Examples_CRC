import React, { Component, useState, useEffect, useRef } from 'react';
import * as Icons from '../Icons/Icons.js';
import { MapContainer, TileLayer, useMap, Marker, Popup } from 'react-leaflet'
import { nekData } from "../json_nekretnine.js";
import customIcon from '../Icons/marker.png';
import L from "leaflet";
import {TrenutniKorisnik} from '../Security/Authorisation.js';



export function InputView() {  

  return (
    <div style={{paddingBottom:40}}>

    <div class="tabs is-boxed">
  <ul>
    <li class="tab is-active" id="1" onClick={() => openTab(1)}>
      <a>
        <span class="icon is-small"><i class="fas fa-image" aria-hidden="true"></i></span>
        <span>Slot 1</span>
      </a>
    </li>
    <li class="tab" id="2" onClick={() => openTab(2)}>
      <a>
        <span class="icon is-small"><i class="fas fa-music" aria-hidden="true"></i></span>
        <span>Slot 2</span>
      </a>
    </li>
    <li class="tab" id="3" onClick={() => openTab(3)}> 
      <a>
        <span class="icon is-small"><i class="fas fa-film" aria-hidden="true"></i></span>
        <span>Slot 3</span>
      </a>
    </li>
  </ul>
</div>

{/* Ovaj dio je s inputima, a gore su tabovi */}
<div class="columns">
  <div class="column is-one-third flexy_column">
  <div class="inputTitle">Vrsta nekretnine</div>
  </div>
  <div class="column">
  <TypeInputGrid/>
  </div>
</div>

<div class="columns">
  <div class="column is-one-third flexy_column">
  <div class="inputTitle">Lifestyle</div>
  </div>
  <div class="column">
  <ModeInputGrid/>
  <div id='customRadiosGrid' className='customRadiosGrid'>
  <RadioCustomInput tekst="Sadržaji" radioName="radioSadrzaji"/>
  <RadioCustomInput tekst="Naseljenost" radioName="radioNaseljenost"/>
  <RadioCustomInput tekst="Udaljenost od centra" radioName="radioUdaljenost"/>
  <RadioCustomInput tekst="Buka" radioName="radioBuka"/>
  <RadioCustomInput tekst="Zelenilo" radioName="radioZelenilo"/>
  </div>
  </div>
</div>

<div class="columns" style={{marginTop:15}}>
  <div class="column is-one-third flexy_column">
  <div class="inputTitle">Max cijena nekretnine</div>
  </div>
  <div class="column is-narrow marginAuto">
  <input class="input" type="number" placeholder="Cijena"></input>
  </div>
</div>

<div class="columns" style={{marginTop:15}}>
  <div class="column is-one-third flexy_column">
  <div class="inputTitle">Županija</div>
  </div>
  <div class="column is-narrow marginAuto">
  <div class="field">
  <div class="control has-icons-left">
  <div class="select">
    <select id='zupanijeSelect'>
      <option selected>Grad Zagreb</option>
      <option>Primorsko-goranska</option>
      <option>Splitsko-dalmatinska</option>
      <option>Istarska</option>
      <option>Koprivničko-križevačka</option>
      <option>Bjelovarsko-bilogorska</option>
      <option>Osječko-baranjska</option>
      <option>Zagrebačka</option>
      <option>Sisačko-moslavačka</option>
      <option>Varaždinska</option>
      <option>Zadarska</option>
      <option>Međimurska</option>
      <option>Vukovarsko-srijemska</option>
      <option>Virovitičko-podravska</option>
      <option>Karlovačka</option>
      <option>Brodsko-posavska</option>
      <option>Šibensko-kninska</option>
      <option>Krapinsko-zagorska</option>
      <option>Dubrovačko-neretvanska</option>
      <option>Požeško-slavonska</option>
      <option>Ličko-senjska</option>
    </select>
  </div>
  <div class="icon is-small is-left">
    <i class="fas fa-map-marker"></i>
  </div>
</div>
</div>
  </div>
</div>


<div class="columns">
  <div className='divFooter'>
    <div class="column is-one-quarter is-offset-8">
    <button class="button">Spremi</button>
    </div>
    </div>
</div>






</div>
  );

  function openTab(tabId) {
    var tablinks, i;
    tablinks = document.getElementsByClassName("tab");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" is-active", "");
    }
    document.getElementById(tabId).className += " is-active";
  }


}

function InputRadioTile({id, tekst, ikona, radioName}) {
  return (
    <div class="input-container">
      <input id={id} class="radio-button" type="radio" name={radioName} onClick={() => enableCustomInputGrid()}></input>
      <div class="input-radio-tile">
        <div class="icon">
          {ikona}
        </div>
        <label for={id} class="input-radio-tile-label">{tekst}</label>
      </div>
    </div>
  )
}


{/* Radio name je bitan jer tako zna da treba deselektati ostala dugmad kada selectaj jedno iz radio grupe*/}
function TypeInputGrid() {
  return (
    <div class="input-radio-container">
  <div class="input-radio-tile-group">
    <InputRadioTile id="stan" tekst="stan" ikona={<Icons.Apartment/>} radioName="typeRadio"/>
    <InputRadioTile id="kuca" tekst="kuća" ikona={<Icons.House/>} radioName="typeRadio"/>
    <InputRadioTile id="prostor" tekst="prostor" ikona={<Icons.Suitcase/>} radioName="typeRadio"/>
    <InputRadioTile id="grad_zemlj" tekst="zemljište" ikona={<Icons.BuildingField/>} radioName="typeRadio"/>
    <InputRadioTile id="pojl_zemljiste" tekst="zemljište" ikona={<Icons.Field/>} radioName="typeRadio"/>
  </div>
</div>
)
}

function ModeInputGrid() {
  return (
    <div class="input-radio-container">
  <div class="input-radio-tile-group">
    <InputRadioTile id="aktivnost" tekst="aktivnost" ikona={<Icons.Activity/>} radioName="modeRadio"/>
    <InputRadioTile id="zelenilo" tekst="zelenilo" ikona={<Icons.Leaf/>} radioName="modeRadio"/>
    <InputRadioTile id="custom" tekst="custom" ikona={<Icons.Gears/>} radioName="modeRadio"/>
  </div>
</div>
)
}

function RadioPositiveNegative({radioName}) {
return (
  <div class="radio-input-good-bad">
 <input type="radio" id="negative" name={radioName} value="negative"></input>
  <div class="circle-good-bad">
   </div> 
 <input  type="radio" id="neutral" name={radioName} value="neutral"></input>
  <div class="circle-good-bad">
  </div> 
 <input type="radio" id="positive" name={radioName} value="positive"></input>
  <div class="circle-good-bad">
  </div>
</div>
)
}

function RadioCustomInput({tekst, radioName}) {
  return (
    <div class="columns">
  <div class="column is-one-quarter is-offset-one-quarter flexy_column">
  <RadioPositiveNegative radioName={radioName}/>
  </div>
  <div class="column is-one-quarter radioPositiveNegativeTitle">{tekst}</div>
  </div>
  )
}

function enableCustomInputGrid() {
  var customGrid = document.getElementById('customRadiosGrid');
  if (customGrid!=null && document.getElementsByName('modeRadio')[2].checked) {
    customGrid.style.display ="block";
  } else {
    customGrid.style.display ="none"
  }
}


export function AnalizaView() {

  /*TODO: ovdje neka je upit koji hvata podatke iz baze i onda puni u nekretnineData a ne hardcode */
  /*var nekretnineData = nekData;*/

  const [totalNekretnine, setTotalneNekretnine] = useState([""]);
  const [mapCenter, setMapCenter] = useState([45.811726310710455, 15.97823617187989]);
  const [map, setMap] = useState();

  const requestOptions = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ user: TrenutniKorisnik.getName().split("@")[0], slot: 1 })
};

  useEffect(() => {
      // GET request using fetch inside useEffect React hook
      fetch('api/findByUserSlot', requestOptions)
          .then(response => response.json())
          .then(data => setTotalneNekretnine(data));

  // empty dependency array means this effect will only run once (like componentDidMount in classes)
  }, []);

  var nekretnineData = totalNekretnine;

  /*ovdje se ordera descending */
  nekretnineData = nekretnineData.sort((a, b) => parseFloat(b.ocjena) - parseFloat(a.ocjena));

  const [fieldsInfoList, setfieldInfoList] = useState([]);

  const getFieldsInfoClick = (data) => {
    document.getElementById("detaljiDiv").innerHTML="";
    setfieldInfoList(fieldsInfoList.concat(
      <FieldInfo iconText="fas fa-ruler" count={data.udaljenost} maxCount={5}/>,
      <FieldInfo iconText="fas fa-users" count={data.naseljenost} maxCount={5}/>,
      <FieldInfo iconText="fas fa-school" count={data.sadrzaji} maxCount={3}/>,
      <FieldInfo iconText="fas fa-volume-up" count={data.buka} maxCount={3}/>,
      <FieldInfo iconText="fas fa-leaf" count={data.zelenilo} maxCount={3}/>,
    <FieldInfo iconText="fas fa-wallet" count={data.cijenaKvadrata} maxCount={3}/>
    ));
    map.panTo({lat:data.latPoint,lng:data.longPoint});
  
  };

  //key je lat-long, a value je array nekretnina koje idu na tu tocku
  var mapaTocki = new Map();
  var uniqueTocke = [];

  
  

  uniqueTocke = nekretnineData.map(nek => nek.latPoint+"-"+nek.longPoint)
  .filter((value, index, array) => array.indexOf(value) === index) //get unique
  .filter(value => value != "null-null" && value != "0-0" )//makni nullove;

if (uniqueTocke.length > 1) {
  for (let q of uniqueTocke) {
    mapaTocki.set(q, [])
  }
}

if (mapaTocki.size == uniqueTocke.length) {
  for (let nek of nekretnineData) {

    if (nek.latPoint != null && nek.latPoint != 0 ) {
      mapaTocki.get(nek.latPoint+"-"+nek.longPoint).push(nek);
    }
  }
}


const entriesArray = [...mapaTocki.entries()];
  


  var markeri = getAllMarkeri();

  return(
    <div class="columns">
  <div class="column is-6">
  <div class="mapDiv windowDiv">
  <MapContainer center={mapCenter} zoom={9} scrollWheelZoom={false} id='map' ref={setMap}>
  <TileLayer
    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
  />

<Marker position={[45.811726310710455, 15.97823617187989]} icon={markeri[0]}>
    <Popup>
      A pretty CSS3 popup. <br /> Easily customizable.
    </Popup>
  </Marker>
 
  
 {
 entriesArray.map(([key, nekArr]) => {
  var latPoint = key.split("-")[0];
  var longPoint = key.split("-")[1];

if(nekArr.length == 1) {
  return (
    <Marker position={[latPoint, longPoint]} icon={markeri[getMarkerIndex(nekArr[0].ocjena,nekretnineData[0].ocjena)]}>
          <Popup>
            <div class="columns popUpContainer">
            <div class="column is-10"> {nekArr[0].lokacija}</div>
            <div class="column is-1 fromMapSelectButton marginAuto" onClick={() => {selectItem(nekretnineData.indexOf(nekArr[0]), nekretnineData, getFieldsInfoClick, true, {map}); map.closePopup();}}>
              <i class="fas fa-share-square" ></i>
              </div>
            </div>
          </Popup>
        </Marker>
  );
} else {
  return (
    <Marker position={[latPoint, longPoint]} icon={markeri[0]}>
<Popup>
  {nekArr.map(nek => {
    return(<div><div class="columns popUpContainer">
            <div class="column is-10"> {nek.lokacija}</div>
            <div class="column is-1 fromMapSelectButton" onClick={() => {selectItem(nekretnineData.indexOf(nek), nekretnineData, getFieldsInfoClick, true, map); map.closePopup();}}>
              <i class="fas fa-share-square" ></i>
              </div>
            </div></div>)
          })}
</Popup>
</Marker>
  );
}   
})}

</MapContainer>
<button onClick={()=> map.panTo({lat:mapCenter[0],lng:mapCenter[1]})}>Pan to center</button>
  </div>
  <div class="generalRezultatiDiv windowDiv"></div>
  </div>

  <div class="column is-3">
    <div class="listaNekretninaDiv windowDiv">

{nekretnineData.map((data, index) => {
return (
  <NekretninaItem ocjena={data.ocjena} tekst={data.tekst} lokacija={data.lokacija} id={data.id} arr={nekretnineData} index={index} getFieldsInfoClick={getFieldsInfoClick} map={map}/>
);
})}


    </div>
    </div>

  <div class="column is-3">
    <div class="ocjenaDiv windowDiv" id="ocjenaDiv">
    <div class="ocjenaDiv-header">
    <span><i class="fa fa-euro-sign" id="cijenaLabel" aria-hidden="true"></i></span>
    <span><i class="fa fa-arrows-alt" id="povrsinaLabel" aria-hidden="true"></i></span>

  
  </div>

  <span class="ocjenaLabel" id="ocjenaLabel"></span>

  <a id="linkNekretnine" href="https://uiverse.io/zanina-yassine/neat-starfish-50" target="_blank">
  <div class="ocjenaLinkButton" href="https://uiverse.io/zanina-yassine/neat-starfish-50">
  <span>Link</span>
  </div>
  </a>
    </div>
    
    <div class="detaljiDiv windowDiv" id="detaljiDiv">
    {fieldsInfoList}

    </div>
    
    </div>
</div>
  )  
}


function ChangeView({ center, zoom }) {
  const map = useMap();
  map.setView(center, zoom);
  return null;
}

function NekretninaItem({ocjena, tekst, lokacija, id, arr, index, getFieldsInfoClick, map}) {
  /*TODO: ovdje napraviti računanje na temelju ocjene koja boja treba ići u style od nekretninaItem, vjerojatno
  proslijediti itemu i total ocjenu */
  var ocjene = []; 
  arr.forEach( ele => ocjene.push(ele.ocjena));
  var boja = getNekretninaColor(ocjena,Math.max(...ocjene));
  var linearBoja = "linear-gradient(145deg, "+ getNekretninaColor(ocjena,Math.max(...ocjene)) + ",lightgrey)";
  var shadowBoja = "2px 3px 3px "+ boja + ", 2px 3px 3px black";

  return(
    <div class="nekretninaItem" 
    style={{background:linearBoja, 
    boxShadow:shadowBoja}}
    onClick={() => {selectItem(index, arr, getFieldsInfoClick, false); map.closePopup();}}>
  <label class="nekretninaNumber">{ocjena}</label>
  <label class="nekretninaInfo">
    <span class="nekInfo-1">{tekst}</span>
    <span class="nekInfo-2">{lokacija}</span>
  </label>
</div>
  )
}

function selectItem(index, arr, getFieldsInfoClick, isBringScroll) {

  const nekretnina = arr[index];
  var ocjene = []; 
  arr.forEach( ele => ocjene.push(ele.ocjena));
  var boja = getNekretninaColor(nekretnina.ocjena,Math.max(...ocjene));
  document.getElementById("cijenaLabel").innerText=" " +nekretnina.cijena;
  document.getElementById("povrsinaLabel").innerText=" " +nekretnina.povrsina + " m2";
  document.getElementById("ocjenaLabel").innerText=nekretnina.ocjena;
  document.getElementById("linkNekretnine").href=nekretnina.link;
  document.getElementById("ocjenaDiv").style.background="radial-gradient(178.94% 106.41% at 26.42% 106.41%, " + boja + " 0%, rgba(255, 255, 255, 0) 71.88%) white";
  getFieldsInfoClick(nekretnina); 

  const allElements = document.querySelectorAll('.nekretninaItem');
allElements.forEach((element) => {
  element.classList.remove('selectedNekInfo');
});

  var broj = index +1;
  var izabraniElement = document.querySelector(".listaNekretninaDiv :nth-child("+broj+").nekretninaItem");
  izabraniElement.classList.add("selectedNekInfo");
  if (isBringScroll) {
    document.querySelector('.listaNekretninaDiv').scrollTop = izabraniElement.offsetTop - 469;
  }
  
  
  

  
  
}

function getNekretninaColor(ocjena, maxOcjena) {
  const segment = maxOcjena/5;
  if (ocjena<= segment) return "#FF8989"
  else if (ocjena> segment & ocjena <=segment*2) return "#EAA781"
  else if (ocjena> segment*2  & ocjena <=segment*3) return "#D4C479"
  else if (ocjena> segment*3  & ocjena <=segment*4) return "#BFE270"
  else if (ocjena> segment*4  ) return "#A9FF68";
}

function getAllMarkeri() {

  var colors = ["greenMarker","lightGreenMarker","yellowMarker","orangeMarker","redMarker"];
  var markers = [];
  var marker = new L.Icon({
    iconUrl: customIcon,
    iconRetinaUrl: customIcon,
    iconSize: [50, 40],
    popupAnchor: [0, -20]
});

  for (let clas in colors) {
    marker = new L.Icon({
      className: colors[clas],
      iconUrl: customIcon,
      iconRetinaUrl: customIcon,
      iconSize: [50, 40],
      popupAnchor: [0, -20]
  });
  markers.push(marker)
  }
  return markers;
}

function getMarkerIndex(ocjena, maxOcjena) {
  const segment = maxOcjena/5;
  if (ocjena<= segment) return 4
  else if (ocjena> segment & ocjena <=segment*2) return 3
  else if (ocjena> segment*2  & ocjena <=segment*3) return 2
  else if (ocjena> segment*3  & ocjena <=segment*4) return 1
  else if (ocjena> segment*4  ) return 0;

}

function FieldInfo({iconText, count, maxCount}) {
  var iconclass = iconText + " fieldInfoIcon";
 
  return (
    <div className='fieldInfo'>
    <span><i class={iconclass} aria-hidden="true"></i></span>
    <div class="fieldValues"> 
    {Array.from({ length: count }).map((it, index) => <div class="fieldValue tickColor"> </div>)}
    {Array.from({ length: maxCount-count }).map((it, index) => <div class="fieldValue"> </div>)}
    </div>
    </div>
  )
}





