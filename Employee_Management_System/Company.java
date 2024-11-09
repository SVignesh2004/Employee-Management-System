import java.util.ArrayList;
import java.util.HashMap;

public class Company {
    private String name;
    private HashMap<String, Employee> employees;
    private HashMap<String, Department> departments;

    public Company(String name) {
        this.name = name;
        employees = new HashMap<>();
        departments = new HashMap<>();
        initializeDepartments();
    }
    public String getName() {
        return name;
    }

    private void initializeDepartments() {
        // Initialize predefined departments
        for (String deptName : Department.getPredefinedDepartments()) {
            Department department = new Department(deptName);
            departments.put(deptName, department);
        }
    }

    public void addDepartment(Department department) {
        departments.put(department.getName(), department);
    }

    public Department getDepartmentByName(String name) {
        return departments.get(name);
    }

    public void addEmployee(Employee employee) {
        // Store the employee in the HashMap using their ID
        employees.put(employee.getEmployeeId(), employee);
        System.out.println("Employee added: " + employee.getEmployeeId());
    }

    public Employee getEmployeeById(String id) {
        return employees.get(id);
    }

    public void listDepartments() {
        if (departments.isEmpty()) {
            System.out.println("No departments available in " + getName() + ".");
        } else {
            System.out.println("Departments in " + getName() + ":");
            for (String deptName : departments.keySet()) {
                System.out.println("- " + deptName);
            }
        }
    }


    public HashMap<String, Employee> getAllEmployees() {
        return new HashMap<>(employees); // Return a copy of the employee map
    }
    public boolean deleteEmployeeById(String employeeId) {
        if (employees.containsKey(employeeId)) {

            Employee employee = employees.get(employeeId);

            Department department = employee.getDepartment();
            if (department != null) {
                department.removeEmployee(employee);
            }


            employees.remove(employeeId);
            System.out.println("Employee with ID " + employeeId + " has been deleted successfully.");
            return true;
        } else {
            System.out.println("Employee with ID " + employeeId + " not found.");
            return false;
        }
    }
    public ArrayList<Department> getAllDepartments() {
        return new ArrayList<>(departments.values());
    }


}
