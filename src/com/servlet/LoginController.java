package com.servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginController() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean found = false;
		HttpSession httpSession = request.getSession();
		Session hibSession = HibernateUtility.getSession();
		List<User> data = hibSession.createQuery("from User").list();
		for(User ob: data) {
			if(ob.getUsername().compareTo(request.getParameter("username")) == 0) {
				if(ob.getPassword().compareTo(request.getParameter("password")) == 0){
					found = true;
					httpSession.setAttribute("name", ob.getName());
					response.sendRedirect("welcome.jsp");
				}
			}
		}
		if(found == false) {
			httpSession.setAttribute("invalidCreds", "Username and password combination do not match our records");
			response.sendRedirect("login.jsp");
		}
		
	}

}
