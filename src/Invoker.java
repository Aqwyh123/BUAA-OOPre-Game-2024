import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Invoker {
    public static ArrayList<ArrayList<String>> scanCommands() {
        ArrayList<ArrayList<String>> commands = new ArrayList<>(); // 解析后的输入将会存进该容器中, 类似于c语言的二维数组
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim()); // 读取行数
        for (int i = 0; i < n; ++i) {
            String nextLine = scanner.nextLine(); // 读取本行指令
            String[] strings = nextLine.trim().split(" +"); // 按空格对行进行分割
            commands.add(new ArrayList<>(Arrays.asList(strings))); // 将指令分割后的各个部分存进容器中
        }
        return commands;
    }
}
