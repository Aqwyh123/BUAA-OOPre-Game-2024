public class ItemInventory extends Inventory<Item> {

    @Override
    public void addUnit(Item item) {
        if (item instanceof Bottle) {
            Bottle bottle = (Bottle) item;
            int bottleNum = countUnit(bottle.getName(), "Bottle");
            if (bottleNum <= bottle.getOwner().getCombatEffectiveness() / 5 + 1) {
                super.addUnit(bottle);
            }
        } else if (item instanceof Equipment) {
            Equipment equipment = (Equipment) item;
            for (Item equ : getUnits().values()) {
                if (equ instanceof Equipment && equ.getName().equals(equipment.getName())) {
                    deleteUnit(equ.getId());
                    break;
                }
            }
            super.addUnit(item);
        }
    }

    public Equipment getEquipment(String name) {
        for (Item item : getUnits().values()) {
            if (item instanceof Equipment && item.getName().equals(name)) {
                return (Equipment) item;
            }
        }
        return null;
    }
}
