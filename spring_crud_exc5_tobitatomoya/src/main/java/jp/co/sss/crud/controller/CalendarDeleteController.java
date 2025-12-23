package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.entity.Calendar;
import jp.co.sss.crud.repository.CalendarRepository;
@Controller
public class CalendarDeleteController {
	
	@Autowired
	 CalendarRepository  calendarRepository;
	
	@RequestMapping(path = "/delete/calendar/check", method = RequestMethod.GET)
	public String checkDelete(Integer calendarId, Model model) {

		Calendar schedule = calendarRepository.findByCalendarId(calendarId);
		
		model.addAttribute("schedules", schedule);
		
		return "calendar/delete_calendar_check";
	}
	@RequestMapping(path = "/delete/calendar/complete", method = RequestMethod.POST)
	public String executeDelete(Integer calendarId) {
	    
	    calendarRepository.deleteById(calendarId);
	    
	    return "redirect:/delete/calendar/complete"; 
	}
	@RequestMapping(path = "/delete/calendar/complete", method = RequestMethod.GET)
	public String completeDelete() {
		return "delete/delete_calendar_complete";
	}


}
