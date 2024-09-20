import java.util.HashMap;

public class Player {
    private static final int DEFAULT_IMPROVEMENT = 1;
    private final HashMap<Integer, Adventurer> adventurers = new HashMap<>();
    private static final Player instance = new Player();

    public static Player getInstance() {
        return instance;
    }

    public void reset() {
        instance.adventurers.clear();
    }

    public void addAdventurer(int id, String name) {
        adventurers.put(id, new Adventurer(id, name));
    }

    public void addBottle(int advId, int id, String name, int capacity, String type, int ce) {
        Adventurer adventurer = adventurers.get(advId);
        adventurer.addItem(new Bottle(id, name, capacity, type, ce, adventurer));
    }

    public void addEquipment(int advId, int id, String name, int durability, int ce) {
        Adventurer adventurer = adventurers.get(advId);
        adventurer.addItem(new Equipment(id, name, durability, ce, adventurer));
    }

    public String improveEquipment(int adventurerId, int equipmentId) {
        Adventurer adventurer = adventurers.get(adventurerId);
        adventurer.improveEquipment(equipmentId, DEFAULT_IMPROVEMENT);
        Equipment equipment = (Equipment) adventurer.getItem(equipmentId);
        return equipment.getName() + " " + equipment.getDurability();
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
        String bottleName = adventurer.getItem(bottleId).getName();
        String advName = adventurer.getName();
        if (adventurer.useBottle(bottleId) == 0) {
            int hp = adventurer.getHitPoint();
            int atk = adventurer.getAttackPoint();
            int def = adventurer.getDefensePoint();
            return advName + " " + hp + " " + atk + " " + def;
        } else {
            return advName + " fail to use " + bottleName;
        }
    }
}
