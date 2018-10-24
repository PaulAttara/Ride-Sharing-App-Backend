package ca.mcgill.ecse321.ridesharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("ca.mcgill.ecse321.ridesharing.model")
@SpringBootApplication(scanBasePackages = "ca.mcgill.ecse321.*")
public class RideSharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideSharingApplication.class, args);
	}
}
