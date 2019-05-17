package in.TSC.service;

import in.TSC.dao.EmployeeDAO;
import in.TSC.exception.ResourceNotFoundException;
import in.TSC.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDAO employeeDAO;

    public List<Employee> getEmployees(){
        return this.employeeDAO.findAll();
    }

    public Employee createEmployee(Employee employee) {
        return this.employeeDAO.save(employee);
    }

    public Employee getEmployeeById(Long employeeId) {
        return this.employeeDAO.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
    }

    public Employee updateEmployee( Employee employee) {
        return this.employeeDAO.save(employee);
    }


    public Employee updateNameEmployee(Long employeeId, Employee employee) {
        Employee employeeOld = this.employeeDAO.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        employeeOld.setName(employee.getName());

        Employee updatedEmployee = this.employeeDAO.save(employeeOld);
        return updatedEmployee;
    }

    public ResponseEntity<?> deleteEmployee(Long employeeId) {
        Employee employee = this.employeeDAO.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        this.employeeDAO.delete(employee);
        return ResponseEntity.ok().build();
    }
}
