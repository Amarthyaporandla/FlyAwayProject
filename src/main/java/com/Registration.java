package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public Registration() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String firstname= request.getParameter("firstname");
		String lastname= request.getParameter("lastname");
		int age= Integer.parseInt(request.getParameter("age"));
		long phnumber= Long.parseLong(request.getParameter("phnumber"));
		long adhaarnum = Long.parseLong(request.getParameter("adhaarnum"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flyawayproject", "root","Amarthya123@");
			System.out.println("Connection is established.");
			PreparedStatement stmt= con.prepareStatement("insert into customerdetails1 values (?,?,?,?,?)");
			stmt.setString(1, firstname);
			stmt.setString(2, lastname);
			stmt.setInt(3, age);
			stmt.setLong(4, phnumber);
			stmt.setLong(5, adhaarnum);
			stmt.execute();
			stmt.close();
			con.close();

			RequestDispatcher rd = request.getRequestDispatcher("payment.html");
			rd.include(request, response);

		}

		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
