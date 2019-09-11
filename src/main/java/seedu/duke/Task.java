package seedu.duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
      this.isDone = true;
    }

    public String getDescription() {
      return this.description;
    }

    public String toString() {
      return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public String toSaveFormat() {
      int isDone = this.isDone ? 1 : 0;
      return isDone + "|" + this.description;
    }

    public boolean equals(Task temp) {
      if (this.description == temp.description) {
        return true; 
      }
      return false;
    }
}
