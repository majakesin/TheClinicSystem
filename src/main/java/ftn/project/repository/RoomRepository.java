package ftn.project.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.Clinic;
import ftn.project.model.Room;

public interface RoomRepository extends JpaRepository<Room,Long>{

	public Room findAllById(Long idDto);

	public Set<Room> findByName(String nameDto);

	
	public Set<Room> findByNameAndHallNumber(String nameDto, String hallNumberDto);

	

	public Set<Room> findByHallNumber(String hallNumberDto);
	
	public Set<Room> findAllByFree(boolean isFree);
}
