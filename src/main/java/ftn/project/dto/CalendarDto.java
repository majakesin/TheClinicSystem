package ftn.project.dto;

import lombok.Data;

@Data
public class CalendarDto {

	private Long calendarId;
	private Long medicalId;
	private String title;
	private String start;
	private String end;
	private String url;
}
