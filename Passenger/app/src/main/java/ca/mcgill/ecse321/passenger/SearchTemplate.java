package ca.mcgill.ecse321.passenger;

public class SearchTemplate {

    int routeID;
    String date;
    int numSeats;

    public SearchTemplate(String date, int routeID, int numSeats) {
        this.date = date;
        this.routeID = routeID;
        this.numSeats = numSeats;
    }

    public int getNumSeats() {
        return numSeats;
    }



    public int getrouteID() {
        return routeID;
    }
    public String getDate() {
        return date;
    }


}
