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
	public String discountDto;
	
}