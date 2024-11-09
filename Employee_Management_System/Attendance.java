public class Attendance {
    private int daysPresent;

    public Attendance() {
        this.daysPresent = 0;
    }

    public void markAttendance() {
        daysPresent++;
    }

    public int getDaysPresent() {
        return daysPresent;
    }
}
