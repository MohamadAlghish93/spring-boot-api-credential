package in.TSC.controller;

import in.TSC.entity.Employee;
import in.TSC.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Get all Employees
    @RequestMapping(
            value = {"/getAll"},
            method = {RequestMethod.GET}
    )
    public List<Employee> get() {
        return employeeService.getEmployees();
    }

    // Create a new Employee
    @PostMapping("/add")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // Get Employee by Id
    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    // Update an Employee
    @PostMapping("/update")
    public Employee updateEmployee(
            @Valid @RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    // Update an Employee
    @PostMapping("/change_name/{id}")
    public Employee updateNameEmployee(@PathVariable(value = "id") Long employeeId,
                                       @Valid @RequestBody Employee employee) {
        return employeeService.updateNameEmployee(employeeId, employee);
    }

    // Delete an Employee
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }
}

