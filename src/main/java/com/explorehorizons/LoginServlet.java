package com.explorehorizons;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String email = request.getParameter("email"); 
		String password = request.getParameter("password") ; 
		
		PrintWriter writer = response.getWriter();
		
		System.out.println ( " incoming param " + email );
		System.out.println ( " incoming param " + password );
		
		int countRows =validateUserEmailAndPassword (email , password);
		
				if ( countRows ==1 )
				{
					
					System.out.println ( " [registerAccount] " + "error exists"  );
				}
				else
				{
					request.setAttribute("accountNotExists", "true");
					System.out.println ( "No such account exists") ; 
				}
		
		
		System.out.println ( "REQUEST GET ATTRINUTE " + request.getAttribute("accountNotExists") ) ; 
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginResult.jsp");
        requestDispatcher.forward(request, response);
		
	}
	
	public int validateUserEmailAndPassword( String userEmail, String password )
	{
		System.out.println ( "[bingo]countRows = [userEmail] " + userEmail + " [password]" + password ) ; 
		int countRows = 0 ; 
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection con = null ; 
			
			Connection con =
				       DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/userdetails", "root", "password");
			
			//con = DriverManager.getConnection(
			//		"jdbc:mysql://localhost:3306/userdetails", "root", "password");
		//PreparedStatement ps = con.prepareStatement("select 1 from USERDETAILS where values(?,?,?,?)");
		
		PreparedStatement myStmt = con.prepareStatement("select * from USERDETAILS where email = ? and password = ?");
		myStmt.setString(1,userEmail);     
		myStmt.setString(2,password);   
		
		ResultSet myRs = myStmt.executeQuery();

		//select * from USERDETAILS where email = 'sc@gmail.com'  and password = 'pw' ;
		
		
		  while (myRs.next()) {
			  System.out.println ( "[bingo]countRows = " + countRows) ; 
			  countRows++;
	        }
        con.close();
		}
		
		catch(Exception sqle)
		{
			/*try
			{
				if ( con!=null ) con.close();
			}
			catch(SQLException sqle2)
			{
				
			}
			*/
			System.out.println ( " Exception occured error.getMessage= " + sqle.getMessage() ) ; 
			
		}
		System.out.println ( "countRows = " + countRows) ; 
		return countRows ; 
	}


}
