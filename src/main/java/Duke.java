import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
  // Creating ArrayList of Task objects
  protected static ArrayList<Task> list = new ArrayList<Task>(); 

  public static void main(String[] args) {
    String logo = " ____        _        \n"
      + "|  _ \\ _   _| | _____ \n"
      + "| | | | | | | |/ / _ \\\n"
      + "| |_| | |_| |   <  __/\n"
      + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);

    Scanner input = new Scanner(System.in);

    load();

    System.out.println();

    // Taking the first word as input for task type
    String task_type = input.next();

    // Index of new element that will be added
    int index = list.size();

    // Taking input and printing till user input is bye or the list hits 100
    while (!task_type.equals("bye")) {
      // If user inputs list
      if (task_type.equals("delete")) {
        int task_id = Integer.parseInt(input.nextLine().substring(1));
        removeTask(task_id);
        index--;
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
            save();  
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
                save();  
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
                save();  
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

  // Loads duke.txt
  public static void load() {
    try {
      Scanner duke_txt = new Scanner(new File("data/duke.txt"));
      while (duke_txt.hasNextLine()) {
        // splits line input based on |
        String task_string[] = duke_txt.nextLine().split("\\|");

        // instantiate classes
        if (task_string[0].equals("T")) {
          list.add(new ToDo(task_string[2]));
        }
        else if (task_string[0].equals("D")) {
          list.add(new Deadline(task_string[2], task_string[3]));
        }
        else {
          list.add(new Event(task_string[2], task_string[3]));
        }

        if (task_string[1].equals("1")) {
          list.get(list.size() - 1).markAsDone();
        }
      }
      duke_txt.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("\t_____________________________________");
      System.out.println("\tNo list saved in database. Please create a list now.");
      System.out.println("\t_____________________________________\n\n");
    }
  }

  public static void save() throws IOException {
    // if list has nothing just quit
    if (list.isEmpty()) {
      return;
    }

    // if data folder doesnt exist create it
    File directory = new File("data");
    if (! directory.exists()){
      directory.mkdir();
    }

    // save inputs
    String saved_line = list.get(0).toSaveFormat();
    for (int i = 1; i < list.size(); i++) {
      /* if (list[i] == null) { */
      /*   break; */
      /* } */
      saved_line = saved_line + "\n" + list.get(i).toSaveFormat();
    }
    BufferedWriter writer = new BufferedWriter(new FileWriter(new File("data/duke.txt")));
    writer.write(saved_line);
    writer.close();
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
        save();  
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
        save();
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
}
