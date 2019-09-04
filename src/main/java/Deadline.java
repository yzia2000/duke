public class Deadline extends Task {
  protected String by;
  protected String date_by;

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
    this.translate_date();
  }

  // translating date
  public void translate_date() {
    // splitting date into individual components
    String date_split[] = by.split("/");
    int day = Integer.parseInt(date_split[0]);
    int month = Integer.parseInt(date_split[1]);
    int year = Integer.parseInt(date_split[2].split("\\s")[0]);
    String time_string = date_split[2].split("\\s")[1];
    if (time_string.length() == 3) {
      time_string = "0" + time_string;
    }
    int hours = Integer.parseInt(time_string.substring(0, 2));
    String mins = time_string.substring(2, 4);
    
    // generating month string
    String monthString = "";
    switch (month) {
      case 1:  monthString = "January";
               break;
      case 2:  monthString = "February";
               break;
      case 3:  monthString = "March";
               break;
      case 4:  monthString = "April";
               break;
      case 5:  monthString = "May";
               break;
      case 6:  monthString = "June";
               break;
      case 7:  monthString = "July";
               break;
      case 8:  monthString = "August";
               break;
      case 9:  monthString = "September";
               break;
      case 10: monthString = "October";
               break;
      case 11: monthString = "November";
               break;
      case 12: monthString = "December";
               break;
    }
    // generating day suffix
    String suffix;
    switch (day % 10)
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

    if (day > 3 && day < 21) {
      suffix = "th";
    }

    // AM or PM 
    String AM_PM = hours >= 12 ? "PM" : "AM";

    // 24 to 12 hour clock
    hours = hours > 12 ? hours - 12 : hours;
    
    // Account of zero hour
    if (hours == 0) {
      hours = 12;
    }

    this.date_by = day + suffix + " of " + monthString + " " + year + ", " + (hours % 13) + ":" + mins + AM_PM;

  }

  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.date_by + ")";
  }

  public String toSaveFormat() {
    int isDone = this.isDone ? 1 : 0;
    return "D|" + super.toSaveFormat() + "|" + this.by;
  }
}
