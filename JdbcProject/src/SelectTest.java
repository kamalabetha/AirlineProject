import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class SelectTest {
	public static void main(String[] args) {
		try
		{
		System.out.println("trying to load the driver");
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		System.out.println("driver loaded");
		System.out.println("trying to connect to the database");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","kamala");
		System.out.println("connected to database");
		System.out.println("Trying to make a statement for DQL");    //3rd step : create statement of your choice select(DQL)/DML/PL-SQL(proce/funs)
        Statement st = conn.createStatement(); // this is for DQL
        System.out.println("Statement made....for DQL");
        System.out.println("Trying to write a query and fire it... ");    //4th step : fire the statement and acquire result if any
        ResultSet rs = st.executeQuery("select * from dept"); // type the query here
        System.out.println("Query fired...and got the result....");
        System.out.println("Now processing the result....."); //5th step : process the result
        while(rs.next()) { // process the result set like a cursor program
            int deptno = rs.getInt(1);     String dname = rs.getString(2); // dname
            String loc = rs.getString(3); System.out.println("DEPTNO : "+deptno);
            System.out.println("DNAME  : "+dname);System.out.println("LOC    : "+loc);
            System.out.println("---------------------------");
        }
        //System.out.println("R");
		}
		catch(SQLException e)
		{
			System.out.println("");
		}
	}
		
	}


