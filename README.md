# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ____________________________________________________________________________________________________
   
   Hello! I'm Duke
   What can I do for you?
   ____________________________________________________________________________________________________
   
   ```
# Features

The followings are the commands that you can use.

## 1. List
Shows the list of tasks. The output is like the following.
>Syntax
    
    list
>Example:
```
>>> list
____________________________________________________________________________________________________
    Here are the tasks in your list:
    1. [T][X] running
    2. [D][X] walking (by: Oct 29 2020 08:00 pm)
    3. [E][X] project meeting (at: Mon 2-4 pm)
____________________________________________________________________________________________________
```
## 2. Bye
Exits the program. The bye message shows beforehand.
>Syntax

    Bye
>Example:
```
>>> list
____________________________________________________________________________________________________
    Bye. Hope to see you again soon!
____________________________________________________________________________________________________
```
## 3. Find
Conducts one-to-one word search over all tasks in their task name.
>Syntax
    
    find <string>
>Example:
```
>>> find meeting
____________________________________________________________________________________________________
    Here are the matching tasks in your list:
    1. [E][X] project meeting (at: Mon 2-4 pm)
____________________________________________________________________________________________________
```
## 4. Done
Marks the selected task as done. 'O' is done and 'X' is not done.
>Syntax

    done <index>
>Example:
```
>>> done 2
____________________________________________________________________________________________________
    Nice! I've marked this task as done:
    [D][O] walking (by: Oct 29 2020 08:00 pm)
____________________________________________________________________________________________________
>>> list
____________________________________________________________________________________________________
    Here are the tasks in your list:
    1. [T][X] running
    2. [D][O] walking (by: Oct 29 2020 08:00 pm)
    3. [E][X] project meeting (at: Mon 2-4 pm)
____________________________________________________________________________________________________
```
## 5. Delete
Removes the selected task from the task list. 
>Syntax

    delete <index>
>Example:
```
>>> delete 2
____________________________________________________________________________________________________
    Noted. I've removed this task:
    [D][X] walking (by: Oct 29 2020 08:00 pm)
____________________________________________________________________________________________________
>>> list
____________________________________________________________________________________________________
    Here are the tasks in your list:
    1. [T][X] running
    2. [E][X] project meeting (at: Mon 2-4 pm)
____________________________________________________________________________________________________
```
## 6. ToDo
Adds new todo task to the list.
>Syntax

    todo <description>
>Example
```
>>> todo work
____________________________________________________________________________________________________
    Got it. I've added this task:
        [T][X] work
    Now you have 3 tasks in the list.
____________________________________________________________________________________________________
>>> list
____________________________________________________________________________________________________
   Here are the tasks in your list:
    1. [T][X] running
    2. [E][X] project meeting (at: Mon 2-4 pm)
    3. [T][X] work
____________________________________________________________________________________________________
```
## 7. Event
Adds new event task to the list.
>Syntax

    event <description> /at <start date and/or time> - <end date and/or time>
>Example
```
>>> event assembling robot /at Mon 2-4 pm 
____________________________________________________________________________________________________
    Got it. I've added this task:
        [E][X] assembling robot (at Mon 2-4 pm) 
    Now you have 4 tasks in the list.
____________________________________________________________________________________________________
>>> list
____________________________________________________________________________________________________
   Here are the tasks in your list:
    1. [T][X] running
    2. [E][X] project meeting (at: Mon 2-4 pm)
    3. [T][X] work
    4. [E][X] assembling robot (at: Mon 2-4 pm)
____________________________________________________________________________________________________
```
## 8. Deadline
Adds new deadline task to the list.
>Syntax

    deadline <description> /by <date and/or time>
>Example
```
>>> deadline homework assignment /by 29/09/2020
____________________________________________________________________________________________________
    Got it. I've added this task:
        [D][X] homework assignment (by: Sep 29 2020)
    Now you have 5 tasks in the list.
____________________________________________________________________________________________________
>>> list
____________________________________________________________________________________________________
   Here are the tasks in your list:
    1. [T][X] running
    2. [E][X] project meeting (at: Mon 2-4 pm)
    3. [T][X] work
    4. [E][X] assembling robot (at: Mon 2-4 pm)
    5. [D][X] homework assignment (by: Sep 29 2020)
____________________________________________________________________________________________________
```
# Summary of Commands
No. | Command | Purpose | Syntax
----|---------|---------|-------
1.|List|show the list|_list_
2.|Bye|exits the program|_Bye_
3.|Find|conducts one-to-one word search over all tasks|_find <string>_
4.|Done|marks the task as done|_done <index>_
5.|Delete|Removes the task|_delete <index>_
6.|ToDo|adds todo task to the list|_todo <description>_
7.|Event|adds event task to the list|_event /at <start date and/or time> - <end date and/or time>_
8.|Deadline|adds deadline task to the list|_<description> /by <date and/or time>_