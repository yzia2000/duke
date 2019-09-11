package seedu.duke;

public class ToDo extends Task {
  public ToDo(String description) {
    super(description);
  }

  public String toString() {
    return "[T]" + super.toString();
  }

  public String toSaveFormat() {
    int isDone = this.isDone ? 1 : 0;
    return "T|" + super.toSaveFormat(); 
  }
  public boolean equals(ToDo temp) {
    if (this.description == temp.description) {
      return true; 
    }
    System.out.println("they arent equal");
    return false;
  }
}
