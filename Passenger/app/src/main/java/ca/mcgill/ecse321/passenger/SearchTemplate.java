package ca.mcgill.ecse321.passenger;

public class SearchTemplate {

    String date;
    String routeID;
    String numSeats;

    public SearchTemplate(String date, String routeID, String numSeats) {
        this.date = date;
        this.routeID = routeID;
        this.numSeats = numSeats;
    }

    public String getNumSeats() {
        return numSeats;
    }
    public String getRouteID() {
        return routeID;
    }
    public String getDate() {
        return date;
    }


}
