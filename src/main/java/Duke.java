import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
      String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
      System.out.println("Hello from\n" + logo);

      Scanner input = new Scanner(System.in);

      System.out.println();

      // Taking first input
      String user_input = input.nextLine();
      
      // Taking input and printing till user input is bye
      while (!user_input.equals("bye")) {
        System.out.println("\t_____________________________________");
        // Printing user input
        System.out.println("\t" + user_input);  
        System.out.println("\t_____________________________________\n\n");
        // Taking user input again
        user_input = input.nextLine();
        System.out.println();
      }
      System.out.println("\t_____________________________________");
      System.out.println("\tBye. Hope to see you again soon!");
      System.out.println("\t_____________________________________");

    }
}
