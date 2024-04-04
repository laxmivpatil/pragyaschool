package com.techverse.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.Model.UserForm;
import com.techverse.Response.ApiResponse;
import com.techverse.Service.EmailService;
import com.techverse.Service.StorageSevice;
import com.techverse.Service.UserService;

@RestController 
public class UserController {
	String schoolEmail="laxmi.patil@techverse.world";
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String uploadFile()
	{
		 
		return "welcome";
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

        emailService.sendEmail(schoolEmail, schoolSubject, schoolBody);
        
        
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
            "<p>Best regards,<br/>Pragya School Admissions Team</p>" +
            "</body></html>";
        
        
        emailService.sendEmail(userForm.getEmail(), userSubject, userBody);

        return new ResponseEntity<>(createdUserForm, HttpStatus.OK);
    }

}
