<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Blank</title>

    <!-- Custom fonts for this template-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->


</head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../js/sb-admin-2.min.css"/>
<link rel="stylesheet" type="text/css" href="../../js/datatables.min.css"/>

<script type="text/javascript" src="../../js/datatables.min.js"></script>
<!-- CSS only -->
<link href="../../js/bootstrap.min.css" rel="stylesheet" type="text/css">
<!-- JavaScript Bundle with Popper -->
<script type="text/javascript" src="../../js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="../../js/bootstrap-datepicker.min.css" type="text/css" />

<link rel="stylesheet" href="../../js/buttons.bootstrap5.min.css" type="text/css" />
<script type="text/javascript" src="../../js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="../../js/buttons.bootstrap5.min.js"></script>




<style>
#header {background-color: #42505b; width: 100%; height: 10%; text-align: right;}
#middle {background-color: #38424b; width: 100%; display: flex;text-align: center;min-height: 86%;position:relative}
#footer {background-color: #42505b; width: 100%; height: 30px;text-align: center; color: #fff}
#middle1 {background-color: #23292f; width: 160px; border-radius: 0 10px 10px 0;}
#middle2 {width: 100%; height: 100%;text-align: center;}
#welcome {font-size: 60px;color: #4e73df}
#home {margin-bottom: 300px;margin-top: 100px}
.but_menu {
                    display: inline-block;
                    outline: none;
                    cursor: pointer;
                    font-size: 16px;
                    line-height: 20px;
                    font-weight: 600;
                    border-radius: 8px;
                    padding: 14px 24px;
                    border: 2px solid #4e73df;
                    background: transparent;
                    color: #fff;
			margin-top: 20px;
			margin-right: 10px;

}

.fas.fa-cubes {color: #4e73df; font-size: 100px;}
.far.fa-file-pdf {color: #E3564B; font-size: 27px;}
html, body {
  height: 100%;margin: 0;
}

table.dataTable thead {background-color:#4e73df}
table.dataTable tbody .even td{background-color:#BBCAF3}

#table_id_paginate {padding:5px;border-bottom: 1px solid #42505b;border-radius: 2px;border-left: 1px solid #42505b;border-right: 1px solid #42505b;background-color: #BBCAF3;}
#table_id_filter > label:nth-child(1) > input:nth-child(1):focus {background-color: #D3E0E9; outline: none}
#table_id_length > label:nth-child(1) > select:nth-child(1) {color: #D3E0E9; background-color: #4E73DF}
#sve {color: #38424B;display: flex;flex-direction: row;justify-content: center;margin:auto;}
#emb {border: 5px solid #4e73df;border-radius: 5px;margin-left: 50px;box-shadow:0 0 15px #4e73df;}

#fadd {width:50%}

.impNew:focus {border: 2px solid #4e73df;box-shadow: 0px 0px 10px #4e73df;outline: none }
#btnCrud {
  background-color: #38424B;
  border-radius: 5px;
  padding: 2px 15px 2px 15px;
  border: 2px solid #4e73df;
  color: white;
  font-size: 24px;
  position: absolute;
  left: 555px;
  top: 82px;
  z-index: 1;}
#btnCrud:hover {
box-shadow: 0px 0px 20px #4e73df;
border-color: #4e73df;}
table.dataTable .sorting.sorting_asc{border-bottom: 4px solid #BBCAF3;}
table.dataTable .sorting.sorting_desc{border-bottom: 4px solid #BBCAF3;}
.dataTables_wrapper .dataTables_paginate {
  text-align: center;
  margin-right: 35%;
}
.fas.fa-pen-square {font-size:38px;}
.fas.fa-pen-square:hover {cursor:pointer;text-shadow: 0px 0px 25px #DFBA4E;transform: rotate(10deg);}
.fas.fa-trash-alt {font-size:38px;}
.fas.fa-trash-alt:hover {cursor:pointer;text-shadow: 0px 0px 25px #DD7A80;transform: rotate(10deg);}
fas.fa-user-circle {font-size:28px}

.alert.alert-danger{left:0px;right:0px;margin:auto;position: absolute;width:40%;z-index:5;top:65%;
color: white;background-color: rgb(221, 122, 128);border-color: white;}
.alert.alert-warning{left:0px;right:0px;margin:auto;position: absolute;width:40%;z-index:5;top:50%;
background-color: #DDD77A;border-color: white;}
.alert.alert-success{left:0px;right:0px;margin:auto;position: absolute;width:40%;z-index:5;top:50%;
color: white;background-color: #4e73df;border-color: white;}
#uredu{
right: 250px;
position: absolute;
top: 25%;
border-radius: 5px;}
#natrag{
right: 180px;
position: absolute;
top: 25%;
border-radius: 5px;}

.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-bottom {box-shadow: 0px 0px 10px white;margin-left:70px;}

</style>
<script>
var fBroj = document.getElementById("fBroj");
var fDatum = document.getElementById("fDatum");
var fDrzava = document.getElementById("fDrzava");
var fSifra = document.getElementById("fSifra");
var fValuta = document.getElementById("fValuta");
var fJedinica = document.getElementById("fJedinica");
var fKupovni = document.getElementById("fKupovni");
var fSrednji = document.getElementById("fSrednji");
var fProdajni = document.getElementById("fProdajni");
var fDrzavaIso = document.getElementById("fDrzavaIso");

function goHome(){
    $( "#middle2" ).load( "tab-home" );console.log("Sad sam u goHome metodi");


};
function goCodebook(){
    $( "#middle2" ).load( "tab-codebook" );

};
function goPdfs(){
    $( "#middle2" ).load( "tab-pdfs" );

};

function add(){


let addForm = document.getElementById("fBroj").value + "," + document.getElementById("fDatum").value + "," +
              document.getElementById("fDrzava").value + "," + document.getElementById("fSifra").value + "," +
              document.getElementById("fValuta").value + "," + document.getElementById("fJedinica").value + "," +
              document.getElementById("fKupovni").value + "," + document.getElementById("fSrednji").value + "," +
              document.getElementById("fProdajni").value + "," + document.getElementById("fDrzavaIso").value;

if (secureCheck()=="secure") {
$.ajax({
  type: "GET",
  url: "crudTable",
  data: {s: addForm, c: "add"},
  success: function(resultData){

                     $('#table_id').DataTable().draw();
                     modal.style.display = "none";
                     document.getElementById("sucessAlert").innerHTML=resultData
                     document.getElementById("sucessAlert").style.display="block";
                     fading();

                 },

});
}

}

function delAlert(id){
document.getElementById("deleteAlert").style.display="block";document.getElementById("uredu").value=id;
}

function del(){
let id = document.getElementById("uredu").value;
$.ajax({
  type: 'GET',
  url: "crudTable",
  data: {s: id, c: "del"},
  success: function(resultData){

                     $('#table_id').DataTable().draw();

                 },

});
document.getElementById("deleteAlert").style.display="none";
}

function chan(){

let addForm = document.getElementById("fBroj").value + "," + document.getElementById("fDatum").value + "," +
              document.getElementById("fDrzava").value + "," + document.getElementById("fSifra").value + "," +
              document.getElementById("fValuta").value + "," + document.getElementById("fJedinica").value + "," +
              document.getElementById("fKupovni").value + "," + document.getElementById("fSrednji").value + "," +
              document.getElementById("fProdajni").value + "," + document.getElementById("fDrzavaIso").value + "," + document.getElementById("btnChange").value ;
if (secureCheck()=="secure") {
$.ajax({
  type: "GET",
  url: "crudTable",
  data: {s: addForm, c: "chan"},
  success: function(resultData){
                     document.getElementById("sucessAlert").style.display="block";
                     document.getElementById("sucessAlert").innerHTML=resultData;fading(document.getElementById("sucessAlert"));
                     fading(document.getElementById("wrongAlert"))
                     $('#table_id').DataTable().draw();
                     modal.style.display = "none";
                 },

});
} else {document.getElementById("wrongAlert").style.display="block";fading(document.getElementById("wrongAlert"))}
}

function secureCheck(){
var fBroj = document.getElementById("fBroj");
var fDatum = document.getElementById("fDatum");
var fDrzava = document.getElementById("fDrzava");
var fSifra = document.getElementById("fSifra");
var fValuta = document.getElementById("fValuta");
var fJedinica = document.getElementById("fJedinica");
var fKupovni = document.getElementById("fKupovni");
var fSrednji = document.getElementById("fSrednji");
var fProdajni = document.getElementById("fProdajni");
var fDrzavaIso = document.getElementById("fDrzavaIso");

var feedBroj = document.getElementById("feedBroj");
var feedDatum = document.getElementById("feedDatum");
var feedDrzava = document.getElementById("feedDrzava");
var feedJedinica = document.getElementById("feedJedinica");
var feedKupovni = document.getElementById("feedKupovni");
var feedSrednji = document.getElementById("feedSrednji");
var feedProdajni = document.getElementById("feedProdajni");

let result = "secure";
clearWrong();
console.log(typeof fKupovni.value);


if (isNaN(fBroj.value) == true || fBroj.value =="" || fBroj.value.length>1) {result="wrong";fBroj.classList.add("is-invalid");feedBroj.style.display="block"};
if (isNaN(fDatum.value) == false || fDatum.value =="") {result="wrong";fDatum.classList.add("is-invalid");feedDatum.style.display="block"};
if (isNaN(fDrzava.value) == false || fDrzava.value =="") {result="wrong";fDrzava.classList.add("is-invalid")};
if (isNaN(fSifra.value) == true || fSifra.value =="") {result="wrong";fSifra.classList.add("is-invalid")};
if (isNaN(fValuta.value) == false || fValuta.value =="") {result="wrong";fValuta.classList.add("is-invalid");feedDrzava.style.display="block"};
if (isNaN(fJedinica.value) == true || fJedinica.value =="" || fJedinica.value.length>1) {result="wrong";fJedinica.classList.add("is-invalid");feedJedinica.style.display="block"};
if (isNaN(fKupovni.value) == true || fKupovni.value =="") {result="wrong";fKupovni.classList.add("is-invalid");feedKupovni.style.display="block"};
if (isNaN(fSrednji.value) == true || fSrednji.value =="") {result="wrong";fSrednji.classList.add("is-invalid");feedSrednji.style.display="block"};
if (isNaN(fProdajni.value) == true || fProdajni.value =="") {result="wrong";fProdajni.classList.add("is-invalid");feedProdajni.style.display="block"};
if (isNaN(fDrzavaIso.value) == false || fDrzavaIso.value =="") {result="wrong";fDrzavaIso.classList.add("is-invalid")};

if (isNaN(fKupovni.value) == false && fKupovni.value.length>8) {let broj = parseFloat(fKupovni.value);fKupovni.value=broj.toFixed(6);};
if (isNaN(fSrednji.value) == false && fSrednji.value.length>8) {let broj = parseFloat(fSrednji.value);fSrednji.value=broj.toFixed(6);};
if (isNaN(fProdajni.value) == false && fProdajni.value.length>8) {let broj = parseFloat(fProdajni.value);fProdajni.value=broj.toFixed(6);}
return result;
}

function clearWrong() {
let inputi = document.getElementsByClassName("form-control");
let count = inputi.length;
for (let i of inputi){i.classList.remove("is-invalid");}
inputi = document.getElementsByClassName("invalid-feedback");
count = inputi.length;
for (let i of inputi){i.style.display="none";}

}
function fading(item){
$("#wrongAlert").fadeOut(4000);
$("#sucessAlert").fadeOut(4000);}
</script>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon">
                    <i class="fas fa-dice-d6"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Dashboard</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item">
                <a class="nav-link" href="index.html">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Interface
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Components</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Custom Components:</h6>
                        <a class="collapse-item" href="buttons.html">Buttons</a>
                        <a class="collapse-item" href="cards.html">Cards</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Utilities</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Custom Utilities:</h6>
                        <a class="collapse-item" href="utilities-color.html">Colors</a>
                        <a class="collapse-item" href="utilities-border.html">Borders</a>
                        <a class="collapse-item" href="utilities-animation.html">Animations</a>
                        <a class="collapse-item" href="utilities-other.html">Other</a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Addons
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item active">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="false"
                    aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Pages</span>
                </a>
                <div id="collapsePages" class="collapse show" aria-labelledby="headingPages"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">LOGIN/REGISTER SCREENS:</h6>
                        <a class="collapse-item" href="login">Login</a>
                        <a class="collapse-item" href="registration">Register</a>

                    </div>
                </div>
            </li>

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="#" onclick="goHome()">
                    <i class="fas fa-home"></i>
                    <span>Home</span></a>
            </li>
            <!-- Nav Item - Charts -->
                        <li class="nav-item">
                            <a class="nav-link" href="#" onclick="goCodebook()">
                                <i class="fas fa-fw fa-table"></i>
                                <span>Tables</span></a>
                        </li>
            <!-- Nav Item - Charts -->
                        <li class="nav-item">
                            <a class="nav-link" href="#" onclick="goPdfs()">
                                <i class="far fa-file"></i>
                                <span>Files</span></a>
                        </li>



            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>
            <!-- Ovdje idu alert boxovi nevidljivi -->
            <div class="alert alert-danger" id="wrongAlert" role="alert" style="display:none">
            Krivo uneseni podaci!
            </div>
            <div class="alert alert-warning" id="deleteAlert" role="alert" style="display:none">
            Stavka će se obrisati!
            <button id="uredu" onclick="del()">Uredu</button>
            <button id="natrag" onclick="document.getElementById('deleteAlert').style.display='none'">Natrag</button>
            </div>
            <div class="alert alert-success" id="sucessAlert" role="alert" style="display:none">
            Podaci uneseni!
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>



                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw" style="font-size: 28px;"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Alerts Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-primary">
                                            <i class="fas fa-file-alt text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 12, 2019</div>
                                        <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-success">
                                            <i class="fas fa-donate text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 7, 2019</div>
                                        $290.29 has been deposited into your account!
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-warning">
                                            <i class="fas fa-exclamation-triangle text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 2, 2019</div>
                                        Spending Alert: We've noticed unusually high spending for your account.
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                            </div>
                        </li>

                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw" style="font-size: 28px;"></i>
                                <!-- Counter - Messages -->
                                <span class="badge badge-danger badge-counter">7</span>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="messagesDropdown">
                                <h6 class="dropdown-header">
                                    Message Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_1.svg"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div class="font-weight-bold">
                                        <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                            problem I've been having.</div>
                                        <div class="small text-gray-500">Emily Fowler · 58m</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_2.svg"
                                            alt="...">
                                        <div class="status-indicator"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">I have the photos that you ordered last month, how
                                            would you like them sent to you?</div>
                                        <div class="small text-gray-500">Jae Chun · 1d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_3.svg"
                                            alt="...">
                                        <div class="status-indicator bg-warning"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Last month's report looks great, I am very happy with
                                            the progress so far, keep up the good work!</div>
                                        <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                            told me that people say this to all dogs, even if they aren't good...</div>
                                        <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${fullname}</span>
                                <i class="fas fa-user-circle" style="font-size:28px;"></i>
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="<c:url value='/logout' />" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->

                    <div id="middle2"></div>

                </div>
                <!-- /.container-fluid -->


            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <a href="https://koios.hr/"><span>Copyright &copy; Koios 2022</span></a>
                    </div>

                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="/logout">Logout</a>
                </div>
            </div>
        </div>
    </div>

<!-- Bootstrap core JavaScript-->



    <!-- Core plugin JavaScript-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/4.1.4/js/sb-admin-2.min.js"></script>

</body>

</html>