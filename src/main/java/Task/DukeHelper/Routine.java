/**
 * Routine class that runs the whole program
 */
package Task.DukeHelper;

import Task.Exception.OtherException;

import java.io.IOException;

public class Routine {
    public static void routine() throws IOException, OtherException {
        Ui.getHelloMessage();
        // read file first
        Storage.readFile();
        Parser.handleCommand();
        // create bye message
        Ui.createByeMessage();
    }

}
