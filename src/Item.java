public class Item extends CombatUnit {
    protected final Adventurer owner;

    protected Item(int id, String name, int combatEffectiveness, Adventurer owner) {
        super(id, name, combatEffectiveness);
        this.owner = owner;
    }
}
