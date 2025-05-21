package entity;

public class Language extends Entity {
    private int language_id;
    private String name;

    public Language(int language_id, String name) {
        this.language_id = language_id;
        this.name = name;
    }

    public int getLanguageId() {
        return language_id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return language_id + " | " + name;
    }
}
