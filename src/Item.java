public class Item extends CombatUnit {
    private final Adventurer owner;

    protected Item(int id, String name, int combatEffectiveness, Adventurer owner) {
        super(id, name, combatEffectiveness);
        this.owner = owner;
    }

    protected Adventurer getOwner() {
        return this.owner;
    }
}
