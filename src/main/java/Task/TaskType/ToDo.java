/**
 * sub-Class of Task class that takes in a to-do task
 */
package Task.TaskType;

public class ToDo extends Task {
    public ToDo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}