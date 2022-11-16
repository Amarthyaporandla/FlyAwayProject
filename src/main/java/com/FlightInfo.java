package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FlightInfo")
public class FlightInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FlightInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		String date = request.getParameter("date");
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/flyawayproject", "root","Amarthya123@");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from flightdetails where dateoftravel="+"'"+date+"'"+" and fsource="+"'"+source+"'"+" and destination="+"'"+destination+"'");


			if (!rs.isBeforeFirst() ) {    
				System.out.println("No data Found");
				out.print("<center> <h1> Flights are not avaliable </h1></center>");
				out.print(" ");
				RequestDispatcher rd = request.getRequestDispatcher("homepage.html");
				rd.include(request, response);

			} 

			while (rs.next()) {

				String flightName=rs.getString("flightId");
				String tktPrice = rs.getString("price");
				String dateOfTravel = rs.getString("dateoftravel");
				String fsource = rs.getString("fsource");
				String destination1= rs.getString("destination");

				out.println("   ");
				out.println("<center><br>flightname: "+flightName+"  and tkt price: "+tktPrice+"  date of travel is : "+dateOfTravel+"  source is: "+fsource+" and destination is: "+destination1+"<br /></center>");
				out.println("   ");
				RequestDispatcher rd = request.getRequestDispatcher("flightdetails.html");
				rd.include(request, response);

			}

			stmt.close();
			con.close();


		}
		catch(Exception e) {
			e.printStackTrace();
		}


	}



}
