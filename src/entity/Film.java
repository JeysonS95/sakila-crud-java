package entity;

public class Film extends Entity {
    private int film_id;
    private String title;
    private String description;
    private Language language;

    public Film(int film_id, String title, String description, Language language) {
        this.film_id = film_id;
        this.title = title;
        this.description = description;
        this.language = language;
    }

    public int getFilmId() {
        return film_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Language getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return film_id + " | " + title + " | " + language.getName();
    }
}
