package util;

import domain.Task;
import domain.User;

import java.util.Map;
import java.util.Scanner;

public class InputUtils {

    private static Scanner keyboard = new Scanner(System.in);

    public static String inputText(String message) {
        System.out.println(message + ": ");
        return keyboard.nextLine();
    }

    public static String inputMandatoryText(String message) {
        String text = null;
        do {
            System.out.println(message + ": ");
            text = keyboard.nextLine();

        } while (text.equals(""));

        return text;
    }

    public static void pressEnterToContinue() {
        System.out.println("Pulsa ENTER para continuar . . .");
        keyboard.nextLine();
    }

    public static int inputIntNumber(String message) {
        String text = null;
        do {
            text = inputText(message);
        } while (!(isANumber(text)));

        return Integer.parseInt(text);
    }

    public void inputTaskName(User user) {
        Map<String, Task> tasks = user.getTaskMap();
        String name = null;
        do {
            name = InputUtils.inputMandatoryText("Introduce el nombre de la tarea");
        } while (!tasks.containsKey(name));
    }

    private static boolean isANumber(String text) {
        return text.matches("[0-9][0-9]*");
    }

}
