package manager;

import java.util.ArrayList;
import entity.Country;

public class CountryManager {
    private ArrayList<Country> countries = new ArrayList<>();

    public void post(Country c) {
        countries.add(c);
    }

    public Country get(int id) {
        for (Country c : countries) {
            if (c.getCountryId() == id) return c;
        }
        return null;
    }

    public ArrayList<Country> getAll() {
        return countries;
    }

    public void put(int id, String newName) {
        Country c = get(id);
        if (c != null) {
            countries.remove(c);
            countries.add(new Country(id, newName));
        }
    }

    public void delete(int id) {
        Country c = get(id);
        if (c != null) {
            countries.remove(c);
        }
    }
}
