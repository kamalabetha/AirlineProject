import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DepartmentTest {
	public static void main(String[] args) {
		/*
		Department deptObj1 = new Department();
		
		deptObj1.setDepartmentNumber(55);
		deptObj1.setDepartmentName("CODING");
		deptObj1.setDepartmentLocation("New Delhi");
		
		DepartmentDAOImpl ddiObj = new DepartmentDAOImpl(); // driver loaded, connected too
		
		ddiObj.addDepartment(deptObj1); // insert query is hidden inside it
		*/
		System.out.println("--------------------------");
		
		/*Department deptObj1 = new Department();
		
		deptObj1.setDepartmentNumber(70);
		deptObj1.setDepartmentName("Quality");
		deptObj1.setDepartmentLocation("Pune");
		
		DepartmentDAOImpl ddiObj = new DepartmentDAOImpl(); // driver loaded, connected too
		
		ddiObj.modifyDepartment(deptObj1); // udpate query is hidden inside it
		*/
		
		/*Department deptObj1 = new Department();
	
		deptObj1.setDepartmentNumber(80); //just fill the primary key
		
		DepartmentDAOImpl ddiObj = new DepartmentDAOImpl();
		ddiObj.removeDepartment(deptObj1); // delete is hidden inside it
		*/
		
		
		Department deptObj = null;
		DepartmentDAOImpl ddiObj = new DepartmentDAOImpl();
		deptObj = ddiObj.findDepartment(20); // select query hidden in it
		System.out.println("dept number   : "+deptObj.getDepartmentNumber());
		System.out.println("dept name     : "+deptObj.getDepartmentName());
		System.out.println("dept location : "+deptObj.getDepartmentLocation());
		
		/*
		List<Department> deptList = null;
		
		DepartmentDAOImpl ddiObj = new DepartmentDAOImpl();
		
		deptList = ddiObj.findDepartments(); // select * from dept hidden in it

		Iterator<Department> iter = deptList.iterator();
		
		while(iter.hasNext()) {
			Department deptObj = iter.next();
			System.out.println("dept number   : "+deptObj.getDepartmentNumber());
			System.out.println("dept name     : "+deptObj.getDepartmentName());
			System.out.println("dept location : "+deptObj.getDepartmentLocation());*/
			System.out.println("--------------------");
		}
	}


//1. POJO
class Department  // pojo is same as dept table structure
{
	private int departmentNumber; //same as deptno column
	private String departmentName; // same as dname column
	private String departmentLocation; //same as loc column
	
	//setter and getter to set and fetch information 
	public int getDepartmentNumber() {
		return departmentNumber;
	}
	public void setDepartmentNumber(int departmentNumber) {
		this.departmentNumber = departmentNumber;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getDepartmentLocation() {
		return departmentLocation;
	}
	public void setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
	}
}

interface DepartmentDAO 
{
	void addDepartment(Department dRef);		
	Department findDepartment(int dno);			
	List<Department> findDepartments();			
	void modifyDepartment(Department dRef);		
	void removeDepartment(Department dRef);     
}

class DepartmentDAOImpl implements DepartmentDAO
{
	Connection conn;
	ResultSet rs;
	Statement st;
	PreparedStatement pst;
	
	DepartmentDAOImpl() {
		try
		{
			//1st step : load the driver
			System.out.println("Trying to load the driver.......");
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Driver loaded....");
		
			//2nd step : connect to the database 
			System.out.println("Trying to connect to the database");
			
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","kamala");
			System.out.println("Connected to the database");
		}
		catch(Exception e) {
			System.out.println("Some Problem : "+e);
		}
	}
	
	public void addDepartment(Department dRef) { //insert query
		try {
			PreparedStatement pst = conn.prepareStatement("insert into dept values (?,?,?)"); // this is for DML
			pst.setInt(1, dRef.getDepartmentNumber());	
			pst.setString(2, dRef.getDepartmentName()); 
			pst.setString(3, dRef.getDepartmentLocation());
			System.out.println("PrepareStatement made....for DML");
			
			System.out.println("Trying to fire it... ");
			System.out.println("started executing");
			int rows = pst.executeUpdate(); 
			System.out.println("Record inserted..."+rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Department findDepartment(int dno) {
		Department deptObj = null;
		
		try {
			st = conn.createStatement();
			System.out.println("Statment made .... ");
			
			rs = st.executeQuery("select * from dept where deptno="+dno); 
			System.out.println("Query fired...and got the result....");
			System.out.println("Now processing the result....."); 
			if(rs.next()) { 
				int x = rs.getInt(1); 	
				String y = rs.getString(2); 
				String z = rs.getString(3); 
				
				deptObj = new Department(); 
				deptObj.setDepartmentNumber(x);
				deptObj.setDepartmentName(y);
				deptObj.setDepartmentLocation(z);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deptObj;
	}
	
	public 	List<Department> findDepartments() {
		List<Department> deptList = new ArrayList<Department>(); 
		
		try {
			Statement st = conn.createStatement();
			System.out.println("Statement made...");
			rs = st.executeQuery("select * from dept"); 
			System.out.println("Query fired...and got the result....");
			System.out.println("Now processing the result....."); 
			while(rs.next()) { 
				int x = rs.getInt(1); 	
				String y = rs.getString(2); 
				String z = rs.getString(3); 
				
				Department deptObj = new Department(); 
				deptObj.setDepartmentNumber(x);
				deptObj.setDepartmentName(y);
				deptObj.setDepartmentLocation(z);
				
				deptList.add(deptObj);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return deptList;
	}
	
	public 	void modifyDepartment(Department dRef) {
		try {
			System.out.println("Trying to make a PreparedStatement for DML(update)");	//3rd step : create statement of your choice select(DQL)/DML/PL-SQL(proce/funs)
			PreparedStatement pst = conn.prepareStatement("update dept set dname=?, loc=? where deptno=?"); // this is for DML
			pst.setString(1, dRef.getDepartmentName()); 
			pst.setString(2, dRef.getDepartmentLocation());
			pst.setInt(3, dRef.getDepartmentNumber());
			
			System.out.println("PrepareStatement made....for DML update");
			System.out.println("Trying to fire it... ");	//4th step : fire the statement and acquire result if any
			int rows = pst.executeUpdate();
			System.out.println("Record updated : "+rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public 	void removeDepartment(Department dRef) {
		try {
			System.out.println("Trying to make a PreparedStatement for DML(delete)");
			PreparedStatement pst = conn.prepareStatement("delete from dept where deptno=?"); 
			pst.setInt(1, dRef.getDepartmentNumber()); 
			
			System.out.println("PrepareStatement made....for DML delete");
			System.out.println("Trying to fire it... ");	
			int rows = pst.executeUpdate(); 
			System.out.println("Record deleted..."+rows);
			System.out.println("record deleted successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

