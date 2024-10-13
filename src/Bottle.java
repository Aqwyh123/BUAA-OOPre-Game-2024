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

    public void fill() {
        this.isUsed = false;
    }

    public void used() {
        int ce = this.getCombatEffectiveness();
        int capacity = this.getCapacity();
        switch (this.getType()) {
            case "HpBottle":
                getOwner().increaseHitPoint(this.getCapacity());
                break;
            case "AtkBottle":
                getOwner().increaseAttackPoint(ce + (int) Math.floor(capacity / RATIO));
                break;
            case "DefBottle":
                getOwner().increaseDefensePoint(ce + (int) Math.floor(capacity / RATIO));
                break;
            default:
                break;
        }
        this.isUsed = true;
    }
}
