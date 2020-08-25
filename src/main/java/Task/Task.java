package Task;

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
        isDone = true;
        System.out.printf(
                "    \n" +
                "Nice! I've marked this task as done:\n" +
                "[%s] %s\n"
                ,getStatusIcon(),description
        );
    }

    public String getDescription(){
        return description;
    }
}
