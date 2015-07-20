import java.lang.*;
import java.util.*;
import java.sql.*; //contains the core JDBC API
import java.net.*;
import java.text.*;
import java.io.*;

import sqlj.runtime.*;
import sqlj.runtime.ref.*;

public class Insert_SP_Supervisor {
	public static void main(String argv[]) {
		System.out.println("Insert_SP_Supervisor");
		try {
			
			//connect with driver
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
			
			//insert the values
			db_statement
			.executeUpdate("insert into SP_Supervisor values ('0', '0', null)"); 
			System.out.println("The command completed successfully.");
			
			db_statement
			.executeUpdate("insert into SP_Supervisor values ('0', '15', null)");
			System.out.println("The command completed successfully.");
			
			db_statement
			.executeUpdate("insert into SP_Supervisor values ('0', '35', null)");
			System.out.println("The command completed successfully.");
			
			db_statement
			.executeUpdate("insert into SP_Supervisor values ('0', '100', null)");
			System.out.println("The command completed successfully.");
			
			db_statement
			.executeUpdate("insert into SP_Supervisor values ('0', '25', null)"); 
			System.out.println("The command completed successfully.");
			
			System.out.println("**** Records all inserted");
			con.commit();
			System.out.println("**** Transaction committed");
			/**
			 * Now we print the data we just inserted.
			 */
			//result set to receive data from db
			ResultSet result = db_statement.executeQuery("Select * from SP_Supervisor");
			
			//get column names
			ResultSetMetaData rsmd = result.getMetaData();
			String name = rsmd.getColumnName(1);
			String name2 = rsmd.getColumnName(2);
			String name3 = rsmd.getColumnName(3);
			
			//print formatting:
			System.out.print(name + "    ");
			System.out.print(name2 + "    ");
			System.out.println(name3);
			System.out.print("----------- ");
			System.out.print("----------- ");
			System.out.println("-------");
			
			//while more rows exist, print...
			int i = 0;
			while(result.next()) {
				System.out.print("\t" + result.getInt(1) + "\t");
				System.out.print(result.getInt(2) + "\t ");
				System.out.println("null");
				i++;
			}
			System.out.println();
			System.out.printf("\t%d record(s) selected.\n", i);
            
			// commit work and end with protocol
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