package ca.mcgill.ecse321.driver;

public class LocationTemplate {

    @Override
    public String toString() {
        return City + ", " + Street;
    }

    String City;
    String Street;
    double price;
    String passenger;

    public LocationTemplate(String city, String street, String passenger, double price) {
        City = city;
        Street = street;
        this.price = price;
        this.passenger = passenger;
    }

    public String getCity() {
        return City;
    }

    public String getStreet() {
        return Street;
    }

    public double getPrice() {
        return price;
    }

}
