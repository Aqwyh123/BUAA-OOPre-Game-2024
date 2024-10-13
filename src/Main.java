import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final ArrayList<ArrayList<String>> commands = scanCommands(); // 读取输入
        final Player player = Player.getInstance(); // 获取Player实例
        for (ArrayList<String> command : commands) {
            int adventurerId = Integer.parseInt(command.get(1));
            String op = command.get(0);
            if (Objects.equals(op, "1")) {
                String name = command.get(2);
                player.addAdventurer(adventurerId, name);
            } else if (Objects.equals(op, "2") || Objects.equals(op, "3")) {
                String name = command.get(3);
                int unitId = Integer.parseInt(command.get(2));
                int attribution = Integer.parseInt(command.get(4));
                String type = command.get(5);
                int ce = Integer.parseInt(command.get(6));
                if (Objects.equals(op, "3")) {
                    player.addEquipment(adventurerId, unitId, name, attribution, type, ce);
                } else {
                    player.addBottle(adventurerId, unitId, name, attribution, type, ce);
                }
            } else if (Objects.equals(op, "4")) {
                int unitId = Integer.parseInt(command.get(2));
                System.out.println(player.improveEquipment(adventurerId, unitId));
            } else if (Objects.equals(op, "5")) {
                int unitId = Integer.parseInt(command.get(2));
                System.out.println(player.deleteItem(adventurerId, unitId));
            } else if (Objects.equals(op, "6")) {
                int unitId = Integer.parseInt(command.get(2));
                player.carryItem(adventurerId, unitId);
            } else if (Objects.equals(op, "7")) {
                int unitId = Integer.parseInt(command.get(2));
                System.out.println(player.useBottle(adventurerId, unitId));
            } else if (Objects.equals(op, "8")) {
                int unitId = Integer.parseInt(command.get(2));
                String name = command.get(3);
                player.addFragment(adventurerId, unitId, name);
            } else if (Objects.equals(op, "9")) {
                String name = command.get(2);
                int welfareId = Integer.parseInt(command.get(3));
                System.out.println(player.redeemWarfare(adventurerId, name, welfareId));
            } else if (Objects.equals(op, "10")) {
                String name = command.get(2);
                int adventureNum = Integer.parseInt(command.get(3));
                ArrayList<Integer> adventurerIds = new ArrayList<>();
                for (int i = 0; i < adventureNum; i++) {
                    adventurerIds.add(Integer.parseInt(command.get(4 + i)));
                }
                System.out.println(player.combat(adventurerId, name, adventurerIds));
            }
        }
    }

    private static ArrayList<ArrayList<String>> scanCommands() {
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