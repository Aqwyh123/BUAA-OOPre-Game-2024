import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerTest {
    private static final Player player = Player.getInstance();

    @Test
    public void improveEquipment() {
        player.addAdventurer(100, "Alice");
        player.addAdventurer(200, "Bob");
        player.addEquipment(100, 1, "Sword", 100, "Axe", 100);
        player.addEquipment(200, 2, "Shield", 10, "Axe", 10);
        Assert.assertEquals("Sword 101", player.improveEquipment(100, 1));
        Assert.assertEquals("Shield 11", player.improveEquipment(200, 2));
        player.reset();
    }

    @Test
    public void deleteItem() {
        player.addAdventurer(100, "Alice");
        player.addAdventurer(200, "Bob");
        player.addEquipment(100, 1, "Sword", 100, "Axe", 100);
        player.addBottle(200, 2, "AtkB", 0, "AtkBottle", 10);
        Assert.assertEquals("Axe Sword 100", player.deleteItem(100, 1));
        player.carryItem(200, 2);
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
        player.carryItem(100, 2);
        Assert.assertEquals("Alice 500 11 24", player.useBottle(100, 2));
        Assert.assertEquals("Alice 500 11 24", player.useBottle(100, 2));
        player.reset();
    }

    @Test
    public void redeemWarfare(){
        player.addAdventurer(100, "Alice");
        player.addFragment(100, 1, "FragA");
        player.addFragment(100, 2, "FragA");
        player.addFragment(100, 3, "FragA");
        player.addFragment(100, 4, "FragA");
        Assert.assertEquals("4: Not enough fragments collected yet", player.redeemWarfare(100, "FragA", 1234));
        player.addFragment(100, 5, "FragA");
        player.addFragment(100, 6, "FragA");
        Assert.assertEquals("Congratulations! HpBottle FragA acquired", player.redeemWarfare(100, "FragA", 1234));
        player.addFragment(100, 7, "FragA");
        player.addFragment(100, 8, "FragA");
        player.addFragment(100, 9, "FragA");
        player.addFragment(100, 10, "FragA");
        Assert.assertEquals("Congratulations! HpBottle FragA acquired", player.redeemWarfare(100, "FragA", 2345));
        player.addEquipment(100, 11, "EquAxe", 100, "Axe", 10);
        player.addFragment(100, 12, "FragB");
        player.addFragment(100, 13, "FragB");
        player.addFragment(100, 14, "FragB");
        player.addFragment(100, 15, "FragB");
        player.addFragment(100, 16, "FragB");
        Assert.assertEquals("EquAxe 101", player.redeemWarfare(100, "FragB", 11));
        player.addBottle(100, 17, "AtkB", 1, "AtkBottle", 10);
        player.addFragment(100, 18, "FragC");
        player.addFragment(100, 19, "FragC");
        player.addFragment(100, 20, "FragC");
        player.addFragment(100, 21, "FragC");
        player.addFragment(100, 22, "FragB");
        Assert.assertEquals("4: Not enough fragments collected yet", player.redeemWarfare(100, "FragC", 17));
        player.addFragment(100, 23, "FragC");
        Assert.assertEquals("AtkB 1", player.redeemWarfare(100, "FragC", 17));
        player.reset();
    }

    @Test
    public void combat() {
        player.addAdventurer(100, "Alice");
        player.addAdventurer(200, "Bob");
        player.addAdventurer(300, "Charlie");
        player.addAdventurer(400, "David");
        player.addEquipment(100, 1, "EquAxe", 100, "Axe", 100);
        Assert.assertEquals("Adventurer 100 defeated", player.combat(100, "EquAxe", new ArrayList<>(Arrays.asList(200, 300, 400))));
        player.carryItem(100, 1);
        Assert.assertEquals("Bob 50\nCharlie 50\nDavid 50", player.combat(100, "EquAxe", new ArrayList<>(Arrays.asList(200, 300, 400))));
        player.reset();
    }
}