package com.util;
//import com.util.validate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.management.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	int total_cart_items = 0;
	SessionBean bean = new SessionBean();
	HashMap map = new HashMap();
	ArrayList list = new ArrayList();
	ArrayList products_in_cart_list = new ArrayList();
	ArrayList<String> quantities_in_cart = new ArrayList<String>();
    ArrayList user_product_name = new ArrayList();
	CartDetails mycart = new CartDetails();
	ProductDetails productdetails = new ProductDetails();
	Details queryObject = new Details();
	HashMap authentication_status_map = new HashMap();
	//ProductDetails productdetails = (ProductDetails) queryObject.getProductDetails();  //initializing productdetails object. //neeed to uncomment this line...

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		try {
			out.println("<h1>Servlet Controller at " + request.getContextPath() + "</h1>");
		} finally {
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login_name;
		String login_password;
		String registration_username;
		String registration_password;
		String registration_emailaddress;
		boolean result;
		String username_exists;
		RequestDispatcher rd;
		HttpSession session = request.getSession(true);
		session.setAttribute("Order_Confirmation", null);

		// Case for logout!
		if (request.getParameter("signout") != null) {
			System.out.println("Reached log out in the controller!!");
			//Query.clearSession();
			request.removeAttribute("quantity");
			session.setAttribute("authentication", null);
			session.removeAttribute("authentication"); 
			session.setAttribute("name", null);
			session.removeAttribute("product_name");
			session.removeAttribute("productname");
			quantities_in_cart.clear();
			products_in_cart_list.clear();
			quantities_in_cart.clear();           
			session.removeAttribute("productdescription");
			session.removeAttribute("brandname");
			session.removeAttribute("productprice");
			session.removeAttribute("quantity_in_cart");
			session.setAttribute("product_in_cart",null);
			session.removeAttribute("product_in_cart");
			session.setAttribute("classifier_name", null);
			session.removeAttribute("classifier_name");
			total_cart_items = 0;
			session.removeAttribute("total_cart_items");
			response.setHeader("Cache-Control", "no-store"); //when you hit back, it displays "Confirm page Submission"
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.sendRedirect(request.getContextPath() + "/Index.jsp");

			/*rd = request.getRequestDispatcher("/Index.jsp");
			rd.forward(request, response);*/
		}
		
		//case for register
		if(request.getParameter("register")!=null){
			response.sendRedirect(request.getContextPath() + "/Register.jsp");

		}

		//Case for Login Submit
		if (request.getParameter("login_submit") != null) {
			login_name = request.getParameter("login_name");
			System.out.println("login_name before trimming = " + login_name);
			System.out.println("login_name after trimming = " + login_name.trim()); // what it does is, if the username is
			login_password = request.getParameter("login_password");                  // "  my_name" it reads the username as  
			System.out.println("login_password = " + login_password.trim());        // "my_name"
			username_exists = new register().if_username_exists(login_name.trim());
			if (username_exists.equals("false")) {
				response.sendRedirect(request.getContextPath() + "/Index.jsp");
				/*rd = request.getRequestDispatcher("/Index.jsp");
				rd.forward(request, response);*/
			}

			if (username_exists.equals("true")) {
				authentication_status_map = new Authentication().authenticate(login_name.trim(), login_password.trim()); 
				String authentication_status = (String) authentication_status_map.get("authentication_status");
				String IsAdmin = (String) authentication_status_map.get("IsAdmin");
				System.out.println("Authentication status = " + authentication_status + " user = " + IsAdmin);
				if (authentication_status.equals("true")) {
					bean.setName(login_name);
					request.setAttribute("name", login_name);
					session.setAttribute("name", login_name);
					System.out.println("Authentication Succesfull. User granted permission!!");
					if (IsAdmin.equals("user")) {
						session.setAttribute("authentication", "Auth_Success");
						response.sendRedirect(request.getContextPath() + "/Cart.jsp");

						/*rd = request.getRequestDispatcher("/Cart.jsp");
						rd.forward(request, response);*/
					} 
					}

				} else {
					System.out.println("Username or Password does not exists!!");
					session.setAttribute("authentication", "Auth_Failure");
/*					response.sendRedirect(request.getContextPath() + "/Index.jsp");

					/*RequestDispatcher reqd = request.getRequestDispatcher("Index.jsp");
					reqd.forward(request, response);*/
				}
			}
		

		//Case for registration submit  
		if (request.getParameter("registration_submit") != null) {
			registration_username = request.getParameter("registration_username");
			registration_password = request.getParameter("registration_password");
			System.out.println("registration_username before trimming = " + registration_username);
			System.out.println("registration username after trimming = " + registration_username.trim());
			System.out.println("registration_password = " + registration_password.trim());
			//Below code checks if the username or password is Null.
			//The following code is not necessary becauase, i have used JavaScript for Form Validations.
			// Just kept this code as backup and understanding purposes!
			if (registration_password.trim().equals("") || registration_username.trim().equals("")) {
				System.out.println("password equal");
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				try {
					out.println("<h1> Username or Password cannot be Null </h1>");
				} finally {
					out.close();
				}
			}
			username_exists = new register().if_username_exists(registration_username.trim());
			System.out.println("username_exists true or false= " + username_exists);
			response.setContentType("text/html"); // need to change it later to reduce the codes that follow it..
			if (username_exists.equals("true")) {
				PrintWriter out = response.getWriter();
				try {
					out.println("<center><h1> Username Taken </h1></center>");
					out.println("<center><h1><a href=Register.jsp> Try with different username </a></h1></center>");

				} finally {
					out.close();
				}
			} else {
				result = new register().register_user(registration_username.trim(), registration_password.trim());
				if (result == true) {
					PrintWriter out = response.getWriter();
					try {
						out.println("<center> <h1> Registration Successful </h1></center>");
						out.println("<center><h1><a href=Index.jsp> Login </a></h1></center>");
					} finally {
						out.close();
					}
				} else {
					PrintWriter out = response.getWriter();
					try {
						out.println("<h1> Registration Failure </h1>");
					} finally {
						out.close();
					}
				}
			}
		} // registration_submit ends here..

		//get the product info..
		if (request.getParameter("product") != null) {
			String classifier_name = (String) request.getParameter("Item");
			System.out.println("the product selected by user is = " + classifier_name);
			map = new Details().get_product_info(classifier_name);
			//sessionbean.setProduct_name((ArrayList) map.get("productname"));
			//productdetails.setProduct_name((ArrayList) map.get("productname"));

			System.out.println("The session bean product name from controller =" + productdetails.getProduct_name());
			//            System.out.println("product details object name = " + productdetails.getProduct_name());
			//            System.out.println("product details object brand = " + productdetails.getProduct_brand());
			//            System.out.println("product details object name = " + productdetails.getProduct_description());
			//            System.out.println("product details object name = " + productdetails.getProduct_price());
			session.setAttribute("productname", map.get("productname"));
			session.setAttribute("brandname", map.get("brandname"));
			session.setAttribute("productdescription", map.get("productdescription"));
			session.setAttribute("productprice", map.get("productprice"));
			session.setAttribute("productid", map.get("productid"));
			session.setAttribute("authentication", "Auth_Success"); //need to use a session bean here..
			//need to use a session bean to add the user name as well. to remove the login , password text box popup
			response.sendRedirect(request.getContextPath() + "/Cart.jsp");
			/*rd = request.getRequestDispatcher("/Cart.jsp");
			rd.forward(request, response);*/
		} //get product info ends here

		//get the cart details 	
		if (request.getParameter("cartdetails") != null) {
	        
			String itemnames[] = request.getParameterValues("quantity");                             
			user_product_name = (ArrayList) session.getAttribute("product_name");

			for (int i = 0; i < itemnames.length; i++) {
				System.out.println("the values are = " + itemnames[i]);
				 if(Integer.parseInt(itemnames[i])>=1) {
					total_cart_items = total_cart_items + (Integer.parseInt(itemnames[i]));                    
					quantities_in_cart.add(itemnames[i]);
					products_in_cart_list.add(user_product_name.get(i));
                    session.setAttribute("total_cart_items", total_cart_items);
					System.out.println("produts obtained in the cart are = " + products_in_cart_list);
				}
			}
			
			session.setAttribute("product_in_cart", products_in_cart_list);
			session.setAttribute("quantity_in_cart", quantities_in_cart);
			System.out.println("cart quantity = " + session.getAttribute("product_in_cart"));
			System.out.println("cart quantity = " + session.getAttribute("quantity_in_cart"));
			response.sendRedirect(request.getContextPath() + "/Cart.jsp");

			/*rd = request.getRequestDispatcher("/Cart.jsp");
			rd.forward(request, response);*/
		} // get cart details ends here

		//checkout the cart
		if (request.getParameter("checkout_cart") != null) {
			Date dNow = new Date();
	        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
	        String refid = ft.format(dNow);
	        session.setAttribute("ref", refid);
			response.sendRedirect(request.getContextPath() + "/Loaded_Cart.jsp");
			
			}
//			rd = request.getRequestDispatcher("/Loaded_Cart.jsp");
//			rd.forward(request, response);
		
		// coupon code 
	/*	if (request.getParameter("payment") != null){
		String coupon = request.getParameter("coupon");
			if(coupon.equalsIgnoreCase("SE10"))
			{
				boolean cpn = false;
				cpn = true;
				session.setAttribute("cpn", new Boolean(true));
				rd = request.getRequestDispatcher("/Loaded_Cart.jsp");
			}
			else{
				
			}
		}*/

		//payment options
		 if (request.getParameter("payment") != null) {
			rd = request.getRequestDispatcher("/Payment.jsp");
			rd.forward(request, response);
	}
		//payment details collection
		if (request.getParameter("place_order") != null) {
			System.out.println("into place order!!");
			//get credit card information
			String card_type = (String) request.getParameter("card_type");
			String card_name = request.getParameter("card_name");
			String card_num = request.getParameter("card_number");
			String expiration_month = (String) request.getParameter("expiration_month");
			String expiration_year = (String) request.getParameter("expiration_year");
			String cvv = (String) request.getParameter("cvv");
			String refid = (String) session.getAttribute("ref");


			//get billing information
			
			session.setAttribute("Order_Confirmation", "Confirmed");
			String Confirmation_Status = (String) session.getAttribute("Order_Confirmation");
			System.out.println("Confirmation_Status = " + Confirmation_Status); 
			DomParsing.card_n_shipping_info(card_type, card_name, card_num, expiration_month, expiration_year, cvv, refid);
			String fullname = request.getParameter("fullname");
			String address = request.getParameter("address");
			String country = request.getParameter("country");
			String zipcode = request.getParameter("zipcode");
			DomParsing.writexml(fullname,address,country,zipcode,refid, quantities_in_cart, products_in_cart_list);
			
			/*DomParsing.writeAdress(fullname, address, country, zipcode,refid);*/
			response.sendRedirect(request.getContextPath() + "/Payment.jsp");

			/*rd = request.getRequestDispatcher("/Payment.jsp");
			rd.forward(request, response);*/
		} //payment and billing information ends here

		//if (request.getParameter("admin_changes") != null) {

	}
	}
