package com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.entity.User;
import com.utility.HibernateUtility;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String name_regex = "^[A-Za-z]*$";
	
	public static final String email_regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9_.-]+$";
	
    public RegisterController() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//query for username and email to check for already in database
		Map<String, String> errors = new HashMap<String, String>();
		HttpSession httpSession = request.getSession();
		Session hibSession = HibernateUtility.getSession();
		Pattern pattern = Pattern.compile(name_regex);
		Matcher matcher = pattern.matcher(request.getParameter("name"));
		Pattern pattern2 = Pattern.compile(email_regex);
		Matcher matcher2 = pattern2.matcher(request.getParameter("email"));
		List<User> data = hibSession.createQuery("from User").list();
		for(User ob: data) {
			if(ob.getUsername().compareTo(request.getParameter("username")) == 0) {
				errors.put("usernameInUse", "Username is already in use");
			}
			if(ob.getEmail().compareTo(request.getParameter("email")) == 0) {
				errors.put("emailInUse", "Email is already in use");
			}
		}
		if(request.getParameter("name").length() == 0) {
			errors.put("emptyName", "<br/>Enter a name<br/>");
		}else if(!matcher.matches()) {
			errors.put("invalidName","Enter a valid name<br/>");
		}
		if(request.getParameter("email").length() == 0) {
			errors.put("emptyEmail", "Enter an email<br/>");
		}else if(!matcher2.matches()) {
			errors.put("invalidEmail", "Enter a valid email<br/>");
		}
		if(request.getParameter("username").length() == 0) {
			errors.put("emptyUsername", "Enter a username<br/>");
		}
		if(request.getParameter("password").length() == 0) {
			errors.put("emptyPass", "Enter a password<br/>");
		}
		if(request.getParameter("confirmPassword").length() == 0) {
			errors.put("emptyConfirmPass", "Confirm password<br/>");
		}else if(request.getParameter("password").compareTo(request.getParameter("confirmPassword")) != 0) {
			errors.put("failConfirmPass", "Passwords do not match<br/>");
		}
		if(errors.isEmpty()) {
			User user = new User(request.getParameter("name"), request.getParameter("email"), request.getParameter("username"), request.getParameter("password"));
			httpSession.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("RegisterInsert");
			rd.forward(request,response);
		}else {
			httpSession.setAttribute("errors", errors);
			response.sendRedirect("register.jsp");
		}
		
	}

}
