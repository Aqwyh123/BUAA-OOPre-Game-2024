import java.util.HashMap;

public class Inventory<T> {
    private final HashMap<Integer, T> units = new HashMap<>();

    public T getUnit(int itemId) {
        return units.get(itemId);
    }

    public T getUnit(String name) {
        for (T unit : units.values()) {
            if (((Unit) unit).getName().equals(name)) {
                return unit;
            }
        }
        return null;
    }

    public void addUnit(T unit) {
        units.put(((Unit) unit).getId(), unit);
    }

    public void deleteUnit(int id) {
        units.remove(id);
    }

    public int countUnit(String name) {
        int count = 0;
        for (T unit : units.values()) {
            if (((Unit) unit).getName().equals(name)) {
                count++;
            }
        }
        return count;
    }
}
