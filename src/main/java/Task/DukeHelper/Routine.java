/**
 * Routine class that runs the whole program
 */
package Task.DukeHelper;

import Task.Exception.OtherException;

import java.io.IOException;

public class Routine {
    /**
     * the order of the routine:
     * first, shows hello message
     * second, reads file from the specified path
     * third, handles input from user
     * after "bye", shows bye message then program ends
     * @throws IOException
     * @throws OtherException
     */
    public static void routine() throws IOException, OtherException {
        Ui.getHelloMessage();
        // read file first
        Storage.readFile();
        Parser.handleCommand();
        // create bye message
        Ui.createByeMessage();
    }

}
