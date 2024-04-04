package com.techverse.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techverse.Model.Achievement;
import com.techverse.Model.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

	
	@Query("SELECT c FROM Calendar c WHERE c.month = :month AND c.year = :year")
	List<Calendar> findByYearAndMonth(@Param("month") String month, @Param("year") String year);

}
