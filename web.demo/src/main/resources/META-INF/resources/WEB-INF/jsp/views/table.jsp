<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>


<script>
$(document).ready( function () {

    $('#table_id').DataTable({
    "searching": true,
    "processing": true,
    "serverSide": true,
   "bPaginate": true,
   pagingType: "simple_numbers",

    "ajax": {url: 'jsonFile',
    dataSrc: 'data',
                     type: "GET"},

    columns: [{ data: "id", render: function ( data, type, row, meta ) {return meta.row + meta.settings._iDisplayStart + 1 ;} },
              { data: "brojTecajnice" },
              { data: "datum", render: function ( data, type, row ) {let dan = parseInt(data.substring(8,10))+1;let praviDatum = dan.toString()+"."+data.substring(5,7)+"."+data.substring(0,4);return praviDatum ;} },
              { data: "drzava" },
              { data: "sifraValute" },
              { data: "valuta" },
              { data: "jedinica" },
              { data: "kupovniTecaj" },
              { data: "srednjiTecaj" },
              { data: "prodajniTecaj" },
              { data: "drzavaIso" },
              { data: "valuta", render: function ( data, type, row ) {return '<i class="fas fa-pen-square" aria-hidden="true" onclick="fillInput('+row.id+')"></i>' ;} },
              { data: "valuta", render: function ( data, type, row ) {return '<i class="fas fa-trash-alt" aria-hidden="true" onclick="delAlert('+row.id+')"></i>' ;} }


              ],rowId: 'id',
              language: {
                  'paginate': {
                    'previous': '<',
                    'next': '>'
                  }
                }





});

$('#fDatum').datepicker({
format: 'dd.mm.yyyy',
startDate: new Date(),
orientation: "bottom left"});


} );







</script>
<style>
thead {color: #fff;}
#table_id_info {color: #fff;}
#table_id_next {color: #fff;}
#table_id_length {color: #fff;}
#table_id_filter {color: #fff;}
#sve {margin:auto;width: 85%;}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  background-color: #23292f;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 30%;
  color: white;
  box-shadow: 0 0 15px #4e73df;
  display:flex;flex-direction:column;position:relative;border-radius:10px;padding-bottom: 40px;
  justify-content: space-evenly;
}

/* The Close Button */
.close {
  color: #aaaaaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
  top: 8px;
  right: 15px;
  cursor: pointer;
  z-index: 12;
  color:orange;
  color: #aaaaaa;
  font-size: 28px;
  font-weight: bold;
  position: absolute;
}

.close:hover,
.close:focus {
  color: #dd7a81;
  text-decoration: none;
  cursor: pointer;}

#impRow {width:65%;margin-left: 12%;height:50px;display:flex;gap:20px;margin-top:13px;justify-content:flex-end;align-items:center;color:white;font-size:20px;}

.impNew {font-size:20px;width:150px;transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;border-radius:.25rem;border: 1px solid #ced4da;}

#btnAdd{position: absolute;
bottom: 8px;
right: 15px;
cursor: pointer;
z-index: 12;
color:#38424b;
font-size: 18px;
font-weight: bold;border-radius:5px;}
#btnAdd:hover {background-color:#81dd7a;border-radius: 5px;box-shadow:0px 0px 5px white}

#btnChange{position: absolute;
bottom: 8px;
right: 15px;
cursor: pointer;
z-index: 12;
color:#38424b;
font-size: 18px;
font-weight: bold;}
#btnChange:hover {background-color:#DDD77A;border-radius: 5px;box-shadow:0px 0px 5px white}
#fDatum {font-size: 20px;height: 36px;}


</style>
<button id="btnCrud">New</button>
<div id="sve">

  <table id="table_id" class="display">
      <thead>
            <tr><th>Row Id</th>${naslovi}<th>Edit</th><th>Delete</th></tr>
      </thead>
<tbody>

</tbody>
  </table>
  </div>


  <!-- The Modal -->
  <div id="myModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <div id="impRow">
        <label for="fname" style="width:250px">Broj tečajnice</label>
        <input type="text" id="fBroj" name="firstname" class="form-control" placeholder="0" style="border-radius:5px"></div>
        <div class="invalid-feedback" id="feedBroj">Unesite ispravan broj.</div>
        <div id="impRow">
        <label for="fname" style="width:250px">Datum</label>
        <input id="fDatum" class="form-control" data-date-format="dd.mm.yyyy" style="border-radius:5px"></div>
        <div class="invalid-feedback" id="feedDatum">Unesite datum.</div>
        <div id="impRow">
        <label for="fname" style="width:250px">Država</label>
        <select id="fDrzava" class="form-control" onchange='inputConnect(this.value)' style="border-radius:5px;padding: 0rem 0.5rem;height:50px;width:330px">${opcije}</select></div>
        <div class="invalid-feedback" id="feedDrzava">Odaberite državu.</div>
        <div id="impRow">
        <label for="fname" style="width:250px">Šifra valute</label>
        <input type="text" id="fSifra" name="firstname" class="form-control" placeholder="000" disabled style="border-radius:5px"></div>
        <div id="impRow">
        <label for="fname" style="width:250px">Valuta</label>
        <input type="text" id="fValuta" name="firstname" class="form-control" placeholder="AAA" disabled style="border-radius:5px"></div>
        <div id="impRow">
        <label for="fname" style="width:250px">Jedinica</label>
        <input type="text" id="fJedinica" name="firstname" class="form-control" placeholder="0" style="border-radius:5px"></div>
        <div class="invalid-feedback" id="feedJedinica">Unesite Jedinicu valute.</div>
        <div id="impRow">
        <label for="fname" style="width:250px">Kupovni tečaj</label>
        <input type="text" id="fKupovni" name="firstname" class="form-control" placeholder="0.00" style="border-radius:5px"></div>
        <div class="invalid-feedback" id="feedKupovni">Unesite decimalan broj.</div>
        <div id="impRow">
        <label for="fname" style="width:250px">Srednji tečaj</label>
        <input type="text" id="fSrednji" name="firstname" class="form-control" placeholder="0.00" style="border-radius:5px"></div>
        <div class="invalid-feedback" id="feedSrednji">Unesite decimalan broj.</div>
        <div id="impRow">
        <label for="fname" style="width:250px">Prodajni tečaj</label>
        <input type="text" id="fProdajni" name="firstname" class="form-control" placeholder="0.00" style="border-radius:5px"></div>
        <div class="invalid-feedback" id="feedProdajni">Unesite decimalan broj.</div>
        <div id="impRow">
        <label for="fname" style="width:250px">Država iso</label>
        <input type="text" id="fDrzavaIso" name="firstname" class="form-control" placeholder="AAA" disabled style="border-radius:5px"></div>

        <button type="button" id="btnAdd" onclick="add()" style="display: block">Add</button>
        <button type="button" id="btnChange" onclick="chan()" style="display: block">Edit</button>
    </div>

  </div>

<script>
// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("btnCrud");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
btn.onclick = function() {
  modal.style.display = "block";
  document.getElementById("btnChange").style.display="none";
  document.getElementById("btnAdd").style.display="block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
  clearWrong();
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

function fillInput(id){
$.ajax({
  type: "GET",
  url: "edit",
  data: {i: id},
  success: function(resultData){
                    let sp = resultData.split("&");
                    let arr = [${editDrzave}];
                    console.log(arr.toString());
                    let drBroj = arr.indexOf(sp[4]);
                     $('#fBroj').val(sp[0]);

                     $('#fDatum').val(sp[1]);
                     document.getElementById("fDrzava").options[drBroj].selected = true;
                     $('#fSifra').val(sp[3]);
                     $('#fValuta').val(sp[4]);
                     $('#fJedinica').val(sp[5]);
                     $('#fKupovni').val(sp[6]);
                     $('#fSrednji').val(sp[7]);
                     $('#fProdajni').val(sp[8]);
                     $('#fDrzavaIso').val(sp[9]);
                 },

});
modal.style.display = "block";
document.getElementById("btnAdd").style.display="none";
document.getElementById("btnChange").style.display="block";
document.getElementById("btnChange").value=id;
}
function inputConnect(s) {
let sp = s.split("&");
document.getElementById("fSifra").value=sp[0];
document.getElementById("fValuta").value=sp[1];
document.getElementById("fDrzavaIso").value=sp[2];
}
</script>
</html>
