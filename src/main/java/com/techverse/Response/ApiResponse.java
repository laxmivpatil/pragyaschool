package com.techverse.Response;

import java.util.List;

import com.techverse.Model.UserForm;

public class ApiResponse {
	
	  private List<UserForm> userForms;

	public List<UserForm> getUserForms() {
		return userForms;
	}

	public void setUserForms(List<UserForm> userForms) {
		this.userForms = userForms;
	}

	public ApiResponse(List<UserForm> userForms) {
		super();
		this.userForms = userForms;
	}
	  
	  
	  
	  
}
