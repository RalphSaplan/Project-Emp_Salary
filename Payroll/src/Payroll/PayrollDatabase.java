package Payroll;

import java.sql.*;

public class PayrollDatabase {

	public static void main(String[] args) {
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:thanh","payroll","AnhChi0912");
		Statement st=con.createStatement();
		String sql="Select * from Employee";
		ResultSet rs=st.executeQuery(sql);
		while (rs.next())
			System.out.println(rs.getInt(1)+" "+rs.getString(2));
		con.close();
		}
		catch (Exception e)
		{
		 System.out.println(e);
		}

	}

}
