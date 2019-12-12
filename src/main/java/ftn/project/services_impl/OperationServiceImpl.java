package ftn.project.services_impl;

import org.springframework.stereotype.Service;

import ftn.project.services.EmailService;
import ftn.project.services.OperationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

	private final EmailService emailService;
	
	@Override
	public void scheduleOperation() {
		// TODO Auto-generated method stub
		emailService.sendMail("prolomercegovac@yahoo.com");
	}

	

}
