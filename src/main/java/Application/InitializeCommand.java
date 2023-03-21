package Application;

import _Dictionary.IDictionary;
import Trees.Tree;
import _Dictionary.DictionaryFactory;

public class InitializeCommand extends AbstractCommand<IDictionary>{


    public InitializeCommand() {
        super(null);
    }

    @Override
    public IDictionary execute(String input) {
        this.dictionary =  switch (input){
            case "1" -> DictionaryFactory.getDictionary(Tree.AVL);
            case "2" -> DictionaryFactory.getDictionary(Tree.RED_BLACK);
            default -> null;
        };

        return this.dictionary;
    }
}
