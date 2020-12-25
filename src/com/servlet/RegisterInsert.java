package com.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.User;
import com.utility.HibernateUtility;

/**
 * Servlet implementation class RegisterInsert
 */
@WebServlet("/RegisterInsert")
public class RegisterInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RegisterInsert() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);
		
		Session hibSession = HibernateUtility.getSession();
		Transaction tx = hibSession.beginTransaction();
		
		User user = (User)httpSession.getAttribute("user");
		hibSession.save(user);
		tx.commit();
		httpSession.setAttribute("welcomeLogin", "Login Successful at " + new Date() + "<br/>");
		httpSession.setAttribute("name", user.getName());
		httpSession.setAttribute("dbSuccess", "Registered Successfully");
		response.sendRedirect("welcome.jsp");
	}

}
