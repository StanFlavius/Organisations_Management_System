package Organisation_Management_System_Entities;

public class Location {
    private String country;
    private String city;
    private String street;
    private Integer nr;

    public Location(String cn, String ct, String str, Integer n){
        this.city = ct;
        this.country = cn;
        this.nr = n;
        this.street = str;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNr() {
        return nr;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }
}
