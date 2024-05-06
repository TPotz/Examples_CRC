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
<style>
#username:invalid {border-color:#dc3545;}
#username:invalid + #user_invalid {display:block;}
#username:valid {border-color:#ced4da;}
#username:valid + #user_invalid {display:none;}

#password:invalid {border-color:#dc3545;}
#password:invalid + #pass_invalid {display:block;}
#password:valid {border-color:#ced4da;}
#password:valid + #pass_invalid {display:none;}
</style>
<body class="bg-gradient-primary">
ovo je verzija iz web-inf
    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                    </div>
                                    <form:form action="" method="post">
                                    <div class="form-group">
                                    <input type="text" name="username" id="username" class="fadeIn third form-control form-control-user" required="required" pattern="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" placeholder="email"/>
                                     <div id="user_invalid" class="invalid-feedback">Please enter an email.</div></div>
                                    <div class="form-group">
                                    <input type="text" name="password" id="password" class="fadeIn third form-control form-control-user" required="required" placeholder="password"/>
                                    <div id="pass_invalid" class="invalid-feedback">Please enter a password.</div></div>
                                    <div class="form-group">
                                    <p id="stay" style="display:inline-block;margin-left: 35%;margin-bottom: 100px;">Stay signed in!</p>
                                    <input type="checkbox" name="remember-me" id="check" style="display:inline-block" /></div><br>
                                    <div class="form-group">
                                    <input type="submit" value="Log in" id="sub" style="display:inline-block" class="btn btn-primary btn-user btn-block" onclick="tako()"/></div>

                                    </form:form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small">Forgot Password?</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="registration">Create an Account!</a>
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

</script>



</html>