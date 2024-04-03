package com.techverse.Response;

import java.util.List;

import com.techverse.Model.GeneralAdmission;
import com.techverse.Model.UserForm;

public class GeneralAdmissionResponse {

	private List<GeneralAdmission> generalAdmissionList;

	public List<GeneralAdmission> getGeneralAdmissionList() {
		return generalAdmissionList;
	}

	public void setGeneralAdmissionList(List<GeneralAdmission> generalAdmissionList) {
		this.generalAdmissionList = generalAdmissionList;
	}

	public GeneralAdmissionResponse(List<GeneralAdmission> generalAdmissionList) {
		super();
		this.generalAdmissionList = generalAdmissionList;
	}
	
	
	
}
