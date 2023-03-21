package Application;

import _Dictionary.IDictionary;

public class DeleteCommand extends AbstractCommand<Void>{


    public DeleteCommand(IDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public Void execute(String ignored) {


        System.out.print("Enter your input : ");
        boolean state = dictionary.delete(scanner.next());

        if (state)
            System.out.println("Deletion completed successfully");
        else
            System.out.println("Error , word doesn't exist");

        dictionary.printDictionary();
        return null;
    }
}
