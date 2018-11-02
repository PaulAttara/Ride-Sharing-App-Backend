package ca.mcgill.ecse321.passenger;

public class SearchTemplate {
    String username;
    String routeID;
    String date;


    public SearchTemplate(String username, String routeID, String date) {
        this.username = username;
        this.routeID = routeID;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }


    public String getrouteID() {
        return routeID;
    }
    public String getDate() {
        return date;
    }


}
