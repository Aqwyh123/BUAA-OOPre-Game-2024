public abstract class Item extends CombatUnit {
    private final String type;

    protected Item(int id, String name, int combatEffectiveness, String type, Adventurer owner) {
        super(id, name, combatEffectiveness, owner);
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
