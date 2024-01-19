package com.explorehorizons;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
	protected void doPost2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		System.out.println ( " [doPost....REsigeSEcgeresss ]"  );
		 Enumeration en = request.getParameterNames();
	        while (en.hasMoreElements()) {
	            Object objOri = en.nextElement();
	            String param = (String) objOri;
	            String value = request.getParameter(param);
	            
	            System.out.println ( " incoming param [" + param + "] [" + value +"]"  );
	        }
	        
	    System.out.println ( " [doPost....2 ]"  );
		String username = request.getParameter("userName"); 
		String password = request.getParameter("password") ; 
		
		//PrintWriter writer = response.getWriter();
		
		System.out.println ( " incoming param [username] " + username );
		System.out.println ( " incoming param [assword] " + password );
		
		if ("xx".equals(username) & "xx".equals(password) )
		{
			
			//writer.println("You have successfully login, "+username );
			request.setAttribute("LoginSuccessful", true);
		}
		else
		{
			//writer.println("Wrong Credentials provideded"); 
			request.setAttribute("error", "Unknown login, please try again."); // Set error.
			request.setAttribute("registerSuccessful", false);
		}
		//writer.close();

		System.out.println ( " going to RegisterSuccessful.jsp " );
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("RegisterSuccessful.jsp");
        requestDispatcher.forward(request, response);
          
		//request.getRequestDispatcher("Login.jsp").forward(request, response) ; 
          
          
          
	}
	
	protected void doPost3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		System.out.println ( " [doPost....doPost.New ]"  );
		 Enumeration en = request.getParameterNames();
	        while (en.hasMoreElements()) {
	            Object objOri = en.nextElement();
	            String param = (String) objOri;
	            String value = request.getParameter(param);
	            
	            System.out.println ( " incoming param [" + param + "] [" + value +"]"  );
	        }
	        
	    System.out.println ( " [doPost....2 ]"  );
		String username = request.getParameter("userName"); 
		String password = request.getParameter("password") ; 
		
		PrintWriter writer = response.getWriter();
		
		System.out.println ( " incoming param [username] " + username );
		System.out.println ( " incoming param [assword] " + password );
		
		if ("xx".equals(username) & "xx".equals(password) )
		{
			
			//writer.println("You have successfully login, "+username );
			request.setAttribute("LoginSuccessful", true);
		}
		else
		{
			//writer.println("Wrong Credentials provideded"); 
			request.setAttribute("error", "Unknown login, please try again."); // Set error.
			request.setAttribute("registerSuccessful", false);
		}
		
	     response.setContentType("text/html;charset=UTF-8");
	     response.setStatus(200);
	     //writer.write("Success Data22");
	     System.out.println ( " sycccessdat  " );
			response.getWriter().write("OK");
		writer.close();

		//System.out.println ( " going to RegisterSuccessful.jsp " );
		//RequestDispatcher requestDispatcher = request.getRequestDispatcher("RegisterSuccessful.jsp");
        //requestDispatcher.forward(request, response);
          
		//request.getRequestDispatcher("Login.jsp").forward(request, response) ; 
          
          
          
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		System.out.println ( " [doPost....REsigeSEcgeresss ]"  );
		 Enumeration en = request.getParameterNames();
	        while (en.hasMoreElements()) {
	            Object objOri = en.nextElement();
	            String param = (String) objOri;
	            String value = request.getParameter(param);
	            
	            System.out.println ( " incoming param [" + param + "] [" + value +"]"  );
	        }
	        
	    System.out.println ( " [doPost....2 ]"  );
		String username = request.getParameter("userName"); 
		String password = request.getParameter("password") ; 
		String email = request.getParameter("email") ;
		String cLanguage = request.getParameter("language") ; 
		
		//PrintWriter writer = response.getWriter();
		
		System.out.println ( " incoming param [username] " + username );
		System.out.println ( " incoming param [password] " + password );
		System.out.println ( " incoming param [email] " + email );
		System.out.println ( " incoming param [cLanguage] " + cLanguage );
		

		//writer.close();

		boolean registerAccount = registerForAccount ( username, password, email, cLanguage ); 
		
		System.out.println ( " [registerAccount] " + registerAccount );
		if (!registerAccount)
		{
			request.setAttribute("accountExists", "true");
			System.out.println ( " [registerAccount] " + "error exists"  );
		}
		System.out.println ( " going to RegisterSuccessful.jsp " );
		
		System.out.println ( "REQUEST GET ATTRINUTE " + request.getAttribute("accountExists") ) ; 
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("RegisterResult.jsp");
        requestDispatcher.forward(request, response);
          
		//request.getRequestDispatcher("Login.jsp").forward(request, response) ; 
          
          
          
	}
	
	public boolean registerForAccount(String name, String password, String email, String cLanguage)
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/userdetails", "root", "password");
		PreparedStatement ps = con.prepareStatement("insert into USERDETAILS values(?,?,?,?)");
		ps.setString(1, name);
		ps.setString(2, password);
		ps.setString(3, email);
		ps.setString(4, cLanguage);
		int i = ps.executeUpdate();
		con.close();
		if ( i > 0)
		{
			return true; 
		}
		
		return false; 
		
	}
	catch (Exception e)
	{
		System.out.println(e);
return false;

	}
	}

}
