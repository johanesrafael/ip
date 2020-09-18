package Task.DukeHelper;

import Task.Exception.OtherException;

import java.io.IOException;

public class Routine {
    public static void routine() throws IOException, OtherException {
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
