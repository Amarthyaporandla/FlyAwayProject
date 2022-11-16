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



@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUp() {
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		String username= request.getParameter("username");
		String gmail= request.getParameter("gmail");
		String password= request.getParameter("password");



		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver loaded");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flyawayproject", "root", "Amarthya123@");
			System.out.println("Connection is established.");

			PreparedStatement stmt= con.prepareStatement("insert into  userdetails values (?,?,?)");

			stmt.setString(1, username);
			stmt.setString(2, gmail);
			stmt.setString(3, password);
			stmt.execute();

			out.print("Data inserted succesfully");
			RequestDispatcher rd= request.getRequestDispatcher("Login.html");
			rd.forward(request, response);
			stmt.close();
			con.close();

		}
		catch(Exception e) {
			e.printStackTrace();
			out.print("<h2>User name already existed...Please try with other username</h2>");  
			RequestDispatcher rd=request.getRequestDispatcher("signup.html");  
			rd.include(request,response); 
		}

	}

}
