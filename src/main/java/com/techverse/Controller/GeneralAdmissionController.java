package com.techverse.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.Model.GeneralAdmission;
import com.techverse.Repository.GeneralAdmissionRepository;
import com.techverse.Response.GeneralAdmissionResponse;
import com.techverse.Service.EmailService;
import com.techverse.Service.GeneralAdmissionService;
import com.techverse.Service.StorageSevice;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admissions")
public class GeneralAdmissionController {

	@Autowired
	private StorageSevice storageService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private GeneralAdmissionService generalAdmissionService;
	String schoolEmail = "laxmi.patil@techverse.world";

	@Autowired
	private GeneralAdmissionRepository generalAdmissionRepository;

	@GetMapping("/")
	public ResponseEntity<GeneralAdmissionResponse> getAllAdmissions() {
		List<GeneralAdmission> admissions = generalAdmissionService.getAllAdmissions();

		GeneralAdmissionResponse admissionResponse = new GeneralAdmissionResponse(admissions);

		return new ResponseEntity<>(admissionResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GeneralAdmission> getAdmissionById(@PathVariable Long id) {
		Optional<GeneralAdmission> admission = generalAdmissionService.getAdmissionById(id);
		return admission.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/")
	public ResponseEntity<?> createAdmission(@RequestPart(value = "firstName", required = false) String firstName,
			@RequestPart(value = "lastName", required = false) String lastName,
			@RequestPart(value = "gender", required = false) String gender,
			@RequestPart(value = "dateOfBirth", required = false) String dateOfBirth,
			@RequestPart(value = "admissionClass", required = false) String admissionClass,
			@RequestPart(value = "fatherName", required = false) String fatherName,
			@RequestPart(value = "motherName", required = false) String motherName,
			@RequestPart(value = "mobileNo", required = false) String mobileNo,
			@RequestPart(value = "email", required = false) String email,
			@RequestPart(value = "type", required = false) String type,
			@RequestPart(value = "pen", required = false) String pen,
			@RequestPart(value = "birthCertificate", required = false) MultipartFile birthCertificate,
			@RequestPart(value = "lastResult", required = false) MultipartFile lastResult,
			@RequestPart(value = "parentAadhar", required = false) MultipartFile parentAadhar,
			@RequestPart(value = "studentAadhar", required = false) MultipartFile studentAadhar,
			@RequestPart(value = "bankDoc", required = false) MultipartFile bankDoc,
			@RequestPart(value = "cast", required = false) MultipartFile cast,
			@RequestPart(value = "transferCertificate", required = false) MultipartFile transferCertificate,
			@RequestPart(value = "profile", required = false) MultipartFile profile,
			@RequestPart(value = "sssmid", required = false) MultipartFile sssmid) {

		
		  if(type.equals("general")) { GeneralAdmission createdAdmission = new
		  GeneralAdmission(firstName,lastName, gender,dateOfBirth,
		  admissionClass,fatherName, motherName, mobileNo, email, type, "", "", "","",
		  "", "","", "", "","");
		  
		  
		  String subject = "New Admission Request";
		  
		  String body = "<html><body>" +
		  "<p>Dear Pragya School Admissions Committee,</p>" +
		  "<p>We are pleased to inform you that a new admission request has been received for the academic year 2024.</p>"
		  + "<p>Details of the applicant are as follows:</p>" + "<ul>" +
		  "<li>Full Name: " + firstName + " " + lastName + "</li>" + "<li>Gender: " +
		  gender + "</li>" + "<li>Date of Birth: " + dateOfBirth + "</li>" +
		  "<li>Class Applying For: " + admissionClass + "</li>" + "<li>Father's Name: "
		  + fatherName + "</li>" + "<li>Mother's Name: " + motherName + "</li>" +
		  "<li>Mobile Number: " + mobileNo + "</li>" + "<li>Email Address: " + email +
		  "</li>" + "</ul>" +
		  "<p>Please review the details. If further information or clarification is required, please feel free to contact us.</p>"
		  + "<p>Thank you for considering this admission request.</p>" +
		  "<p>Best regards,<br/>" + firstName + " " + lastName + "<br/>" + mobileNo +
		  "</p>" + "</body></html>";
		  
		  // emailService.sendEmail(schoolEmail, subject, body);
		  
		  
		  
		  String userSubject = "Your Admission Enquiry"; String userBody =
		  "<html><body>" + "<p>Dear " + firstName + " " + lastName + ",</p>" +
		  "<p>Thank you for your admission enquiry. We have received your request and will get back to you shortly.</p>"
		  + "<p>Best regards,<br/>Pragya School Admissions Team</p>" +
		  "</body></html>";
		  
		  // emailService.sendEmail(email, userSubject, userBody);
		  
		  // emailService.sendSimpleEmail(email, "New Admission", firstName+lastName);
		 /* return new
		  ResponseEntity<>(generalAdmissionRepository.save(createdAdmission),
		  HttpStatus.OK);
		  */
		  
		  generalAdmissionRepository.save(createdAdmission);
		  return new
				  ResponseEntity<>("Form Submitted successfully",
				  HttpStatus.OK);
		  } 
		  else { 
			  String birthC="",lastR="",parentA="",studentA="", sssmi="", bankD="", castC="",transferC="", profileP=""; //name String
			  String birthCN="",lastRN="",parentAN="",studentAN="", sssmiN="", bankDN="",castCN="",transferCN="", profilePN=""; //extension String
			  String birthT="",lastRT="",parentAT="",studentAT="", sssmiT="", bankDT="", castCT="",transferCT="", profilePT="";
		  
		  
		  if (birthCertificate != null && !birthCertificate.isEmpty()) { birthT =
		  getExtension(birthCertificate);
		  
		  birthCN = "Birth_Certificate" + " " + Instant.now().toEpochMilli();
		  
		  birthC = storageService.uploadFileOnAzure(birthCertificate,
		  birthCN+"."+birthT );
		  
		  }
		  
		  if (lastResult != null && !lastResult.isEmpty()) { lastRT=
		  getExtension(lastResult); lastRN= "Last_Year_Result" + " " +
		  Instant.now().toEpochMilli(); lastR =
		  storageService.uploadFileOnAzure(lastResult, lastRN+"."+lastRT); }
		  
		  if (parentAadhar != null && !parentAadhar.isEmpty()) { parentAT=
		  getExtension(parentAadhar); parentAN = "Parent_Aadhar" + " " +
		  Instant.now().toEpochMilli(); parentA =
		  storageService.uploadFileOnAzure(parentAadhar,parentAN+"."+parentAT); }
		  
		  if (studentAadhar != null && !studentAadhar.isEmpty()) { studentAT =
		  getExtension(studentAadhar); studentAN = "Student_Aadhar" + " " +
		  Instant.now().toEpochMilli(); studentA =
		  storageService.uploadFileOnAzure(studentAadhar, studentAN+"."+studentAT ); }
		  
		  if (bankDoc != null && !bankDoc.isEmpty()) { bankDT = getExtension(bankDoc);
		  bankDN = "Bank_Doc" + " " + Instant.now().toEpochMilli() ; bankD =
		  storageService.uploadFileOnAzure(bankDoc, bankDN+"."+bankDT); }
		  
		  if (cast != null && !cast.isEmpty()) { castCT = getExtension(cast); castCN =
		  "Cast" + " " + Instant.now().toEpochMilli(); castC =
		  storageService.uploadFileOnAzure(cast, castCN+"."+castCT); }
		  
		  if (transferCertificate != null && !transferCertificate.isEmpty()) {
		  transferCT= getExtension(transferCertificate); transferCN =
		  "Transfer_Certificate" + " " + Instant.now().toEpochMilli(); transferC =
		  storageService.uploadFileOnAzure(transferCertificate,
		  transferCN+"."+transferCT); }
		  
		  if (profile != null && !profile.isEmpty()) { profilePT =
		  getExtension(profile); profilePN = "Profile" + " " +
		  Instant.now().toEpochMilli() ; profileP =
		  storageService.uploadFileOnAzure(profile, profilePN+"."+profilePT); }
		  
		  if (sssmid != null && !sssmid.isEmpty()) { sssmiT = getExtension(sssmid);
		  sssmiN = "SSSMID" + " " + Instant.now().toEpochMilli() ; sssmi =
		  storageService.uploadFileOnAzure(sssmid, sssmiN+"."+sssmiT); }
		  
		  GeneralAdmission createdAdmission = new GeneralAdmission(firstName,lastName,
		  gender,dateOfBirth, admissionClass,fatherName, motherName, mobileNo, email,
		  type, pen, birthC, lastR,parentA, studentA, sssmi, bankD, castC, transferC,
		  profileP); String subject = "New Admission Request";
		  
		  String body = "<html><body>" +
		  "<p>Dear Pragya School Admissions Committee,</p>" +
		  "<p>We are pleased to inform you that a new admission request has been received for the academic year 2024.</p>"
		  + "<p>Details of the applicant are as follows:</p>" + "<ul>" +
		  "<li>Full Name: " + firstName + " " + lastName + "</li>" + "<li>Gender: " +
		  gender + "</li>" + "<li>Date of Birth: " + dateOfBirth + "</li>" +
		  "<li>Class Applying For: " + admissionClass + "</li>" + "<li>Father's Name: "
		  + fatherName + "</li>" + "<li>Mother's Name: " + motherName + "</li>" +
		  "<li>Mobile Number: " + mobileNo + "</li>" + "<li>Email Address: " + email +
		  "</li>" + "<li>PEN: " + pen + "</li>" + "</ul>" +
		  "<p>Attached documents:</p>" + "<ul>" + "<li>Birth Certificate</li>" +
		  "<li>Previous Year's Result</li>" + "<li>Parent's Aadhar Card</li>" +
		  "<li>Student's Aadhar Card</li>" + "<li>Bank Documents</li>" +
		  "<li>Caste Certificate</li>" + "<li>Transfer Certificate</li>" +
		  "<li>Profile Picture</li>" + "</ul>" +
		  "<p>Please review the details and documents attached. If further information or clarification is required, please feel free to contact us.</p>"
		  + "<p>Thank you for considering this admission request.</p>" +
		  "<p>Best regards,<br/>" + firstName + " " + lastName + "<br/>" + mobileNo +
		  "</p>" + "</body></html>";
		  
		  
		  emailService.sendEmailWithAttachment(schoolEmail, subject, body,
		  birthCertificate, lastResult, parentAadhar, studentAadhar, bankDoc, cast,
		  transferCertificate, profile, sssmid);
		  
		  String userSubject = "Your Admission Enquiry"; String userBody =
		  "<html><body>" + "<p>Dear " + firstName + " " + lastName + ",</p>" +
		  "<p>Thank you for your admission enquiry. We have received your request and will get back to you shortly.</p>"
		  + "<p>Best regards,<br/>Pragya School Admissions Team</p>" +
		  "</body></html>";
		  
		  //emailService.sendEmail(email, userSubject, userBody);
		  
		  generalAdmissionRepository.save(createdAdmission);
		  
		 /* return new
		  ResponseEntity<>(generalAdmissionRepository.save(createdAdmission),
		  HttpStatus.OK);*/
		  
		  return new
				  ResponseEntity<>("Form Submitted successfully",
				  HttpStatus.OK);
		  
		  
		  }
		 
		
	
	}

	public String getExtension(MultipartFile file) {
		String originalFileName = file.getOriginalFilename();
		String ext = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
		return ext;
	}

}
