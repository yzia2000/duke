package seedu.duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
  // Creating ArrayList of Task objects
  protected static TaskList list;
  protected static Storage storage = new Storage("data/duke.txt");
  protected static Ui ui = new Ui(new Scanner(System.in));
  protected static Parser parser = new Parser();


  public static void main(String[] args) {
    ui.show_opening_string();

    list = new TaskList(storage.load());

    System.out.println();

    // Taking the first word as input for task type
    String[] user_input = parser.parse(ui.take_input());

    // Taking input and printing till user input is bye or the list hits 100
    while (!user_input[0].equals("bye")) {
      // If user inputs list
      if (user_input[0].equals("find")) {
        if (user_input[1].equals("")) {
          ui.empty_description_error();
        }
        else {
          list.findTask(user_input[1]);
        }
      }

      else if (user_input[0].equals("delete")) {
        int task_id = Integer.parseInt(user_input[1]) - 1;
        list.removeTask(task_id);
      }
      
      // list tasks
      else if (user_input[0].equals("list")) {
        list.displayList();
      }

      // Do task.
      else if (user_input[0].equals("done")) {
        // Extracting which task to do from the rest of the line after inputting only task type in line 20
        int task_id = Integer.parseInt(user_input[1]) - 1;
        list.doTask(task_id);
      }

      else {
          // add task to list
        if (user_input[1].equals("")) {
          ui.empty_description_error();
        }
        else {
        list.add(user_input[0], user_input[1]);
        }
      }

      try {
        storage.save(list.return_list());  
      } catch(IOException e){
        ui.show_save_error();
      }
      user_input = parser.parse(ui.take_input());
      System.out.println();
    }
    ui.show_bye_message();
  }
}
