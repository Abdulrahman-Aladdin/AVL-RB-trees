package Application;

import _Dictionary.IDictionary;
import org.apache.commons.lang3.tuple.Pair;

public class BatchInsertCommand extends AbstractCommand<Void>{


    public BatchInsertCommand(IDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public Void execute(String ignored) {

        Pair<Integer,Integer> output = dictionary.batchInsert(scanner.next());

        if (output == null){
            System.out.println("File not found");
            return null;
        }

        System.out.println("Number of words inserted = " + output.getLeft());
        System.out.println("Number of words existing = " + output.getRight());

        return null;
    }
}
