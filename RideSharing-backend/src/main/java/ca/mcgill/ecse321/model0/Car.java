/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse321.model0;
import javax.persistence.*;

// line 17 "../../../../rideSharing.ump"
@Entity
@Table(name = "cars")
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT e FROM Car e")
})
public class Car
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Car Attributes
  private int vehicleId;
  private String brand;
  private String model;
  private String licensePlate;

  //Car Associations
  private User driver;
  private Route route;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Car(User aDriver)
  {
    brand = null;
    model = null;
    licensePlate = null;
    if (aDriver == null || aDriver.getCar() != null)
    {
      throw new RuntimeException("Unable to create Car due to aDriver");
    }
    driver = aDriver;
  }

  public Car(Route aRouteForDriver, Request aRequestForDriver)
  {
    brand = null;
    model = null;
    licensePlate = null;
    driver = new User(this, aRouteForDriver, aRequestForDriver);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setVehicleId(int aVehicleId)
  {
    boolean wasSet = false;
    vehicleId = aVehicleId;
    wasSet = true;
    return wasSet;
  }

  public boolean setBrand(String aBrand)
  {
    boolean wasSet = false;
    brand = aBrand;
    wasSet = true;
    return wasSet;
  }

  public boolean setModel(String aModel)
  {
    boolean wasSet = false;
    model = aModel;
    wasSet = true;
    return wasSet;
  }

  public boolean setLicensePlate(String aLicensePlate)
  {
    boolean wasSet = false;
    licensePlate = aLicensePlate;
    wasSet = true;
    return wasSet;
  }

  @Id
  @Column(name = "vehicleid")
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getVehicleId()
  {
    return vehicleId;
  }
  
  @Column
  public String getBrand()
  {
    return brand;
  }

  @Column
  public String getModel()
  {
    return model;
  }

  @Column
  public String getLicensePlate()
  {
    return licensePlate;
  }
  /* Code from template association_GetOne */
  @OneToOne
  @JoinColumn(name = "username")
  public User getDriver()
  {
    return driver;
  }
  /* Code from template association_GetOne */
  @OneToOne
  @JoinColumn(name = "routeid")
  public Route getRoute()
  {
    return route;
  }

  public boolean hasRoute()
  {
    boolean has = route != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setRoute(Route aNewRoute)
  {
    boolean wasSet = false;
    if (route != null && !route.equals(aNewRoute) && equals(route.getCar()))
    {
      //Unable to setRoute, as existing route would become an orphan
      return wasSet;
    }

    route = aNewRoute;
    Car anOldCar = aNewRoute != null ? aNewRoute.getCar() : null;

    if (!this.equals(anOldCar))
    {
      if (anOldCar != null)
      {
        anOldCar.route = null;
      }
      if (route != null)
      {
        route.setCar(this);
      }
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
            "vehicleId" + ":" + getVehicleId()+ "," +
            "brand" + ":" + getBrand()+ "," +
            "model" + ":" + getModel()+ "," +
            "licensePlate" + ":" + getLicensePlate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "driver = "+(getDriver()!=null?Integer.toHexString(System.identityHashCode(getDriver())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "route = "+(getRoute()!=null?Integer.toHexString(System.identityHashCode(getRoute())):"null");
  }
}