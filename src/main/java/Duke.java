import java.util.Scanner;

public class Duke {
    // Utilize Task class
    protected static Task list[] = new Task[100];
    
    public static void main(String[] args) {
      String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
      System.out.println("Hello from\n" + logo);

      Scanner input = new Scanner(System.in);

      System.out.println();

      // Taking first input
      String user_input = input.nextLine();
      
      int index = 0;

      // Taking input and printing till user input is bye or the list hits 100
      while (!user_input.equals("bye") && index < 100) {
        String split_for_done[] = user_input.split("\\s");
        // If user inputs list
        if (user_input.equals("list")) {
          displayList();
        }
        // Do task.
        else if (split_for_done[0].equals("done")) {
          // Extracting which task to do
          int task_id = Integer.parseInt(user_input.split("\\s")[1]);
          doTask(task_id);
        }
        else {
          // Appending list
          list[index] = new Task(user_input);
          index++;
  
          System.out.println("\t_____________________________________");
          // Printing user input
          System.out.println("\tadded: " + user_input);  
          System.out.println("\t_____________________________________\n\n");
          // Taking user input again
        }
        user_input = input.nextLine();
        System.out.println();
      }
      System.out.println("\t_____________________________________");
      System.out.println("\tBye. Hope to see you again soon!");
      System.out.println("\t_____________________________________");

    }

    public static void displayList() {
      // If user inputs list without appending list even once.
      if (list[0] == null) {
        System.out.println("\t_____________________________________");
        System.out.println("\tList is empty. Please type another command apart from list.");
        System.out.println("\t_____________________________________\n\n");
        return;
      }

      System.out.println("\t_____________________________________");
      System.out.println("\tHere are the tasks in your list:");
      for (int i = 0; i < 100; i++) {
        if (list[i] == null) {
          break;
        }
        System.out.println("\t" + (i + 1) + ".[" + list[i].getStatusIcon() + "] " + list[i].getDescription());
      } 
      System.out.println("\t_____________________________________\n\n");
      return;
    }

    public static void doTask(int i) {
      // If task has not been created yet.
      if (list[i - 1] == null) {
        System.out.println("\t_____________________________________");
        System.out.println("\tTask doesn't exist. Please choose another.");
        System.out.println("\t_____________________________________\n\n");
        return;
      }
      // mark task as done
      list[i - 1].markAsDone();
      System.out.println("\t_____________________________________");
      System.out.println("\tNice! I've marked this task as done:");
      System.out.println("\t  " + (i) + ".[" + list[i - 1].getStatusIcon() + "] " + list[i - 1].getDescription());
      System.out.println("\t_____________________________________\n\n");
    }
}
