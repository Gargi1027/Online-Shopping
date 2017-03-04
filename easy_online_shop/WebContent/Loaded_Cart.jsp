
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*" %>
<% ArrayList itemname = new ArrayList();%>
<% ArrayList<String> quantityno = new ArrayList<String>();%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">        
        <style type="text/css">
           
           *{
           font-family:Arial, Helvetica, sans-serif		;  
           }
           #big_wrapper {
            	font-weight: bold;
                text-align: center;
                
                }
           
            #table{
                color:black;
                margin-bottom:20px;
                margin-left: 100px;
                text-align: center;
            }
            #total{
            margin-bottom: 30px;
            margin-top: 30px;
            font-weight: bold;
            margin-left:300px;
            color:black;
            font-size: 20px;
            text-align : left;
            }
            #coupon{
                margin-left: 250px;
                height: 75px;  
                width : 500px; 
                }
            #payment{
            margin-left: 400px;
            margin-top : 10px;
            margin-bottom: 40px;
            }
            #logout{
            margin-left: 1000px ;   
            }
            #cart_head{
            height:40px;
            }
        </style>
    </head>
    
    <body>
    <script type="text/javascript">
    function offer(){
    	var cpn = document.ofform.coupon.value;
    	if(cpn.localeCompare("SE10")== 0){
    		alert("Congrats ! You got 10% Off on your order !");
    		return true;
    	}
    	
    }
    
    </script>
        <div id="big_wrapper">

            <center>
                <% itemname = (ArrayList) session.getAttribute("product_in_cart");
                    quantityno = (ArrayList) session.getAttribute("quantity_in_cart");
                    ArrayList<String>  productprice = (ArrayList<String>) session.getAttribute("productprice");
                  %>
                <jsp:useBean id="product_in_cart" class="java.util.ArrayList" scope="session" ></jsp:useBean>
                <% 
                        if (itemname != null) {%>
                <br />    
                   
            </center>
            
            <div id="cart_head">
                 <h1 style="color:#0011FC;"> Please Review Your Items In The Cart </h1>            
                </div>
             <br/>
            <center>
                <table id="table" border="1px solid black">
                
                
                    <tr>
                        <th> No. of items </th>
                        <th> Item Name </th>
                        <th> Quantity </th>
                        <th>Price</th>
                        <th>Subtotal</th>
                        <th>Remove</th>
                    </tr>       
                    <% int total=0; for (int i = 1; i <= itemname.size(); i++) {%>
                    <tr>
                        <td> <%=i%> </td>
                        <td>  <%= itemname.get(i-1)%>   </td>
                        <td> <%= quantityno.get(i - 1)%> </td> 
                        <td> <%= "$ " +productprice.get(i-1)%> </td>
                       
                        <td><% 
                        
                        int sum =Integer.valueOf(quantityno.get(i - 1)) * Integer.valueOf(productprice.get(i-1));
                        out.print("$ " +sum); 
                        total = sum+ total;
                        session.setAttribute("sum", sum);
                        session.setAttribute("total",total);
                        %></td>
                        <form></form><td><img alt="remove" src="images/remove.png" style="width:30px; height:auto; margin-left: auto;
    margin-right: auto;"> </td>            
                    </tr>
                    <%}%>
                </table>
                </center>
                
                <table id ="total"  cellspacing="5" cellpadding="5">
                <tr>
                <td> <%= "Total Number Of items : "+session.getAttribute("total_cart_items") %></td>
                </tr>
                <tr>
                <td><%                
                int sum = (Integer)session.getAttribute("total");
                String off = new String();
                off =	request.getParameter("coupon");
                System.out.println(off);
                if(off != null && off.equalsIgnoreCase("SE10"))
            	{
            		int ntotal = (sum * 90)/100 ;
            		out.println("<br/><p style='color:#0011FC'>You have Successfully Applied coupon code : SE10 <br/><br/> Discounted Price : $  "+ ntotal);
            	}
               
                else{
                    out.print(" <p style='color:#0011FC'>Order Total: $ "+sum);

                }
                %>
                </td>
                </tr>
                </table>   
                <%  if (itemname == null) {%>
                <h1> There are no items in your cart now.. Please add items to your cart first!! </h1>
             <%}} %>
              
            
            <div id="coupon">
                <form action="Loaded_Cart.jsp" method="Post" name="ofform">
                <br/>
                	<h3 style="color:black">Enter your coupon code here : <input type="text" name="coupon" style="height:25px; width:75px">
                	<input type="submit" id="button" name="" value="Apply" onclick="return offer()"></h3>
                	<br/> <br/>
                </form>
            </div>
            <div id="payment">
            <form action="Controller" method="Post">
             <input id="button" type="submit" name="payment" value=" Continue To Payment " /> 
            </form> 
            </div>
        </div>
        
    </body>
    <br/>
</html>
