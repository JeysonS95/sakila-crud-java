package entity;

public class Inventory extends Entity {
    private int inventoryId;
    private int filmId;
    private int storeId;

    public Inventory(int inventoryId, int filmId, int storeId) {
        this.inventoryId = inventoryId;
        this.filmId = filmId;
        this.storeId = storeId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public int getFilmId() {
        return filmId;
    }

    public int getStoreId() {
        return storeId;
    }

    @Override
    public String toString() {
        return inventoryId + " | Film: " + filmId + " | Store: " + storeId;
    }
}
