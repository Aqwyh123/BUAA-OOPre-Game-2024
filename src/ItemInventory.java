import java.util.HashMap;

public class ItemInventory extends Inventory<Item> {

    public ItemInventory(Adventurer owner) {
        super(owner);
    }

    @Override
    public void addUnit(Item item) {
        if (item instanceof Bottle) {
            Bottle bottle = (Bottle) item;
            int bottleNum = countUnit(bottle.getName(), "Bottle");
            if (bottleNum < getOwner().getBottleLimit()) {
                super.addUnit(bottle);
            }
        } else if (item instanceof Equipment) {
            Equipment haveEquipment = getEquipment(item.getName());
            if (haveEquipment != null) {
                deleteUnit(haveEquipment.getId());
            }
            super.addUnit(item);
        }
    }

    public Equipment getEquipment(String name) {
        HashMap<Integer, Item> equipments = getUnits(name, "Equipment");
        for (Item item : equipments.values()) {
            return (Equipment) item;
        }
        return null;
    }
}
