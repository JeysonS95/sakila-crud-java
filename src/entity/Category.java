package entity;

public class Category extends Entity {
    private int category_id;
    private String name;

    public Category(int category_id, String name) {
        this.category_id = category_id;
        this.name = name;
    }

    public int getCategoryId() {
        return category_id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return category_id + " - " + name;
    }
}
