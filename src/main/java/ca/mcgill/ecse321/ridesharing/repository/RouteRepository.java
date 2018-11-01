package ca.mcgill.ecse321.ridesharing.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.ridesharing.model.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Repository
public class RouteRepository {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public Route createRoute(int seatsAvailable, int carId, String date, String time) {
		Route route = new Route();
		route.setSeatsAvailable(seatsAvailable);
		Car car = em.find(Car.class, carId);
		route.setCar(car);
		//test for push 3
		//date must be in format yyyy-mm-dd hh:mm
		Timestamp anyDate = Timestamp.valueOf(date + " " + time + ":00");
		route.setDate(anyDate);
		em.persist(route);
		Set<Route> routes = car.getRoute();
		routes.add(route);
		em.persist(car);
		return route;
	}
	
	@Transactional
	public Route getRoute(int id) {
		return em.find(Route.class, id);
	}

	@Transactional
	public List<Route> getRoutesForDriver(String username) {
		User driver = em.find(User.class, username);
		Set<Route> routes = driver.getCar().getRoute();
		List<Route> routeList = new ArrayList<Route>();
		routeList.addAll(routes);
		return routeList;
	}
}
