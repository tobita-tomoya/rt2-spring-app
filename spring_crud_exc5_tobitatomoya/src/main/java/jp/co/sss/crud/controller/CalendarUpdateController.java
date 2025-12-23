package jp.co.sss.crud.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Calendar;
import jp.co.sss.crud.form.CalendarForm;
import jp.co.sss.crud.repository.CalendarRepository;

@Controller
public class CalendarUpdateController {
	@Autowired
	 CalendarRepository  calendarRepository;
	
	@RequestMapping(path="/update/calendar/input", method = RequestMethod.GET)
	public String input(Integer calendarId, Model model) {
		 Calendar calendar = calendarRepository.findByCalendarId(calendarId);
		CalendarForm form = new CalendarForm();
	    form.setCalendarId(calendar.getCalendarId());
	    form.setVisitDate(calendar.getVisitDate().toString()); 
	    form.setClient(calendar.getClient());
	    form.setPic(calendar.getPic());

	    model.addAttribute("calendarForm", form);
	    return "calendar/update_calendar";
	}
	@RequestMapping(path="/update/calendar/check", method = RequestMethod.POST)
    public String check(CalendarForm form, Model model) {
        model.addAttribute("calendarForm", form);
        return "calendar/update_calendar_check";
    }
	@RequestMapping(path="/update/calendar/execute", method = RequestMethod.POST)
	public String execute(CalendarForm form, @SessionAttribute("loginUser") EmployeeBean loginUser) {
		if (loginUser == null) {
	        return "redirect:/";
	    }
	  
		Calendar calendar = calendarRepository.findByCalendarId(form.getCalendarId());
		 
	    if (calendar != null) {
	       
	        calendar.setVisitDate(java.sql.Date.valueOf(form.getVisitDate()));
	        calendar.setClient(form.getClient());
	        calendar.setPic(form.getPic());
	        
	        calendar.setEmpId(loginUser.getEmpId());

	        calendarRepository.save(calendar);
	    }
	    return "redirect:/calendar";
	}
	
}
		
	
	
	


