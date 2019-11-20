package ftn.project.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
	
	VerificationToken findByToken(String token);

	VerificationToken findByUser(User user);
}
