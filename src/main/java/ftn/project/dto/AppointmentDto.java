package ftn.project.dto;






import lombok.Data;

@Data
public class AppointmentDto {

	public Long idDto;
	public String dateDto;
	public String timeDto;
	public String roomDto;
	public String typeDto;
	public String priceDto;
	public Long doctorDto;
	public Long pacientId;
	public String discountDto;
	public Long roomId;
	public Long PatientIdDto;
	public boolean isBusyDto;
	public boolean isAcceptDto;
	

	public String operationTypeDto;

}