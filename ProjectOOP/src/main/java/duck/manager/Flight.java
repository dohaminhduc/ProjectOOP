package duck.manager;

import java.time.LocalDate;

public class Flight {
    private String from;
    private String to;
    private String time;
    private LocalDate date;

    public Flight(String from, String to, String time, LocalDate date) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.date = date;
    }

    // Getter and setter methods
    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
