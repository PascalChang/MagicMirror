package com.horo;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/horoService")
public class horoService {
	horoDao horoDao  = new horoDao();
	
	@GET
	@Path("/{Rpi_id}")
	@Produces(MediaType.APPLICATION_JSON)
	 public List<horo> getHoroinfo( @PathParam("Rpi_id")String Rpi_id){
		 int id = horoDao.getHoroid(Rpi_id);
		 List<horo> info = horoDao.findHoroinfo(id);
		 return info;
	 }

}
