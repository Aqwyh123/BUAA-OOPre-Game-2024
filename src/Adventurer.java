import java.util.HashSet;

public class Adventurer extends CombatUnit {
    private static final int ORIGINAL_HIT_POINT = 500;
    private static final int ORIGINAL_ATTACK_POINT = 1;
    private static final int ORIGINAL_DEFENSE_POINT = 0;
    private int hitPoint;
    private int attackPoint;
    private int defensePoint;
    private final ItemInventory backpack = new ItemInventory();
    private final UnitInventory possessions = new UnitInventory();

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

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public void increaseHitPoint(int hitPoint) {
        this.hitPoint += hitPoint;
    }

    public void decreaseHitPoint(int hitPoint) {
        this.hitPoint -= hitPoint;
    }

    public void increaseAttackPoint(int attackPoint) {
        this.attackPoint += attackPoint;
    }

    public void increaseDefensePoint(int defensePoint) {
        this.defensePoint += defensePoint;
    }

    public Unit getUnit(int unitId) {
        return possessions.getUnit(unitId);
    }

    public void addUnit(Unit unit) {
        possessions.addUnit(unit);
    }

    public void deleteUnit(int unitId) {
        this.discardItem(unitId);
        possessions.deleteUnit(unitId);
    }

    public int countUnit(String name, String type) {
        return possessions.countUnit(name, type);
    }

    public void carryItem(int itemId) {
        Item item = (Item) possessions.getUnit(itemId);
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
            bottle.used();
            return 0;
        }
    }

    public void improveEquipment(int equipmentId) {
        Equipment equipment = (Equipment) possessions.getUnit(equipmentId);
        if (equipment == null) {
            return;
        }
        equipment.increaseDurability();
    }

    public int redeemWarfare(String name, int welfareId) {
        int fragmentNum = countUnit(name, "Fragment");
        if (fragmentNum < 5) {
            return -1;
        } else {
            Fragment fragment = possessions.getFragment(name);
            return fragment.redeemed(welfareId);
        }
    }

    public int combat(String equName, HashSet<Adventurer> adventurers) {
        Equipment equipment = backpack.getEquipment(equName);
        if (equipment == null) {
            return -1;
        }
        int overallAttack = this.attackPoint + equipment.getCombatEffectiveness();
        int overallDefense = 0;
        for (Adventurer adventurer : adventurers) {
            overallDefense = Math.max(overallDefense, adventurer.defensePoint);
        }
        if (overallAttack <= overallDefense) {
            return -1;
        }
        equipment.used(adventurers);
        if (equipment.getDurability() == 0) {
            deleteUnit(equipment.getId());
        }
        return 0;
    }
}
