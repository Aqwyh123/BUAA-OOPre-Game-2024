import java.util.HashMap;

public abstract class Inventory<T> {
    private final HashMap<Integer, T> units = new HashMap<>();

    public T getUnit(int itemId) {
        return units.get(itemId);
    }

    protected HashMap<Integer, T> getUnits() {
        return units;
    }

    public void addUnit(T unit) {
        units.put(((Unit) unit).getId(), unit);
    }

    public void deleteUnit(int id) {
        units.remove(id);
    }

    public HashMap<Integer,T> getUnits(String name, String type) {
        HashMap<Integer, T> result = new HashMap<>();
        for (T unit : units.values()) {
            if (((Unit) unit).getName().equals(name)) {
                switch (type) {
                    case "Bottle":
                        if (unit instanceof Bottle) {
                            result.put(((Unit) unit).getId(), unit);
                        }
                        break;
                    case "Equipment":
                        if (unit instanceof Equipment) {
                            result.put(((Unit) unit).getId(), unit);
                        }
                        break;
                    case "Fragment":
                        if (unit instanceof Fragment) {
                            result.put(((Unit) unit).getId(), unit);
                        }
                        break;
                    default:
                }
            }
        }
        return result;
    }

    public int countUnit(String name, String type) {
        int count = 0;
        for (T unit : units.values()) {
            if (((Unit) unit).getName().equals(name)) {
                switch (type) {
                    case "Bottle":
                        if (unit instanceof Bottle) {
                            count++;
                        }
                        break;
                    case "Equipment":
                        if (unit instanceof Equipment) {
                            count++;
                        }
                        break;
                    case "Fragment":
                        if (unit instanceof Fragment) {
                            count++;
                        }
                        break;
                    default:
                }
            }
        }
        return count;
    }
}
