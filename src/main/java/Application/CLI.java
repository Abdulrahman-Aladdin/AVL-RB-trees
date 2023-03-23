package Application;

import _Dictionary.IDictionary;
import _Dictionary.Dictionary;

import java.util.Scanner;

import static java.lang.System.*;

public class CLI implements Runnable {

    Dictionary dictionary;
    Scanner scanner;

    public CLI() {
        scanner = new Scanner(in);
    }

    private void showMenu_1() {
        out.println("Choose dictionary backend tree:");
        out.println("1- AVL");
        out.println("2- RedBlack");
        out.print("Enter your choice : ");
    }

    private void showMenu_2(){
        out.println("Choose an operation:");
        out.println("1- Insert");
        out.println("2- Delete");
        out.println("3- Search");
        out.println("4- BatchInsert");
        out.println("5- BatchDelete");
        out.println("6- Size");
        out.println("7- TreeHeight");
        out.println("8- Exit");
        out.print("Enter your choice : ");
    }

    @Override
    public void run() {
        while (true) {
            showMenu_1();
            AbstractCommand<IDictionary> initialize = new InitializeCommand();
            dictionary = (Dictionary) initialize.execute(scanner.next());

            if (dictionary == null){
                CLI.clearScreen();
                out.println("Enter a valid option");
            } else break;
        }

        CLI.clearScreen();

        while (true){
            showMenu_2();
            AbstractCommand<Void> command = getCommandFromMenu(scanner.next());

            if (command == null){
                CLI.clearScreen();
                out.println("Enter a valid option");
                continue;
            }


            command.execute(null);
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 100; i++) out.println();


        /*try{
            String operatingSystem = System.getProperty("os.name");

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }catch(Exception e){
            e.printStackTrace();
        }*/
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
            case "8" -> CommandFactory.getCommand(Commands.EXIT,dictionary);
            default -> null;
        };
    }
}
