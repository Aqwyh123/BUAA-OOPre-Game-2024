import org.junit.Test;

import static org.junit.Assert.*;

public class AdventurerTest {

    @Test
    public void addBottle() {
        Adventurer adventurer = new Adventurer(1, "Alice");
        adventurer.addBottle(new Bottle(1, "bottle1", 10));
        adventurer.addBottle(new Bottle(2, "bottle2", 20));
        assertEquals(2, adventurer.getBottleCount());
    }

    @Test
    public void addEquipment() {
        Adventurer adventurer = new Adventurer(1, "Alice");
        adventurer.addEquipment(new Equipment(1, "equipment1", 10));
        adventurer.addEquipment(new Equipment(2, "equipment2", 20));
        assertEquals(2, adventurer.getEquipmentCount());
    }

    @Test
    public void deleteBottle() {
        Adventurer adventurer = new Adventurer(1, "Alice");
        adventurer.addBottle(new Bottle(1, "bottle1", 10));
        adventurer.addBottle(new Bottle(2, "bottle2", 20));
        adventurer.deleteBottle(1);
        assertEquals(1, adventurer.getBottleCount());
    }

    @Test
    public void deleteEquipment() {
        Adventurer adventurer = new Adventurer(1, "Alice");
        adventurer.addEquipment(new Equipment(1, "equipment1", 10));
        adventurer.addEquipment(new Equipment(2, "equipment2", 20));
        adventurer.deleteEquipment(1);
        assertEquals(1, adventurer.getEquipmentCount());
    }
}