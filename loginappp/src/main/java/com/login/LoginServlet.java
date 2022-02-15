package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	
	Connection con = null;
	
	public void init(ServletConfig config) {
	
	try {
	//	"com.mysql.cj.jdbc.Driver"
		Class.forName("com.mysql.cj.jdbc.Driver");
 
	
	System.out.println("Driver is registered");

	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root", "root");

	System.out.println("Connection is established");
	
	}
	catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		e.getMessage();
	}
	catch(Exception e){
		e.printStackTrace();
		}
		
	}
	
	public void service(HttpServletRequest request,HttpServletResponse response)
	{
	try{
	String user = request.getParameter("uname");
	String pass = request.getParameter("upass");

	Statement st = con.createStatement();
	String query ="select * from inetusers where usr='"+user+"' and pwd='"+pass+"'";
	System.out.println(query);

	ResultSet rs = st.executeQuery(query);
	PrintWriter out = response.getWriter();
	response.setContentType("type/html");

	if(rs.next()){
	out.println("you are a valid user");
	}
	else{
	out.println("you are an invalid user");
	out.println("<a href ='login.html'>Click here to Login</a>");
	 }
	}
	catch(SQLException sqle){
	 sqle.printStackTrace();
	}
	catch(IOException ioe){
	ioe.printStackTrace();
	 }
	
	}
	public void destroy(){
		try{
		 con.close();
		 } catch(SQLException sqle){
		   sqle.printStackTrace();
		  }
		}



	


}
