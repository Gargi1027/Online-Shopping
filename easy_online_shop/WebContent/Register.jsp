<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" href="css/style.css" type="text/css">        
    </head>
    <body>
    <script type="text/javascript">
function validation(){
	
	var fname= document.myform.firstname.value;
	var lname = document.myform.lastname.value;
	var uname = document.myform.registration_username.value;
	var pass = document.myform.registration_password.value;
	var flag = true;
	
	if (fname == "" || fname == null) {
		alert( "Please provide your first name!" );
        document.myform.firstname.focus() ;
		flag = false;
	}
	if (lname == "" || lname == null ) {
		alert( "Please provide your last name!" );
        document.myform.lastname.focus() ;
		flag = false;
	}
	 if ( ( myform.gender[0].checked == false ) && ( myform.gender[1].checked == false ) ) 
 {
		alert( "Please provide your gender !" );
		flag = false;
	}
	if (uname == "" || uname == null) {
		alert( "Please provide your user name!" );
        document.myform.registration_username.focus() ;
		flag = false;
	}
	if (pass == "" || pass == null) {
		alert("Please provide your password!");
        document.myform.registration_password.focus() ;
		flag = false;
	}
	return flag;
}
    
    </script>
        <div id="big_wrapper" style="background-color:white;">
        
            <form action="Controller" method="Post" name="myform">
            <br/><br/>
                <div align="center"><h1> Easy Online Shop</h1></center>
                <br> 
               <div align="center"><h1> Welcome To Register Page</h1><br/><br/></center>                 
                <div>
                                        <div align="center">
                
                       <div id="invalid_login"></div>
                       
                        
                First name: <input type="text" name="firstname"><br/><br/>
 				Last name: <input type="text" name="lastname"><br/> <br/>
 				Gender:  <input type="radio" name="gender" value="male"> Male
 				<input type="radio" name="gender" value="female"> Female<br/><br/>
                Username <input type="text" name="registration_username" > <br><br>
                Password <input type="password" name="registration_password"> <br> <br> <br/><br/>
                           
                        <input id="button" type="submit" name="registration_submit" value="Register" onclick="return validation()"> &nbsp&nbsp&nbsp
                        <input id="button" type="reset" value="Reset">    <br> <br>
                        <a id="button" href="Index.jsp"> Home </a>
                        </div>
                </div>

            </form>
        </div>
    </body>
</html>
