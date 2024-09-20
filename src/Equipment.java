public class Equipment extends Item {
    private int durability;

    public Equipment(int id, String name, int durability, int combatEffectiveness, Adventurer owner) {
        super(id, name, combatEffectiveness, owner);
        this.durability = durability;
    }

    public int getDurability() {
        return this.durability;
    }

    public void improveDurability(int durability) {
        this.durability += durability;
    }
}
