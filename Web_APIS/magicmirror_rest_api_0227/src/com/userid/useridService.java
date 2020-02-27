package com.userid;

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


@Path("/useridService")
public class useridService {
useridDao useridDao  = new useridDao();
	
	@POST
	@Path("/insertuserid")
	public void insertUserid(@FormParam("rpi_id") String rpi_id,
			@FormParam("weather_code") int wc,
			@FormParam("horo_code") int hc
			) throws SQLException {
	userid newUserid = new userid(rpi_id, wc, hc);
	 useridDao.insertUserid(newUserid);
	 
	}
	
	@POST
	@Path("/insertpassword")
	public void insertUserid(@FormParam("rpi_id") String rpi_id,
			@FormParam("password") String pw
			) throws SQLException {
	userid newUserid = new userid(rpi_id, pw);
	 useridDao.insertPasswd(newUserid);
	 
	}
	
	@POST
	@Path("/insertstocknum")
	public void insertStocknum(@FormParam("rpi_id") String rpi_id,
			@FormParam("stock_num1") int st1,
			@FormParam("stock_num2") int st2,
			@FormParam("stock_num3") int st3
			) throws SQLException {
	userid newUserid = new userid(rpi_id, st1, st2, st3);
	 useridDao.insertStocknum(newUserid);
	 
	}	

	@GET
	@Path("/selectuserid/{rpi_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<userid> getuserid(@PathParam("rpi_id") String rpi_id){
		List<userid> info = useridDao.selectUserid(rpi_id);
		return info;
		   }
	
	@GET
	@Path("/selectalluserid")
	@Produces(MediaType.APPLICATION_JSON)
	 public List<userid> getallUserid(){
		   return useridDao.selectAllUserid();
		   }
	 
	 @DELETE
	 @Path("/deleteuserid/{rpi_id}")
	 public void deleteUserid(@PathParam("rpi_id") String rpi_id) throws SQLException { 
		 useridDao.deleteUserid(rpi_id);
	 }
	 
	 @PUT
	 @Path("/updateuserid")
	 public  void updateUserid(@FormParam("rpi_id") String rpi_id,
				@FormParam("weather_code") int wc,
				@FormParam("horo_code") int hc
				) throws SQLException {
		userid newUserid = new userid(rpi_id, wc, hc);
		    useridDao.updateUserid(newUserid);
		}
	 
	 @PUT
	 @Path("/updatepassword")
	 public  void updateUserid(@FormParam("rpi_id") String rpi_id,
				@FormParam("password") String pw
				) throws SQLException {
		userid newUserid = new userid(rpi_id, pw);
		    useridDao.updatePasswd(newUserid);
		}	
	 
	 @PUT
	 @Path("/updatestocknum")
	 public  void updateUserid(@FormParam("rpi_id") String rpi_id,
				@FormParam("stock_num1") int st1,
				@FormParam("stock_num2") int st2,
				@FormParam("stock_num3") int st3
				) throws SQLException {
		userid newUserid = new userid(rpi_id, st1, st2, st3);
		    useridDao.updateStocknum(newUserid);
		}	
}
