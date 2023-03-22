package Application;

import _Dictionary.IDictionary;

public class ExitCommand extends AbstractCommand<Void> {

        public ExitCommand() {}

        @Override
        public Void execute(String ignored) {
            System.exit(0);
            return null;
        }

}
