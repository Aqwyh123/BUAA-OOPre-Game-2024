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

    public void addBottle(int advId, int botId, String name, int capacity, String type, int ce) {
        Adventurer adventurer = adventurers.get(advId);
        adventurer.addItem(new Bottle(botId, name, capacity, type, ce, adventurer));
    }

    public void addEquipment(int advId, int equId, String name, int durability, String type, int ce) {
        Adventurer adventurer = adventurers.get(advId);
        adventurer.addItem(new Equipment(equId, name, durability, type, ce, adventurer));
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
        String className = item.getName();
        int value = 0;
        if (item instanceof Bottle) {
            value = ((Bottle) item).getCapacity();
        } else if (item instanceof Equipment) {
            value = ((Equipment) item).getDurability();
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
        int status = adventurer.useBottle(bottleId);
        if (status == 0) {
            int hp = adventurer.getHitPoint();
            int atk = adventurer.getAttackPoint();
            int def = adventurer.getDefensePoint();
            return advName + " " + hp + " " + atk + " " + def;
        } else {
            return advName + " fail to use " + bottleName;
        }
    }
}
