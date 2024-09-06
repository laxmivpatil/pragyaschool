package com.techverse.Service;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import com.techverse.Model.UserForm;
import com.techverse.Repository.UserFormRepository;
@Service
public class UserService {
	 
	 @Autowired
	    private UserFormRepository userFormRepository;
	
	 
	 
	 public List<UserForm> getAllUserForms() {
	        return userFormRepository.findAll();
	    }

	    public UserForm createUserForm(UserForm userForm) {
	        return userFormRepository.save(userForm);
	    }

}
