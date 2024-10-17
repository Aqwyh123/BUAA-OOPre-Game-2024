public abstract class CombatUnit extends Unit {
    private int combatEffectiveness;

    protected CombatUnit(int id, String name, int combatEffectiveness, Adventurer owner) {
        super(id, name, owner);
        this.combatEffectiveness = combatEffectiveness;
    }

    public int getCombatEffectiveness() {
        return this.combatEffectiveness;
    }

    protected void setCombatEffectiveness(int combatEffectiveness) {
        this.combatEffectiveness = combatEffectiveness;
    }
}
