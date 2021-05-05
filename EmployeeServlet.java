package com.employees;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
	ResultSet rs;
	Statement st;
	
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("calling....");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
		System.out.println("trying to load the driver");
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		System.out.println("driver loaded");
		System.out.println("trying to connect to the database");
	    conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","kamala");
		System.out.println("connected to database");
		}
		catch(Exception e) {
			System.out.println("Some Problem : "+e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			st.close();
			rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		try {
		Statement st = conn.createStatement();
		System.out.println("Statement made...");
		rs = st.executeQuery("select * from emp");
		pw.println("<html><body><h2>The Select query has following results : </h2>");
		pw.println("<hr></br><table cellspacing='0' cellpadding='5' border='1'>");
		pw.println("<tr>");
		pw.println("<td><b>empno</b></td>");
		pw.println("<td><b>ename</b></td>");
		pw.println("<td><b>job</b></td>");
		pw.println("<td><b>mgr</b></td>");
		pw.println("<td><b>hiredate</b></td>");
		pw.println("<td><b>sal</b></td>");
		pw.println("<td><b>comm</b></td>");
		pw.println("<td><b>deptno</b></td>");
		pw.println("</tr>");
     while(rs.next()) {
			   pw.println("<tr>");
			  pw.println("<td>"+rs.getInt(1) + "</td>");
			  pw.println("<td>"+rs.getString(2) + "</td>");
			   pw.println("<td>"+rs.getString(3) + "</td>");
			   pw.println("<td>"+rs.getString(4) + "</td>");
			   pw.println("<td>"+rs.getDate(5) + "</td>");
			   pw.println("<td>"+rs.getInt(6) + "</td>");
			   pw.println("<td>"+rs.getString(7) + "</td>");
			   pw.println("<td>"+rs.getInt(8) + "</td>");
			   pw.println("</tr>");
}
pw.println("</table></br><hr></body></html>");
	  }
		catch(Exception e) {
			System.out.println("Some Problem : "+e);
		}
		
	}

}

