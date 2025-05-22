package util;

import java.util.Scanner;

public class InputUtil {
    public static Scanner scanner = new Scanner(System.in);

    public static int inputInt(String info) {
        System.out.println(info + " : ");
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            }catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka, coba lagi: ");
            }
        }
    }

    public static String inputString(String info) {
        System.out.println(info + " : ");
        return scanner.nextLine();
    }

    public static boolean inputBoolean(String info) {
        System.out.println(info + " (y/n) : ");
        String input = scanner.nextLine().toLowerCase();
        return input.equals("y") || input.equals("yes") || input.equals("ya");
    }
}
