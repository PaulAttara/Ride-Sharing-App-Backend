package ca.mcgill.ecse321.driver;

import java.sql.Timestamp;
import java.util.List;

public class RouteTemplate {

    List<LocationTemplate> locations;
    String date;
    String startLocation;
    int id;
    int numseats;
    int carid;

    public RouteTemplate(List<LocationTemplate> locations, String date, int id, int numseats, int carid, String startLocation) {
        this.locations = locations;
        this.date = date;
        this.id = id;
        this.numseats = numseats;
        this.carid = carid;
        this.startLocation = startLocation;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setLocations(List<LocationTemplate> locations) {
        this.locations = locations;
    }

    public String getStartLocation (){
        return startLocation;
    }

    public LocationTemplate getEndLocation (){
        if (!locations.isEmpty()) {
            return locations.get(locations.size()-1);
        }
        return null;
    }

}
