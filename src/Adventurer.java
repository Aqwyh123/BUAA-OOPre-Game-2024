public class Adventurer extends CombatUnit {
    private static final int ORIGINAL_HIT_POINT = 500;
    private static final int ORIGINAL_ATTACK_POINT = 1;
    private static final int ORIGINAL_DEFENSE_POINT = 0;
    private int hitPoint;
    private int attackPoint;
    private int defensePoint;
    private final Inventory<Item> backpack = new Inventory<>();
    private final Inventory<Item> possessions = new Inventory<>();
    private final Inventory<Fragment> fragments = new Inventory<>();

    public Adventurer(int id, String name) {
        super(id, name, ORIGINAL_ATTACK_POINT + ORIGINAL_DEFENSE_POINT, null);
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
        return possessions.getUnit(itemId);
    }

    public void addItem(Item item) {
        possessions.addUnit(item);
    }

    public void deleteItem(int itemId) {
        this.discardItem(itemId);
        possessions.deleteUnit(itemId);
    }

    public void carryItem(int itemId) {
        Item item = getItem(itemId);
        if (item == null) {
            return;
        }
        backpack.addUnit(item);
    }

    public void discardItem(int itemId) {
        Item item = backpack.getUnit(itemId);
        if (item == null) {
            return;
        }
        backpack.deleteUnit(itemId);
    }

    public int useBottle(int bottleId) {
        Bottle bottle = (Bottle) backpack.getUnit(bottleId);
        if (bottle == null) {
            return -1;
        } else if (bottle.isUsed()) {
            this.deleteItem(bottleId);
            return 0;
        } else {
            bottle.drunk();
            return 0;
        }
    }

    public void improveEquipment(int equipmentId, int durability) {
        Equipment equipment = (Equipment) possessions.getUnit(equipmentId);
        if (equipment == null) {
            return;
        }
        equipment.improveDurability(durability);
    }

    public void addFragment(Fragment fragment) {
        fragments.addUnit(fragment);
    }
}
