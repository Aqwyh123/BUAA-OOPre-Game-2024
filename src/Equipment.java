import java.util.HashSet;

public class Equipment extends Item {
    private static final int DEFAULT_INCREASE = 1;
    private static final int DEFAULT_DECREASE = 1;
    private int durability;

    public Equipment(int id, String name, int durability, String type, int ce, Adventurer owner) {
        super(id, name, ce, type, owner);
        this.durability = durability;
    }

    public int getDurability() {
        return this.durability;
    }

    public void increaseDurability() {
        this.durability += DEFAULT_INCREASE;
    }

    public void used(HashSet<Adventurer> opponents) {
        for (Adventurer opponent : opponents) {
            switch (getType()) {
                case "Axe": {
                    opponent.setHitPoint(opponent.getHitPoint() / 10);
                    break;
                }
                case "Sword": {
                    int ce = this.getCombatEffectiveness();
                    int ownerAP = getOwner().getAttackPoint();
                    int opponentDP = opponent.getDefensePoint();
                    opponent.decreaseHitPoint(ce + ownerAP - opponentDP);
                    break;
                }
                case "Blade": {
                    int ce = this.getCombatEffectiveness();
                    opponent.decreaseHitPoint(ce + getOwner().getAttackPoint());
                    break;
                }
                default:
                    break;
            }
        }
        this.durability -= DEFAULT_DECREASE;
    }
}
