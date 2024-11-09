public class Salary {
    private double amount;


    public Salary(double amount) {
        this.amount = amount;
    }
    public Salary(String position) {
        this.amount = setSalaryBasedOnPosition(position);
    }
    private double setSalaryBasedOnPosition(String position) {
        switch (position.toLowerCase()) {
            case "developer":
                return 100000.00; // Developer salary
            case "tester":
                return 50000.00; // Tester salary
            case "manager":
                return 75000.00; // Manager salary
            default:
                return 0.0; // Default salary if the position is unknown
        }
    }

    public Salary() {
        this.amount = 0.0;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Calculate salary based on attendance
    public double calculateSalary(int daysPresent) {

        double attendanceFactor = daysPresent / 30.0;
        return amount * attendanceFactor;
    }
}
