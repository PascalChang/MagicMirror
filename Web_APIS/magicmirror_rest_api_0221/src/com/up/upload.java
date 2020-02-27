package com.up;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.catalina.webresources.FileResource;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.sun.jersey.core.header.FormDataContentDisposition;

@Path("/file")
public class upload {

//			@POST
//			@Path("/upload")
//			@Consumes(MediaType.MULTIPART_FORM_DATA)
//			public Response uploadFile(
//				@FormDataParam("file") InputStream uploadedInputStream,
//				@FormDataParam("file") FormDataContentDisposition fileDetail) {
//
//				String uploadedFileLocation = "c://uploaded/" + fileDetail.getFileName();
//				
//				writeToFile(uploadedInputStream, uploadedFileLocation);
//
//				String output = "File uploaded to : " + uploadedFileLocation;
//
//				return Response.status(200).entity(output).build();
//
//			}
//			
//			private void writeToFile(InputStream uploadedInputStream,
//					String uploadedFileLocation) {
//
//					try {
//						OutputStream out = new FileOutputStream(new File(
//								uploadedFileLocation));
//						int read = 0;
//						byte[] bytes = new byte[1024];
//
//						out = new FileOutputStream(new File(uploadedFileLocation));
//						while ((read = uploadedInputStream.read(bytes)) != -1) {
//							out.write(bytes, 0, read);
//						}
//						out.flush();
//						out.close();
//					} catch (IOException e) {
//
//						e.printStackTrace();
//					}
//
//				}
			private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C://Users/nikos/Desktop/Upload_Files/";
			 
		    /**
		     * Upload a File
		     */
		 
		    @POST
		    @Path("/upload")
		    @Consumes(MediaType.MULTIPART_FORM_DATA)
		    public Response uploadFile(
		            @FormDataParam("file") InputStream fileInputStream,
		            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {
		 
		        String filePath = SERVER_UPLOAD_LOCATION_FOLDER + contentDispositionHeader.getFileName();
		 
		        // save the file to the server
		        saveFile(fileInputStream, filePath);
		 
		        String output = "File saved to server location : " + filePath;
		 
		        return Response.status(200).entity(output).build();
		 
		    }
		 
		    // save uploaded file to a defined location on the server
		    private void saveFile(InputStream uploadedInputStream,
		            String serverLocation) {
		 
		        try {
		            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
		            int read = 0;
		            byte[] bytes = new byte[1024];
		 
		            outpuStream = new FileOutputStream(new File(serverLocation));
		            while ((read = uploadedInputStream.read(bytes)) != -1) {
		                outpuStream.write(bytes, 0, read);
		            }
		            outpuStream.flush();
		            outpuStream.close();
		        } catch (IOException e) {
		 
		            e.printStackTrace();
		        }
		 
		    }
}
