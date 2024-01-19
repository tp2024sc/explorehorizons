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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class showItiernaryServlet
 */
@WebServlet("/showItiernaryServlet")
public class showItiernaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/userdetails";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showItiernaryServlet() {
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
		
		String datepicker = request.getParameter("datepicker"); 
		System.out.println ( " [datepicker ] " + datepicker ) ; 
		try
		{
			listTravelDetails(request,response);
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
	
	private void listTravelDetails(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
		
		String SELECT_ALL_TRAVEL_DETAILS = "select id, title,start_Date,end_date from traveldetails where ? > START_DATE and ? < END_DATE "; 
			List <TravelDetails> allTravelDetails = new ArrayList <>();
			try 
			{
			Connection connection = getConnection();
			// Step 5.1: Create a statement using connection object
			PreparedStatement preparedStatement =
			connection.prepareStatement(SELECT_ALL_TRAVEL_DETAILS);
			
			String datepicker = request.getParameter("datepicker"); 
			System.out.println ( " [datepicker ] " + datepicker ) ; 
			String startDate =datepicker.substring(0,10);
			String endDate =datepicker.substring(13,datepicker.length());
			
			System.out.println ( " [startDate ] " + startDate + "[ endDate] " + endDate ) ; 
			
			SimpleDateFormat SDFormat = new SimpleDateFormat("dd-mm-yyyy");

			
			
			java.util.Date dateStart = SDFormat.parse(startDate);
			java.util.Date dateEnd = SDFormat.parse(endDate);
			
			
			
			//preparedStatement.setString(1,startDate);     
			//preparedStatement.setString(2,endDate);   
			preparedStatement.setDate(1, new java.sql.Date((dateStart).getTime())  );
			preparedStatement.setDate(2, new java.sql.Date((dateEnd).getTime()) );
			
			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id") ; 
			String titleTravelDetails = rs.getString("title");
			
			

			
			
			Date startDate2 = rs.getDate("start_Date");
			Date endDate2 = rs.getDate("end_date");

			System.out.println ( "titleTravelDetails added!!" + titleTravelDetails ) ; 
			
			allTravelDetails.add(new TravelDetails(id, titleTravelDetails, startDate2, endDate2 ));
			}
			} catch (SQLException e) {
			System.out.println(e.getMessage());
			}
			catch(Exception e)
			{
				throw new ServletException(e);			}
			// Step 5.4: Set the users list into the listUsers attribute to be pass to the userManagement.jsp
			request.setAttribute("listTravelDetails", allTravelDetails);
			request.getRequestDispatcher("showItinery1.jsp").forward(request, response);
			}
	
}
