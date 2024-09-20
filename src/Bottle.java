public class Bottle extends Item {
    private int capacity;
    private final String type;
    protected static final double RATIO = 100.0;

    protected Bottle(int id, String name, int capacity, String type, int combatEffectiveness) {
        super(id, name, combatEffectiveness);
        this.type = type;
        this.capacity = capacity;
    }

    protected int getCapacity() {
        return capacity;
    }

    protected String getType() {
        return type;
    }

    protected void empty() {
        this.capacity = 0;
    }

    public int drunk(Adventurer owner) {
        if (this.getCapacity() == 0) {
            return -1;
        }
        switch (type) {
            case "HpBottle":
                owner.improveHitPoint(this.getCapacity());
                break;
            case "AtkBottle":
                owner.improveAttackPoint(this.getCombatEffectiveness() + (int) Math.floor(this.getCapacity() / RATIO));
                break;
            case "DefBottle":
                owner.improveDefensePoint(this.getCombatEffectiveness() + (int) Math.floor(this.getCapacity() / RATIO));
                break;
        }
        this.empty();
        return 0;
    }
}
