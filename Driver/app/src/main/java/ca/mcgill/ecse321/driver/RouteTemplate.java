package ca.mcgill.ecse321.driver;

import java.sql.Timestamp;
import java.util.List;

public class RouteTemplate {

    List<LocationTemplate> locations;
    String date;
    int id;
    int numseats;
    int carid;

    public RouteTemplate(List<LocationTemplate> locations, String date, int id, int numseats, int carid) {
        this.locations = locations;
        this.date = date;
        this.id = id;
        this.numseats = numseats;
        this.carid = carid;
    }

    public int getId() {
        return id;
    }

    public void setLocations(List<LocationTemplate> locations) {
        this.locations = locations;
    }

    public LocationTemplate getStartLocation (){
        if (!locations.isEmpty()) {
            return locations.get(0);
        }
        return null;
    }

    public LocationTemplate getEndLocation (){
        if (!locations.isEmpty()) {
            return locations.get(locations.size()-1);
        }
        return null;
    }

}
