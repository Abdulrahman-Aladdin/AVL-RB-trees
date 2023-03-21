package Application;

import _Dictionary.IDictionary;

public class SearchCommand extends AbstractCommand<Void>{

    public SearchCommand(IDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public Void execute(String ignored) {

        System.out.print("Enter your input : ");
        boolean state = dictionary.search(scanner.next());

        if (state)
            System.out.println("Word found");
        else
            System.out.println("Word not found");


        return null;
    }
}
