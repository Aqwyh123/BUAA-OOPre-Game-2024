import java.util.ArrayList;

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

    public void use(ArrayList<Adventurer> opponents) {
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
                    int decrease = ce + ownerAP - opponentDP;
                    opponent.setHitPoint(opponent.getHitPoint() - decrease);
                    break;
                }
                case "Blade": {
                    int ce = this.getCombatEffectiveness();
                    int decrease = ce + getOwner().getAttackPoint();
                    opponent.setHitPoint(opponent.getHitPoint() - decrease);
                    break;
                }
                default:
                    break;
            }
        }
        this.durability -= DEFAULT_DECREASE;
    }
}
