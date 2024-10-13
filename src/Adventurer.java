public class Adventurer extends CombatUnit {
    private static final int ORIGINAL_HIT_POINT = 500;
    private static final int ORIGINAL_ATTACK_POINT = 1;
    private static final int ORIGINAL_DEFENSE_POINT = 0;
    private int hitPoint;
    private int attackPoint;
    private int defensePoint;
    private final Inventory<Item> backpack = new Inventory<>();
    private final Inventory<Unit> possessions = new Inventory<>();

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

    public Unit getUnit(int unitId) {
        return possessions.getUnit(unitId);
    }

    public void addUnit(Item item) {
        possessions.addUnit(item);
    }

    public void addUnit(Fragment fragment) {
        possessions.addUnit(fragment);
    }

    public void deleteUnit(int unitId) {
        this.discardItem(unitId);
        possessions.deleteUnit(unitId);
    }

    public int countUnit(String name) {
        return possessions.countUnit(name);
    }

    public void carryItem(int itemId) {
        Item item = (Item) getUnit(itemId);
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
            this.deleteUnit(bottleId);
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

    public int redeemWarfare(String name, int welfareId) {
        int fragmentNum = countUnit(name);
        if (fragmentNum < 5) {
            return -1;
        } else {
            Fragment fragment = (Fragment) possessions.getUnit(name);
            return fragment.redeemed(welfareId);
        }
    }
}
