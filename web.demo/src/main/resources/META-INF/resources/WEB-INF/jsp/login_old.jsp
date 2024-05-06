<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<script
			  src="https://code.jquery.com/jquery-3.6.0.min.js"
			  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
			  crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<style>
@import url('https://fonts.googleapis.com/css?family=Poppins');

/* BASIC */

html {
  background-color: #42505b;
}

body {
  font-family: "Poppins", sans-serif;
  height: 100vh;
}

a {
  color: #7A80DD;
  display:inline-block;
  text-decoration: none;
  font-weight: 400;
}

h2 {
  text-align: center;
  font-size: 16px;
  font-weight: 600;
  text-transform: uppercase;
  display:inline-block;
  margin: 40px 8px 10px 8px;
  color: #cccccc;
}



/* STRUCTURE */

.wrapper {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  min-height: 100%;
  padding: 20px;
}

#formContent {
  -webkit-border-radius: 10px 10px 10px 10px;
  border-radius: 10px 10px 10px 10px;
  background: #fff;
  padding: 30px;
  width: 90%;
  max-width: 450px;
  position: relative;
  padding: 0px;
  -webkit-box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3);
  box-shadow: 0 30px 60px 0 rgba(0,0,0,0.3);
  text-align: center;
}

#formFooter {
  background-color: #f6f6f6;
  border-top: 1px solid #dce8f1;
  padding: 25px;
  text-align: center;
  -webkit-border-radius: 0 0 10px 10px;
  border-radius: 0 0 10px 10px;
}



/* TABS */

h2.inactive {
  color: #cccccc;
}

h2.active {
  color: #0d0d0d;
  border-bottom: 2px solid #7A80DD;
}



/* FORM TYPOGRAPHY*/

input[type=button], input[type=submit], input[type=reset]  {
  background-color: #7A80DD;
  border: none;
  color: white;
  padding: 15px 80px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  text-transform: uppercase;
  font-size: 13px;
  -webkit-box-shadow: 0 10px 30px 0 rgba(95,186,233,0.4);
  box-shadow: 0 10px 30px 0 rgba(95,186,233,0.4);
  -webkit-border-radius: 5px 5px 5px 5px;
  border-radius: 5px 5px 5px 5px;
  margin: 5px 20px 40px 20px;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  -ms-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

input[type=button]:hover, input[type=submit]:hover, input[type=reset]:hover  {
  background-color: #5159D3;
}

input[type=button]:active, input[type=submit]:active, input[type=reset]:active  {
  -moz-transform: scale(0.95);
  -webkit-transform: scale(0.95);
  -o-transform: scale(0.95);
  -ms-transform: scale(0.95);
  transform: scale(0.95);
}

input[type=text] {
  background-color: #f6f6f6;
  border: none;
  color: #0d0d0d;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 5px;
  width: 85%;
  border: 2px solid #f6f6f6;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
  transition: all 0.5s ease-in-out;
  -webkit-border-radius: 5px 5px 5px 5px;
  border-radius: 5px 5px 5px 5px;
  border-bottom: 2px solid #D9DFE3;
}

input[type=text]:focus {
  background-color: #D9DFE3;
  border-bottom: 2px solid #7A80DD;
}

input[type=text]:placeholder {
  color: #cccccc;
}



/* ANIMATIONS */

/* Simple CSS3 Fade-in-down Animation */
.fadeInDown {
  -webkit-animation-name: fadeInDown;
  animation-name: fadeInDown;
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: both;
  animation-fill-mode: both;
}

@-webkit-keyframes fadeInDown {
  0% {
    opacity: 0;
    -webkit-transform: translate3d(0, -100%, 0);
    transform: translate3d(0, -100%, 0);
  }
  100% {
    opacity: 1;
    -webkit-transform: none;
    transform: none;
  }
}

@keyframes fadeInDown {
  0% {
    opacity: 0;
    -webkit-transform: translate3d(0, -100%, 0);
    transform: translate3d(0, -100%, 0);
  }
  100% {
    opacity: 1;
    -webkit-transform: none;
    transform: none;
  }
}

/* Simple CSS3 Fade-in Animation */
@-webkit-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
@-moz-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
@keyframes fadeIn { from { opacity:0; } to { opacity:1; } }

.fadeIn {
  opacity:0;
  -webkit-animation:fadeIn ease-in 1;
  -moz-animation:fadeIn ease-in 1;
  animation:fadeIn ease-in 1;

  -webkit-animation-fill-mode:forwards;
  -moz-animation-fill-mode:forwards;
  animation-fill-mode:forwards;

  -webkit-animation-duration:1s;
  -moz-animation-duration:1s;
  animation-duration:1s;
}

.fadeIn.first {
  -webkit-animation-delay: 0.4s;
  -moz-animation-delay: 0.4s;
  animation-delay: 0.4s;
}

.fadeIn.second {
  -webkit-animation-delay: 0.6s;
  -moz-animation-delay: 0.6s;
  animation-delay: 0.6s;
}

.fadeIn.third {
  -webkit-animation-delay: 0.8s;
  -moz-animation-delay: 0.8s;
  animation-delay: 0.8s;
}

.fadeIn.fourth {
  -webkit-animation-delay: 1s;
  -moz-animation-delay: 1s;
  animation-delay: 1s;
}

/* Simple CSS3 Fade-in Animation */
.underlineHover:after {
  display: block;
  left: 0;
  bottom: -10px;
  width: 0;
  height: 2px;
  background-color: #7A80DD;
  content: "";
  transition: width 0.2s;
}

.underlineHover:hover {
  color: #0d0d0d;
  cursor: pointer;
}

.underlineHover:hover:after{
  width: 100%;
}



/* OTHERS */

*:focus {
    outline: none;
}


* {
  box-sizing: border-box;
}
.fa.fa-user-circle-o  {font-size: 60px;
      padding: 30px;
      color: #23292f;}
.fa.fa-id-card-o  {font-size: 60px;
      padding: 30px;
      color: #23292f;}
#stay {  font-size: 13px;
         color: gray;
       }

</style>
	<div class="wrapper fadeInDown">
      <div id="formContent">
        <!-- Tabs Titles -->
        <h2 class="active" id="in" onclick="actIn()"> Sign In </h2>
        <h2 class="inactive underlineHover" id ="up" onclick="actUp()">Sign Up </h2>

        <!-- Icon -->
        <div class="fadeIn first" id="icon">
          <i class="fa fa-user-circle-o" aria-hidden="true"></i>
        </div>

        <!-- Login Form -->
        <c:url value="/login" var="login"/>

        		<form:form action="" method="post">
        		<div class="form-group">
        			<input type="text" name="password" id="password" class="fadeIn third form-control form-control-user" placeholder="password"/></div>
        			<div class="form-group">
        			<input type="text" name="email" id="email" placeholder="@email" class="form-control form-control-user"/></div>
        			<br>
        			<p id="stay" style="display:inline-block">Stay signed in!</p>
        			<input type="checkbox" name="remember-me" id="check" style="display:inline-block" /><br>
        			<input type="submit" value="Log in" id="sub" style="display:inline-block" class="btn btn-primary btn-user btn-block"/>
        			<input type="button" value="Register" id="reg" style="display:none" onclick="register()"/>

        		</form:form>

        <!-- Remind Passowrd -->
        <div id="formFooter">
          <a class="underlineHover" id="forget" href="#">Forgot Password?</a>
        </div>

      </div>
    </div>


	</body>
	<script>
	function actUp(){
	document.getElementById("in").classList.remove("active");
	document.getElementById("in").classList.add("inactive","underlineHover");
	document.getElementById("up").classList.remove("inactive","underlineHover");
    document.getElementById("up").classList.add("active");
	document.getElementById("email").style.display="inline-block";
	document.getElementById("sub").style.display="none";
	document.getElementById("reg").style.display="inline-block";
	document.getElementById("forget").style.display="none";
	document.getElementById("icon").innerHTML='<i class="fa fa-id-card-o" aria-hidden="true"></i>'
	document.getElementById("stay").style.display="none";
	document.getElementById("check").style.display="none";
	}
	function actIn(){
    	document.getElementById("up").classList.remove("active");
    	document.getElementById("up").classList.add("inactive","underlineHover");
    	document.getElementById("in").classList.remove("inactive","underlineHover");
        document.getElementById("in").classList.add("active");
    	document.getElementById("email").style.display="none";
    	document.getElementById("sub").style.display="inline-block";
    	document.getElementById("reg").style.display="none";
    	document.getElementById("forget").style.display="inline-block";
    	document.getElementById("icon").innerHTML='<i class="fa fa-user-circle-o" aria-hidden="true"></i>'
    	document.getElementById("stay").style.display="inline-block";
    	document.getElementById("check").style.display="inline-block";

    	}
    function register(){
    var user = document.getElementById("username").value;
    var pass = document.getElementById("password").value;
    var email = document.getElementById("email").value;
    let s = validateRegister();
    if (s=="secure") {
    $.ajax({
      type: "GET",
      url: "register",
      data: {u: user, p: pass, e: email},
      success: function(resultData){
                         alert(resultData);
                     },

    });
    }
    }

    function validateRegister(){
    var u = document.getElementById("username");
    var p = document.getElementById("password");
    var e = document.getElementById("email");
    u.style.borderBottomColor = "#D9DFE3";
    p.style.borderBottomColor = "#D9DFE3";
    e.style.borderBottomColor = "#D9DFE3";
    console.log("ovo je user: "+u.value);
    console.log("ovo je pass: "+p.value);console.log("ovo je email: "+e.value);

    res="secure";
    if (u.value=="" || isNaN(u.value) == false) {u.style.borderBottomColor = "#DD7A80";res="wrong"};
    if (p.value=="") {p.style.borderBottomColor = "#DD7A80";res="wrong"};
    if (e.value=="" || e.value.includes("@") == false || e.value.includes(".") == false) {e.style.borderBottomColor = "#DD7A80";res="wrong"};
    console.log(res);
    return res;
    }
	</script>
</html>