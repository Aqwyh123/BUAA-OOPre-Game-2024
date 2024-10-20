public class Bottle extends Item {
    private final int capacity;
    private boolean isUsed;
    private static final double RATIO = 100.0;

    public Bottle(int id, String name, int capacity, String type, int ce, Adventurer owner) {
        super(id, name, type, ce, owner);
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

    public void use() {
        int ce = this.getCombatEffectiveness();
        Adventurer owner = this.getOwner();
        switch (this.getType()) {
            case "HpBottle": {
                owner.setHitPoint(owner.getHitPoint() + capacity);
                break;
            }
            case "AtkBottle": {
                int increase = ce + (int) Math.floor(capacity / RATIO);
                owner.setAttackPoint(owner.getAttackPoint() + increase);
                break;
            }
            case "DefBottle": {
                int increase = ce + (int) Math.floor(capacity / RATIO);
                owner.setDefensePoint(owner.getDefensePoint() + increase);
                break;
            }
            default:
                break;
        }
        this.isUsed = true;
    }
}
