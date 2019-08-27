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
      
      String list[] = new String[100];
      int index = 0;

      // Taking input and printing till user input is bye or the list hits 100
      while (!user_input.equals("bye") && index < 100) {
        
        // If user inputs list
        if (user_input.equals("list")) {

          // If user inputs list without appending list even once.
          if (list[0] == null) {
            System.out.println("\t_____________________________________");
            System.out.println("\tList is empty. Please type another command apart from list.");
            System.out.println("\t_____________________________________\n\n");
            user_input = input.nextLine();
            System.out.println();
            continue;
          }

          System.out.println("\t_____________________________________");
          for (int i = 0; i < 100; i++) {
            if (list[i] == null) {
              break;
            }
            System.out.println("\t" + (i + 1) + ". " + list[i]);
          } 
          System.out.println("\t_____________________________________\n\n");
          user_input = input.nextLine();
          System.out.println();
          continue;
        }
        // Appending list
        list[index] = user_input;
        index++;

        System.out.println("\t_____________________________________");
        // Printing user input
        System.out.println("\tadded: " + user_input);  
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
