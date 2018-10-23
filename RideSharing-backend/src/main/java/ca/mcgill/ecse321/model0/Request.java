/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse321.model0;
import javax.persistence.*;

// line 37 "../../../../rideSharing.ump"
@Entity
@Table(name = "requests")
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT e FROM Request e")
})
public class Request
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Request Attributes
  private String status;
  private int requestId;

  //Request Associations
  private User passenger;
  private Route route;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Request(User aPassenger, Route aRoute)
  {
    status = null;
    if (aPassenger == null || aPassenger.getRequest() != null)
    {
      throw new RuntimeException("Unable to create Request due to aPassenger");
    }
    passenger = aPassenger;
    if (aRoute == null || aRoute.getRequest() != null)
    {
      throw new RuntimeException("Unable to create Request due to aRoute");
    }
    route = aRoute;
  }

  public Request(Car aCarForPassenger, Route aRouteForPassenger, User aDriverForRoute, Car aCarForRoute)
  {
    status = null;
    passenger = new User(aCarForPassenger, aRouteForPassenger, this);
    route = new Route(aDriverForRoute, aCarForRoute, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStatus(String aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setRequestId(int aRequestId)
  {
    boolean wasSet = false;
    requestId = aRequestId;
    wasSet = true;
    return wasSet;
  }
  
  @Id
  @Column(name = "requestid")
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getRequestId()
  {
	  return requestId;
  }
  
  @Column
  public String getStatus()
  {
    return status;
  }
  /* Code from template association_GetOne */
  @OneToOne
  @JoinColumn(name ="username")
  public User getPassenger()
  {
    return passenger;
  }
  /* Code from template association_GetOne */
  @OneToOne
  @JoinColumn(name="routeid")
  public Route getRoute()
  {
    return route;
  }

  public void delete()
  {
    User existingPassenger = passenger;
    passenger = null;
    if (existingPassenger != null)
    {
      existingPassenger.delete();
    }
    Route existingRoute = route;
    route = null;
    if (existingRoute != null)
    {
      existingRoute.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "status" + ":" + getStatus()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "passenger = "+(getPassenger()!=null?Integer.toHexString(System.identityHashCode(getPassenger())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "route = "+(getRoute()!=null?Integer.toHexString(System.identityHashCode(getRoute())):"null");
  }
}