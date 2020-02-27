package com.news;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/newsService")
public class newsService {
	newsDao newsDao  = new newsDao();
	
	@GET
	@Path("/news")
	@Produces(MediaType.APPLICATION_JSON)
	   public List<news> getNews(){
	   return newsDao.findNews();
	   }	

}
