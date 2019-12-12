package ftn.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TheClinicSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheClinicSystemApplication.class, args);
	}

}
