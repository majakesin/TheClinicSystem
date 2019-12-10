package ftn.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.Clinic;
import ftn.project.model.Room;

public interface RoomRepository extends JpaRepository<Room,Long>{

	public Room findAllById(Long idDto);
}
