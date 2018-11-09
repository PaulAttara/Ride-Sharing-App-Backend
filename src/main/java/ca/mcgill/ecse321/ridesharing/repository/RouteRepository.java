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
	public Route createRoute(int seatsAvailable, int carId, String date, String startLocation, String time) {
		Route route = new Route();
		route.setSeatsAvailable(seatsAvailable);
		Car car = em.find(Car.class, carId);
		route.setCar(car);
		//test for push 3
		//date must be in format yyyy-mm-dd hh:mm
		Timestamp anyDate = Timestamp.valueOf(date + " " + time + ":00");
		route.setDate(anyDate);
		route.setStartLocation(startLocation);
		em.persist(route);
//		Set<Route> routes = car.getRoute();
//		routes.add(route);
//		em.persist(car);
		//test
		return route;
	}
	
	@Transactional
	public Route getRoute(int id) {
		return em.find(Route.class, id);
	}

	@Transactional
	public List<Route> getRoutesForDriver(String username) {
		Car car = em.createQuery("select e from Car e where driver_username = '"+username+"'", Car.class).getSingleResult();
		int carId = car.getVehicleId();
		TypedQuery<Route> query = em.createQuery("select e from Route e where car_vehicleid = '"+carId+"'", Route.class);
		List<Route> routes = query.getResultList();
		return routes;
	}

	@Transactional
	public boolean removeRoute(int id) {
		int dependancy = em.createQuery("delete from Location e where route_routeid = '" + id + "'").executeUpdate();
		int rowsDeleted = em.createQuery("delete from Route e where routeid = '" + id + "'").executeUpdate();
		if(rowsDeleted == 1 && dependancy >=0) {
			return true;
		}
		return false;
	}

	@Transactional
	public boolean updateRoute(int id, String date, String time) {
		Timestamp anyDate = Timestamp.valueOf(date + " " + time + ":00");
		Route route = getRoute(id);
		route.setDate(anyDate);
		em.persist(route);
		
		return true;
	}
	
	@Transactional
	public List<Location> getStops(int id) {
		TypedQuery<Location> query = em.createQuery("select e from Location e where route_routeid = '"+id+"'", Location.class);
		List<Location> stops = query.getResultList();
		return stops;
	}

}