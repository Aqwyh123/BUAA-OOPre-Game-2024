import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
    private static final Player player = Player.getInstance();

    @Test
    public void improveEquipment() {
        player.addAdventurer(100, "Alice");
        player.addAdventurer(200, "Bob");
        player.addEquipment(100, 1, "Sword", 100, 100);
        player.addEquipment(200, 2, "Shield", 10, 10);
        Assert.assertEquals("Sword 101", player.improveEquipment(100, 1));
        Assert.assertEquals("Shield 11", player.improveEquipment(200, 2));
        player.reset();
    }

    @Test
    public void deleteItem() {
        player.addAdventurer(100, "Alice");
        player.addAdventurer(200, "Bob");
        player.addEquipment(100, 1, "Sword", 100, 100);
        player.addBottle(200, 2, "AtkB", 0, "AtkBottle", 10);
        Assert.assertEquals("Equipment Sword 100", player.deleteItem(100, 1));
        Assert.assertEquals("AtkBottle AtkB 0", player.deleteItem(200, 2));
        player.reset();
    }

    @Test
    public void useBottle() {
        player.addAdventurer(100, "Alice");
        player.addBottle(100, 1, "AtkB", 0, "AtkBottle", 10);
        player.addBottle(100, 2, "DefB", 402, "DefBottle", 20);
        player.addBottle(100, 3, "HpB", 0, "HpBottle", 100);
        Assert.assertEquals("Alice fail to use AtkB", player.useBottle(100, 1));
        player.carryItem(100, 1);
        Assert.assertEquals("Alice 500 11 0", player.useBottle(100, 1));
        Assert.assertEquals("Alice fail to use DefB", player.useBottle(100, 2));
        Assert.assertEquals("Alice fail to use DefB", player.useBottle(100, 2));
        player.reset();
    }
}