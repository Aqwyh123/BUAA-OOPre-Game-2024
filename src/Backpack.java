import java.util.HashMap;

public class Backpack {
    private final HashMap<Integer, Item> items = new HashMap<>();

    public Item getItem(int itemId) {
        return items.get(itemId);
    }

    public void addItem(Item item) {
        items.put(item.getId(), item);
    }

    public void deleteItem(int itemId) {
        items.remove(itemId);
    }
}
