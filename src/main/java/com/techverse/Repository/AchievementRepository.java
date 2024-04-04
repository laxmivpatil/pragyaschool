package com.techverse.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techverse.Model.Achievement;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
	
	
	List<Achievement> findByYear(String year);

}
