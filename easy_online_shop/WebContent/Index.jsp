<%@ page import="java.util.Date"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%
String name=(String) request.getAttribute("name");
String names=(String) session.getAttribute("name");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Home</title>
</head>
<body>

	<script type="text/javascript">
		function form_validation() {
			var name = document.Login_Form.login_name.value;
			var password = document.Login_Form.login_password.value;
			var flag = true;
			if (name == "") {
				alert( "Username is required !" );
		        document.Login_Form.login_name.focus() ;
				flag = false;
			}
			if (password == "") {
				alert( "Password is required !" );
		        document.Login_Form.login_password.focus() ;
				flag = false;
			}
			return flag;
		}
	</script>

	<!--div id="date">  Current Time =  <%=new Date() %> </div-->
	<div id="invalid_login"></div>
	<% String authentication = (String) session.getAttribute("authentication"); 
            if (authentication == null) { %>
	<div id="login_in">
		<form name="Login_Form" action="Controller" method="Post">
			<div align="right">
				Username <input type="text" maxlength="40" name="login_name">
				&nbsp;&nbsp; Password <input type="password" maxlength="40"
					name="login_password"> <input id="button" type="submit"
					name="login_submit" value="&nbsp Login &nbsp"
					onclick="return form_validation()"> &nbsp <input id="button" type="submit"
					name="register" value="&nbsp Register &nbsp">
			</div>
		</form>
		<% }
                if (authentication != null) { if(authentication.equals("Auth_Success"))
                { // out.println("authentication = " + authentication);
            %>
        <span>
        <div id="user_info" > Welcome  <%= (session.getAttribute("name"))%>			
        </span>
		<form name="logout" action="Controller" method="Post">
			<div id="one">
				<input id="button" type="submit" name="signout" value=" Logout ">
			</div>
		</form>
		<%} else { %>
		<div id="wrong_user">
			<span style="color: red"> Username/Password is wrong! </span>
		</div>
		<form name="Login_Form" action="Controller" method="Post">
			<div id="login_in" align="right">
				Username <input type="text" maxlength="40" name="login_name">
				&nbsp;&nbsp; Password <input type="password" maxlength="40"
					name="login_password"> <input id="button" type="submit"
					name="login_submit" value="&nbsp Login &nbsp"
					onclick="return form_validation()"> <input id="button" type="submit"
					name="register" value="&nbsp Register &nbsp">
			</div>
		</form>

		<%}}%>

	</div>
	<div id="big_wrapper">
		<header id="top_header"> E-store </header>

		<section id="main_content">
			<article id="main_article">
				<select name="Item">
					<option>Books</option>
					<option>Smartphone</option>
					<option>Ice Cream</option>
				</select>
			</article>
			<article id="main_article">Welcome To E-store ! <br/>
			We will provide the best customer service. Hope to see you Again :)</article>
		</section>

		<!--  <div id="updates">
			Summer Offer : Apply Coupon SE10 and get 10% Discount !
		</div>-->
		<footer id="the_footer">Project submitted by - Tapas Patel, Bhumit Patel, Gargi Thakker, Krupali Desai </footer>
	</div>
</body>
</html>
    