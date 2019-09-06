package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime at;
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, DATE_TIME_FORMAT);
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at);
    }

    public LocalDateTime getAt() {
        return at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at.format(DATE_TIME_FORMAT));
    }
}