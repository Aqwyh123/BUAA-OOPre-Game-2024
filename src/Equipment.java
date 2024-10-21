public class Equipment extends Item {
    private static final int DEFAULT_INCREASE = 1;
    private static final int DEFAULT_DECREASE = 1;
    private int durability;

    public Equipment(int id, String name, int durability, String type, int ce, Adventurer owner) {
        super(id, name, type, ce, owner);
        this.durability = durability;
    }

    public int getDurability() {
        return this.durability;
    }

    public void improve() {
        this.durability += DEFAULT_INCREASE;
    }

    public void use(Adventurer to) {
        Adventurer from = this.getOwner();
        switch (getType()) {
            case "Axe": {
                to.setHitPoint(to.getHitPoint() / 10);
                break;
            }
            case "Sword": {
                int ce = this.getCombatEffectiveness();
                int ownerAP = from.getAttackPoint();
                int opponentDP = to.getDefensePoint();
                int decrease = ce + ownerAP - opponentDP;
                to.setHitPoint(to.getHitPoint() - decrease);
                break;
            }
            case "Blade": {
                int ce = this.getCombatEffectiveness();
                int decrease = ce + from.getAttackPoint();
                to.setHitPoint(to.getHitPoint() - decrease);
                break;
            }
            default:
                break;
        }
        this.durability -= DEFAULT_DECREASE;
    }
}
