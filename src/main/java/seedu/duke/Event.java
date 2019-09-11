package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Event extends Task {
  
  protected String at;
  // translated date version
  protected String date_at;
  private Ui ui = new Ui();

  public Event(String description, String at) {
    super(description);
    this.at = at;
    this.to_date();
  }
  
  // translating date
  public void to_date() {
    // splitting date
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/uuuu HHmm");
    LocalDateTime parsedDate;
    try {
      parsedDate = LocalDateTime.parse(this.at, formatter);
    }
    catch (DateTimeParseException e) {
      ui.date_time_error(); 
      this.date_at = this.at;
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

    this.date_at = parsedDate.format(print_format);
}

  public String toString() {
    return "[E]" + super.toString() + " (at: " + this.date_at + ")";
  }

  public String toSaveFormat() {
    return "E|" + super.toSaveFormat() + "|" + this.at;
  }

  public boolean equals(Event temp) {
    if (this.description == temp.description && this.at == temp.at && this.date_at == temp.date_at) {
      return true; 
    }
    return false;
  }
}
