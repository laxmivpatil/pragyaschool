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
	
	
	public SocialResponsibility createSocialResponsibility(String title,String subtitle,MultipartFile image) {
		String uniqueBlobName="";
		String path="";
		
		 
			 uniqueBlobName = "SocialResponsibility_" + UUID.randomUUID().toString().substring(0, 8);

		 
		 String originalFileName = image.getOriginalFilename();
         String ext = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);

         path = storageService.uploadFileOnAzure(image, uniqueBlobName + '.' + ext);
         
		 
			 
		 
		SocialResponsibility socialResponsibility=new SocialResponsibility();
		socialResponsibility.setTitle(title);
		socialResponsibility.setSubtitle(subtitle);
		socialResponsibility.setImage(path);
		SocialResponsibility savedsocialResponsibility=socialResponsibilityRepository.save(socialResponsibility);
		return savedsocialResponsibility;
		 
	}
	
	
	public List<SocialResponsibility> getAllsocialResponsibility()
	{
		return socialResponsibilityRepository.findAll();
	}

}
