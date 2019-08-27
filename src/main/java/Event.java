public class Event extends Task {
  
  protected String at;

  public Event(String description, String at) {
    super(description);
    this.at = at;
  }

  public String toString() {
    return "[E]" + super.toString() + " (at: " + this.at + ")";
  }

  public String toSaveFormat() {
    int isDone = this.isDone ? 1 : 0;
    return "E|" + super.toSaveFormat() + "|" + this.at;
  }
}
