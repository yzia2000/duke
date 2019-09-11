package seedu.duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * A personal assitant that takes in user input and gives and performs an operation that can help the user
 * in his day to day needs. Has a tasklist with multiple features. 
 */

public class Duke {
  // Creating ArrayList of Task objects
  protected static TaskList list;
  protected static Storage storage = new Storage("data/duke.txt");
  protected static Ui ui = new Ui(new Scanner(System.in));
  protected static Parser parser = new Parser();

  /**
   * Runs Duke which commences the user to machine feedback loop until the user enters "bye".
   * Loads existing tasklist and performs operations like list, find, delete and add on the tasklist.
   * Saves the list to disk for next duke session inside data/duke.txt.
   * @see Storage, TaskList, Parser, Ui
   */
  public static void run() {
    ui.show_opening_string();

    list = new TaskList(storage.load());

    System.out.println();

    // Taking the first word as input for task type
    String raw_input = ui.take_input();

    // Taking input and printing till user input is bye
    while (!raw_input.equals("bye")) {
      list = parser.parse(raw_input, list);

      try {
        storage.save(list.return_list());  
      } catch(IOException e){
        ui.show_save_error();
      }
      raw_input = ui.take_input();

      System.out.println();
    }
    ui.show_bye_message();
  }

  public static void main(String[] args) {
    Duke.run();
  }
}
