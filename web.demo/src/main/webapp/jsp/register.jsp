<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

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

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                            <div class="col-lg-6" style="margin:auto">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                                    </div>
                                    <form:form action="" method="post">
                                    <div class="form-group row">
                                    <input type="text" name="username" id="username" class="fadeIn third form-control form-control-user" style="width: 40%;margin-left: 7%;" placeholder="First name"/>
                                    <input type="text" name="lastname" id="lastname" class="fadeIn third form-control form-control-user" style="width: 40%;margin-right: 7%;" placeholder="Last name"/>
                                    <div id="user_invalid" class="invalid-feedback" style="margin-left:30px">Please enter your name (at least 3 characters).</div>
                                    <div id="last_invalid" class="invalid-feedback" style="margin-left:30px">Please enter your last name.</div></div>
                                    <div class="form-group">
                                    <input type="text" name="email" id="email" class="fadeIn third form-control form-control-user" placeholder="Email"/>
                                    <div id="email_invalid" class="invalid-feedback" style="margin-left:30px">Please enter an email.</div></div>
                                    <div class="form-group row">
                                    <input type="password" name="password" id="password" class="fadeIn third form-control form-control-user" style="width: 40%;margin-left: 7%;" placeholder="Password"/>
                                    <input type="password" name="password2" id="password2" class="fadeIn third form-control form-control-user" style="width: 40%;margin-right: 7%;" placeholder="Repeat Password"/>
                                    <div id="pass_invalid" class="invalid-feedback" style="margin-left:30px">Please enter a password and confirm it.</div></div>


                                    <br>
                                    <div class="form-group" style="margin-top: 100px;">
                                    <input type="button" value="Register Account" id="reg" class="btn btn-primary btn-user btn-block" onclick="register()"/></div>

                                    </form:form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="forgot-password.html">Forgot Password?</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="login">Already have an Account! Log in.</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

     <!-- Core plugin JavaScript-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/4.1.4/js/sb-admin-2.min.js"></script>

</body>
<script>
function register(){
    var user = document.getElementById("username").value;
    var last = document.getElementById("lastname").value;
    var pass = document.getElementById("password").value;
    var pass = document.getElementById("password2").value;
    var email = document.getElementById("email").value;
    let s = validateRegister();
    if (s=="secure") {
    $.ajax({
      type: "POST",
      url: "register",
      data: {u: user, l: last, p: pass, e: email},
      success: function(resultData){
                         alert(resultData);
                     },

    });
    }
    }

    function validateRegister(){
    var u = document.getElementById("username");
    var l = document.getElementById("lastname");
    var p = document.getElementById("password");
    var p2 = document.getElementById("password2");
    var e = document.getElementById("email");

    var iu = document.getElementById("user_invalid");
    var il = document.getElementById("last_invalid");
    var ie = document.getElementById("email_invalid");
    var ip = document.getElementById("pass_invalid");


    u.style.borderColor = "#D9DFE3";
    l.style.borderColor = "#D9DFE3";
    p.style.borderColor = "#D9DFE3";
    p2.style.borderColor = "#D9DFE3";
    e.style.borderColor = "#D9DFE3";
    iu.style.display="none";
    il.style.display="none";
    ie.style.display="none";
    ip.style.display="none";

    res="secure";
    if (u.value=="" || isNaN(u.value) == false || u.value.length<3) {u.style.borderColor = "#DD7A80";res="wrong";iu.style.display="block";};
    if (l.value=="" || isNaN(l.value) == false || l.value.length<2) {l.style.borderColor = "#DD7A80";res="wrong";il.style.display="block";};
    if (p.value=="" || p.value != p2.value) {p.style.borderColor = "#DD7A80";res="wrong";ip.style.display="block";};
    if (p2.value=="" || p.value != p2.value) {p2.style.borderColor = "#DD7A80";res="wrong";ip.style.display="block";};
    if (e.value=="" || e.value.includes("@") == false || e.value.includes(".") == false) {e.style.borderColor = "#DD7A80";res="wrong";ie.style.display="block";};
    console.log(res);
    return res;
    }
</script>
</html>