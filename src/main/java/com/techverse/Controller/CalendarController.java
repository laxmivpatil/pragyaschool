package com.techverse.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techverse.Model.Achievement;
import com.techverse.Model.Calendar;
import com.techverse.Response.AchievementResponse;
import com.techverse.Response.CalendarResponse;
import com.techverse.Response.CommonResponse;
import com.techverse.Service.AchievementService;
import com.techverse.Service.CalendarService;
import com.techverse.Service.StorageSevice;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
	
	
	@Autowired
	private StorageSevice storageService;
	
	 @Autowired
	    private CalendarService calendarService;

	    @GetMapping("/")
	    public ResponseEntity< CalendarResponse > getAllCalendar() {
	        List<Calendar> calendars = calendarService.getAllCalendars();
	        
	        CalendarResponse response =new CalendarResponse();
	        response.setCalendars(calendars);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	    @PostMapping("/")
	    public ResponseEntity<CommonResponse> createCalendar( @RequestPart(value = "eventName", required = false) String eventName ,@RequestPart(value = "location", required = false) String location, @RequestPart(value = "day", required = false) String day,
	    		@RequestPart(value = "month", required = false) String month,@RequestPart(value = "year", required = false) String year,	@RequestPart(value = "image", required = false) MultipartFile image) {
	    	String uniqueBlobName="";
			String path="";
			
			if(image!=null  && !image.isEmpty()) {
			 uniqueBlobName = "calendar_"+year+"_"+UUID.randomUUID().toString();
				  path=storageService.uploadFileOnAzure(image, uniqueBlobName);
			}
			Calendar calendar =new Calendar(); 
			calendar.setImage(path);
	    	 calendar.setDay(day);
	    	 calendar.setLocation(location);
	    	 calendar.setMonth(month);
	    	 calendar.setYear(year);
	    	 calendar.setEventName(eventName);
	    	 Calendar createdCalendar = calendarService.createCalendar(calendar);
	        
	        CommonResponse response=new CommonResponse();
			response.setMessage("Calendar added Successfully");
			
			 return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	
	    @GetMapping("/month")
	    public ResponseEntity<CalendarResponse> getCalendarByYear(@RequestParam String month,@RequestParam String year) {
	        List<Calendar> calendars = calendarService.getCalendarsByYear(month,year);
	        
	        CalendarResponse response =new CalendarResponse();
	        response.setCalendars(calendars);
	        
	        return new ResponseEntity<>(response, HttpStatus.OK);
	        
	    }

}
