package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a specific start and end time.
 * Extends the Task class and includes functionality to parse and handle date/time information.
 */

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an Event object with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param startTime   The start time of the event in the format "yyyy-MM-dd HH:mm".
     * @param endTime     The end time of the event in the format "yyyy-MM-dd HH:mm".
     * @throws DateTimeParseException If there is an issue parsing the date/time strings.
     */

    public Event(String description, String startTime, String endTime) {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.startTime = LocalDateTime.parse(startTime, formatter);
            this.endTime = LocalDateTime.parse(endTime, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format. Please use: yyyy-MM-dd HH:mm");
            // Handle the exception as needed, e.g., log it or inform the user
            throw e;
        }
    }
    /**
     * Gets the start time of the event.
     *
     * @return The start time as a LocalDateTime object.
     */

    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time as a LocalDateTime object.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns a string representation of the Event, including its description, start time, and end time.
     *
     * @return A string representation of the Event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedStartTime = startTime.format(formatter);
        String formattedEndTime = endTime.format(formatter);

        return "[E]" + super.toString() + " (from: " + formattedStartTime + " to: " + formattedEndTime + ")";
    }
}
