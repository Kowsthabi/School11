
package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {
	private static final String URL="jdbc:mysql://localhost:3306/schools11";
	private static final String username="root";
	private static final String password="kowsthabi@123";
	
	//creating an object for the Scanner class
	public static Scanner obj=new Scanner(System.in);
	
	//insertRecord method will insert record into the database
	public static void insertData(Connection connect) throws SQLException
	{
		
		int RollNo,Cls=0,Marks=0;
		String Name="",Section="",DOB="",Subject="";
		
		PreparedStatement ps=connect.prepareStatement("insert into StudentDetails values(?,?,?,?,?);");
		
		
		System.out.println("Enter student Roll Number:");
		 RollNo=obj.nextInt();
        System.out.println("Enter student name:");
        obj.nextLine();
        Name=obj.nextLine();
        System.out.println("Enter student class:");
        Cls=obj.nextInt();
        System.out.println("Enter student section:");
        Section=obj.next();
        System.out.println("Enter the student date-of-birth:");
        DOB=obj.next();
        
		ps.setInt(1,RollNo);
		ps.setString(2,Name);
		ps.setInt(3, Cls);
		ps.setString(4, Section);
		ps.setString(5, DOB);
		
        PreparedStatement ps1=connect.prepareStatement("insert into StudentMarks values(?,?,?);");
		
		//taking the inputs from user and setting the values for parameters
		 System.out.println("Enter student subject:");
		 Subject=obj.next();
		 System.out.println("Enter student marks:");
		 Marks=obj.nextInt();
		 
		 ps1.setInt(1, RollNo);
		 ps1.setString(2, Subject);
		 ps1.setInt(3, Marks);
		
		//displays the result of the operation
        int rows=ps.executeUpdate();
        int rows1=ps1.executeUpdate();
		
		if(rows==0|| rows1==0)
		{
			System.out.println("Error occured during Insertion...Try again!!");
			System.out.println("-----------------------------------------------");
		}
		else
		{
			System.out.println("Insertion completed..");
			System.out.println("-----------------------------------------------");
		}
	}
	
	//readRecords method will retrieve all the records from the database
	
	public static void displayData(Connection connect) throws SQLException
	{
	    String query = "SELECT SD.RollNo, SD.Name, SD.Cls, SD.Section, SD.DOB, SM.Subject, SM.Marks " +
                "FROM StudentDetails SD " +
                "LEFT JOIN StudentMarks SM ON SD.RollNo = SM.RollNo;";
		PreparedStatement ps=connect.prepareStatement(query);
		ResultSet rs= ps.executeQuery();
		
		while(rs.next())
		{
			//assiging the column values in each row to the below variables and displaying them
			int RollNo=rs.getInt("RollNo");
			String Name=rs.getString("Name");
			int Cls=rs.getInt("Cls");
			String Section=rs.getString("Section");
			String DOB=rs.getString("DOB");
			String Subject=rs.getString("Subject");
			int Marks=rs.getInt("Marks");
			
			System.out.println("-----------------------------------------------");
			System.out.println("Roll Number: "+RollNo+"\nName: "+Name+"\nClass: "+Cls+"\nSection: "+Section+"\nDate-of-Birth: "+DOB+"\nSubject: "+Subject+"\nMarks: "+Marks);
			System.out.println("-----------------------------------------------");
		}
	}
	
	//updating the records in database
	public static void updateData(Connection connect) throws SQLException
	{
		PreparedStatement ps=connect.prepareStatement("update StudentDetails set Cls=? where RollNo=?");
		
		//taking the inputs from user and setting the values for parameters
		System.out.println("Enter RollNo");
		int RollNo=obj.nextInt();
		System.out.println("Enter the class");
		int Cls=obj.nextInt();
		
		ps.setInt(1, Cls);
		ps.setInt(2, RollNo);
		
		PreparedStatement ps1=connect.prepareStatement("update StudentMarks set Marks=? where RollNo=?");
		System.out.println("Enter the marks:");
		int Marks=obj.nextInt();
		
		ps1.setInt(1, Marks);
		ps1.setInt(2, RollNo);
		
		//displays the result of the operation
		 int rows=ps.executeUpdate();
		 ps1.executeUpdate();
			
			if(rows==0)
			{
				System.out.println("Try again!!");
				System.out.println("-----------------------------------------------");
			}
			else
			{
				System.out.println("Data updated..");
				System.out.println("-----------------------------------------------");
			}
		
	} 
	
	//deleteRecord will delete a specific row from database
	
	public static void deleteData(Connection connect) throws SQLException
	{
		PreparedStatement ps=connect.prepareStatement("delete from StudentDetails where RollNo=?");
		
		System.out.println("Enter the Roll Number of the student:");
		int RollNo=obj.nextInt();
		
		ps.setInt(1, RollNo);
		
		PreparedStatement ps1=connect.prepareStatement("delete from StudentMarks where RollNo=?");
		 ps1.setInt(1, RollNo);
		
		//displays the result of the operation
		 int rows=ps.executeUpdate();
		 int rows1=ps1.executeUpdate();
			
			if(rows==0||rows1==0)
			{
				System.out.println("Try again!!");
				System.out.println("-----------------------------------------------");
			}
			else
			{
				System.out.println("Deleted Sucessfully..");
				System.out.println("-----------------------------------------------");
			}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		int choice=0,tableNum=0; //choice variable contains the user preferred operation
		
		//loading the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//connection establishment
		Connection connect=DriverManager.getConnection(URL,username,password);
		
		//calling the create table methods to create tables in the database
		  School11Table1.createTable1(connect);
		  School11Table2.createTable2(connect);
		 
		  
		 while(choice!=5)
		 {
			 System.out.println("Enter your Choice");
			 System.out.println("1.Insert Data");
			 System.out.println("2.Delete Data");
			 System.out.println("3.Update Data");
			 System.out.println("4.Display Data");
			 System.out.println("5.Exit");
			 
				
			 System.out.println("Hello.. Please choose an option:");
			 System.out.println("---------------------------------");
			 System.out.println("1.Create \n 2.Read \n 3.Update \n 4.Delete \n 5.exit");
				
			 choice=obj.nextInt();
			 
			 switch(choice)
			 {
			     case 1:
			    	 insertData(connect);
			    	 displayData(connect);
			    	 break;
			     case 2:
			    	 deleteData(connect);
			    	 displayData(connect);
			    	 break;
			     case 3:
			    	 updateData(connect);
			    	 break;
			     case 4:
			    	 displayData(connect);
			    	 break;
			     case 5:
			    	 System.out.println("Thanks for visiting....");
			    	 return;
			     default:
			    	 System.out.println("Enter an valid option");
			 }
		 }

	}
}

	



				
				