<%@page import="com.util.CartDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="com.util.ProductDetails" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<%@page import="com.util.Details" %>
<%
    response.setHeader("Cache-Control", "no-store"); //when you hit back, it displays "Confirm page Submission"
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<!--%! String ifnull(String s){          You could use this, if the String ever goes null
    return (s==null)?\"\":s;
}
%-->
<%@ page errorPage="error.jsp"%>

<%!  ProductDetails pro = new ProductDetails();  //instantiating ProductDetails class and calling the getter mthods.
    CartDetails mycart = new CartDetails();
    ProductDetails sessionbean = new ProductDetails();  //need to remove 
    String name = null;
    //name = (String) request.getAttribute("name");  // very much needed line. use setters and getters instead of this.
    ArrayList productname = new ArrayList();
    ArrayList productdescription = new ArrayList();
    ArrayList brandname = new ArrayList();
    ArrayList productprice = new ArrayList();
    Details queryObject = new Details();
    //ProductDetails productdetailsObject = queryObject.getProductDetails();   need to comment it if you need to get the object from..

    //getAttribute and setAttribute always works
%>
<%
    productname = (ArrayList) session.getAttribute("productname");
    productdescription = (ArrayList) session.getAttribute("productdescription");
    brandname = (ArrayList) session.getAttribute("brandname");
    productprice = (ArrayList) session.getAttribute("productprice");
    System.out.println("Product DESCRIPTION SET IN CART.JSP");
    sessionbean.setProduct_description(productdescription);
//    out.println("productdetailsObject values name = " + productdetailsObject.getProduct_name());  
//    out.println("productdetailsObject values name = " + productdetailsObject.getProduct_description());
//    out.println("productdetailsObject values name = " + productdetailsObject.getProduct_price());
//    out.println("productdetailsObject values name = " + productdetailsObject.getProduct_brand());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <style type="text/css">

</style>
        <title>Home</title>
    </head>
    <body>
        <script type="text/javascript">
        
            function changeImage()
            {
                element=document.getElementById('myimage');
                
               
                //                itemid=document.getElementbyId('myimage');
                //                alert(itemid.id);
                
                if (element.src.match("images/product.jpg"))
                {
                    element.src="images/product.jpg";
                }
                else if(element.src.match("images/product1.jpg"))
                {
                    element.src="images/product1.jpg";
                }
                else 
                {
                    element.src="images/prodcut2.jpg";
                }
            }    
        </script>
        <% String authentication = (String) session.getAttribute("authentication");
            if (authentication == null) {
        %>
        <!--jsp:useBean id="name" scope="session" class="com.util.Controller"--><!--/jsp:useBean-->        
        <div id="login_in">
            <form name="Home_Page" action="Controller" method="Post">
                <div align="right">                       
                    Username    <input  type="text" maxlength="40" name="login_name"> &nbsp;&nbsp;
                    Password    <input type="password" maxlength="40" name="login_password">             
                    <input type="submit" name="login_submit" value="Submit" onclick="check();">             
                    <a href="register"> Register </a>
                </div>                    
            </form>
            <% }
                if (authentication != null) {
                    if (authentication.equals("Auth_Success")) {
            %>
            <div id="user_info" > Welcome  <%= (session.getAttribute("name"))%> <span>   <i> <!--%= name %-->! </i>  </span> </div>
            <form name="logout" action="Controller" method="Post">
                <div class="logmeout"> <input id="button" type="submit" name="signout" value=" Logout "> </div>
            </form>         
            <%} else {%>
            <div id="wrong_user"> <span style="color:red"> Username/Password is wrong! </span> </div>
            <form name="Home_Page" action="Controller" method="Post">
                <div id="login_in" align="right">                       
                    Username    <input  type="text" maxlength="40" name="login_name"> &nbsp;&nbsp;
                    Password    <input type="password" maxlength="40" name="login_password">             
                    <input type="submit" name="login_submit" value="Submit" onclick="check();">             
                    <a href="register"> Register </a>
                </div>                    
            </form>

            <%}
                }%>

        </div>  
        <div id="big_wrapper">            
            <header id="top_header"> Easy Online Shop </header>
            <nav id="links"> 
                <ul>
                    <li> <a href=""> HOME </a> </li>
                    <li> <a href=""> OFFERS </a> </li>
                    <li> <a href=""> CART </a></li>
                    <li> <a href=""> CHECKOUT </a> </li>
                </ul>
            </nav>
            <section id="main_content">
                <article id="main_article">
                    <form name="cart" action="Controller" method="Post">
                        <select name="Item" style="height:25px;">
                            <option value="books"> Books </option>
                            <option value="smartphone"> Smartphone </option>
                            <option value="icecream"> Ice Cream </option>
                        </select>
                        <input type="submit" id="button" value="Show me Products" name="product">&nbsp &nbsp&nbsp
                    </form>

                    <% if (session.getAttribute("total_cart_items") != null) {%>
                    <span id="total_cart_items" style="position:right;"> Total items in cart = <%= session.getAttribute("total_cart_items")%></span>
                    <% }%>

                    <% if (productname != null) {%> <form name="cart_details" method="Post" action="Controller">                         
                        <span id="add_to_cart"> <input id="button" type="submit" value=" Add to Cart " name="cartdetails"> </span>
                        <br>
                        <span id="Finish_n_checkout"> <input id="button" type="submit" value=" Checkout " name="checkout_cart"> </span>    
                            <%     for (int i = 1; i <= productname.size(); i++) {
                            %> 

                        <article id="inner_article">                      
                            <table cellspacing="0" cellpadding="0">
                                <br /> 
                                <tr><td id="table"> <%= i%> . 
                                        <img id="myimage"  onclick="changeImage()" border="0" src="images/product1.jpg" width="75" height="75" alt="Loading..">
                                        <span id="inner_artcile_heading" > <%= productname.get(i - 1)%> </span> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; </td></tr>
							
							<select id="quantity" name="quantity">
								<option value="0">Select Quantity:</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>

							</select> <br>
                                <tr><td id="table_data"> by 
                                        <span id="inner_artcile_dealer"> <%=brandname.get(i - 1)%> </span> <br> </td></tr>
                                <tr><td id="table_desc"> <%= productdescription.get(i - 1)%> <br>  </td></tr>
                                <tr><td id="price"> Price: $<%= productprice.get(i - 1)%> <br>  </td></tr>                                
                            </table>
                        </article>

                        <%
                            }%>
                        <% session.setAttribute("product_name", productname);
                            }%>                            

                    </form>  
                </article>
                <article id="main_article">
                   Welcome To Easy Online Shop !
                  <br> Lowest Price | Best Customer Service | Best Shopping Experience 
                </article>            
            </section>

            <aside id="updates"> 			Summer Offer : Apply Coupon SE10 and get 10% Discount !
             </aside>
            <footer id="the_footer"> Get Social : FB Twitter </footer>
        </div>
    </body>
</html>
