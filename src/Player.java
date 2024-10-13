import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private final HashMap<Integer, Adventurer> adventurers = new HashMap<>();
    private static final Player instance = new Player();

    public static Player getInstance() {
        return instance;
    }

    public void reset() {
        instance.adventurers.clear();
    }

    public void addAdventurer(int id, String name) {
        adventurers.put(id, new Adventurer(id, name));
    }

    public void addBottle(int advId, int botId, String name, int capacity, String type, int ce) {
        Adventurer adventurer = adventurers.get(advId);
        adventurer.addUnit(new Bottle(botId, name, capacity, type, ce, adventurer));
    }

    public void addEquipment(int advId, int equId, String name, int dur, String type, int ce) {
        Adventurer adventurer = adventurers.get(advId);
        adventurer.addUnit(new Equipment(equId, name, dur, type, ce, adventurer));
    }

    public String improveEquipment(int adventurerId, int equipmentId) {
        Adventurer adventurer = adventurers.get(adventurerId);
        adventurer.improveEquipment(equipmentId);
        Equipment equipment = (Equipment) adventurer.getUnit(equipmentId);
        return equipment.getName() + " " + equipment.getDurability();
    }

    public String deleteItem(int adventurerId, int itemId) {
        Adventurer adventurer = adventurers.get(adventurerId);
        Item item = (Item) adventurer.getUnit(itemId);
        adventurer.deleteUnit(itemId);
        int value = 0;
        if (item instanceof Bottle) {
            value = ((Bottle) item).getCapacity();
        } else if (item instanceof Equipment) {
            value = ((Equipment) item).getDurability();
        }
        return item.getType() + " " + item.getName() + " " + value;
    }

    public void carryItem(int adventurerId, int itemId) {
        Adventurer adventurer = adventurers.get(adventurerId);
        adventurer.carryItem(itemId);
    }

    public String useBottle(int adventurerId, int bottleId) {
        Adventurer adventurer = adventurers.get(adventurerId);
        String bottleName = adventurer.getUnit(bottleId).getName();
        String advName = adventurer.getName();
        int status = adventurer.useBottle(bottleId);
        if (status == 0) {
            int hp = adventurer.getHitPoint();
            int atk = adventurer.getAttackPoint();
            int def = adventurer.getDefensePoint();
            return advName + " " + hp + " " + atk + " " + def;
        } else {
            return advName + " fail to use " + bottleName;
        }
    }

    public void addFragment(int advId, int fragId, String name) {
        Adventurer adventurer = adventurers.get(advId);
        adventurer.addUnit(new Fragment(fragId, name, adventurer));
    }

    public String redeemWarfare(int advId, String name, int welfareId) {
        Adventurer adventurer = adventurers.get(advId);
        int status = adventurer.redeemWarfare(name, welfareId);
        switch (status) {
            case 0: {
                Bottle bottle = (Bottle) adventurer.getUnit(welfareId);
                return bottle.getName() + " " + bottle.getCapacity();
            }
            case 1: {
                Equipment equipment = (Equipment) adventurer.getUnit(welfareId);
                return equipment.getName() + " " + equipment.getDurability();
            }
            case 2: {
                Bottle bottle = (Bottle) adventurer.getUnit(welfareId);
                return String.format("Congratulations! HpBottle %s acquired", bottle.getName());
            }
            default: {
                int count = adventurer.countUnit(name, "Fragment");
                return String.format("%d: Not enough fragments collected yet", count);
            }
        }
    }

    public String combat(int fromIds, String equName, ArrayList<Integer> toIds) {
        Adventurer from = adventurers.get(fromIds);
        ArrayList<Adventurer> to = new ArrayList<>();
        for (int id : toIds) {
            to.add(adventurers.get(id));
        }
        int status = from.combat(equName, to);
        String result = "";
        if (status != 0) {
            result = String.format("Adventurer %d defeated", fromIds);
        } else {
            for (Adventurer adventurer : to) {
                if (adventurer.getHitPoint() > 0) {
                    result = result.concat(adventurer.getName() + " " + adventurer.getHitPoint() + "\n");
                }
            }
        }
        return result.trim();
    }
}
