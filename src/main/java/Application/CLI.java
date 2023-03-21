package Application;

import _Dictionary.IDictionary;
import _Dictionary.Dictionary;

import java.io.IOException;
import java.util.Scanner;

public class CLI implements Runnable {

    Dictionary dictionary;
    Scanner scanner;

    public CLI() {
        scanner = new Scanner(System.in);
    }

    private void showMenu_1() {
        System.out.println("Choose dictionary backend tree:");
        System.out.println("1- AVL");
        System.out.println("2- RedBlack");
        System.out.print("Enter your choice : ");
    }

    private void showMenu_2(){
        System.out.println("Choose an operation:");
        System.out.println("1- Insert");
        System.out.println("2- Delete");
        System.out.println("3- Search");
        System.out.println("4- BatchInsert");
        System.out.println("5- BatchDelete");
        System.out.println("6- Size");
        System.out.println("7- TreeHeight");
        System.out.print("Enter your choice : ");
    }

    @Override
    public void run() {
        while (true) {
            showMenu_1();
            AbstractCommand<IDictionary> initialize = new InitializeCommand();
            dictionary = (Dictionary) initialize.execute(scanner.next());

            if (dictionary == null){
                clearScreen();
                System.out.println("Enter a valid option");
            } else break;
        }


        while (true){
            clearScreen();
            showMenu_2();
            AbstractCommand<Void> command = getCommandFromMenu(scanner.next());

            if (command == null){
                clearScreen();
                System.out.println("Enter a valid option");
                continue;
            }

            command.execute(null);
        }
    }

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");

        } catch (IOException | InterruptedException ignored) {}
    }

    private AbstractCommand<Void> getCommandFromMenu(String commandNumber){
        return switch (commandNumber){
            case "1" -> CommandFactory.getCommand(Commands.INSERT,dictionary);
            case "2" -> CommandFactory.getCommand(Commands.DELETE,dictionary);
            case "3" -> CommandFactory.getCommand(Commands.SEARCH,dictionary);
            case "4" -> CommandFactory.getCommand(Commands.BATCH_INSERT,dictionary);
            case "5" -> CommandFactory.getCommand(Commands.BATCH_DELETE,dictionary);
            case "6" -> CommandFactory.getCommand(Commands.GET_SIZE,dictionary);
            case "7" -> CommandFactory.getCommand(Commands.GET_HEIGHT,dictionary);
            default -> null;
        };
    }
}
