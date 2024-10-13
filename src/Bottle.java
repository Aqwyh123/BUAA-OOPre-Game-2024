public class Bottle extends Item {
    private final int capacity;
    private boolean isUsed;
    private static final double RATIO = 100.0;

    public Bottle(int id, String name, int capacity, String type, int ce, Adventurer owner) {
        super(id, name, ce, type, owner);
        this.capacity = capacity;
        this.isUsed = false;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void drunk() {
        int ce = this.getCombatEffectiveness();
        int capacity = this.getCapacity();
        switch (this.getType()) {
            case "HpBottle":
                getOwner().improveHitPoint(this.getCapacity());
                break;
            case "AtkBottle":
                getOwner().improveAttackPoint(ce + (int) Math.floor(capacity / RATIO));
                break;
            case "DefBottle":
                getOwner().improveDefensePoint(ce + (int) Math.floor(capacity / RATIO));
                break;
            default:
                break;
        }
        this.isUsed = true;
    }
}
