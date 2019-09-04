import java.util.Scanner;

public class Ui {
  /* protected String task_type; */
  /* protected String task_description_full; */
  protected Scanner input;

  public Ui (Scanner input) {
    this.input = input;
  }

  public Ui () {
  }

  public String take_input() {
    String user_input = input.nextLine();
    return user_input;
  }

  public void show_save_error() {
    System.out.println("\t_____________________________________");
    System.out.println("\tCouldn't save file.");
    System.out.println("\t_____________________________________\n\n");
  }

  public void show_bye_message() {
    System.out.println("\t_____________________________________");
    System.out.println("\tBye. Hope to see you again soon!");
    System.out.println("\t_____________________________________");
  }
  
  public void correct_command_error() {
    System.out.println("\t_____________________________________");
    System.out.println("\tPlease enter a valid command: todo, deadline, event, list, bye, find, delete.");
    System.out.println("\t_____________________________________\n\n");
  }

  public void wrong_description_error() {
    System.out.println("\t_____________________________________");
    System.out.println("\tDescription needs a '/' before by/at");
    System.out.println("\t_____________________________________\n\n");
  }

  public void task_doesnt_exist_error() {
    System.out.println("\t_____________________________________");
    System.out.println("\tTask doesn't exist. Please choose another.");
    System.out.println("\t_____________________________________\n\n");
  }

  public void show_empty_list_error() {
    System.out.println("\t_____________________________________");
    System.out.println("\tList is empty. Please type another command apart from list.");
    System.out.println("\t_____________________________________\n\n");
  }

  public void empty_description_error() {
    System.out.println("\t_____________________________________");
    System.out.println("\tPlease enter a description after the command.");
    System.out.println("\t_____________________________________\n\n");
  }

  public void show_opening_string () {
    String logo = " ____        _        \n"
      + "|  _ \\ _   _| | _____ \n"
      + "| | | | | | | |/ / _ \\\n"
      + "| |_| | |_| |   <  __/\n"
      + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
  }
}
