import java.util.HashMap;

public class Inventory<T extends Unit> implements Ownable {
    private final Adventurer owner;
    private final HashMap<Integer, T> units = new HashMap<>();

    public Inventory(Adventurer owner) {
        this.owner = owner;
    }

    public Adventurer getOwner() {
        return owner;
    }

    public T getUnit(int itemId) {
        return units.get(itemId);
    }

    public HashMap<Integer, T> getUnits(String type) {
        HashMap<Integer, T> filteredUnits = new HashMap<>();
        for (T unit : units.values()) {
            switch (type) {
                case "Item":
                    if (unit instanceof Item) {
                        filteredUnits.put(unit.getId(), unit);
                    }
                    break;
                case "Bottle":
                    if (unit instanceof Bottle) {
                        filteredUnits.put(unit.getId(), unit);
                    }
                    break;
                case "Equipment":
                    if (unit instanceof Equipment) {
                        filteredUnits.put(unit.getId(), unit);
                    }
                    break;
                case "Fragment":
                    if (unit instanceof Fragment) {
                        filteredUnits.put(unit.getId(), unit);
                    }
                    break;
                default:
            }
        }
        return filteredUnits;
    }

    public HashMap<Integer, T> getUnits(String name, String type) {
        HashMap<Integer, T> filteredUnits = new HashMap<>();
        for (T unit : units.values()) {
            if (unit.getName().equals(name)) {
                switch (type) {
                    case "Item":
                        if (unit instanceof Item) {
                            filteredUnits.put(unit.getId(), unit);
                        }
                        break;
                    case "Bottle":
                        if (unit instanceof Bottle) {
                            filteredUnits.put(unit.getId(), unit);
                        }
                        break;
                    case "Equipment":
                        if (unit instanceof Equipment) {
                            filteredUnits.put(unit.getId(), unit);
                        }
                        break;
                    case "Fragment":
                        if (unit instanceof Fragment) {
                            filteredUnits.put(unit.getId(), unit);
                        }
                        break;
                    default:
                }
            }
        }
        return filteredUnits;
    }

    public void addUnit(T unit) {
        units.put(unit.getId(), unit);
    }

    public void deleteUnit(int id) {
        units.remove(id);
    }

    public int countUnit(String name, String type) {
        int count = 0;
        for (T unit : units.values()) {
            if (unit.getName().equals(name)) {
                switch (type) {
                    case "Item":
                        if (unit instanceof Item) {
                            count++;
                        }
                        break;
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
