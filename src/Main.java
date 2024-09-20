import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> commands = new Main().scanCommands(); // 读取输入
        final Player player = Player.getInstance(); // 获取Player实例
        for (ArrayList<String> command : commands) {
            int adventurerId = Integer.parseInt(command.get(1));
            switch (command.get(0)) {
                case "1": {
                    String name = command.get(2);
                    player.addAdventurer(adventurerId, name);
                    break;
                }
                case "2": {
                    int id = Integer.parseInt(command.get(2));
                    String name = command.get(3);
                    int capacity = Integer.parseInt(command.get(4));
                    String type = command.get(5);
                    int combatEffectiveness = Integer.parseInt(command.get(6));
                    player.addBottle(adventurerId, id, name, capacity, type, combatEffectiveness);
                    break;
                }
                case "3": {
                    int id = Integer.parseInt(command.get(2));
                    String name = command.get(3);
                    int durability = Integer.parseInt(command.get(4));
                    int combatEffectiveness = Integer.parseInt(command.get(5));
                    player.addEquipment(adventurerId, id, name, durability, combatEffectiveness);
                    break;
                }
                case "4": {
                    int equipmentId = Integer.parseInt(command.get(2));
                    System.out.println(player.improveEquipment(adventurerId, equipmentId));
                    break;
                }
                case "5": {
                    int itemId = Integer.parseInt(command.get(2));
                    System.out.println(player.deleteItem(adventurerId, itemId));
                    break;
                }
                case "6": {
                    int itemId = Integer.parseInt(command.get(2));
                    player.carryItem(adventurerId, itemId);
                    break;
                }
                case "7": {
                    int bottleId = Integer.parseInt(command.get(2));
                    System.out.println(player.useBottle(adventurerId, bottleId));
                    break;
                }
                default:
                    break;
            }
        }
    }

    private ArrayList<ArrayList<String>> scanCommands() {
        ArrayList<ArrayList<String>> inputInfo = new ArrayList<>(); // 解析后的输入将会存进该容器中, 类似于c语言的二维数组
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim()); // 读取行数
        for (int i = 0; i < n; ++i) {
            String nextLine = scanner.nextLine(); // 读取本行指令
            String[] strings = nextLine.trim().split(" +"); // 按空格对行进行分割
            inputInfo.add(new ArrayList<>(Arrays.asList(strings))); // 将指令分割后的各个部分存进容器中
        }
        return inputInfo;
    }
}