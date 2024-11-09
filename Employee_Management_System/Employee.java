import java.util.Random;

public class Employee {
    private String employeeId;
    private String name;
    private String phoneNumber;
    private Department department;
    private String position;
    private Salary salary;
    private Attendance attendance;

    public Employee(String name, String phoneNumber, Department department, String position) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.position = position;
        this.salary = new Salary(position);
        this.attendance = new Attendance();
        this.employeeId = generateEmployeeId(); // Generate the ID here
    }


    private String generateEmployeeId() {
        String deptPart = department.getName().substring(0, 2).toUpperCase();
        String namePart = name.substring(0, 2).toUpperCase();
        String phonePart = getRandomDigitFromPhoneNumber();
        return deptPart + namePart + phonePart;
    }

    // random digit from the phone number
    private String getRandomDigitFromPhoneNumber() {
        if (phoneNumber.length() >= 10) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(phoneNumber.length());
            // Return a random digit from the phone number
            return String.valueOf(phoneNumber.charAt(randomIndex));
        } else {
            return "0"; // Fallback if the phone number is too short
        }
    }


    public String getEmployeeId() {
        return employeeId;
    }


    public double getSalary() {
        return salary.getAmount();
    }


    public double calculateSalary() {
        int daysPresent = attendance.getDaysPresent();
        return salary.calculateSalary(daysPresent);
    }


    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public Department getDepartment() {
        return department;
    }


    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + "\nName: " + name + "\nPhone: " + phoneNumber +
                "\nDepartment: " + department.getName() + "\nPosition: " + position;
    }


}
