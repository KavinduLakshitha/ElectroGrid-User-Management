package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

	// Database Connection
	private Connection connect() {

		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb", "root", "");

			System.out.print("Successfully connected");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	
	//CREATE Operation
	
	public String insertUser(String name, String email, String phone, String acc_no) {
		String output = "";

		try {

			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = "insert into users(`userID`,`userName`,`userEmail`,`userPhone`,`userAcc_no`)"
					+ "values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, acc_no);

			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\":\""+ newUsers +"\"}";
			
		} catch (Exception e) 
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//READ Operation
	
	public String readUsers() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the HTML table to be declared
			output = "<table border='1'><tr>"
					+ "<th>User Name</th>"
					+ "<th>User Email</th>"
					+ "<th>User Phone</th>"
					+ "<th>Account No</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>";

			String query = "select * from users";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String userID = Integer.toString(rs.getInt("userID"));
				String userName = rs.getString("userName");
				String userEmail = rs.getString("userEmail");
				String userPhone = Integer.toString(rs.getInt("userPhone"));
				String userAcc_no = Integer.toString(rs.getInt("userAcc_no"));

				// Add into the HTML table
				output += "<tr><td>" + userName + "</td>"; 
				output += "<td>" + userEmail + "</td>";
				output += "<td>" + userPhone + "</td>";
				output += "<td>" + userAcc_no + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' " 
						 + " type='button' value='Update'"
						 + "class='btnUpdate btn btn-secondary'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove'"
						 + "class='btnRemove btn btn-danger'"
						 +"data-userid='"
						 + userID + "'>" + "</td></tr>";
			}

			con.close();

			// complete the HTML table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the users.";
			System.err.println(e.getMessage());
		}

		return output;

	}
	
	//UPDATE Operation

	public String updateUser(String ID, String name, String email, String phone, String acc_no) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE users SET "
					+ "userName=?,userEmail=?,userPhone=?,userAcc_no=?" + "WHERE userID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, email);
			preparedStmt.setInt(3, Integer.parseInt(phone));
			preparedStmt.setInt(4, Integer.parseInt(acc_no));
			preparedStmt.setInt(5, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\":\""+ newUsers +"\"}";
			
		}

		catch (Exception e) 
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}

		return output;

	}

	public String deleteUser(String userID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from users where userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(userID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\":\""+ newUsers +"\"}";
			
		} 
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}
}