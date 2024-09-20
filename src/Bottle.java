public class Bottle extends Item {
    private final int capacity;
    private boolean isUsed;
    private final String type;
    private static final double RATIO = 100.0;

    public Bottle(int id, String name, int capacity, String type, int combatEffectiveness, Adventurer owner) {
        super(id, name, combatEffectiveness, owner);
        this.type = type;
        this.capacity = capacity;
        this.isUsed = false;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void drunk() {
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
        this.isUsed = true;
    }
}
