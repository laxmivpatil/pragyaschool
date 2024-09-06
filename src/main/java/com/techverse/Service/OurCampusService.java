package com.techverse.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.Model.OurCampus; 
import com.techverse.Repository.OurCampusRepository; 

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
		 uniqueBlobName = "OurCampus_"+UUID.randomUUID().toString();
		 String originalFileName = image.getOriginalFilename();
			String ext = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
		 
		 
			  path=storageService.uploadFileOnAzure(image, uniqueBlobName+'.'+ext);
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

	 public OurCampus updateOurCampusImage(Long campusId, MultipartFile image) {
	        OurCampus ourCampus = ourCampusRepository.findById(campusId)
	            .orElseThrow(() -> new RuntimeException("OurCampus not found with id " + campusId));

	        if (image != null && !image.isEmpty()) {
	            String uniqueBlobName = "OurCampus_" + UUID.randomUUID().toString();
	            String originalFileName = image.getOriginalFilename();
	            String ext = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);

	            String path = storageService.uploadFileOnAzure(image, uniqueBlobName + '.' + ext);
	            ourCampus.setImage(path);
	        }

	        return ourCampusRepository.save(ourCampus);
	    }
	
	
}
