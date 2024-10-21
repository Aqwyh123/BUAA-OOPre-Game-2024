public class Fragment extends Unit implements Ownable {

    private static final int DEFAULT_CAPACITY = 100;
    private static final String DEFAULT_TYPE = "HpBottle";
    private static final int DEFAULT_CE = 0;
    public static final int REDEEMABLE_NUMBER = 5;
    private final Adventurer owner;

    public Fragment(int id, String name, Adventurer owner) {
        super(id, name);
        this.owner = owner;
    }

    public Adventurer getOwner() {
        return owner;
    }

    public int redeemed(int welfareId) {
        Adventurer owner = getOwner();
        Item welfare = (Item) owner.getUnit(welfareId);
        if (welfare == null) {
            owner.addUnit(new Bottle(welfareId, getName(),
                    DEFAULT_CAPACITY, DEFAULT_TYPE, DEFAULT_CE, owner));
            return 2;
        }
        if (welfare instanceof Bottle) {
            Bottle bottle = (Bottle) welfare;
            bottle.fill();
            return 0;
        }
        if (welfare instanceof Equipment) {
            Equipment equipment = (Equipment) welfare;
            equipment.improve();
            return 1;
        }
        return -1;
    }
}
