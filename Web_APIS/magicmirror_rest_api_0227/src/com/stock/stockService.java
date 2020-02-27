package com.stock;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/stockService")
public class stockService {
	stockDao stockDao  = new stockDao();
	
	@GET
	@Path("/{Rpi_id}/1")
	@Produces(MediaType.APPLICATION_JSON)
		public List<stock> getStockinfo1( @PathParam("Rpi_id")String Rpi_id){
			int n1 = stockDao.getStocknum1(Rpi_id);
			List<stock> info1 = stockDao.findStockinfo1(n1);
			return info1;		 
	 	}
		
	@GET
	@Path("/{Rpi_id}/2")
	@Produces(MediaType.APPLICATION_JSON)
	public List<stock> getStockinfo2( @PathParam("Rpi_id")String Rpi_id){
		 int n2 = stockDao.getStocknum2(Rpi_id);
		 List<stock> info2 = stockDao.findStockinfo2(n2);
		 return info2;
	 }
	
	@GET
	@Path("/{Rpi_id}/3")
	@Produces(MediaType.APPLICATION_JSON)
	public List<stock> getStockinfo3( @PathParam("Rpi_id")String Rpi_id){
		 int n3 = stockDao.getStocknum3(Rpi_id);
		 List<stock> info3 = stockDao.findStockinfo3(n3);
		 return info3;
	 }
}