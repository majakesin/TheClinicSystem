package ftn.project.services;



import ftn.project.dto.UserDto;
import ftn.project.model.User;
import ftn.project.model.VerificationToken;

public interface IUserService {

	User getUser(String verificationToken);

	void createVerificationToken(User user, String token);

	VerificationToken getVerificationToken(String VerificationToken);
}
