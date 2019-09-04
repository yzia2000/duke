public class Parser {
  public String[] parse (String user_input) {
    String[] output = user_input.split(" ", 2);
    return output;
  }
}
