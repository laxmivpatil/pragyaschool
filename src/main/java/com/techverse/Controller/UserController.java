package com.techverse.Controller;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.Model.GeneralAdmission;
import com.techverse.Model.UserForm;
import com.techverse.Repository.UserFormRepository;
import com.techverse.Response.ApiResponse;
import com.techverse.Service.EmailService;
import com.techverse.Service.StorageSevice;
import com.techverse.Service.UserService;

@RestController 
public class UserController {
	String schoolEmail="laxmi.patil@techverse.world";
	 @Autowired
	    private UserFormRepository userFormRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserService userService;
	@Autowired
	private StorageSevice storageService;
	
	@GetMapping("/")
	public String uploadFile()
	{
		 
		return "welcome";
	}
	@PostMapping("/test")
	public String test()
	{
		 
		return "welcome";
	}
	 @PostMapping("/testwithstring")
	    public ResponseEntity<?> testwithstring( 
	    		 @RequestParam("testString") String testString
	    		 
	             ) {
	    	
	    	 
	    		 return new ResponseEntity<>(testString, HttpStatus.OK);
	    		 
	        	
	    	 
	        
	    }
	 @PostMapping("/testwithstringimg")
	    public ResponseEntity<?> testwithstringimg( 
	    		  @RequestPart(value = "testString", required = false) String testString,
	      		@RequestPart(value = "img", required = false) MultipartFile img
	             
	    		 
	             ) {
		 
		 String path="abc";
		 if(img!=null && !img.isEmpty()) {
		 path= storageService.uploadFileOnAzure(img, "hi"+ UUID.randomUUID().toString());
		 }
	    	UserForm n=new UserForm();
	    	n.setMessage(path);
	    	  userFormRepository.save(n);
	    		 return new ResponseEntity<>(testString, HttpStatus.OK);
	    		 
	        	
	    	 
	        
	    }
	
 
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllUserForms() {
        List<UserForm> userForms = userService.getAllUserForms();
        ApiResponse apiResponse=new ApiResponse(userForms);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserForm> createUserForm(@RequestBody UserForm userForm) {
        UserForm createdUserForm = userService.createUserForm(userForm);
        String schoolSubject = "New Message from Contact Form";
        String schoolBody = "<html><body>" +
            "<p>Dear Pragya School Admissions Committee,</p>" +
            "<p>A new message has been received through the contact form:</p>" +
            "<ul>" +
            "<li>Full Name: " + userForm.getFullName() + "</li>" +
            "<li>Email: " + userForm.getEmail() + "</li>" +
            "<li>Phone Number: " + userForm.getPhoneNumber() + "</li>" +
            "<li>Subject: " + userForm.getSubject() + "</li>" +
            "<li>Message:</li>" +
            "<p>" + userForm.getMessage() + "</p>" +
            "</ul>" +
            "<p>Please review and respond accordingly.</p>" +
            "<p>Thank you.</p>" +
            "</body></html>";

       // emailService.sendEmail(schoolEmail, schoolSubject, schoolBody);
        
        
        String userSubject = "Your Message to Pragya School";
        String userBody = "<html><body>" +
            "<p>Dear " + userForm.getFullName() + ",</p>" +
            "<p>Thank you for contacting Pragya School. We have received your message and will get back to you soon.</p>" +
            "<p>Here are the details of your query:</p>" +
            "<ul>" +
            "<li>Subject: " + userForm.getSubject() + "</li>" +
            "<li>Message:</li>" +
            "<p>" + userForm.getMessage()+ "</p>" +
            "</ul>" +
            "<p>Best regards,<br/>Pragya School Admi"
            + "ssions Team</p>" +
            "</body></html>";
        
        
      //  emailService.sendEmail(userForm.getEmail(), userSubject, userBody);

        return new ResponseEntity<>(createdUserForm, HttpStatus.OK);
    }

}
