import java.util.Collection;

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

    public void use(Collection<Adventurer> adventurers) {
        Adventurer from = this.getOwner();
        for (Adventurer adventurer : adventurers) {
            switch (getType()) {
                case "Axe": {
                    adventurer.setHitPoint(adventurer.getHitPoint() / 10);
                    break;
                }
                case "Sword": {
                    int ce = this.getCombatEffectiveness();
                    int ownerAP = from.getAttackPoint();
                    int opponentDP = adventurer.getDefensePoint();
                    int decrease = ce + ownerAP - opponentDP;
                    adventurer.setHitPoint(adventurer.getHitPoint() - decrease);
                    break;
                }
                case "Blade": {
                    int ce = this.getCombatEffectiveness();
                    int decrease = ce + from.getAttackPoint();
                    adventurer.setHitPoint(adventurer.getHitPoint() - decrease);
                    break;
                }
                default:
                    break;
            }
        }
        this.durability -= DEFAULT_DECREASE;
    }
}
