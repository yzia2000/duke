package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Deadline extends Task {
  protected String by;
  protected String date_by;
  private Ui ui = new Ui();

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
    this.translate_date();
  }

  // translating date
  public void translate_date() {
    // splitting date into individual components
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/uuuu HHmm");
    LocalDateTime parsedDate;
    try {
      parsedDate = LocalDateTime.parse(this.by, formatter);
    }
    catch (DateTimeParseException e) {
      ui.date_time_error(); 
      this.date_by = this.by;
      return;
    }
   
    String suffix;
    switch (parsedDate.getDayOfMonth() % 10)
    {
      case 1:
        suffix = "st";
        break;
      case 2:
        suffix = "nd";
        break;
      case 3:
        suffix = "rd";
        break;
      default:
        suffix = "th";
        break;
    }

    if (parsedDate.getDayOfMonth() > 3 && parsedDate.getDayOfMonth() < 21) {
      suffix = "th";
    }

    DateTimeFormatter print_format = DateTimeFormatter.ofPattern("d'" + suffix + "' 'of' MMMM uuuu',' h:mma", Locale.ENGLISH);

    this.date_by = parsedDate.format(print_format);


  }

  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.date_by + ")";
  }

  public String toSaveFormat() {
    return "D|" + super.toSaveFormat() + "|" + this.by;
  }

  public boolean equals(Deadline temp) {
    if (this.description == temp.description && this.by == temp.by && this.date_by == temp.date_by) {
      return true; 
    }
    return false;
  }
}
