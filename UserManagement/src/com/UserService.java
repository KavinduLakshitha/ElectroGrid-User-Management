package com;

import com.User;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Users")
public class UserService {

	User userObj = new User();
	
	//READ Operation
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers() {
		return userObj.readUsers();
	}

	//CREATE Operation
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(
			@FormParam("userName") String userName,
			@FormParam("userEmail") String userEmail,
			@FormParam("userPhone") String userPhone, 
			@FormParam("userAcc_no") String userAcc_no) {
		
		String output = userObj.insertUser(userName, userEmail, userPhone, userAcc_no);
		return output;
	}
	
	//UPDATE Operation
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateUser(
			@FormParam("userID") String userID,
			@FormParam("userName") String userName,
			@FormParam("userEmail") String userEmail,
			@FormParam("userPhone") String userPhone, 
			@FormParam("userAcc_no") String userAcc_no) {
		
		String output = userObj.updateUser(userID, userName, userEmail, userPhone, userAcc_no);
		return output;
	}
	
	//DELETE Operation
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData) {
		
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

		// Read the value from the element <userID>
		String userID = doc.select("userID").text();
		String output = userObj.deleteUser(userID);
		return output;
	}
	
}
