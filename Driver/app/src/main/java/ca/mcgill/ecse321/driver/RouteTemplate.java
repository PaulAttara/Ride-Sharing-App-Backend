package ca.mcgill.ecse321.driver;

public class RouteTemplate {
    String startAddress;
    String endAddress;


    public RouteTemplate(String startAddress, String endAddress) {
        this.startAddress = startAddress;
        this.endAddress = endAddress;
    }

    public String getStartAddress() {
        return startAddress;
    }


    public String getEndAddress() {
        return endAddress;
    }

}
