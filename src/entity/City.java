package entity;

public class City extends Entity {
    private int cityId;
    private String city;
    private Country country;

    public City(int cityId, String city, Country country) {
        this.cityId = cityId;
        this.city = city;
        this.country = country;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return cityId + " | " + city + " | " + country.getCountry();
    }
}
