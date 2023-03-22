package Application;

import _Dictionary.Dictionary;

public class CommandFactory {

    public static AbstractCommand getCommand(Commands command, Dictionary dictionary){
        return switch (command){
            case INSERT -> new InsertCommand(dictionary);
            case SEARCH -> new SearchCommand(dictionary);
            case DELETE -> new DeleteCommand(dictionary);
            case BATCH_INSERT -> new BatchInsertCommand(dictionary);
            case BATCH_DELETE -> new BatchDeleteCommand(dictionary);
            case GET_HEIGHT -> new GetDictionaryHeightCommand(dictionary);
            case GET_SIZE -> new GetDictionarySizeCommand(dictionary);
            case EXIT -> new ExitCommand();
            default -> null;
        };
    }

}
