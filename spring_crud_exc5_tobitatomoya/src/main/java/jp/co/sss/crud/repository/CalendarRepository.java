package jp.co.sss.crud.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.crud.entity.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
    
    
    List<Calendar> findByEmpIdOrderByVisitDateAsc(Integer empId);
    
    List<Calendar> findByVisitDateAndEmpId(Date visitDate, Integer empId);
    
    Calendar findByCalendarId(Integer calendarId);
}