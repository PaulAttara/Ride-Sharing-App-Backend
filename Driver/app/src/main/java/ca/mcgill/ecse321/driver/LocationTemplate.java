package ca.mcgill.ecse321.driver;

public class LocationTemplate {

    String City;
    String Street;
    int price;
    String passenger;

    public LocationTemplate(String city, String street, String passenger, int price) {
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

    public int getPrice() {
        return price;
    }

}
