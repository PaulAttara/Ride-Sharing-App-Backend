package ca.mcgill.ecse321.passenger;

public class SearchTemplate {
    String startAddress;
    String endAddress;


    public SearchTemplate(String startAddress, String endAddress) {
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
