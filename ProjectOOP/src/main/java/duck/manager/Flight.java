package duck.manager;

public class Flight {
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Flight(String from, String to, String time, String date, String month, String year) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.date = date;
        this.month = month;
        this.year = year;
    }

    private String from;
    private String to;
    private String time;
    private String date;
    private String month;
    private String year;
}
