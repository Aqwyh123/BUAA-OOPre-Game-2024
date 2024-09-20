public class CombatUnit {
    protected final int id;
    protected final String name;
    protected final int combatEffectiveness;

    protected CombatUnit(int id, String name, int attackPoint, int defensePoint) {
        this.id = id;
        this.name = name;
        this.combatEffectiveness = attackPoint + defensePoint;
    }

    protected CombatUnit(int id, String name, int combatEffectiveness) {
        this.id = id;
        this.name = name;
        this.combatEffectiveness = combatEffectiveness;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getCombatEffectiveness() {
        return this.combatEffectiveness;
    }
}
