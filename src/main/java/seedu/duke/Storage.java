package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;

/**
 * A class that stores current task list and loads it on request from disc.
 */
public class Storage {
  private String filePath;
  
  public Storage(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Loads list of tasks from disc from a csv style file.
   *
   * @return an array list loaded from the disc.
   */
  public ArrayList<Task> load() {
    ArrayList<Task> list = new ArrayList<Task>();
    try {
      Scanner duke_txt = new Scanner(new File(this.filePath));
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
    return list;
  }
  
  /**
   * Saves the input task list to disc.
   *
   * @param input_list the list of tasks to save to disc.
   * @throws IOException if file could not be saved
   */
  public void save(ArrayList<Task> input_list) throws IOException {
    // if list has nothing just quit
    if (input_list.isEmpty()) {
      (new File(this.filePath)).delete();
      return;
    }

    //if data folder doesnt exist create it
    File directory = new File(this.filePath.split("/")[0]);
    if (! directory.exists()){
      directory.mkdir();
    }

    // save inputs
    String saved_line = input_list.get(0).toSaveFormat();
    for (int i = 1; i < input_list.size(); i++) {
      saved_line = saved_line + "\n" + input_list.get(i).toSaveFormat();
    }
    BufferedWriter writer = new BufferedWriter(new FileWriter(new File(this.filePath)));
    writer.write(saved_line);
    writer.close();
  }
}
