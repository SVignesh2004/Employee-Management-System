import java.util.ArrayList;

public class EmployeeDirectory {
    public static void displayAllEmployees(ArrayList<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }


        System.out.printf("%-15s %-20s %-20s %-25s%n", "Employee ID", "Name", "Phone Number", "Department");
        System.out.println("--------------------------------------------------------------------------");

        for (Employee employee : employees) {
            System.out.printf("%-15s %-20s %-20s %-25s%n",
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getPhoneNumber(),
                    employee.getDepartment().getName());
        }

        System.out.println("--------------------------------------------------------------------------");
    }
}
