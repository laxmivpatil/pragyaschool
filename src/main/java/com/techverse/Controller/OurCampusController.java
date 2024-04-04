package com.techverse.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.Response.CommonResponse;
import com.techverse.Response.OurCampusResponse;
import com.techverse.Response.SocialResponsibilityResponse;
import com.techverse.Service.OurCampusService;
import com.techverse.Service.SocialResponsibilityService;

@RestController
@RequestMapping("/ourcampus")
public class OurCampusController {
	
	
	@Autowired
	private OurCampusService ourCampusService;
	
	@GetMapping("/")
	public ResponseEntity<OurCampusResponse> getAllOurCampus() 
		{
		OurCampusResponse response=new OurCampusResponse();
		response.setCampus(ourCampusService.getAllOurCampus());
		 
			 return new ResponseEntity<>(response, HttpStatus.OK);
		}
	
		@PostMapping("/")
		public ResponseEntity<CommonResponse> addOurCampus( @RequestPart(value = "title", required = false) String title,
	    		@RequestPart(value = "image", required = false) MultipartFile image) 
			{
			ourCampusService.createOurCampus(title, image);
				
				CommonResponse response=new CommonResponse();
				response.setMessage("OurCampus added Successfully");
				
				 return new ResponseEntity<>(response, HttpStatus.OK);
			}
		
	
	

}
