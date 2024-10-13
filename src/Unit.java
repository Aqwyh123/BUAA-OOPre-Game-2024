public abstract class Unit {
    private final int id;
    private final String name;
    private final Adventurer owner;

    protected Unit(int id, String name, Adventurer owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    protected Adventurer getOwner() {
        return this.owner;
    }
}
