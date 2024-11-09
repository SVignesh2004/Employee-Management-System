import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Company company = new Company("Team 9 Mass");
        System.out.println(company.getName());
        String adminUsername = "team9";
        String adminPassword = "123";


        boolean authenticated = false;
        while (!authenticated) {
            System.out.print("Enter admin username: ");
            String inputUsername = scanner.nextLine();

            System.out.print("Enter admin password: ");
            String inputPassword = scanner.nextLine();


            if (inputUsername.equals(adminUsername) && inputPassword.equals(adminPassword)) {
                authenticated = true;
                System.out.println("Login successful! Welcome to Employee Management System.");
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add Department");
            System.out.println("2. Add Employee");
            System.out.println("3. Mark Attendance");
            System.out.println("4. Calculate Salary");
            System.out.println("5. Display Employee Details");
            System.out.println("6. List All Departments");
            System.out.println("7. View Employee Attendance");
            System.out.println("8. View Employee Directory");
            System.out.println("9. Delete Employee");
            System.out.println("10. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Select a department from the predefined list or add a new department:");
                    String[] predefinedDepartments = Department.getPredefinedDepartments();
                    for (int i = 0; i < predefinedDepartments.length; i++) {
                        System.out.println((i + 1) + ". " + predefinedDepartments[i]);
                    }
                    System.out.println((predefinedDepartments.length + 1) + ". Add a new department");

                    System.out.print("Enter your choice: ");
                    int deptChoice = scanner.nextInt();
                    scanner.nextLine();

                    // add a new department
                    if (deptChoice == predefinedDepartments.length + 1) {
                        System.out.print("Enter the name of the new department: ");
                        String newDeptName = scanner.nextLine();

                        //  new department name
                        if (newDeptName.isEmpty() || newDeptName.matches(".*\\d.*")) {
                            System.out.println("Invalid department name. It should not be empty or contain numbers.");
                        } else {
                            Department newDepartment = new Department(newDeptName);
                            company.addDepartment(newDepartment);
                            System.out.println("New department '" + newDeptName + "' added successfully.");
                        }
                    } else if (deptChoice >= 1 && deptChoice <= predefinedDepartments.length) {
                        String selectedDeptName = predefinedDepartments[deptChoice - 1];
                        Department department = company.getDepartmentByName(selectedDeptName);
                        if (department == null) {
                            department = new Department(selectedDeptName);
                            company.addDepartment(department);
                            System.out.println("Department '" + selectedDeptName + "' created and added to the company.");
                        } else {
                            System.out.println("Department '" + selectedDeptName + "' already exists.");
                        }
                    } else {
                        System.out.println("Invalid choice. Please select a valid department number.");
                    }
                    break;


                case 2: // Add Employee
                    System.out.println("Select a position for the new employee:");
                    System.out.println("1. Developer");
                    System.out.println("2. Tester");
                    System.out.println("3. Manager");

                    System.out.print("Enter the number of the position: ");
                    int positionChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    String position = "";
                    switch (positionChoice) {
                        case 1:
                            position = "Developer";
                            break;
                        case 2:
                            position = "Tester";
                            break;
                        case 3:
                            position = "Manager";
                            break;
                        default:
                            System.out.println("Invalid choice, please choose a valid position.");
                            break;
                    }

                    // Add the employee
                    if (!position.isEmpty()) {
                        // Display all available departments (predefined and dynamically created)
                        System.out.println("Select a department from the list:");

                        // Get all departments (including dynamically created ones)
                        ArrayList<Department> allDepartments = company.getAllDepartments();
                        for (int i = 0; i < allDepartments.size(); i++) {
                            System.out.println((i + 1) + ". " + allDepartments.get(i).getName());
                        }

                        //  Select a department
                        System.out.print("Enter the number of the department for the new employee: ");
                        int departmentChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        // Validate the choice and get the department
                        Department selectedDepartment = null;
                        if (departmentChoice >= 1 && departmentChoice <= allDepartments.size()) {
                            selectedDepartment = allDepartments.get(departmentChoice - 1);
                        } else {
                            System.out.println("Invalid choice. Please select a valid department number.");
                            break;
                        }

                        // Input for employee name with validation
                        String employeeName = "";
                        while (true) {
                            System.out.print("Enter employee name (only alphabetic characters): ");
                            employeeName = scanner.nextLine();
                            if (employeeName.matches("[a-zA-Z]+")) {
                                break;
                            } else {
                                System.out.println("Invalid name. Please enter a name containing only letters (A-Z).");
                            }
                        }

                        // Input for phone number with validation
                        String employeePhoneNumber = "";
                        while (true) {
                            System.out.print("Enter employee phone number (10 digits): ");
                            employeePhoneNumber = scanner.nextLine();
                            if (employeePhoneNumber.matches("\\d{10}")) {
                                break;
                            } else {
                                System.out.println("Invalid phone number. Please enter exactly 10 digits.");
                            }
                        }

                        // Create the new employee
                        Employee newEmployee = new Employee(employeeName, employeePhoneNumber, selectedDepartment, position);
                        company.addEmployee(newEmployee);
                        selectedDepartment.addEmployee(newEmployee);
                        System.out.println("Employee added successfully with ID: " + newEmployee.getEmployeeId());
                    }
                    break;


                case 3: // Mark Attendance
                    System.out.print("Enter employee ID: ");
                    String empIdForMarkingAttendance = scanner.nextLine();
                    Employee employeeToMarkAttendance = company.getEmployeeById(empIdForMarkingAttendance);

                    if (employeeToMarkAttendance != null) {
                        employeeToMarkAttendance.getAttendance().markAttendance();
                        int daysPresent = employeeToMarkAttendance.getAttendance().getDaysPresent();
                        int totalWorkingDays = 30;
                        double attendancePercentage = (daysPresent / (double) totalWorkingDays) * 100;

                        System.out.println("Attendance marked for employee ID: " + employeeToMarkAttendance.getEmployeeId());
                        System.out.println("Days Present: " + daysPresent);
                        System.out.printf("Attendance Percentage: %.2f%%\n", attendancePercentage);
                    } else {
                        System.out.println("Employee not found. Please check the employee ID.");
                    }
                    break;



                case 4: // Calculate Salary
                    System.out.print("Enter employee ID: ");
                    String empIdForSalary = scanner.nextLine();
                    Employee empForSalary = company.getEmployeeById(empIdForSalary);

                    if (empForSalary != null) {
                        // Get the attendance and calculate salary based on it
                        int daysPresent = empForSalary.getAttendance().getDaysPresent();
                        double calculatedSalary = empForSalary.calculateSalary(); // Calculate salary with attendance

                        System.out.println("Salary for employee ID " + empForSalary.getEmployeeId() + " is: " + calculatedSalary);
                        System.out.println("Days Present: " + daysPresent);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;


                case 5:
                    // Display employee details
                    System.out.println("Enter employee ID to view details:");
                    String employeeId = scanner.nextLine();
                    Employee empForDetails = company.getEmployeeById(employeeId);
                    if (empForDetails != null) {
                        System.out.println(empForDetails);  // This will call the toString method, which includes the salary
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;


                case 6:
                    company.listDepartments();
                    break;

                case 7: // View Employee Attendance
                    System.out.print("Enter employee ID to view attendance: ");
                    String empIdForAttendanceView = scanner.nextLine();
                    Employee empForAttendanceView = company.getEmployeeById(empIdForAttendanceView);

                    if (empForAttendanceView != null) {
                        System.out.println("\nEmployee Attendance Details:");
                        System.out.println("------------------------------");
                        System.out.println("Employee ID: " + empForAttendanceView.getEmployeeId());
                        System.out.println("Name: " + empForAttendanceView.getName());
                        System.out.println("Department: " + empForAttendanceView.getDepartment().getName());
                        System.out.println("Days Present: " + empForAttendanceView.getAttendance().getDaysPresent() + " days.");
                        System.out.println("------------------------------");
                    } else {
                        System.out.println("Employee not found. Please check the employee ID.");
                    }
                    break;

                case 8: // View Employee Directory
                    EmployeeDirectory.displayAllEmployees(new ArrayList<>(company.getAllEmployees().values()));
                    break;


                case 9: // Delete Employee
                    System.out.print("Enter employee ID to delete: ");
                    String empIdToDelete = scanner.nextLine();
                    boolean isDeleted = company.deleteEmployeeById(empIdToDelete);
                    if (isDeleted) {
                        System.out.println("Employee deleted successfully.");
                    } else {
                        System.out.println("Employee not found. Deletion failed.");
                    }
                    break;



                case 10:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
        scanner.close();
    }
}
