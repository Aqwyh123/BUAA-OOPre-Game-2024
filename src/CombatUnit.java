public abstract class CombatUnit extends Unit {
    private final int combatEffectiveness;

    protected CombatUnit(int id, String name, int combatEffectiveness, Adventurer owner) {
        super(id, name, owner);
        this.combatEffectiveness = combatEffectiveness;
    }

    public int getCombatEffectiveness() {
        return this.combatEffectiveness;
    }
}
