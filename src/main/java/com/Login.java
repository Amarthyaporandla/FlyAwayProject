package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver loaded");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flyawayproject", "root",
					"Amarthya123@");
			System.out.println("Connection is established.");

			Statement stmt = con.createStatement();
			System.out.println("");
			ResultSet rs = stmt.executeQuery("select * from userdetails where user_name=" + "'" + username + "'"
					);

			if (rs.next()) {

				if (rs.getString("user_name").equalsIgnoreCase(username)
						&& rs.getString("pass_word").equalsIgnoreCase(password)) {
					out.print("User Successfully Logged in");
					RequestDispatcher rd = request.getRequestDispatcher("homepage.html");
					rd.forward(request, response);
				} else {

					out.print("Sorry username or password error");  
					RequestDispatcher rd=request.getRequestDispatcher("Login.html");  
					rd.include(request,response); 
				}
			} else {
				out.print("Please signup first");  
				RequestDispatcher rd=request.getRequestDispatcher("signup.html");  
				rd.include(request,response); 
			}


			stmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
