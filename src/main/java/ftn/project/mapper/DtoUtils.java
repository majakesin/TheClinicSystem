package ftn.project.mapper;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import ftn.project.dto.DtoEntity;
import lombok.Data;

@Component
@Data
public class DtoUtils {
	 
	
	  public DtoEntity convertToDto(Object obj, DtoEntity mapper) {
	    return new ModelMapper().map(obj, mapper.getClass());
	  }
	 
	  public Object convertToEntity(Object obj, DtoEntity mapper) {
	    return new ModelMapper().map(mapper, obj.getClass());
	  }
	  
}
