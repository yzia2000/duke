import java.util.ArrayList;

public class Tasklist {
  protected ArrayList<Task> list = new  ArrayList<Task>();
  
  public Tasklist(ArrayList<Task> list) {
    this.list = list;
  }

  public Task get(int index) {
    return list.get(index);
  }

  public void add (String task_type, String task_description_full) {
    // if tasktype is not ToDo
    if (task_type.equals("todo")) {
      list.add(new ToDo(task_description_full));
    } 
    else {
      // Extract task time and task description and initialize as deadline
      if (task_type.equals("deadline")) {
        try {
          String task_description = task_description_full.split("/", 2)[0];
          String task_time = task_description_full.split("/", 2)[1].substring(3);
          list.add(new Deadline(task_description, task_time));
        }
        // if /by is not included in deadline command
        catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("\t_____________________________________");
          System.out.println("\tDeadline needs a '/' before by");
          System.out.println("\t_____________________________________\n\n");
          return;
        }
      }
      // Extract task time and task description and initialize as event
      else if (task_type.equals("event")) {
        try {
          String task_description = task_description_full.split("/", 2)[0];
          String task_time = task_description_full.split("/", 2)[1].substring(3);
          list.add(new Event(task_description, task_time));
        }
        // if /at is not included in event command
        catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("\t_____________________________________");
          System.out.println("\tEvent needs a '/' before at");
          System.out.println("\t_____________________________________\n\n");
          return;
          /* task_type = input.next(); */
          /* System.out.println(); */
          /* continue; */
        }
      }
      else {
        System.out.println("\t_____________________________________");
        System.out.println("\tPlease enter a valid command: todo, deadline, event, list, bye, find, delete.");
        System.out.println("\t_____________________________________\n\n");
        return;
        /* task_type = input.next(); */
        /* System.out.println(); */
        /* continue; */
      }
    }

    // Printing user input
    /* try { */
    // The output to print on writing correct command
    String output = "\t  " + list.get(list.size() - 1).toString();
    System.out.println("\t_____________________________________");
    System.out.println("\tGot it. I've added this task:");
    System.out.println(output);  
    // Printing number of items in list
    System.out.println("\tNow you have " + list.size() + " tasks in the list.");
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

  public void doTask(int i) {
    try {
      list.get(i - 1).markAsDone();
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

  public void removeTask(int i) {
    try {
      Task last_task = list.get(i - 1);
      list.remove(i - 1);
      System.out.println("\t_____________________________________");
      System.out.println("\tNoted. I have removed this task:");
      System.out.println("\t  " + (i) + "." + last_task.toString());
      System.out.println("\tNow there are " + list.size() + " tasks left.");
      System.out.println("\t_____________________________________\n\n");
    }
    catch (IndexOutOfBoundsException e) {
      System.out.println("\t_____________________________________");
      System.out.println("\tTask doesn't exist. Please choose another.");
      System.out.println("\t_____________________________________\n\n");
    }
  }

}
