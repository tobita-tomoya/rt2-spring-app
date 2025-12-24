package jp.co.sss.crud.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.validation.Valid;
import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Calendar;
import jp.co.sss.crud.form.CalendarForm;
import jp.co.sss.crud.repository.CalendarRepository;

@Controller
public class CalendarController {
	
	@Autowired
    CalendarRepository calendarRepository; 

	  @RequestMapping("/calendar")
	    public String showCalendar(
	            @SessionAttribute(name = "loginUser", required = false) EmployeeBean loginUser,
	            Model model) {
	       
	        if (loginUser == null || loginUser.getDeptId() != 1) {
	            return "redirect:/list";
	        }

	        
	        List<Calendar> mySchedules = calendarRepository.findByEmpIdOrderByVisitDateAsc(loginUser.getEmpId());
	        model.addAttribute("schedules", mySchedules);

	        return "calendar/view"; 
	    }
	    
	    @RequestMapping("/calendar/detail")
	    public String showDetail(@RequestParam("date") String date, Model model) {
	    	CalendarForm form = new CalendarForm();
	        form.setVisitDate(date);
	    	model.addAttribute("calendarForm", form);
	        model.addAttribute("selectedDate", date);
	        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
	        List<Calendar> scheduleList = calendarRepository.findByVisitDate(sqlDate); 
	        model.addAttribute("scheduleList", scheduleList);
	        return "calendar/detail"; 
	    }
	    @RequestMapping(path="/calendar/register", method=RequestMethod.POST)
public String register(
		@Valid @ModelAttribute("calendarForm") CalendarForm form, 
        BindingResult result,                     
        @SessionAttribute(name="loginUser", required = false) EmployeeBean loginUser,
        Model model) {

          if (loginUser == null) return "redirect:/login";

          if (result.hasErrors()) {
    	  model.addAttribute("selectedDate", form.getVisitDate());
          java.sql.Date sqlDate = java.sql.Date.valueOf(form.getVisitDate());
          List<Calendar> scheduleList = calendarRepository.findByVisitDate(sqlDate); 
          model.addAttribute("scheduleList", scheduleList);
        
      
          return "calendar/detail"; 
    }

   
          Calendar newSche = new Calendar();
          newSche.setVisitDate(java.sql.Date.valueOf(form.getVisitDate()));
          newSche.setClient(form.getClient());
          newSche.setPic(form.getPic());
          newSche.setEmpId(loginUser.getEmpId());
          calendarRepository.save(newSche);

   
          return "redirect:/calendar/detail?date=" + form.getVisitDate();
}
	    @RequestMapping(path="/calendar/register", method=RequestMethod.GET)
public String registerGet() {
          return "redirect:/calendar";
}
	
}