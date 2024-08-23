package com.techverse.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.Model.Achievement;
import com.techverse.Response.AchievementResponse;
import com.techverse.Response.CommonResponse;
import com.techverse.Service.AchievementService;
import com.techverse.Service.StorageSevice;

@RestController
@RequestMapping("/achievement")
public class AchievementController {
	
	@Autowired
	private StorageSevice storageService;
	
	 @Autowired
	    private AchievementService achievementService;

	    @GetMapping("/")
	    public ResponseEntity< AchievementResponse > getAllAchievements() {
	        List<Achievement> achievements = achievementService.getAllAchievements();
	        
	        AchievementResponse response =new AchievementResponse();
	        response.setAchievements(achievements);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	    @PostMapping("/")
	    public ResponseEntity<CommonResponse> createAchievement( @RequestPart(value = "title", required = false) String title, @RequestPart(value = "subtitle", required = false) String subtitle,
	    		@RequestPart(value = "year", required = false) String year,	@RequestPart(value = "image", required = false) MultipartFile image) {
	    	String uniqueBlobName="";
			String path="";
			
			if(image!=null  && !image.isEmpty()) {
			 uniqueBlobName = "achievemnet_"+year+"_"+UUID.randomUUID().toString();
			 String originalFileName = image.getOriginalFilename();
				String ext = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
			 
			 
				  path=storageService.uploadFileOnAzure(image, uniqueBlobName+'.'+ext);
		
			}
			Achievement achievement=new Achievement();
	    	achievement.setImage(path);
	    	achievement.setTitle(title);
	    	achievement.setSubtitle(subtitle);
	    	achievement.setYear(year);
	    	
	        Achievement createdAchievement = achievementService.createAchievement(achievement);
	        
	        CommonResponse response=new CommonResponse();
			response.setMessage("Achievement added Successfully");
			
			 return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	
	    @GetMapping("/year/{year}")
	    public ResponseEntity<AchievementResponse> getAchievementsByYear(@PathVariable String year) {
	        List<Achievement> achievements = achievementService.getAchievementsByYear(year);
	        
	        AchievementResponse response =new AchievementResponse();
	        response.setAchievements(achievements);
	        
	        return new ResponseEntity<>(response, HttpStatus.OK);
	        
	    }
	    
	    @PutMapping("/{id}/image")
	    public ResponseEntity<CommonResponse> updateAchievementImage(
	            @PathVariable("id") Long id, 
	            @RequestPart("image") MultipartFile image) {
	        
	       

	        // Generate a unique name for the new image
	        String uniqueBlobName = "achievement_" + UUID.randomUUID().toString();
	        String originalFileName = image.getOriginalFilename();
	        String ext = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
	        String path = storageService.uploadFileOnAzure(image, uniqueBlobName + '.' + ext);

	        // Update the achievement image in the database
	        Achievement updatedAchievement = achievementService.updateAchievementImage(id, path);
	        CommonResponse response=new CommonResponse();
			response.setMessage("Achievement updated Successfully");
			
			 return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	    
}
