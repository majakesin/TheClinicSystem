package ftn.project.services_impl;

import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ftn.project.dto.UserDto;
import ftn.project.mapper.UserMapper;
import ftn.project.model.User;
import ftn.project.model.VerificationToken;
import ftn.project.repository.UserRepository;
import ftn.project.repository.VerificationTokenRepository;
import ftn.project.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	protected final Log LOGGER = LogFactory.getLog(getClass());

	// user service
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	// spring security
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	// email generate
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	public void createUser(UserDto userDto) {
		userRepository.save(userMapper.DtoToUser(userDto));
	}

	@Override
	public void deleteUser(Long idDto) {

		userRepository.deleteById(idDto);
	}

	public Set<UserDto> allUsers() {
		return userMapper.UserToDtoSet(userRepository.findAll());
	}

	@Override
	public Set<UserDto> allMedicalStaff() {
		// TODO Auto-generated method stub
		return userMapper.UserToDtoSet(userRepository.findAll());
	}

	@Override

	public UserDto getUserById(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	
	


	public void changePassword(String oldPassword, String newPassword) {

		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();

		if (authenticationManager != null) {
			LOGGER.debug("Re-authenticating user '" + username + "' for password change request.");

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		} else {
			LOGGER.debug("No authentication manager set. can't change Password!");

			return;
		}

		LOGGER.debug("Changing password for user '" + username + "'");

		User user = (User) loadUserByUsername(username);

		// pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
		// ne zelimo da u bazi cuvamo lozinke u plain text formatu
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);

	}



	@Override
	public void createVerificationToken(User user, String token) {
		// TODO Auto-generated method stub

	}

	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String verificationToken) {
		return verificationTokenRepository.findByToken(verificationToken).getUser();
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/*
	 * 
	 * public Set<UserDto> allMedicalStaff() {
	 * 
	 * 
	 * 
	 * List<User> sviKorisnici = userRepository.findAll();
	 * 
	 * System.out.println("bio sam ovdje");
	 * 
	 * for (User user : sviKorisnici) {
	 * 
	 * System.out.println("korisnik:"+user.getName());
	 * 
	 * if(!user.getRole().equals("doktor")) {
	 * 
	 * sviKorisnici.remove(user); }
	 * 
	 * } if(sviKorisnici.isEmpty()) {
	 * 
	 * return userMapper.UserToDtoSet(userRepository.findAll()); } else { return
	 * userMapper.UserToDtoSet(sviKorisnici); } }
	 */
}
