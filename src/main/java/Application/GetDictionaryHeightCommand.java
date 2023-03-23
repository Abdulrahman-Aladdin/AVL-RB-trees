package Application;

import _Dictionary.IDictionary;

public class GetDictionaryHeightCommand extends AbstractCommand<Void>{


    public GetDictionaryHeightCommand(IDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public Void execute(String ignored) {
        int height = dictionary.treeHeight();

        CLI.clearScreen();
        System.out.println("Dictionary height = " + height);
        return null;
    }
}
