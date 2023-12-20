package JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class School11Table1 {
	public static void createTable1(Connection connect) throws SQLException
	{
		PreparedStatement ps = connect.prepareStatement("CREATE TABLE IF NOT EXISTS StudentDetails (" +
                "RollNo INT, " +
                "Name VARCHAR(20), " +
                "Cls INT, " +
                "Section VARCHAR(1), " +
                "DOB VARCHAR(10))");
       ps.executeUpdate();
		
	}
	

}

