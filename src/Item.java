public abstract class Item extends Unit implements Combatable {
    private final String type;
    private final int combatEffectiveness;

    protected Item(int id, String name, String type, int ce, Adventurer owner) {
        super(id, name, owner);
        this.type = type;
        this.combatEffectiveness = ce;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public int getCombatEffectiveness() {
        return this.combatEffectiveness;
    }
}
