import java.util.HashMap;

public class Player {
    private static final int DEFAULT_IMPROVEMENT = 1;
    private final HashMap<Integer, Adventurer> adventurers = new HashMap<>();
    private static final Player instance = new Player();

    public static Player getInstance() {
        return instance;
    }

    public void addAdventurer(int id, String name) {
        adventurers.put(id, new Adventurer(id, name));
    }

    public void addBottle(int adventurerId, int id, String name, int capacity, String type, int combatEffectiveness) {
        Adventurer adventurer = adventurers.get(adventurerId);
        adventurer.addItem(new Bottle(id, name, capacity, type, combatEffectiveness));
    }

    public void addEquipment(int adventurerId, int id, String name, int durability, int combatEffectiveness) {
        Adventurer adventurer = adventurers.get(adventurerId);
        adventurer.addItem(new Equipment(id, name, durability, combatEffectiveness));
    }

    public String improveEquipment(int adventurerId, int equipmentId) {
        Adventurer adventurer = adventurers.get(adventurerId);
        adventurer.improveEquipment(equipmentId, DEFAULT_IMPROVEMENT);
        Equipment equipment = (Equipment) adventurer.getItem(equipmentId);
        return adventurer.getName() + " " + equipment.getDurability();
    }

    public String deleteItem(int adventurerId, int itemId) {
        Adventurer adventurer = adventurers.get(adventurerId);
        Item item = adventurer.getItem(itemId);
        adventurer.deleteItem(itemId);
        String className;
        int value;
        if (item instanceof Bottle) {
            switch (((Bottle) item).getType()) {
                case "HpBottle":
                    className = "HpBottle";
                    break;
                case "AtkBottle":
                    className = "AtkBottle";
                    break;
                case "DefBottle":
                    className = "DefBottle";
                    break;
                default:
                    className = "Bottle";
                    break;
            }
            value = ((Bottle) item).getCapacity();
        } else if (item instanceof Equipment) {
            className = "Equipment";
            value = ((Equipment) item).getDurability();
        } else {
            className = "Item";
            value = 0;
        }
        return className + " " + item.getName() + " " + value;
    }

    public void carryItem(int adventurerId, int itemId) {
        Adventurer adventurer = adventurers.get(adventurerId);
        adventurer.carryItem(itemId);
    }

    public String useBottle(int adventurerId, int bottleId) {
        Adventurer adventurer = adventurers.get(adventurerId);
        Bottle bottle = (Bottle) adventurer.getItem(bottleId);
        if (adventurer.useBottle(bottleId) == 0) {
            return adventurer.getName() + " " + adventurer.getHitPoint() + " " + adventurer.getAttackPoint() + " " + adventurer.getDefensePoint();
        } else {
            return adventurer.getName() + " fail to use " + bottle.getName();
        }
    }
}
