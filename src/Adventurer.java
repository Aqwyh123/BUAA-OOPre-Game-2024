import java.util.HashMap;

public class Adventurer extends CombatUnit {
    private static final int ORIGINAL_HIT_POINT = 500;
    private static final int ORIGINAL_ATTACK_POINT = 1;
    private static final int ORIGINAL_DEFENSE_POINT = 0;
    private int hitPoint;
    private int attackPoint;
    private int defensePoint;
    private final Backpack backpack = new Backpack();
    private final HashMap<Integer, Item> possessions = new HashMap<>();

    public Adventurer(int id, String name) {
        super(id, name, ORIGINAL_ATTACK_POINT, ORIGINAL_DEFENSE_POINT);
        this.hitPoint = ORIGINAL_HIT_POINT;
        this.attackPoint = ORIGINAL_ATTACK_POINT;
        this.defensePoint = ORIGINAL_DEFENSE_POINT;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    public void improveHitPoint(int hitPoint) {
        this.hitPoint += hitPoint;
    }

    public void improveAttackPoint(int attackPoint) {
        this.attackPoint += attackPoint;
    }

    public void improveDefensePoint(int defensePoint) {
        this.defensePoint += defensePoint;
    }

    public Item getItem(int itemId) {
        return possessions.get(itemId);
    }

    public void addItem(Item item) {
        possessions.put(item.getId(), item);
    }

    public void deleteItem(int itemId) {
        this.discardItem(itemId);
        possessions.remove(itemId);
    }

    public void carryItem(int itemId) {
        Item item = getItem(itemId);
        if (item == null) {
            return;
        }
        backpack.addItem(item);
    }

    public void discardItem(int itemId) {
        Item item = backpack.getItem(itemId);
        if (item == null) {
            return;
        }
        backpack.deleteItem(itemId);
    }

    public int useBottle(int bottleId) {
        Bottle bottle = (Bottle) backpack.getItem(bottleId);
        if (bottle == null) {
            return -1;
        }
        int status = bottle.drunk(this);
        deleteItem(bottleId);
        return status;
    }

    public void improveEquipment(int equipmentId, int durability) {
        Equipment equipment = (Equipment) possessions.get(equipmentId);
        if (equipment == null) {
            return;
        }
        equipment.improveDurability(durability);
    }
}
