package entity;

public class Country extends Entity {
    private int country_id;
    private String country;

    public Country(int country_id, String country) {
        this.country_id = country_id;
        this.country = country;
    }

    public int getCountryId() {
        return country_id;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Country #" + country_id + " | Name: " + country;
    }
}
