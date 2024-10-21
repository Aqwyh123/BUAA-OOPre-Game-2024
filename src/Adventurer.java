import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Adventurer extends Unit implements Combatable {
    private static final int ORIGINAL_HIT_POINT = 500;
    private static final int ORIGINAL_ATTACK_POINT = 1;
    private static final int ORIGINAL_DEFENSE_POINT = 0;
    private int hitPoint;
    private int attackPoint;
    private int defensePoint;
    private final ItemInventory backpack = new ItemInventory(this);
    private final Inventory<Unit> possessions = new Inventory<>(this);
    private final HashMap<Adventurer, Integer> assistEmployerAttackPoint = new HashMap<>();
    private final HashMap<Adventurer, Integer> assistCount = new HashMap<>();
    private final HashSet<Adventurer> employees = new HashSet<>();

    public Adventurer(int id, String name) {
        super(id, name);
        this.hitPoint = ORIGINAL_HIT_POINT;
        this.attackPoint = ORIGINAL_ATTACK_POINT;
        this.defensePoint = ORIGINAL_DEFENSE_POINT;
    }

    public int getCombatEffectiveness() {
        return this.attackPoint + this.defensePoint;
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
        boolean isDecrease = hitPoint < this.hitPoint;
        this.hitPoint = hitPoint;
        if (isDecrease) {
            for (Adventurer employee : employees) {
                employee.processCombatFor(this);
            }
        }
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
    }

    public Unit getUnit(int unitId) {
        return possessions.getUnit(unitId);
    }

    public void addUnit(Unit unit) {
        possessions.addUnit(unit);
    }

    public void deleteUnit(int unitId) {
        if (backpack.getUnit(unitId) != null) {
            this.discardItem(unitId);
        }
        assert possessions.getUnit(unitId) != null;
        possessions.deleteUnit(unitId);
    }

    public int countFragment(String name) {
        return possessions.countUnit(name, "Fragment");
    }

    public int getBottleLimit() {
        return this.getCombatEffectiveness() / 5 + 1;
    }

    public void carryItem(int itemId) {
        Item item = (Item) possessions.getUnit(itemId);
        assert item != null;
        backpack.addUnit(item);
    }

    public void discardItem(int itemId) {
        assert backpack.getUnit(itemId) != null;
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
        if (fragments.size() < Fragment.REDEEMABLE_NUMBER) {
            return -1;
        } else {
            Fragment fragmentInstance = (Fragment) fragments.values().iterator().next();
            int status = fragmentInstance.redeemed(welfareId);
            int count = 0;
            for (Unit fragment : fragments.values()) {
                possessions.deleteUnit(fragment.getId());
                count++;
                if (count == Fragment.REDEEMABLE_NUMBER) {
                    break;
                }
            }
            return status;
        }
    }

    public void employ(Adventurer employee) {
        employee.employedBy(this);
        employees.add(employee);
    }

    private void employedBy(Adventurer employer) {
        assistCount.put(employer, 0);
    }

    public void dismiss(Adventurer employee) {
        employee.dismissedBy(this);
        employees.remove(employee);
    }

    private void dismissedBy(Adventurer employer) {
        assistCount.remove(employer);
    }

    public HashSet<Adventurer> getCompanions(int layer) {
        HashSet<Adventurer> companions = new HashSet<>();
        companions.add(this);
        if (layer == 1) {
            return companions;
        }
        for (Adventurer employee : employees) {
            companions.addAll(employee.getCompanions(layer - 1));
        }
        return companions;
    }

    private void prepareCombatFor(Adventurer employer) {
        assistEmployerAttackPoint.put(employer, employer.getAttackPoint());
    }

    private void stopCombatFor(Adventurer employer) {
        assistEmployerAttackPoint.remove(employer);
    }

    private void prepareCombatedBy() {
        for (Adventurer employee : employees) {
            employee.prepareCombatFor(this);
        }
    }

    private void stopCombatedBy() {
        for (Adventurer employee : employees) {
            employee.stopCombatFor(this);
        }
    }

    private boolean isAttackable(Equipment equipment, Collection<Adventurer> toAdventurers) {
        if (equipment == null) {
            return false;
        }
        int overallAttack = this.attackPoint + equipment.getCombatEffectiveness();
        int overallDefense = 0;
        for (Adventurer toAdventurer : toAdventurers) {
            overallDefense = Math.max(overallDefense, toAdventurer.defensePoint);
        }
        return overallAttack > overallDefense;
    }

    public int normalCombat(String equName, ArrayList<Adventurer> toAdventurers) {
        Equipment equipment = backpack.getEquipment(equName);
        if (isAttackable(equipment, toAdventurers)) {
            toAdventurers.forEach(Adventurer::prepareCombatedBy);
            for (Adventurer adventurer : toAdventurers) {
                equipment.use(adventurer);
            }
            if (equipment.getDurability() == 0) {
                deleteUnit(equipment.getId());
            }
            toAdventurers.forEach(Adventurer::stopCombatedBy);
            return 0;
        }
        return -1;
    }

    public int chainCombat(String equName, ArrayList<Adventurer> toAdventures) {
        HashSet<Adventurer> toCompanions = new HashSet<>();
        for (Adventurer toAdventurer : toAdventures) {
            toCompanions.addAll(toAdventurer.getCompanions(5));
        }
        Equipment equipment = backpack.getEquipment(equName);
        if (isAttackable(equipment, toCompanions)) {
            for (Adventurer adventurer : toCompanions) {
                equipment.use(adventurer);
            }
            if (equipment.getDurability() == 0) {
                deleteUnit(equipment.getId());
            }
            return 0;
        }
        return -1;
    }

    private void processCombatFor(Adventurer employer) {
        if (employer.getHitPoint() <= assistEmployerAttackPoint.get(employer) / 2) {
            this.assist(employer);
        }
    }

    private void assist(Adventurer employer) {
        HashMap<Integer, Item> equipments = backpack.getUnits("Equipment");
        for (Item item : equipments.values()) {
            Equipment equipment = (Equipment) item;
            deleteUnit(equipment.getId());
            employer.addUnit(equipment);
        }
        int count = assistCount.get(employer);
        count++;
        assistCount.put(employer, count);
        if (count > 3) {
            employer.dismiss(this);
        }
    }

    public String challenge() {
        int overallCombatEffectiveness = 0;
        overallCombatEffectiveness += this.getCombatEffectiveness();
        for (Item item : backpack.getUnits("Item").values()) {
            overallCombatEffectiveness += item.getCombatEffectiveness();
        }
        for (Adventurer employee : employees) {
            overallCombatEffectiveness += employee.getCombatEffectiveness();
        }

        String result = "";

        if (overallCombatEffectiveness <= 1000) {
            return result.trim();
        }
        result = result.concat("Cloak of Shadows\n");
        this.setDefensePoint(getDefensePoint() + 40);
        for (Adventurer employee : employees) {
            employee.setDefensePoint(employee.getDefensePoint() + 40);
        }

        if (overallCombatEffectiveness <= 2000) {
            return result.trim();
        }
        result = result.concat("Flamebrand Sword\n");
        this.setAttackPoint(getAttackPoint() + 40);
        for (Adventurer employee : employees) {
            employee.setAttackPoint(employee.getAttackPoint() + 40);
        }

        if (overallCombatEffectiveness <= 3000) {
            return result.trim();
        }
        result = result.concat("Stoneheart Amulet\n");
        this.setDefensePoint(getDefensePoint() + 40);
        for (Adventurer employee : employees) {
            employee.setDefensePoint(employee.getDefensePoint() + 40);
        }

        if (overallCombatEffectiveness <= 4000) {
            return result.trim();
        }
        result = result.concat("Windrunner Boots\n");
        this.setDefensePoint(getDefensePoint() + 30);
        for (Adventurer employee : employees) {
            employee.setDefensePoint(employee.getDefensePoint() + 30);
        }

        if (overallCombatEffectiveness <= 5000) {
            return result.trim();
        }
        result = result.concat("Frostbite Staff\n");
        this.setAttackPoint(getAttackPoint() + 50);
        for (Adventurer employee : employees) {
            employee.setAttackPoint(employee.getAttackPoint() + 50);
        }
        return result.trim();
    }
}
