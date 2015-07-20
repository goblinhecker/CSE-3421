import java.lang.*;
import java.util.*;
import java.sql.*; //contains the core JDBC API
import java.net.*;
import java.text.*;
import java.io.*;

import sqlj.runtime.*;
import sqlj.runtime.ref.*;

public class Insert_SP_Emp {
	public static void main(String argv[]) {
		System.out.println("Insert_SP_Emp");
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			System.out.println("**** Loaded the JDBC driver");
			
			//connect with database
			String url = "jdbc:db2:c3421m";
			Connection con = null;
			con = DriverManager.getConnection(url);
			
			System.out
			.println("**** Created a JDBC connection to the data source");
			Statement db_statement = con.createStatement();
			System.out.println("**** Created JDBC Statement object");
			
			//insert values
			db_statement
			.executeUpdate("insert into SP_Emp values ('0', 'General Manager')"); 
			ResultSet result = db_statement.executeQuery("Select * from SP_Emp");
			System.out.println("The command completed successfully.");
			
			db_statement
			.executeUpdate("insert into SP_Emp values ('15', 'Supervisor 15')");
			System.out.println("The command completed successfully.");
			
			db_statement
			.executeUpdate("insert into SP_Emp values ('25', 'Supervisor 25')");
			System.out.println("The command completed successfully.");
			
			db_statement
			.executeUpdate("insert into SP_Emp values ('35', 'Supervisor 35')");
			System.out.println("The command completed successfully.");
			
			db_statement
			.executeUpdate("insert into SP_Emp values ('100', 'Supervisor 100')");
			System.out.println("The command completed successfully.");
			
			System.out.println("**** Records all inserted");
			
			//commit
			con.commit();
			System.out.println("**** Transaction committed");
			
			//create result set to hold db values received
			result = db_statement.executeQuery("Select * from SP_Emp");
			

			/**
			 * Now we print the data we just inserted.
			 */
			//get column names
			ResultSetMetaData rsmd = result.getMetaData();
			String name = rsmd.getColumnName(1);
			String name2 = rsmd.getColumnName(2);
			
			//print format
			System.out.print(name + "\t\t"); 
			System.out.println(name2);
			System.out.print("----------- 	");
			System.out.println("-------------------------");
			
			//while not null, print
			int i=0;
			while(result.next()) {
				System.out.print(result.getInt(1) + "\t\t");
				System.out.println(result.getString(2));
				i++;
			}
			
			System.out.println();
			System.out.printf("\t%d record(s) selected.\n", i);
			
			// commit work
			System.out.println("End of list");
			con.commit();
			System.out.println("**** Transaction committed");
			db_statement.close();
			System.out.println("Statement closed");
			con.close();
			System.out.println("Connection closed");
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load JDBC driver");
			System.out.println("Exception: " + e);
			e.printStackTrace();
		} catch (SQLException ex) {
			System.err.println("SQLException information");
			while (ex != null) {
				System.err.println("Error msg: " + ex.getMessage());
				System.err.println("SQLSTATE: " + ex.getSQLState());
				System.err.println("Error code: " + ex.getErrorCode());
				ex.printStackTrace();
				ex = ex.getNextException(); // For drivers that support chained exceptions
			}
		}
	}
}