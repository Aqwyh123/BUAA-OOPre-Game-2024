import java.util.ArrayList;

public class Player {
    private final ArrayList<Adventurer> adventurers;
    private static final Player instance = new Player();

    private Player() {
        adventurers = new ArrayList<>();
    }

    public static Player getInstance() {
        return instance;
    }

    public void addAdventurer(int id, String name) {
        adventurers.add(new Adventurer(id, name));
    }

    public void addBottle(int adventurerId, int id, String name, int capacity) {
        for (Adventurer adventurer : adventurers) {
            if (adventurer.getId() == adventurerId) {
                adventurer.addBottle(new Bottle(id, name, capacity));
            }
        }
    }

    public void addEquipment(int adventurerId, int id, String name, int durability) {
        for (Adventurer adventurer : adventurers) {
            if (adventurer.getId() == adventurerId) {
                adventurer.addEquipment(new Equipment(id, name, durability));
            }
        }
    }

    public String improveEquipment(int adventurerId, int equipmentId, int durability) {
        for (Adventurer adventurer : adventurers) {
            if (adventurer.getId() == adventurerId) {
                Equipment equipment = adventurer.getEquipment(equipmentId);
                equipment.improve(durability);
                return equipment.getName() + " " + equipment.getDurability();
            }
        }
        return null;
    }

    public String deleteBottle(int adventurerId, int bottleId) {
        for (Adventurer adventurer : adventurers) {
            if (adventurer.getId() == adventurerId) {
                Bottle bottle = adventurer.getBottle(bottleId);
                adventurer.deleteBottle(bottleId);
                int count = adventurer.getBottleCount();
                String name = bottle.getName();
                int capacity = bottle.getCapacity();
                return count + " " + name + " " + capacity;
            }
        }
        return null;
    }

    public String deleteEquipment(int adventurerId, int equipmentId) {
        for (Adventurer adventurer : adventurers) {
            if (adventurer.getId() == adventurerId) {
                Equipment equipment = adventurer.getEquipment(equipmentId);
                adventurer.deleteEquipment(equipmentId);
                int count = adventurer.getEquipmentCount();
                String name = equipment.getName();
                int durability = equipment.getDurability();
                return count + " " + name + " " + durability;
            }
        }
        return null;
    }
}
