/**
 * Task Class that encapsulates its features and commands
 * Acts as the super-class of ToDo, Event, and Deadline classes
 */
package Task.TaskType;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "O" : "X"); //return O or X symbols
    }

    public void markAsDone(){
        // set isDone to be true
        isDone = true;
        // print the marked task
        System.out.printf("    \n" +
                "   Nice! I've marked this task as done:\n" +
                "\t" + toString() +"\n");
    }

    public void deleteTaskMessage(){
        System.out.println("     Noted. I've removed this task: \n" +
                "     " + toString() + "\n");
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString() {
        return "["+ getStatusIcon() + "]" + getDescription();
    }
}
