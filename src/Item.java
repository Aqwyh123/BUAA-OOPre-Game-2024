public abstract class Item extends Unit implements Combatable, Ownable {
    private final String type;
    private final int combatEffectiveness;
    private Adventurer owner;

    protected Item(int id, String name, String type, int ce, Adventurer owner) {
        super(id, name);
        this.type = type;
        this.combatEffectiveness = ce;
        this.owner = owner;
    }

    public String getType() {
        return this.type;
    }

    public int getCombatEffectiveness() {
        return this.combatEffectiveness;
    }

    public Adventurer getOwner() {
        return this.owner;
    }

    public void setOwner(Adventurer owner) {
        this.owner = owner;
    }
}
