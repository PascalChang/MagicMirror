package com.user;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/userService")
public class userService {
	userDao userDao  = new userDao();
	
	@POST
	@Path("/insertuser")
	public void insertUser(@FormParam("rpi_id") String rpi_id,
			@FormParam("nickname") String nickname,
			@FormParam("gender") String gender,
			@FormParam("date_of_birth") String date_of_birth,
			@FormParam("email") String email) throws SQLException {
	user newUser = new user(rpi_id, nickname, gender, date_of_birth, email);
	 userDao.insertUser(newUser);
	 
	}	

	@GET
	@Path("/selectuser/{rpi_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<user> getuser(@PathParam("rpi_id") String rpi_id){
		List<user> info = userDao.selectUser(rpi_id);
		return info;
		   }
	
	@GET
	@Path("/selectalluser")
	@Produces(MediaType.APPLICATION_JSON)
	 public List<user> getallUsers(){
		   return userDao.selectAllUsers();
		   }
	 
	 @DELETE
	 @Path("/deleteuser/{rpi_id}")
	 public void deleteUser(@PathParam("rpi_id") String rpi_id) throws SQLException { 
		 userDao.deleteUser(rpi_id);
	 }
	 
	 @PUT
	 @Path("/updateuser")
	 public  void updateUser(@FormParam("rpi_id") String rpi_id,
				@FormParam("nickname") String nickname,
				@FormParam("gender") String gender,
				@FormParam("date_of_birth") String date_of_birth,
				@FormParam("email") String email) throws SQLException {
		 	user newUser = new user(rpi_id, nickname, gender, date_of_birth, email);
		    userDao.updateUser(newUser);
		}	
}
