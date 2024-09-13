import java.util.ArrayList;

public class Adventurer {
    private final int id;
    private final String name;
    private final ArrayList<Bottle> bottles = new ArrayList<>();
    private final ArrayList<Equipment> equipments = new ArrayList<>();

    public Adventurer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Bottle getBottle(int bottleId) {
        for (Bottle bottle : bottles) {
            if (bottle.getId() == bottleId) {
                return bottle;
            }
        }
        return null;
    }

    public Equipment getEquipment(int equipmentId) {
        for (Equipment equipment : equipments) {
            if (equipment.getId() == equipmentId) {
                return equipment;
            }
        }
        return null;
    }

    public int getBottleCount() {
        return bottles.size();
    }

    public int getEquipmentCount() {
        return equipments.size();
    }

    public void addBottle(Bottle bottle) {
        bottles.add(bottle);
    }

    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }

    public void deleteBottle(int bottleId) {
        for (Bottle bottle : bottles) {
            if (bottle.getId() == bottleId) {
                bottles.remove(bottle);
                break;
            }
        }
    }

    public void deleteEquipment(int equipmentId) {
        for (Equipment equipment : equipments) {
            if (equipment.getId() == equipmentId) {
                equipments.remove(equipment);
                break;
            }
        }
    }
}
