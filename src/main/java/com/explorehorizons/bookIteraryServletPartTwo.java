package com.explorehorizons;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.explorehorizons.TravelDetails;

/**
 * Servlet implementation class bookIteraryServletPartTwo
 */
@WebServlet("/bookIteraryServletPartTwo")
public class bookIteraryServletPartTwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String jdbcURL = "jdbc:mysql://localhost:3306/userdetails";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookIteraryServletPartTwo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	
		try
		{
			confirmTravelDetails(request,response);
		}
		catch (SQLException ex) {
			throw new ServletException(ex);
			}
		
	}
	protected Connection getConnection() {
		Connection connection = null;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
		e.printStackTrace();
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}
		return connection;
		}

	private void confirmTravelDetails(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
		
		String SELECT_ALL_TRAVEL_DETAILS = "select id, title,start_Date,end_date from traveldetails where id = ? "; 
		String INSERT_TRAVEL_DETAILS_FOR_EMAIL = "insert into confirmtraveldetails (travelId, email) values ( ?,?  )";
	
			try 
			{
			Connection connection = getConnection();
			// Step 5.1: Create a statement using connection object
			PreparedStatement preparedStatement =
			connection.prepareStatement(INSERT_TRAVEL_DETAILS_FOR_EMAIL);
		
			String email = request.getParameter("email") ; 
			String travelId = request.getParameter("travelId") ; 
			preparedStatement.setString(2, email );
			preparedStatement.setString(1, travelId );
			
			// Step 5.2: Execute the query or update query
			//ResultSet rs = preparedStatement.executeUpdate();
			
			
			int i = preparedStatement.executeUpdate();
			connection.close();

			request.getRequestDispatcher("showItinery3.jsp").forward(request, response);
			
			}
			catch(Exception e)
			{
				throw new ServletException(e);
			// Step 5.4: Set the users list into the listUsers attribute to be pass to the userManagement.jsp
			
			}

}


}
