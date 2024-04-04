package com.techverse.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.Model.SocialResponsibility;
import com.techverse.Repository.SocialResponsibilityRepository;

@Service
public class SocialResponsibilityService {
	
	
	@Autowired
	private StorageSevice storageService;
	
	@Autowired
	private SocialResponsibilityRepository  socialResponsibilityRepository;
	
	
	public SocialResponsibility createSocialResponsibility(String title,MultipartFile image) {
		String uniqueBlobName="";
		String path="";
		
		if(image!=null  && !image.isEmpty()) {
		 uniqueBlobName = "SocialResponsibility_"+UUID.randomUUID().toString();
			  path=storageService.uploadFileOnAzure(image, uniqueBlobName);
		}
		
		SocialResponsibility socialResponsibility=new SocialResponsibility();
		socialResponsibility.setTitle(title);
		socialResponsibility.setImage(path);
		SocialResponsibility savedsocialResponsibility=socialResponsibilityRepository.save(socialResponsibility);
		return savedsocialResponsibility;
		 
	}
	
	
	public List<SocialResponsibility> getAllsocialResponsibility()
	{
		return socialResponsibilityRepository.findAll();
	}

}
