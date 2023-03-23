package Application;

import _Dictionary.IDictionary;

public class InsertCommand extends AbstractCommand<Void>{

    public InsertCommand(IDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public Void execute(String ignored) {

        System.out.print("Enter your input : ");
        boolean state = dictionary.insert(scanner.nextLine());

        CLI.clearScreen();
        if (state)
            System.out.println("Insertion completed successfully");
        else
            System.out.println("Error , word already exist");

        dictionary.printDictionary();
        return null;
    }
}
