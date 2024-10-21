import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Game {
    private static final HashMap<Integer, Adventurer> adventurers = new HashMap<>();

    public static void reset() {
        adventurers.clear();
    }

    public static void executeCommand(ArrayList<String> command) {
        String op = command.get(0);
        int adventurerId = Integer.parseInt(command.get(1));
        if (Objects.equals(op, "1")) {
            String name = command.get(2);
            addAdventurer(adventurerId, name);
        } else if (Objects.equals(op, "2") || Objects.equals(op, "3")) {
            String name = command.get(3);
            int unitId = Integer.parseInt(command.get(2));
            int attribution = Integer.parseInt(command.get(4));
            String type = command.get(5);
            int ce = Integer.parseInt(command.get(6));
            if (Objects.equals(op, "3")) {
                addEquipment(adventurerId, unitId, name, attribution, type, ce);
            } else {
                addBottle(adventurerId, unitId, name, attribution, type, ce);
            }
        } else if (Objects.equals(op, "4")) {
            int unitId = Integer.parseInt(command.get(2));
            System.out.println(improveEquipment(adventurerId, unitId));
        } else if (Objects.equals(op, "5")) {
            int unitId = Integer.parseInt(command.get(2));
            System.out.println(deleteItem(adventurerId, unitId));
        } else if (Objects.equals(op, "6")) {
            int unitId = Integer.parseInt(command.get(2));
            carryItem(adventurerId, unitId);
        } else if (Objects.equals(op, "7")) {
            int unitId = Integer.parseInt(command.get(2));
            System.out.println(useBottle(adventurerId, unitId));
        } else if (Objects.equals(op, "8")) {
            int unitId = Integer.parseInt(command.get(2));
            String name = command.get(3);
            addFragment(adventurerId, unitId, name);
        } else if (Objects.equals(op, "9")) {
            String name = command.get(2);
            int welfareId = Integer.parseInt(command.get(3));
            System.out.println(redeemWarfare(adventurerId, name, welfareId));
        } else if (Objects.equals(op, "10")) {
            String name = command.get(2);
            String attackType = command.get(3);
            int adventureNum = Integer.parseInt(command.get(4));
            ArrayList<Integer> adventurerIds = new ArrayList<>();
            for (int i = 0; i < adventureNum; i++) {
                adventurerIds.add(Integer.parseInt(command.get(5 + i)));
            }
            System.out.println(combat(adventurerId, name, attackType, adventurerIds));
        } else if (Objects.equals(op, "11")) {
            int employeeId = Integer.parseInt(command.get(2));
            employ(adventurerId, employeeId);
        } else if(Objects.equals(op, "12")) {
            String result = challenge(adventurerId);
            if(!Objects.equals(result, "")) {
                System.out.println(result);
            }
        } else {
            assert false;
        }
    }

    public static void addAdventurer(int id, String name) {
        adventurers.put(id, new Adventurer(id, name));
    }

    public static void addBottle(int advId, int botId,
                                 String name, int capacity, String type, int ce) {
        Adventurer adventurer = adventurers.get(advId);
        adventurer.addUnit(new Bottle(botId, name, capacity, type, ce, adventurer));
    }

    public static void addEquipment(int advId, int equId,
                                    String name, int durability, String type, int ce) {
        Adventurer adventurer = adventurers.get(advId);
        adventurer.addUnit(new Equipment(equId, name, durability, type, ce, adventurer));
    }

    public static String improveEquipment(int adventurerId, int equipmentId) {
        Adventurer adventurer = adventurers.get(adventurerId);
        adventurer.improveEquipment(equipmentId);
        Equipment equipment = (Equipment) adventurer.getUnit(equipmentId);
        return equipment.getName() + " " + equipment.getDurability();
    }

    public static String deleteItem(int adventurerId, int itemId) {
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

    public static void carryItem(int adventurerId, int itemId) {
        Adventurer adventurer = adventurers.get(adventurerId);
        adventurer.carryItem(itemId);
    }

    public static String useBottle(int adventurerId, int bottleId) {
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
            return String.format("%s fail to use %s", advName, bottleName);
        }
    }

    public static void addFragment(int advId, int fragId, String name) {
        Adventurer adventurer = adventurers.get(advId);
        adventurer.addUnit(new Fragment(fragId, name, adventurer));
    }

    public static String redeemWarfare(int advId, String name, int welfareId) {
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
                int count = adventurer.countFragment(name);
                return String.format("%d: Not enough fragments collected yet", count);
            }
        }
    }

    public static String combat(int fromId, String equName, String type, ArrayList<Integer> toIds) {
        Adventurer from = adventurers.get(fromId);
        ArrayList<Adventurer> to = new ArrayList<>();
        for (int id : toIds) {
            to.add(adventurers.get(id));
        }
        if (Objects.equals(type, "normal")) {
            int status = from.normalCombat(equName, to);
            String result = "";
            if (status != 0) {
                result = String.format("Adventurer %d defeated", fromId);
            } else {
                for (Adventurer toAdv : to) {
                    result = result.concat(toAdv.getName() + " " + toAdv.getHitPoint() + "\n");
                }
            }
            return result.trim();
        } else if (Objects.equals(type, "chain")) {
            HashSet<Adventurer> toChain = new HashSet<>();
            for (Adventurer toAdv : to) {
                toChain.addAll(toAdv.getCompanions(5));
            }
            int originalHitPoint = 0;
            int eventualHitPoint = 0;
            for (Adventurer toAdv : toChain) {
                originalHitPoint += toAdv.getHitPoint();
            }
            int status = from.chainCombat(equName, to);
            if (status == 0) {
                return String.format("Adventurer %d defeated", fromId);
            } else {
                for (Adventurer toAdv : toChain) {
                    eventualHitPoint += toAdv.getHitPoint();
                }
                return eventualHitPoint - originalHitPoint + "";
            }
        } else {
            return null;
        }
    }

    public static void employ(int employerId, int employeeId) {
        Adventurer employer = adventurers.get(employerId);
        Adventurer employee = adventurers.get(employeeId);
        employer.employ(employee);
    }

    public static String challenge(int adventurerId) {
        Adventurer adventurer = adventurers.get(adventurerId);
        return adventurer.challenge();
    }
}
