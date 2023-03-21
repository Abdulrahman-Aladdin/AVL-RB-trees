package Application;

import _Dictionary.IDictionary;

import java.util.Scanner;

public abstract class AbstractCommand<T> {

    IDictionary dictionary;
    Scanner scanner;

    public AbstractCommand(IDictionary dictionary){
        this.dictionary = dictionary;
        this.scanner = new Scanner(System.in);
    }
    public abstract T execute(String input);
}
