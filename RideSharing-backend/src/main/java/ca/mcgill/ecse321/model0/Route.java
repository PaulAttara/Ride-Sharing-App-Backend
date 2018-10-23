/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse321.model0;
import javax.persistence.*;

// line 28 "../../../../rideSharing.ump"
@Entity
@Table(name = "routes")
@NamedQueries({
    @NamedQuery(name = "Route.findAll", query = "SELECT e FROM Route e")
})
public class Route
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Route Attributes
  private String city;
  private String street;
  private int seatsAvail;
  private int routeId;

  //Route Associations
  private User driver;
  private Car car;
  private Request request;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Route(User aDriver, Car aCar, Request aRequest)
  {
    city = null;
    street = null;
    if (aDriver == null || aDriver.getRoute() != null)
    {
      throw new RuntimeException("Unable to create Route due to aDriver");
    }
    driver = aDriver;
    boolean didAddCar = setCar(aCar);
    if (!didAddCar)
    {
      throw new RuntimeException("Unable to create route due to car");
    }
    if (aRequest == null || aRequest.getRoute() != null)
    {
      throw new RuntimeException("Unable to create Route due to aRequest");
    }
    request = aRequest;
  }

  public Route(Car aCarForDriver, Request aRequestForDriver, Car aCar, User aPassengerForRequest)
  {
    city = null;
    street = null;
    driver = new User(aCarForDriver, this, aRequestForDriver);
    boolean didAddCar = setCar(aCar);
    if (!didAddCar)
    {
      throw new RuntimeException("Unable to create route due to car");
    }
    request = new Request(aPassengerForRequest, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }

  public boolean setStreet(String aStreet)
  {
    boolean wasSet = false;
    street = aStreet;
    wasSet = true;
    return wasSet;
  }

  public boolean setSeatsAvail(int aSeatsAvail)
  {
    boolean wasSet = false;
    seatsAvail = aSeatsAvail;
    wasSet = true;
    return wasSet;
  }
  
  public boolean setRouteId(int aRouteId)
  {
    boolean wasSet = false;
    routeId = aRouteId;
    wasSet = true;
    return wasSet;
  }

  @Column
  public String getCity()
  {
    return city;
  }

  @Column
  public String getStreet()
  {
    return street;
  }

  @Column
  public int getSeatsAvail()
  {
    return seatsAvail;
  }
  
  @Id
  @Column 
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getRouteId()
  {
    return routeId;
  }
  /* Code from template association_GetOne */
  @OneToOne
  @JoinColumn(name ="username")
  public User getDriver()
  {
    return driver;
  }
  /* Code from template association_GetOne */
  @OneToOne
  @JoinColumn(name="vehicleid")
  public Car getCar()
  {
    return car;
  }
  /* Code from template association_GetOne */
  @OneToOne
  @JoinColumn(name="requestid")
  public Request getRequest()
  {
    return request;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setCar(Car aNewCar)
  {
    boolean wasSet = false;
    if (aNewCar == null)
    {
      //Unable to setCar to null, as route must always be associated to a car
      return wasSet;
    }
    
    Route existingRoute = aNewCar.getRoute();
    if (existingRoute != null && !equals(existingRoute))
    {
      //Unable to setCar, the current car already has a route, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Car anOldCar = car;
    car = aNewCar;
    car.setRoute(this);

    if (anOldCar != null)
    {
      anOldCar.setRoute(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    User existingDriver = driver;
    driver = null;
    if (existingDriver != null)
    {
      existingDriver.delete();
    }
    Car existingCar = car;
    car = null;
    if (existingCar != null)
    {
      existingCar.setRoute(null);
    }
    Request existingRequest = request;
    request = null;
    if (existingRequest != null)
    {
      existingRequest.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "city" + ":" + getCity()+ "," +
            "street" + ":" + getStreet()+ "," +
            "seatsAvail" + ":" + getSeatsAvail()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "driver = "+(getDriver()!=null?Integer.toHexString(System.identityHashCode(getDriver())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "car = "+(getCar()!=null?Integer.toHexString(System.identityHashCode(getCar())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "request = "+(getRequest()!=null?Integer.toHexString(System.identityHashCode(getRequest())):"null");
  }
}