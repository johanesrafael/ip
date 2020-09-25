/**
 * Duke class that acts as main to execute the program
 */
package Task;

import Task.DukeHelper.Routine;
import Task.Exception.OtherException;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException, OtherException {
        Routine.routine();
    }

}
