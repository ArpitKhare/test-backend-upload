package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.net.HttpHeaders;

@RestController
@EnableAutoConfiguration
@ComponentScan
@RequestMapping("/hortonworks/support-tool/v1/*")
public class AppController {
		

		@CrossOrigin(origins = "*")
		@RequestMapping(value = "/test", method = RequestMethod.GET)
		public @ResponseBody String cancelReservation(){
			System.out.println("hi");
			return "<html><h1>Support Tool RESTful web service.</h1><p><font color='blue'>Hello Team! This is a sample RESTful API which would download the logs at backend "
					+ "and would respond with the recommendations</font></p><p>- akhare@hortonworks.com</p>";
		}
		
		
		@RequestMapping(value="/downloadLogFile",method=RequestMethod.GET	)
		public void getLogFile(HttpSession session,HttpServletResponse response) throws Exception {
		    try {
		    	String fileName="Arpit";
		        String filePathToBeServed ="/Users/akhare/Desktop/text.rtf"; //complete file name with path;
		        File fileToDownload = new File(filePathToBeServed);
		        InputStream inputStream = new FileInputStream(fileToDownload);
		        response.setContentType("application/force-download");
		        response.setHeader("Content-Disposition", "attachment; filename="+fileName+".txt"); 
		        IOUtils.copy(inputStream, response.getOutputStream());
		        response.flushBuffer();
		        inputStream.close();
		    } catch (Exception e){
		       e.printStackTrace();
		    }

		}
		

	    @RequestMapping(value="/upload", method=RequestMethod.GET)
	    public @ResponseBody String provideUploadInfo() {
	        return "You can upload a file by posting to this same URL.";
	    }

	    @RequestMapping(value="/upload", method=RequestMethod.POST)
	    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
	            @RequestParam("file") MultipartFile file){
	        if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(name)));
	                stream.write(bytes);
	                stream.close();
	                return "You successfully uploaded " + name + "!";
	            } catch (Exception e) {
	                return "You failed to upload " + name + " => " + e.getMessage();
	            }
	        } else {
	            return "You failed to upload " + name + " because the file was empty.";
	        }
	    }

	    
		

}
