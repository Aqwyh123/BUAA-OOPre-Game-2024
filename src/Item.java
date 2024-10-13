public class Item extends CombatUnit {
    private final Adventurer owner;
    private final String type;

    protected Item(int id, String name, int combatEffectiveness, String type, Adventurer owner) {
        super(id, name, combatEffectiveness);
        this.type = type;
        this.owner = owner;
    }

    public String getType() {
        return this.type;
    }

    protected Adventurer getOwner() {
        return this.owner;
    }
}
