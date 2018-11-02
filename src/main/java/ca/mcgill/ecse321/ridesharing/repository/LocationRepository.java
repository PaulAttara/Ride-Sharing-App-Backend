package ca.mcgill.ecse321.ridesharing.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ridesharing.model.*;

@Repository
public class LocationRepository {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Location createLocation(String city, String street, double price, int routeId) {
		Location location = new Location();
		location.setCity(city);
		location.setStreet(street);
		location.setPrice(price);
		Route route = em.find(Route.class, routeId);
		location.setRoute(route);
		em.persist(location);
		return location;
	}
	
	@Transactional
	public boolean addPassenger(String username, int routeId, int locationId){
		User passenger = em.find(User.class, username);
		if(passenger == null) {
			return false;
		}
		Location location = getLocation(locationId);
		Route route = location.getRoute();
	
		if(route.getRouteId() != routeId) {
			return false;
		}
		int seatsAvailable = route.getSeatsAvailable();
		seatsAvailable--;
		route.setSeatsAvailable(seatsAvailable);
		
		if(seatsAvailable < 0) {
			route.setSeatsAvailable(0);
			return false;
		}
		
		location.setPassenger(passenger);
		em.persist(location);
		em.persist(route);
		return true;
	}
	
	@Transactional
	public Location getLocation(int id) {
		return em.find(Location.class, id);
	}

	@Transactional
	public boolean assignToRoute(int locationId, int routeId) {
		Route route = em.find(Route.class, routeId);
		if(route == null) {
			return false;
		}
		Location stop = getLocation(locationId);
		stop.setRoute(route);
		em.persist(stop);
		return true;
	}

	@Transactional
	public List<Location> getStops(int routeId) {
		Query query_stops = em.createNativeQuery("select * from locations where route_routeid = '"+routeId+"';");
		@SuppressWarnings("unchecked")
		List<Location> stops = (List<Location>) query_stops.getResultList();
		return stops;
	}
}