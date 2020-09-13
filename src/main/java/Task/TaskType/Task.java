package Task.TaskType;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        // set isDone to be true
        isDone = true;
        // print the marked task
        System.out.printf("    \n" +
                "   Nice! I've marked this task as done:\n" +
                "\t[%s] %s\n"
                ,getStatusIcon(),description);
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
