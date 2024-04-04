package com.techverse.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.Model.SocialResponsibility;
import com.techverse.Repository.SocialResponsibilityRepository;
import com.techverse.Response.CommonResponse;
import com.techverse.Response.GeneralAdmissionResponse;
import com.techverse.Response.SocialResponsibilityResponse;
import com.techverse.Service.SocialResponsibilityService;

@RestController
@RequestMapping("/socialresponsibility")
public class SocialResponsibilityController {
	
	@Autowired
	private SocialResponsibilityService socialResponsibilityService;
	
	@GetMapping("/")
	public ResponseEntity<SocialResponsibilityResponse> getAllSocialResponsibility() 
		{
		SocialResponsibilityResponse response=new SocialResponsibilityResponse();
		response.setResponsibilities(socialResponsibilityService.getAllsocialResponsibility());
			
			 return new ResponseEntity<>(response, HttpStatus.OK);
		}
	
		@PostMapping("/")
		public ResponseEntity<CommonResponse> addSocialResponsibility( @RequestPart(value = "title", required = false) String title,
	    		@RequestPart(value = "image", required = false) MultipartFile image) 
			{
				socialResponsibilityService.createSocialResponsibility(title, image);
				
				CommonResponse response=new CommonResponse();
				response.setMessage("SocialResponsibility added Successfully");
				
				 return new ResponseEntity<>(response, HttpStatus.OK);
			}
		
	
	

}
