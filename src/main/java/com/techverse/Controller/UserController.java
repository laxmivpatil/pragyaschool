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
import com.techverse.Service.EmailService1;
import com.techverse.Service.StorageSevice;
import com.techverse.Service.UserService;

@RestController 
public class UserController {
	String schoolEmail="contactus@pragyagirlsschool.com";
	 @Autowired
	    private UserFormRepository userFormRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private EmailService1 emailService1;
	@Autowired
	private UserService userService;
	@Autowired
	private StorageSevice storageService;
	
	@GetMapping("/")
	public String uploadFile()
	{
 	String recipientEmail = "laxmi.patil@techverse.world";
		String emailSubject = "Welcome to Our Service";
		String emailBody = "<h2>Welcome to Our Service</h2><p>We are glad to have you with us.</p>";
		String imagePath = "src/main/resources/static/images/logo.png"; // Example: "src/main/resources/static/images/logo.png"

		boolean result = emailService.sendEmail1(recipientEmail, emailSubject, emailBody, imagePath);

		if (result) {
		    System.out.println("Email sent successfully.");
		} else {
		    System.out.println("Failed to send email.");
		}
		return "welcome";
	}
	@PostMapping("/test")
	public String test()
	{ 
		String recipientEmail = "laxmipatil070295@gmail.com";
		//String recipientEmail = "maneomkar94@gmail.com";
		//String recipientEmail = "bhaktithorat20@gmail.com";
		//String recipientEmail ="keerthanams@techverse.world";
		//String recipientEmail ="keerthu.m230@gmail.com";
		
		//String recipientEmail="test@pragyagirlsschool.com";
	//String recipientEmail="laxmi.patil@techverse.world";
		String emailSubject = "Welcome to Our Service";
		String emailBody = "<h2>Welcome to Our Service</h2><p>We are glad to have you with us.</p>";
		 

		emailService1.sendEmail(recipientEmail, emailSubject, emailBody);
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
        String schoolSubject = "Contact Us Request";
        
        StringBuilder schoolBodyBuilder = new StringBuilder();
        schoolBodyBuilder.append("<html><body>")
            .append("<p>Dear Pragya School Admissions Committee,</p>")
            .append("<p>A new message has been received through the contact form:</p>")
            .append("<ul>")
            .append("<li>Full Name: ").append(userForm.getFullName()).append("</li>")
            .append("<li>Email: ").append(userForm.getEmail()).append("</li>")
            .append("<li>Phone Number: ").append(userForm.getPhoneNumber()).append("</li>")
            .append("<li>Subject: ").append(userForm.getSubject()).append("</li>")
            .append("<li>Message: ").append(userForm.getMessage()).append("</li>")
          //  .append("<li>Message:</li>")
            .append("<p>").append(userForm.getMessage()).append("</p>");
        
        // Add city and state if provided
        if (!userForm.getCity().isEmpty()) {
            schoolBodyBuilder.append("<li>City: ").append(userForm.getCity()).append("</li>");
        }
        if (!userForm.getState().isEmpty()) {
            schoolBodyBuilder.append("<li>State: ").append(userForm.getState()).append("</li>");
        }
        
        schoolBodyBuilder.append("</ul>")
            .append("<p>Please review and respond accordingly.</p>")
            .append("<p>Thank you.</p>")
            .append("</body></html>");
        
        String schoolBody = schoolBodyBuilder.toString();
         emailService1.sendEmail(schoolEmail, schoolSubject, schoolBody);

        String userSubject = "Your Message to Pragya School";
        
        StringBuilder userBodyBuilder = new StringBuilder();
        userBodyBuilder.append("<html><body>")
            .append("<p>Dear ").append(userForm.getFullName()).append(",</p>")
            .append("<p>Thank you for contacting Pragya School. We have received your message and will get back to you soon.</p>")
            .append("<p>Here are the details of your query:</p>")
            .append("<ul>")
            .append("<li>Subject: ").append(userForm.getSubject()).append("</li>")
            .append("<li>Message: ").append(userForm.getMessage()).append("</li>")
          //  .append("<li>Message:</li>")
            .append("<p>").append(userForm.getMessage()).append("</p>");
        
        // Add city and state if provided
        if (!userForm.getCity().isEmpty()) {
            userBodyBuilder.append("<li>City: ").append(userForm.getCity()).append("</li>");
        }
        if (!userForm.getState().isEmpty()) {
            userBodyBuilder.append("<li>State: ").append(userForm.getState()).append("</li>");
        }
        
        userBodyBuilder.append("</ul>")
            .append("<p>Best regards,<br/>Pragya School Admissions Team</p>")
            .append("</body></html>");
        
        String userBody = userBodyBuilder.toString();
        
        System.out.println(userForm.getEmail()+" "+userSubject+" "+userBody);
         emailService1.sendEmail(userForm.getEmail(), userSubject, userBody);

        return new ResponseEntity<>(createdUserForm, HttpStatus.OK);
    }

}
