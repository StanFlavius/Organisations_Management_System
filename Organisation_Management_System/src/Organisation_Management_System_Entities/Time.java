package Organisation_Management_System_Entities;

public class Time {
    private Integer year;
    private Integer month;

    public Time(){
        year = 2020;
        month = 3;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
