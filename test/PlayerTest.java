import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerTest {

    @Test
    public void improveEquipment() {
        Game.addAdventurer(100, "Alice");
        Game.addAdventurer(200, "Bob");
        Game.addEquipment(100, 1, "Sword", 100, "Axe", 100);
        Game.addEquipment(200, 2, "Shield", 10, "Axe", 10);
        Assert.assertEquals("Sword 101", Game.improveEquipment(100, 1));
        Assert.assertEquals("Shield 11", Game.improveEquipment(200, 2));
        Game.reset();
    }

    @Test
    public void deleteItem() {
        Game.addAdventurer(100, "Alice");
        Game.addAdventurer(200, "Bob");
        Game.addEquipment(100, 1, "Sword", 100, "Axe", 100);
        Game.addBottle(200, 2, "AtkB", 0, "AtkBottle", 10);
        Assert.assertEquals("Axe Sword 100", Game.deleteItem(100, 1));
        Game.carryItem(200, 2);
        Assert.assertEquals("AtkBottle AtkB 0", Game.deleteItem(200, 2));
        Game.reset();
    }

    @Test
    public void useBottle() {
        Game.addAdventurer(100, "Alice");
        Game.addBottle(100, 1, "AtkB", 0, "AtkBottle", 10);
        Game.addBottle(100, 2, "DefB", 402, "DefBottle", 20);
        Game.addBottle(100, 3, "HpB", 0, "HpBottle", 100);
        Assert.assertEquals("Alice fail to use AtkB", Game.useBottle(100, 1));
        Game.carryItem(100, 1);
        Assert.assertEquals("Alice 500 11 0", Game.useBottle(100, 1));
        Assert.assertEquals("Alice fail to use DefB", Game.useBottle(100, 2));
        Assert.assertEquals("Alice fail to use DefB", Game.useBottle(100, 2));
        Game.carryItem(100, 2);
        Assert.assertEquals("Alice 500 11 24", Game.useBottle(100, 2));
        Assert.assertEquals("Alice 500 11 24", Game.useBottle(100, 2));
        Game.reset();
    }

    @Test
    public void redeemWarfare(){
        Game.addAdventurer(100, "Alice");
        Game.addFragment(100, 1, "FragA");
        Game.addFragment(100, 2, "FragA");
        Game.addFragment(100, 3, "FragA");
        Game.addFragment(100, 4, "FragA");
        Assert.assertEquals("4: Not enough fragments collected yet", Game.redeemWarfare(100, "FragA", 1234));
        Game.addFragment(100, 5, "FragA");
        Game.addFragment(100, 6, "FragA");
        Assert.assertEquals("Congratulations! HpBottle FragA acquired", Game.redeemWarfare(100, "FragA", 1234));
        Game.addFragment(100, 7, "FragA");
        Game.addFragment(100, 8, "FragA");
        Game.addFragment(100, 9, "FragA");
        Game.addFragment(100, 10, "FragA");
        Assert.assertEquals("Congratulations! HpBottle FragA acquired", Game.redeemWarfare(100, "FragA", 2345));
        Game.addEquipment(100, 11, "EquAxe", 100, "Axe", 10);
        Game.addFragment(100, 12, "FragB");
        Game.addFragment(100, 13, "FragB");
        Game.addFragment(100, 14, "FragB");
        Game.addFragment(100, 15, "FragB");
        Game.addFragment(100, 16, "FragB");
        Assert.assertEquals("EquAxe 101", Game.redeemWarfare(100, "FragB", 11));
        Game.addBottle(100, 17, "AtkB", 1, "AtkBottle", 10);
        Game.addFragment(100, 18, "FragC");
        Game.addFragment(100, 19, "FragC");
        Game.addFragment(100, 20, "FragC");
        Game.addFragment(100, 21, "FragC");
        Game.addFragment(100, 22, "FragB");
        Assert.assertEquals("4: Not enough fragments collected yet", Game.redeemWarfare(100, "FragC", 17));
        Game.addFragment(100, 23, "FragC");
        Assert.assertEquals("AtkB 1", Game.redeemWarfare(100, "FragC", 17));
        Game.reset();
    }

    @Test
    public void combat() {
        Game.addAdventurer(100, "Alice");
        Game.addAdventurer(200, "Bob");
        Game.addAdventurer(300, "Charlie");
        Game.addAdventurer(400, "David");
        Game.addEquipment(100, 1, "EquAxe", 100, "Axe", 100);
        Assert.assertEquals("Adventurer 100 defeated", Game.combat(100, "EquAxe", "normal", new ArrayList<>(Arrays.asList(200, 300, 400))));
        Game.carryItem(100, 1);
        Assert.assertEquals("Bob 50\nCharlie 50\nDavid 50", Game.combat(100, "EquAxe", "normal", new ArrayList<>(Arrays.asList(200, 300, 400))));
        Game.employ(300, 100);
        Assert.assertEquals("Bob 5\nCharlie 5\nDavid 5", Game.combat(100, "EquAxe", "normal", new ArrayList<>(Arrays.asList(200, 300, 400))));
        Assert.assertEquals("Axe EquAxe 98",Game.deleteItem(300, 1));
        Game.addEquipment(100, 2, "EquAxe", 100, "Axe", 100);
        Game.addEquipment(200, 3, "EquAxe", 100, "Axe", 100);
        Game.carryItem(100, 2);
        Assert.assertEquals("465", Game.combat(100, "EquAxe", "chain", new ArrayList<>(Arrays.asList(200, 300, 400))));
        Game.reset();
    }

    @Test
    public void challenge(){
        Game.addAdventurer(100, "Alice");
        Game.addAdventurer(200, "Bob");
        Game.addAdventurer(300, "Charlie");
        Game.addAdventurer(400, "David");

        Game.addBottle(200, 2, "AtkB", 0, "AtkBottle", 999);
        Game.addBottle(300, 3, "AtkB", 0, "AtkBottle", 999);
        Game.addBottle(400, 4, "AtkB", 0, "AtkBottle", 999);
        Game.carryItem(200,2);
        Game.carryItem(300,3);
        Game.carryItem(400,4);

        Game.employ(100, 200);
        Assert.assertEquals("", Game.challenge(100));

        Game.useBottle(200, 2);
        Assert.assertEquals("Cloak of Shadows", Game.challenge(100));
        Game.addEquipment(100, 5, "EquAxe", 100, "Axe", 939);
        Game.carryItem(100, 5);
        Assert.assertEquals("Cloak of Shadows\nFlamebrand Sword", Game.challenge(100));
    }

    @Test
    public void test() {
        Game.executeCommand(new ArrayList<>(Arrays.asList("1", "988244353", "adv1")));
        Game.executeCommand(new ArrayList<>(Arrays.asList("2", "988244353", "1", "bottle","50","AtkBottle", "1000")));
        Game.executeCommand(new ArrayList<>(Arrays.asList("3", "988244353", "2", "equipment","50","Axe", "2000")));
        Game.executeCommand(new ArrayList<>(Arrays.asList("6", "988244353", "1")));
        Game.executeCommand(new ArrayList<>(Arrays.asList("6", "988244353", "2")));
        Game.executeCommand(new ArrayList<>(Arrays.asList("1", "1000000007", "adv2")));
        Game.executeCommand(new ArrayList<>(Arrays.asList("11", "988244353", "1000000007")));
        Game.executeCommand(new ArrayList<>(Arrays.asList("12", "988244353")));
        Game.executeCommand(new ArrayList<>(Arrays.asList("7", "988244353", "1")));
    }
}