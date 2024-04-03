package com.techverse.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GeneralAdmission {
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String admissionClass;
    private String fatherName;
    private String motherName;
    private String mobileNo;
    private String email;
    
    
    private String type;
    
    
    private String PEN;
    private String birthCertificate;
    private String lastResult;
    private String parentAadhar;
    private String studentAadhar;
    private String SSSMID;
    private String bankDoc;
    private String cast;
    private String transferCertificate;
    private String profile;
    
    
    
    
    
    
    
    public GeneralAdmission(Long id, String firstName, String lastName, String gender, String dateOfBirth,
			String admissionClass, String fatherName, String motherName, String mobileNo, String email, String type,
			String pEN, String birthCertificate, String lastResult, String parentAadhar, String studentAadhar,
			String sSSMID, String bankDoc, String cast, String transferCertificate, String profile) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.admissionClass = admissionClass;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.type = type;
		PEN = pEN;
		this.birthCertificate = birthCertificate;
		this.lastResult = lastResult;
		this.parentAadhar = parentAadhar;
		this.studentAadhar = studentAadhar;
		SSSMID = sSSMID;
		this.bankDoc = bankDoc;
		this.cast = cast;
		this.transferCertificate = transferCertificate;
		this.profile = profile;
	}
    public GeneralAdmission( String firstName, String lastName, String gender, String dateOfBirth,
			String admissionClass, String fatherName, String motherName, String mobileNo, String email, String type,
			String pEN, String birthCertificate, String lastResult, String parentAadhar, String studentAadhar,
			String sSSMID, String bankDoc, String cast, String transferCertificate, String profile) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.admissionClass = admissionClass;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.type = type;
		PEN = pEN;
		this.birthCertificate = birthCertificate;
		this.lastResult = lastResult;
		this.parentAadhar = parentAadhar;
		this.studentAadhar = studentAadhar;
		SSSMID = sSSMID;
		this.bankDoc = bankDoc;
		this.cast = cast;
		this.transferCertificate = transferCertificate;
		this.profile = profile;
	}

	public GeneralAdmission() {
		// TODO Auto-generated constructor stub
	}
    
	public GeneralAdmission(Long id, String firstName, String lastName, String gender, String dateOfBirth,
			String admissionClass, String fatherName, String motherName, String mobileNo, String email,String type) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.admissionClass = admissionClass;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.type=type;
	}
	
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPEN() {
		return PEN;
	}

	public void setPEN(String pEN) {
		PEN = pEN;
	}

	public String getBirthCertificate() {
		return birthCertificate;
	}

	public void setBirthCertificate(String birthCertificate) {
		this.birthCertificate = birthCertificate;
	}

	public String getLastResult() {
		return lastResult;
	}

	public void setLastResult(String lastResult) {
		this.lastResult = lastResult;
	}

	public String getParentAadhar() {
		return parentAadhar;
	}

	public void setParentAadhar(String parentAadhar) {
		this.parentAadhar = parentAadhar;
	}

	public String getStudentAadhar() {
		return studentAadhar;
	}

	public void setStudentAadhar(String studentAadhar) {
		this.studentAadhar = studentAadhar;
	}

	public String getSSSMID() {
		return SSSMID;
	}

	public void setSSSMID(String sSSMID) {
		SSSMID = sSSMID;
	}

	public String getBankDoc() {
		return bankDoc;
	}

	public void setBankDoc(String bankDoc) {
		this.bankDoc = bankDoc;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getTransferCertificate() {
		return transferCertificate;
	}

	public void setTransferCertificate(String transferCertificate) {
		this.transferCertificate = transferCertificate;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAdmissionClass() {
		return admissionClass;
	}
	public void setAdmissionClass(String admissionClass) {
		this.admissionClass = admissionClass;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

    
    
    
    
}
