package Task.DukeHelper;

import Task.Exception.OtherException;
import Task.Exception.ToDoException;

import java.io.IOException;
import java.util.Scanner;

public class Parser {

    // insert user input to the list
    public static void insertToList(String userInput) throws OtherException, IOException {
        String userInputFirstWord = userInput.split(" ")[0];

        switch(userInputFirstWord) {
        case "todo":
            try {
                TaskList.insertToDo(userInput);
            } catch (ToDoException e) {
                Ui.viewEmptyToDoMessage();
            }
            break;
        case "deadline":
            TaskList.insertDeadline(userInput);
            break;
        case "event":
            TaskList.insertEvent(userInput);
            break;
        default:
            throw new OtherException();
        }
        // update file
        Storage.createFile();
    }

    public static void handleCommand() throws IOException {
        Scanner echo = new Scanner(System.in);
        // scan user input
        String userInput = echo.nextLine();

        // execute command of the user input until "bye" is entered
        while (!userInput.equals("Bye")){
            // display list
            if(userInput.equals("list")){
                TaskList.getList();
            }
            else if(userInput.startsWith("done")){
                TaskList.setDone(userInput);
                // update the file
                Storage.createFile();
            }
            else if(userInput.startsWith("delete")){
                TaskList.delete(userInput);
                // update the file
                Storage.createFile();
            }
            else{
                // insert into list
                try {
                    insertToList(userInput);
                } catch (OtherException | IOException e){
                    Ui.viewInvalidCommandMessage();
                }
                // print newly added task
                TaskList.printAddedTask();
            }
            // ask user input
            userInput = echo.nextLine();
        }
    }

}
