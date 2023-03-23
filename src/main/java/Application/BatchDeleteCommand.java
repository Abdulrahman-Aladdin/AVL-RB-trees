package Application;

import _Dictionary.IDictionary;
import org.apache.commons.lang3.tuple.Pair;

public class BatchDeleteCommand extends AbstractCommand<Void>{

    public BatchDeleteCommand(IDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public Void execute(String ignored) {

        System.out.print("Enter the path : ");

        Pair<Integer,Integer> output = dictionary.batchDelete(scanner.nextLine());

        CLI.clearScreen();

        if (output == null){
            System.out.println("File not found");
            return null;
        }

        System.out.println("Number of words deleted = " + output.getLeft());
        System.out.println("Number of words existing = " + output.getRight());

        dictionary.printDictionary();
        return null;
    }
}
