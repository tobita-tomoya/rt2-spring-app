package jp.co.sss.crud.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Calendar;
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
	        model.addAttribute("selectedDate", date);
	        return "calendar/detail"; 
	    }
	    
	    @PostMapping("/calendar/register")
	    public String register(
	            @RequestParam("visitDate") String visitDate,
	            @RequestParam("client") String client,
	            @RequestParam("pic") String pic,
	            @SessionAttribute("loginUser") EmployeeBean loginUser) {

	        Calendar newSche = new Calendar();
	        newSche.setVisitDate(java.sql.Date.valueOf(visitDate));
	        newSche.setClient(client);
	        newSche.setPic(pic);
	        newSche.setEmpId(loginUser.getEmpId());

	        calendarRepository.save(newSche);

	        
	        return "redirect:/calendar/detail?date=" + visitDate;
	    }
	
}