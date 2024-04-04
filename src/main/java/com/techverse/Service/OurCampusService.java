package com.techverse.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.Model.OurCampus;
import com.techverse.Model.SocialResponsibility;
import com.techverse.Repository.OurCampusRepository;
import com.techverse.Repository.SocialResponsibilityRepository;

@Service
public class OurCampusService {

	
	
	
	@Autowired
	private StorageSevice storageService;
	
	@Autowired
	private OurCampusRepository  ourCampusRepository;
	
	
	public OurCampus createOurCampus(String title,MultipartFile image) {
		String uniqueBlobName="";
		String path="";
		
		if(image!=null  && !image.isEmpty()) {
		 uniqueBlobName = "SocialResponsibility_"+UUID.randomUUID().toString();
			  path=storageService.uploadFileOnAzure(image, uniqueBlobName);
		}
		
		OurCampus ourCampus=new OurCampus();
		ourCampus.setTitle(title);
		ourCampus.setImage(path);
		OurCampus savedOurCampus=ourCampusRepository.save(ourCampus);
		return savedOurCampus;
		 
	}
	
	
	public List<OurCampus> getAllOurCampus()
	{
		return ourCampusRepository.findAll();
	}

}
