package Task.DukeHelper;

public class Constants {

    // introduce a constant for list size
    public static final int MAX_LIST_SIZE = 100;
    // create relative path
    public static String folder = "\\data";
    public static String path ="D:" + folder;
    // create file name variable
    public static  String fileName = "progress.txt";

    public enum command{
        GREET,
        BYE,
        PROMPT_INPUT,
        ADD_TO_DEADLINE,
        ADD_TO_TODO,
        ADD_TO_EVENT,
        SHOW_LIST,
        SHOW_MARKED_AS_DONE,
        DELETE_TASK,
        SAVE_FILE,
        LOAD_FILE,
    }
}
