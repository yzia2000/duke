import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {
  // Creating ArrayList of Task objects
  protected static ArrayList<Task> list = new ArrayList<Task>(); 
  protected static Storage storage = new Storage("data/duke.txt");


  public static void main(String[] args) {
    String logo = " ____        _        \n"
      + "|  _ \\ _   _| | _____ \n"
      + "| | | | | | | |/ / _ \\\n"
      + "| |_| | |_| |   <  __/\n"
      + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);

    Scanner input = new Scanner(System.in);
    
    list = storage.load();

    System.out.println();

    // Taking the first word as input for task type
    String task_type = input.next();

    // Index of new element that will be added
    int index = list.size();

    // Taking input and printing till user input is bye or the list hits 100
    while (!task_type.equals("bye")) {
      // If user inputs list
      if (task_type.equals("find")) {
        String task_description_full = input.nextLine().substring(1);
        findTask(task_description_full);
      }
      else if (task_type.equals("delete")) {

        int task_id = Integer.parseInt(input.nextLine().substring(1));
        removeTask(task_id);
        index--;

        if (list.isEmpty()) {
          index = 0;
        }
      }
      else if (task_type.equals("list")) {
        displayList();
      }
      // Do task.
      else if (task_type.equals("done")) {
        // Extracting which task to do from the rest of the line after inputting only task type in line 20
        int task_id = Integer.parseInt(input.nextLine().substring(1));
        doTask(task_id);
      }
      else {
        // if tasktype is todo initialize object of class ToDo
        if (task_type.equals("todo")) {
          String task_description_full = input.nextLine().substring(1);
          list.add(new ToDo(task_description_full));

          try {
            storage.save(list);  
          } catch(IOException e){
            System.out.println("\t_____________________________________");
            System.out.println("\tCouldn't save file.");
            System.out.println("\t_____________________________________\n\n");
          }
        } 
        // if tasktype is not ToDo
        else {
          // Extract task time and task description and initialize as deadline
          if (task_type.equals("deadline")) {
            try {
              String task_description_full = input.nextLine().substring(1);
              String task_description = task_description_full.split("/", 2)[0];
              String task_time = task_description_full.split("/", 2)[1].substring(3);
              list.add(new Deadline(task_description, task_time));
              try {
                storage.save(list);  
              } catch(IOException e){
                System.out.println("\t_____________________________________");
                System.out.println("\tCouldn't save file.");
                System.out.println("\t_____________________________________\n\n");
              }
            }
            // if /by is not included in deadline command
            catch (ArrayIndexOutOfBoundsException e) {
              System.out.println("\t_____________________________________");
              System.out.println("\tDeadline needs a '/' before by");
              System.out.println("\t_____________________________________\n\n");
              task_type = input.next();
              System.out.println();
              continue;
            }
          }
          // Extract task time and task description and initialize as event
          else if (task_type.equals("event")) {
            try {
              String task_description_full = input.nextLine().substring(1);
              String task_description = task_description_full.split("/", 2)[0];
              String task_time = task_description_full.split("/", 2)[1].substring(3);
              list.add(new Event(task_description, task_time));
              try {
                storage.save(list);  
              } catch(IOException e){
                System.out.println("\t_____________________________________");
                System.out.println("\tCouldn't save file.");
                System.out.println("\t_____________________________________\n\n");
              }
            }
            // if /at is not included in event command
            catch (ArrayIndexOutOfBoundsException e) {
              System.out.println("\t_____________________________________");
              System.out.println("\tEvent needs a '/' before at");
              System.out.println("\t_____________________________________\n\n");
              task_type = input.next();
              System.out.println();
              continue;
            }
          }
          else {
            System.out.println("\t_____________________________________");
            System.out.println("\tPlease enter a valid command: todo, deadline, event, list, bye");
            System.out.println("\t_____________________________________\n\n");
            task_type = input.next();
            System.out.println();
            continue;
          }
        }

        // Printing user input
        /* try { */
          // The output to print on writing correct command
          String output = "\t  " + list.get(index).toString();
          index++;
          System.out.println("\t_____________________________________");
          System.out.println("\tGot it. I've added this task:");
          System.out.println(output);  
          // Printing number of items in list
          System.out.println("\tNow you have " + index + " tasks in the list.");
          System.out.println("\t_____________________________________\n\n");
        /* } */
        // if unknown command is inputted
        /* catch (IndexOutOfBoundsException e) { */
        /*   System.out.println("\t_____________________________________"); */
        /*   System.out.println("\tPlease enter a valid command: todo, deadline, event, list, bye"); */
        /*   System.out.println("\t_____________________________________\n\n"); */
        /* } */
        // Taking user input again
      }
      task_type = input.next();
      System.out.println();
    }
    System.out.println("\t_____________________________________");
    System.out.println("\tBye. Hope to see you again soon!");
    System.out.println("\t_____________________________________");
    input.close();

  }

  public static void displayList() {
    // If user inputs list without appending list even once.
    if (list.isEmpty()) {
      System.out.println("\t_____________________________________");
      System.out.println("\tList is empty. Please type another command apart from list.");
      System.out.println("\t_____________________________________\n\n");
      return;
    }

    System.out.println("\t_____________________________________");
    System.out.println("\tHere are the tasks in your list:");
    for (int i = 0; i < list.size(); i++) {
      System.out.println("\t" + (i + 1) + "." + list.get(i).toString());
    } 
    System.out.println("\t_____________________________________\n\n");
  }

  public static void doTask(int i) {
    try {
      list.get(i - 1).markAsDone();
      try {
        storage.save(list);  
      } catch(IOException e){
        System.out.println("\t_____________________________________");
        System.out.println("\tCouldn't save file.");
        System.out.println("\t_____________________________________\n\n");
      }
      System.out.println("\t_____________________________________");
      System.out.println("\tNice! I've marked this task as done:");
      System.out.println("\t  " + (i) + "." + list.get(i - 1).toString());
      System.out.println("\t_____________________________________\n\n");
    }
    // Catch exception if wrong task id is done
    catch (IndexOutOfBoundsException e) {
      System.out.println("\t_____________________________________");
      System.out.println("\tTask doesn't exist. Please choose another.");
      System.out.println("\t_____________________________________\n\n");
    }
  }

  public static void removeTask(int i) {
    try {
      Task last_task = list.get(i - 1);
      list.remove(i - 1);
      System.out.println("\t_____________________________________");
      System.out.println("\tNoted. I have removed this task:");
      System.out.println("\t  " + (i) + "." + last_task.toString());
      System.out.println("\tNow there are " + list.size() + " tasks left.");
      System.out.println("\t_____________________________________\n\n");
      try {
        storage.save(list);
      } catch(IOException e){
        System.out.println("\t_____________________________________");
        System.out.println("\tCouldn't save file.");
        System.out.println("\t_____________________________________\n\n");
      }
    }
    catch (IndexOutOfBoundsException e) {
      System.out.println("\t_____________________________________");
      System.out.println("\tTask doesn't exist. Please choose another.");
      System.out.println("\t_____________________________________\n\n");
    }
  }
  
  public static void findTask(String task_description) {
    ArrayList<Integer> matching_tasks = new ArrayList<Integer>();
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getDescription().contains(task_description)) {
        matching_tasks.add(Integer.valueOf(i));
      }
    }
    if (matching_tasks.isEmpty()) {
      System.out.println("\t_____________________________________");
      System.out.println("\tTask doesn't exist. Please choose another.");
      System.out.println("\t_____________________________________\n\n");
      return;
    }
    System.out.println("\t_____________________________________");
    System.out.println("\tFound " + matching_tasks.size() + ". Here you go.");
    for (Integer id : matching_tasks) {
      System.out.println("\t  " + (id + 1) + "." + list.get(id).toString());
    }
    System.out.println("\t_____________________________________\n\n");
  }
}
