<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-// W3C// DTD HTML 4.01
 Transitional// EN" "http:// www.w3.org/TR/html4/loose.dtd">
<html lang="hr">
<script src="../../js/jquery.min.js"></script>
<script src="../../js/pdfmake.min.js"></script>
<script src="../../js/vfs_fonts.min.js"></script>

<script>
var objekti = [""];

function funk1(){
    $( "#ajax" ).load( "json #spremnik" );
    alert("Stvoren json oblik tečajeva iz baze!\n Može se vidjeti na /json")
};

function funk2(){
    if (document.getElementById('ajax').innerHTML!=""){
    let str = document.getElementById('spremnik').innerHTML;
    objekti = JSON.parse(str);
    alert("Json podaci pretvoreni u objekte i stavljeni u array 'objekti'!");}
    else {alert("Nije stvoren json oblik! Napravi ajax poziv.")}
};

function toPdf(){
    if (objekti!="") {
    var rows = [];
    rows.push(['Broj', 'Datum', 'Država', 'Šifra', 'Valuta', 'Jedinica', 'Kupovni', 'Srednji', 'Prodajni']);
    for(let i = 0; i < objekti.length; i++) {
    rows.push([objekti[i].brojTecajnice, objekti[i].datum.substring(0,12), objekti[i].drzava, objekti[i].sifraValute, objekti[i].valuta, objekti[i].jedinica, objekti[i].kupovniTecaj, objekti[i].srednjiTecaj, objekti[i].prodajniTecaj]);
    }
    var dd = {
    content: {
        table: {
                widths: ['auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto'],
                body: rows
            }
    }
    }
    pdfMake.createPdf(dd).open();}
    else {alert("Objekti nisu popunjeni! Pretvori json u objekte.")}
};

function showSpecific(){
$.ajax({
  method: "GET",
  url: "spec",
  data: {op: document.getElementById("datumSelect").value},
  success: function(response){
          document.getElementById("tab").innerHTML=response
      }
});
}

function toPdfJasper(){
$.ajax({
  method: "GET",
  url: "jasp",
  data: {dat: document.getElementById("datumSelect").value},
  success: function(response){
  var blob = new Blob([response], { type: "application/pdf" });
  saveAs(blob, "nesto.pdf");


      }
});



}

</script>
<style>

body {background-color: #282B28;font-size: 0.9em;font-family: sans-serif;}
.styled-table {
    border-collapse: collapse;
    margin: auto;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);

}
.styled-table thead tr {
    background-color: #8ebca9;
    color: #282B28;
    text-align: center;
}
.styled-table th,
.styled-table td {
    padding: 12px 15px;
}
.styled-table tbody tr {
    border-bottom: 3px solid #83bca9;
}

.styled-table tbody tr:nth-of-type(even) {
    background-color: white;
}
.styled-table tbody tr:nth-of-type(odd) {
    background-color: #3E5641;
}
.styled-table tr:last-of-type {
    border-bottom: 2px solid #83bca9;
}
.button-25 {
  background-color: #8ebca9;
  border: 1px solid #2A8387;
  border-radius: 4px;
  box-shadow: rgba(0, 0, 0, 0.12) 0 1px 1px;
  color: #282B28;
  cursor: pointer;
  font-family: -apple-system,".SFNSDisplay-Regular","Helvetica Neue",Helvetica,Arial,sans-serif;
  font-size: 17px;
  line-height: 100%;
  margin: 0 0 15px 0;
  outline: 0;
  padding: 11px 15px 12px;
  text-align: center;
  transition: box-shadow .05s ease-in-out,opacity .05s ease-in-out;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  width: 15%;
}

.button-25:hover {
  box-shadow: rgba(255, 255, 255, 0.3) 0 0 2px inset, rgba(0, 0, 0, 0.4) 0 1px 2px;
  text-decoration: none;
  transition-duration: .15s, .15s;
}

.button-25:active {
  box-shadow: rgba(0, 0, 0, 0.15) 0 2px 4px inset, rgba(0, 0, 0, 0.4) 0 1px 1px;
}

.button-25:disabled {
  cursor: not-allowed;
  opacity: .6;
}

.button-25:disabled:active {
  pointer-events: none;
}

.button-25:disabled:hover {
  box-shadow: none;
}
#datumSelect {background-color: #3E5641;padding: 11px 5px 12px;color: white}
#btDatumSelect {background-color: #3E5641; color:white}






</style>
<head>
<meta http-equiv="Content-Type" content="text/html;
 charset=ISO-8859-1">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>HNB JSP</title>
</head>
<body style="text-align:center">
<h1 style="color:#8ebca9;">HNB TEČAJEVI</h1>
<button id="bt1" class="button-25" onclick="funk1()">Ajax request</button>
<button id="bt2" class="button-25" onclick="funk2()">Json u js objekte</button>
<button id="bt3" class="button-25" onclick="toPdf()">Spremi u pdf-u</button>

<select id="datumSelect" class="button-25">
${select}
</select>
<button class="button-25" id="btDatumSelect" type="button" onclick="showSpecific()">Izaberi datum</button>
<button id="bt4" onclick="toPdfJasper()">Download pdf</button>
<table class="styled-table" id="tab">
<thead><tr>
${naslovi}
</thead></tr>
${element}
<c:forEach var="var" items="${inputLista}">
    <tr><td>${var.brojTecajnice}</td>
    <td>${var.datum}</td>
    <td>${var.drzava}</td>
    <td>${var.sifraValute}</td>
    <td>${var.valuta}</td>
    <td>${var.jedinica}</td>
    <td>${var.kupovniTecaj}</td>
    <td>${var.srednjiTecaj}</td>
    <td>${var.prodajniTecaj}</td>
    <td>${var.drzavaIso}</td></tr>
</c:forEach>
</table>
<div id="ajax" style="display: none;"></div>
</body>

</html>