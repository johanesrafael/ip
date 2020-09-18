package Task;

import Task.DukeHelper.Parser;
import Task.DukeHelper.Storage;
import Task.DukeHelper.Ui;
import Task.Exception.OtherException;

import java.io.IOException;


public class Duke {
    public static void main(String[] args) throws IOException, OtherException {
        // create logo
        Ui.createLogo();
        // greet
        Ui.greet();
        // read file first
        Storage.readFile();
        Parser.handleCommand();
        // create bye message
        Ui.createByeMessage();
    }
}
