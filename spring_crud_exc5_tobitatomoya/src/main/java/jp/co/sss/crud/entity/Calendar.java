package jp.co.sss.crud.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CALENDAR")
public class Calendar {
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cal_seq_gen")
	    @SequenceGenerator(name = "cal_seq_gen", sequenceName = "cal_seq", allocationSize = 1)
	    @Column(name = "CALENDAR_ID") 
	    private Integer calendarId;

	    @Column(name = "VISIT_DATE", nullable = false)
	    private Date visitDate;

	    @Column(name = "CLIENT", length = 100)
	    private String client;

	    @Column(name = "PIC", length = 30)
	    private String pic;

	    @Column(name = "EMP_ID", nullable = false)
	    private Integer empId;

		public Integer getCalendarId() {
			return calendarId;
		}

		public void setCalendarId(Integer calendarId) {
			this.calendarId = calendarId;
		}

		public Date getVisitDate() {
			return visitDate;
		}

		public void setVisitDate(Date visitDate) {
			this.visitDate = visitDate;
		}

		public String getClient() {
			return client;
		}

		public void setClient(String client) {
			this.client = client;
		}

		public String getPic() {
			return pic;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}

		public Integer getEmpId() {
			return empId;
		}

		public void setEmpId(Integer empId) {
			this.empId = empId;
		}


}
