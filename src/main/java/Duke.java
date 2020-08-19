/* import java.util.ArrayList; */
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greet = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greet);
        Scanner echo = new Scanner(System.in);
        //ArrayList<String> toDoList = new ArrayList<String>(100);

        String usrin = echo.nextLine();

        if(!usrin.equals("Bye")) {

            //toDoList.add(usrin);
//          Task t = new Task(usrin);

            System.out.println("   Added: " + usrin);

            while (!usrin.equals("Bye")) {
                usrin = echo.nextLine();

                if(usrin.equals("list")){
                    System.out.println("Here are the tasks in your list:\n");
/*
                    for (String num: toDoList)
                    {
                        int i = toDoList.indexOf(num) + 1;
                        System.out.println("   " + i + ". " + num);
                    }
*/

                }
//                if(!usrin.equals(""));
                if(!usrin.equals("list"))
                    System.out.println("   Added: " + usrin);
                //toDoList.add(usrin);
                //t = new Task(usrin);
            }
        }

        String exit = "\nBye. Hope to see you again soon!\n";
        System.out.println("   " + exit);
    }
}
