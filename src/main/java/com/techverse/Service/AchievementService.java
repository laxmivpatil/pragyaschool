package com.techverse.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techverse.Model.Achievement;
import com.techverse.Repository.AchievementRepository;

@Service
public class AchievementService {
	
	
	
	@Autowired
    private AchievementRepository achievementRepository;

    
    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    
    public Achievement createAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }
    
    
    public List<Achievement> getAchievementsByYear(String year) {
        return achievementRepository.findByYear(year);
    }
    
    public Achievement updateAchievementImage(Long id, String imagePath) {
        Optional<Achievement> optionalAchievement = achievementRepository.findById(id);
        
        if (optionalAchievement.isPresent()) {
            Achievement achievement = optionalAchievement.get();
            achievement.setImage(imagePath);
            return achievementRepository.save(achievement);
        }
        
        return null;
    }
    
    
    

}
