package jp.co.sss.crud.form;

import jakarta.validation.constraints.NotBlank;

public class CalendarForm {
	
	    private Integer calendarId;

	    @NotBlank(message = "訪問日を入力してください")
	    private String visitDate;

	    @NotBlank(message = "顧客名を入力してください")
	    private String client;

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
