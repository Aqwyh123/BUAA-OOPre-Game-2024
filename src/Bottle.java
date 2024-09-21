public class Bottle extends Item {
    private final int capacity;
    private boolean isUsed;
    private final String type;
    private static final double RATIO = 100.0;

    public Bottle(int id, String name, int capacity, String type, int ce, Adventurer owner) {
        super(id, name, ce, owner);
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
        int ce = this.getCombatEffectiveness();
        int capacity = this.getCapacity();
        switch (type) {
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
