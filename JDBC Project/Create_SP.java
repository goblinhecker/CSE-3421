import java.lang.*;
import java.util.*;
import java.sql.*; //contains the core JDBC API
import java.net.*;
import java.text.*;
import java.io.*;
import sqlj.runtime.*;
import sqlj.runtime.ref.*;

public class Create_SP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Create_SP");

		try {

			//load driver
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			System.out.println("**** Loaded the JDBC driver");

			//identify database
			String url = "jdbc:db2:C3421M";
			
			//create connection with default id/pass
			Connection con = null;
			con = DriverManager.getConnection(url);
			System.out
			.println("**** Created a JDBC connection to the data source");

			Statement db_statement = con.createStatement();
			System.out.println("**** Created JDBC Statement object");

			try {
				//drop table
				db_statement.executeUpdate("Drop table SP_Emp");
			} catch(SQLException se) {
				System.out.println("Table doesn't exist...");
			}
			 
			//create SP_Emp table
			db_statement
			.executeUpdate("Create table SP_Emp (SP_E_ID integer not null primary key, SP_E_Name varchar(25))");
			System.out.println("SP_Emp created successfully...");
			
			try {
				//drop SP_Sup
				db_statement.executeUpdate("Drop table SP_Supervisor");
			} catch(SQLException se) {
				System.out.println("Table doesn't exist...");
			}
			
			///create SP_Sup table
			db_statement
			.executeUpdate("Create table SP_Supervisor (SP_E_Sup integer not null, SP_E_Emp integer not null, SP_Quot varchar(2) default null, constraint SP_Quot1 check(SP_Quot in ('A', 'B', 'C', 'D', 'F')), constraint SP_PK_Sup primary key(SP_E_Emp), constraint SP_FK_Sup foreign key (SP_E_Sup) references SP_Emp(SP_E_ID) on delete cascade, constraint SP_FK_Emp foreign key (SP_E_Emp) references SP_Emp (SP_E_ID) on delete cascade)");
			System.out.println("SP_Supervisor created successfully... ");

			//end with protocol
			con.commit();
			System.out.println("**** Transaction committed");
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
				ex = ex.getNextException(); // For drivers that support chained
				// exceptions
			}
		}
	}
}