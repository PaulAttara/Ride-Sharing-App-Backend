/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse321.model0;
import javax.persistence.*;

// line 2 "../../../../rideSharing.ump"
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT e FROM User e")
})
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String city;
  private String address;
  private double avgRating;
  private int numPastTrips;
  private String role;

  //User Associations
  private Car car;
  private Route route;
  private Request request;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(Car aCar, Route aRoute, Request aRequest)
  {
    username = null;
    password = null;
    firstName = null;
    lastName = null;
    phoneNumber = null;
    city = null;
    address = null;
    role = null;
    if (aCar == null || aCar.getDriver() != null)
    {
      throw new RuntimeException("Unable to create User due to aCar");
    }
    car = aCar;
    if (aRoute == null || aRoute.getDriver() != null)
    {
      throw new RuntimeException("Unable to create User due to aRoute");
    }
    route = aRoute;
    if (aRequest == null || aRequest.getPassenger() != null)
    {
      throw new RuntimeException("Unable to create User due to aRequest");
    }
    request = aRequest;
  }

  public User(Car aCarForRoute, Request aRequestForRoute, Route aRouteForRequest)
  {
    username = null;
    password = null;
    firstName = null;
    lastName = null;
    phoneNumber = null;
    city = null;
    address = null;
    role = null;
    car = new Car(this);
    route = new Route(this, aCarForRoute, aRequestForRoute);
    request = new Request(this, aRouteForRequest);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setFirstName(String aFirstName)
  {
    boolean wasSet = false;
    firstName = aFirstName;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastName(String aLastName)
  {
    boolean wasSet = false;
    lastName = aLastName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setAvgRating(double aAvgRating)
  {
    boolean wasSet = false;
    avgRating = aAvgRating;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumPastTrips(int aNumPastTrips)
  {
    boolean wasSet = false;
    numPastTrips = aNumPastTrips;
    wasSet = true;
    return wasSet;
  }

  public boolean setRole(String aRole)
  {
    boolean wasSet = false;
    role = aRole;
    wasSet = true;
    return wasSet;
  }
  
  @Id
  @Column(name = "username")
  public String getUsername()
  {
    return username;
  }

  @Column
  public String getPassword()
  {
    return password;
  }

  @Column
  public String getFirstName()
  {
    return firstName;
  }

  @Column
  public String getLastName()
  {
    return lastName;
  }

  @Column
  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  @Column
  public String getCity()
  {
    return city;
  }

  @Column
  public String getAddress()
  {
    return address;
  }

  @Column
  public double getAvgRating()
  {
    return avgRating;
  }

  @Column
  public int getNumPastTrips()
  {
    return numPastTrips;
  }
  
  @Column
  public String getRole()
  {
    return role;
  }
  /* Code from template association_GetOne */
  @OneToOne
  @JoinColumn(name ="vehicleid")
  public Car getCar()
  {
    return car;
  }
  /* Code from template association_GetOne */
  @OneToOne
  @JoinColumn(name="routeid")
  public Route getRoute()
  {
    return route;
  }
  /* Code from template association_GetOne */
  @OneToOne
  @JoinColumn(name="requestid")
  public Request getRequest()
  {
    return request;
  }

  public void delete()
  {
    Car existingCar = car;
    car = null;
    if (existingCar != null)
    {
      existingCar.delete();
    }
    Route existingRoute = route;
    route = null;
    if (existingRoute != null)
    {
      existingRoute.delete();
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
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "firstName" + ":" + getFirstName()+ "," +
            "lastName" + ":" + getLastName()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "city" + ":" + getCity()+ "," +
            "address" + ":" + getAddress()+ "," +
            "avgRating" + ":" + getAvgRating()+ "," +
            "numPastTrips" + ":" + getNumPastTrips()+ "," +
            "role" + ":" + getRole()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "car = "+(getCar()!=null?Integer.toHexString(System.identityHashCode(getCar())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "route = "+(getRoute()!=null?Integer.toHexString(System.identityHashCode(getRoute())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "request = "+(getRequest()!=null?Integer.toHexString(System.identityHashCode(getRequest())):"null");
  }
}