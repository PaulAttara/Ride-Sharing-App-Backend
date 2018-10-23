package ca.mcgill.ecse321.model0;
import javax.persistence.*;

//@Entity
//@Table(name = "vehicles")
//@NamedQueries({
//	@NamedQuery(name = "Vehicle.findAll", query = "SELECT e FROM Vehicle e")
//})
public class Vehicle {
	private int vehicleId;
	private String brand;
	private String model;
	private String licensePlate;
	
	@Id
    @Column(name = "vehicleid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getVehicleId() {
        return vehicleId;
    }
	
	public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "licenseplate")
    public String getLicensePlate() {
        return this.licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
	
}
