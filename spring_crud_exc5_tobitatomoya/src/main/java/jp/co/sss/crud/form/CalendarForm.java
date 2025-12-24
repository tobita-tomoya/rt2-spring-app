package jp.co.sss.crud.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CalendarForm {
	
	    private Integer calendarId;

	    @NotBlank(message = "訪問日を入力してください")
	    private String visitDate;

	    @NotBlank(message = "営業先を入力してください")
	    @Size(max = 50, message = "顧客名は50文字以内で入力してください")
	    private String client;
	    
	    @NotBlank(message = "担当者名を入力してください")
	    @Size(max = 30, message = "担当者名は30文字以内で入力してください")
	    private String pic;

		public Integer getCalendarId() {
			return calendarId;
		}

		public void setCalendarId(Integer calendarId) {
			this.calendarId = calendarId;
		}

		public String getVisitDate() {
			return visitDate;
		}

		public void setVisitDate(String visitDate) {
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
	    

}
