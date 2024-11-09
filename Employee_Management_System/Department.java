import java.util.ArrayList;

public class Department {
    private String name;
    private ArrayList<Employee> employees;

    // Predefined departments as a static list
    private static final String[] PREDEFINED_DEPARTMENTS = {
            "IT Project Managers", "Business Analysts", "Network Engineers",
            "Software Testers", "Hardware Technicians", "Software Development",
            "Security Development", "IT Department"
    };

    public Department(String name) {
        this.name = name;
        employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public static void listPredefinedDepartments() {
        System.out.println("Predefined departments:");
        for (String dept : PREDEFINED_DEPARTMENTS) {
            System.out.println("- " + dept);
        }
    }
    public boolean removeEmployee(Employee employee) {
        if (employees.contains(employee)) {
            employees.remove(employee);
            System.out.println("Employee " + employee.getEmployeeId() + " removed from department " + name);
            return true;
        }
        return false;
    }


    public static Department getDepartmentByName(String name) {
        for (String deptName : PREDEFINED_DEPARTMENTS) {
            if (deptName.equalsIgnoreCase(name)) {
                return new Department(deptName);
            }
        }
        return null;
    }

    // add a new department
    public static Department addNewDepartment(String name) {
        return new Department(name);
    }


    public static String[] getPredefinedDepartments() {
        return PREDEFINED_DEPARTMENTS;
    }
}
