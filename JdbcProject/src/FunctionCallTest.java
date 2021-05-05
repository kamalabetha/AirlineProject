import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class FunctionCallTest {
	public static void main(String[] args) {
		try
		{
		System.out.println("trying to load the driver");
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		System.out.println("driver loaded");
		System.out.println("trying to connect to the database");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","kamala");
		System.out.println("connected to database");
		System.out.println("Trying to make a statement for DQL");
		//3rd step : create statement of your choice select(DQL)/DML/PL-SQL(proce/funs)
		
		
        CallableStatement cst = conn.prepareCall("{?=call myemp.calc_sal(?)}"); // this is for DQL
        System.out.println("callableStatement made....for DQL");
        Scanner sc=new Scanner(System.in);
       
        System.out.println("enter emp no");
        int eno=sc.nextInt();
        cst.setInt(2,eno);
        cst.registerOutParameter(1, Types.DOUBLE);
        //cst.registerOutParameter(3, Types.VARCHAR);
        cst.execute();
        
        
        double salary=cst.getDouble(1);
        System.out.println("plsql block executed..result");
        System.out.println("sal"+salary);
        
        		
        System.out.println("Trying to close callablestatement and connection... "); 
        cst.close();conn.close();
        System.out.println("statement and connection closed");//4th step : fire the statement and acquire result if any
		}
	
		catch(SQLException e)
		{
			System.out.println("");
		}
	}
}
//select myemp.calc_sal(7369) from dual;

			


