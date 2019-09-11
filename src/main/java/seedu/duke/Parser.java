package seedu.duke;

public class Parser {
  public String[] split (String raw_input) {
    String[] user_input = raw_input.split(" ", 2);
    return user_input;
  }

  public TaskList parse (String raw_input, TaskList list) {
    Ui ui = new Ui();
    String[] user_input = this.split(raw_input); 
    /* return output; */

    // If user inputs list
    if (user_input[0].equals("find")) {
      if (user_input.length == 1) {
        ui.empty_description_error();
      }
      else {
        list.findTask(user_input[1]);
      }
    }

    else if (user_input[0].equals("delete")) {
      if (user_input.length == 1) {
        ui.empty_description_error();
      }
      else {
        int task_id = Integer.parseInt(user_input[1]) - 1;
        list.removeTask(task_id);
      }
    }

    // list tasks
    else if (user_input[0].equals("list")) {
      list.displayList();
    }

    // Do task.
    else if (user_input[0].equals("done")) {
      // Extracting which task to do from the rest of the line after inputting only task type in line 20
      if (user_input.length == 1) {
        ui.empty_description_error(); 
      }
      else {
        int task_id = Integer.parseInt(user_input[1]) - 1;
        list.doTask(task_id);
      }
    }

    else {
      // add task to list
      if (user_input[0].equals("todo") || user_input[0].equals("deadline") || user_input[0].equals("event")) {
        if (user_input.length == 1) {
          ui.empty_description_error();
        }
        else {
          list.add(user_input[0], user_input[1]);
        }
      }
      else if (!user_input[0].equals("bye")) {
        ui.correct_command_error();
      }
    }
    return list;
  }
}
