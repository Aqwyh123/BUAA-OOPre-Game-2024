import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> commands = Invoker.scanCommands();
        for (ArrayList<String> command : commands) {
            Game.executeCommand(command);
        }
    }
}