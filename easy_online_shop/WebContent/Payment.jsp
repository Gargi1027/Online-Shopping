

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css" /> 
        <style type="text/css">
        
        
            #big_wrapper{
                text-align: center;
                color:black;
            }
            #link.active{
                color:black;
            }
        </style>
    </head>
    <body>    
    <script>
    function paymentAuth(){
    	var cn= document.payform.card_number.value;
    	var cname = document.payform.card_name.value;
    	var cvv = document.payform.cvv.value;
    	var fname = document.payform.fullname.value;
		var add = document.payform.address.value;
    	var cntry = document.payform.country.value;
    	var zip = document.payform.zipcode.value;
    	
    	if(cn == null || cn=="")
    		{
    		alert("Please enter card number !");
	        document.payform.card_number.focus() ;
    		return false;
    		}
    	if(cname == null || cname=="")
		{
		alert("Please enter Name on Card !");
        document.payform.card_name.focus() ;
		return false;
		}
    	if(cvv == null || cvv=="")
		{
		alert("Please enter cvv number !");
        document.payform.cvv.focus() ;
		return false;
		}
    	if(fname==null || fname=="")
		{
		alert("Please enter first name !");
        document.payform.fullname.focus() ;
		return false;
		}
    	if(add==null || add=="")
		{
		alert("Please enter your address !");
        document.payform.address.focus() ;
		return false;
		}
    	if(cntry==null || cntry=="")
		{
		alert("Please enter your country !");
        document.payform.country.focus() ;
		return false;
		}
    	if(zip==null || zip=="")
		{
		alert("Please enter your zip code!");
        document.payform.zipcode.focus() ;

		return false;
		}
    	return true;
    }
    </script>    
         <!-- <form action="Controller" method="Post">                   
                <div class="logmeout"> <input type="submit" name="signout" value=" Logout "> </div>
        </form> --> 
        <div id="big_wrapper">
		
		<%
			String Confirmation = (String) session.getAttribute("Order_Confirmation");
			String ref = (String) session.getAttribute("ref");
			if (Confirmation == null) {
		%>

            <form action="Controller" method="Post" name="payform">                   
                <!--div class="logmeout"> <input type="submit" name="signout" value=" Logout "> </div-->
                <h2 style="color:#FF0000;">Your Unique Reference Number is : <%=ref %></h2></br>
                <h2> Payment Information </h2> <br /> <br />
                Card Type <select name="card_type">
                	<option> Visa </option>
                	<option> Master Card </option>
                    <option> American Express </option>   
                    <option> CITI Bank </option>   
                    <option> Disover </option>   
                </select> <br /> <br />                                         
                Card Number <input type="text" value="" maxlength="16" name="card_number" /> <br /> <br />
                Name on Card <input type="text" value="" maxlength="20" name="card_name" /> <br /> <br />
                Expiration Date <select name="expiration_month">
                    <option> 1 </option> <option> 2 </option> <option> 3 </option> <option> 4 </option>   
                    <option> 5 </option> <option> 6 </option> <option> 7 </option> <option> 8 </option>   
                    <option> 9 </option> <option> 10 </option> <option> 11 </option> <option> 12 </option>   
                </select> 
                               
                <select name="expiration_year">
                    <option> 2016 </option>   
                    <option> 2017 </option>   
                    <option> 2018 </option>   
                    <option> 2019 </option>   
                </select> <br />  <br />
                CVV <input type = "text" value="" maxlength="4" name="cvv" style="width: 35px"/> 
                <br/> <br/> <br/>     

                <h2> Billing Information </h2> <br /> 
                Full Name <input type="text" value="" name="fullname" /> <br /> <br />
                Address  &nbsp; &nbsp; <input type="text" value="" name="address" /> <br /> <br />
                Country  &nbsp; <input type="text" value="" name="country" /> <br /> <br />
                Zip &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" value="" name="zipcode" /> <br /> <br />
                <input style="margin-bottom:20px;" id="button" value="Pay Now" type="submit" name="place_order" onclick="return paymentAuth()" />              


                <% }  // ending billing n payment info %>
                <%
                    if (Confirmation != null) {
                %>

                <!--div class="logmeout"> <input type="submit" name="signout" value=" Logout "> </div-->

                <h1 style="color:#7E006D; font-family:font-family: "Lucida Sans Typewriter","Lucida Console",monaco,"Bitstream Vera Sans Mono",monospace;"> Congratulations, Order Has been received !! <br/>
                Your Order Number is <%=ref %><br/>
                Look out for your product !  </h1><br /> <br /> <br /> 
                    <h1 style="color:#060357">Thank you for ordering at Easy Online Store </h1>  <br /> <br /> <br /> <br /> <br />
                <div id="link">    <h2> <a href="Index.jsp"> Shop Again! </a> </h2> </div>
                <% }%>

            </form>         
        </div>
    </body>
</html>
