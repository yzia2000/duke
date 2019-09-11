package seedu.duke;

import java.util.ArrayList;

public class TaskList {
  protected ArrayList<Task> list = new  ArrayList<Task>();
  protected Ui ui = new Ui();
  
  public TaskList(ArrayList<Task> list) {
    this.list = list;
  }

  public Task get(int index) {
    return list.get(index);
  }

  public int size() {
    return list.size();
  }

  public boolean isEmpty() {
    return list.isEmpty();
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
          ui.wrong_description_error();
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
          ui.wrong_description_error();
          return;
          /* task_type = input.next(); */
          /* System.out.println(); */
          /* continue; */
        }
      }
      else {
        ui.correct_command_error();
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

  public ArrayList<Task> return_list() {
    return list;
  }

  public void doTask(int i) {
    try {
      list.get(i).markAsDone();
      System.out.println("\t_____________________________________");
      System.out.println("\tNice! I've marked this task as done:");
      System.out.println("\t  " + (i + 1) + "." + list.get(i).toString());
      System.out.println("\t_____________________________________\n\n");
    }
    // Catch exception if wrong task id is done
    catch (IndexOutOfBoundsException e) {
      ui.task_doesnt_exist_error();
    }
  }

  public void removeTask(int i) {
    try {
      Task last_task = list.get(i);
      list.remove(i);
      System.out.println("\t_____________________________________");
      System.out.println("\tNoted. I have removed this task:");
      System.out.println("\t  " + (i + 1) + "." + last_task.toString());
      System.out.println("\tNow there are " + list.size() + " tasks left.");
      System.out.println("\t_____________________________________\n\n");
    }
    catch (IndexOutOfBoundsException e) {
      ui.task_doesnt_exist_error();
    }
  }

  public void displayList() {
    // If user inputs list without appending list even once.
    if (list.isEmpty()) {
      ui.show_empty_list_error();
      return;
    }

    System.out.println("\t_____________________________________");
    System.out.println("\tHere are the tasks in your list:");
    for (int i = 0; i < list.size(); i++) {
      System.out.println("\t" + (i + 1) + "." + list.get(i).toString());
    } 
    System.out.println("\t_____________________________________\n\n");
  }

  public void findTask(String task_description) {
    ArrayList<Integer> matching_tasks = new ArrayList<Integer>();
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getDescription().contains(task_description)) {
        matching_tasks.add(Integer.valueOf(i));
      }
    }
    if (matching_tasks.isEmpty()) {
      ui.task_doesnt_exist_error();
      return;
    }
    System.out.println("\t_____________________________________");
    System.out.println("\tFound " + matching_tasks.size() + ". Here you go.");
    for (Integer id : matching_tasks) {
      System.out.println("\t  " + (id + 1) + "." + list.get(id).toString());
    }
    System.out.println("\t_____________________________________\n\n");
  }

  public boolean equals(TaskList temp) {

    if (this.size() != temp.size()) {
      System.out.println("Length not equal");
      return false;  
    }

    for (int i = 0; i < this.size(); i++) {
      if (!this.get(i).equals(temp.get(i))) {
        System.out.println(this.get(i).description + temp.get(i).description + "?");
        return false;
      }
    }

    return true;

  }
}
