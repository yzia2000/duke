package seedu.duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
  // Creating ArrayList of Task objects
  protected static TaskList list;
  protected static Storage storage = new Storage("data/duke.txt");
  protected static Ui ui = new Ui(new Scanner(System.in));
  protected static Parser parser = new Parser();


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
