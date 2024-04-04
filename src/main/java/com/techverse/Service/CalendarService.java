package com.techverse.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techverse.Model.Achievement;
import com.techverse.Model.Calendar;
import com.techverse.Repository.AchievementRepository;
import com.techverse.Repository.CalendarRepository;

@Service
public class CalendarService {
	
	
	@Autowired
    private CalendarRepository calendarRepository;

    
    public List<Calendar> getAllCalendars() {
        return calendarRepository.findAll();
    }

    
    public Calendar createCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }
    
    
    public List<Calendar> getCalendarsByYear(String month,String year) {
        return calendarRepository.findByYearAndMonth(month,year);
    }
	
	

}
