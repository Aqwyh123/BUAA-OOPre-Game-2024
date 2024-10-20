import java.util.ArrayList;
import java.util.HashMap;

public class Adventurer extends CombatUnit {
    private static final int ORIGINAL_HIT_POINT = 500;
    private static final int ORIGINAL_ATTACK_POINT = 1;
    private static final int ORIGINAL_DEFENSE_POINT = 0;
    private int hitPoint;
    private int attackPoint;
    private int defensePoint;
    private final ItemInventory backpack = new ItemInventory(this);
    private final Inventory<Unit> possessions = new Inventory<>(this);

    public Adventurer(int id, String name) {
        super(id, name, ORIGINAL_ATTACK_POINT + ORIGINAL_DEFENSE_POINT, null);
        this.hitPoint = ORIGINAL_HIT_POINT;
        this.attackPoint = ORIGINAL_ATTACK_POINT;
        this.defensePoint = ORIGINAL_DEFENSE_POINT;
    }

    public void updateCombatEffectiveness() {
        this.setCombatEffectiveness(this.attackPoint + this.defensePoint);
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

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
        updateCombatEffectiveness();
    }

    public void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
        updateCombatEffectiveness();
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

    public int countFragment(String name) {
        return possessions.countUnit(name, "Fragment");
    }

    public void carryItem(int itemId) {
        Item item = (Item) possessions.getUnit(itemId);
        assert item != null;
        backpack.addUnit(item);
    }

    public void discardItem(int itemId) {
        Item item = backpack.getUnit(itemId);
        assert item != null;
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
            bottle.use();
            return 0;
        }
    }

    public void improveEquipment(int equipmentId) {
        Equipment equipment = (Equipment) possessions.getUnit(equipmentId);
        assert equipment != null;
        equipment.improve();
    }

    public int redeemWarfare(String name, int welfareId) {
        HashMap<Integer, Unit> fragments = possessions.getUnits(name, "Fragment");
        if (fragments.size() < 5) {
            return -1;
        } else {
            Fragment fragmentInstance = (Fragment) fragments.values().iterator().next();
            int status = fragmentInstance.redeemed(welfareId);
            int count = 0;
            for (Unit fragment : fragments.values()) {
                possessions.deleteUnit(fragment.getId());
                count++;
                if (count == 5) {
                    break;
                }
            }
            return status;
        }
    }

    public int combat(String equName, ArrayList<Adventurer> adventurers) {
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
