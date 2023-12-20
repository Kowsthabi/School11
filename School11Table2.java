package JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class School11Table2 {
	public static void createTable2(Connection connect) throws SQLException
	{
		 PreparedStatement ps = connect.prepareStatement("CREATE TABLE IF NOT EXISTS StudentMarks (" +
                 "RollNo INT PRIMARY KEY, " +
                 "Subject VARCHAR(10), " +
                 "Marks INT)");
         ps.executeUpdate();
	}
}


