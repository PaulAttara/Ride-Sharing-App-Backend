package ca.mcgill.ecse321.ridesharing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.ridesharing.controller.*;
import ca.mcgill.ecse321.ridesharing.repository.CarRepository;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {
	
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	CarController carController;

	CarRepository carRepository;

	@Test
	public void getVehicle() {
		Car aCar = new Car();
		User aDriver = new Driver();
		aDriver.setUser();
		aCar.setBrand("Mazda");


		List<Arrival> allCars = singletonList(allCars);

		given(arrivalController.getAllArrivals()).willReturn(allCars);

		mvc.perform(get(VERSION + ARRIVAL + "all")
				.with(user("blaze").password("Q1w2e3r4"))
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].city", is(arrival.getCity())));
	}

	@Test
	public void testParticipant() {
		
	}

}
