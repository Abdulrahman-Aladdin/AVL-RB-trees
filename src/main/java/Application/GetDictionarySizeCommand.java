package Application;

import _Dictionary.IDictionary;

public class GetDictionarySizeCommand extends AbstractCommand<Void>{


    public GetDictionarySizeCommand(IDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public Void execute(String ignored) {
        int size = dictionary.size();

        System.out.println("Dictionary size = " + size);

        return null;
    }
}
