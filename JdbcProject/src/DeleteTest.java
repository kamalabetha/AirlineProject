
import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;


public class DeleteTest {
	public static void main(String[] args) {
		try
		{
		System.out.println("trying to load the driver");
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		System.out.println("driver loaded");
		System.out.println("trying to connect to the database");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","kamala");
		System.out.println("connected to database");
		conn.setAutoCommit(false);
		System.out.println("Trying to make a preparedstatement for DML delete");    //3rd step : create statement of your choice select(DQL)/DML/PL-SQL(proce/funs)
        PreparedStatement pst = conn.prepareStatement("delete from dept where deptno=?"); 
        Scanner sc1=new Scanner(System.in);
        Scanner sc2=new Scanner(System.in);
       
        


        System.out.println("which deptno u want to delete");
        
        int dno=sc1.nextInt();
        
        pst.setInt(1, dno);
        
        
        System.out.println("prepareStatement made....for DML");
        System.out.println("Trying to fire it... ");//4th step : fire the statement and acquire result if any
        int rows=pst.executeUpdate();
        System.out.println("do you want to save this record yes/no");// type the query here
        String ans=sc2.nextLine();
        if(ans.equalsIgnoreCase("yes"))
        {
        	conn.commit();
        	System.out.println("rows deleted"+rows);
        }
        else if(ans.equalsIgnoreCase("no"))
        {
        	conn.rollback();
        	System.out.println("row deletion failed"+rows);
        }
        System.out.println("Query fired...rows updated..and got the result...."+rows);
        System.out.println("trying to close connection and preparestatement");
        
        pst.close();conn.close();
        System.out.println("preparestatement,connection closed"); //5th step : process the result
       
		}
		catch(SQLException e)
		{
			System.out.println("");
		}
	}
		
	}




