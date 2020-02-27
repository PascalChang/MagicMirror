package com.wea;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/weaService")
public class weatherService {
weatherDao weaDao  = new weatherDao();
	
	@GET
	@Path("/{Rpi_id}")
	@Produces(MediaType.APPLICATION_JSON)
	 public List<weather> getWeatherinfo( @PathParam("Rpi_id")String Rpi_id){
		 int id = weaDao.getWeatherid(Rpi_id);
		 List<weather> info = weaDao.findWeatherinfo(id);
		 return info;
	 }
}
