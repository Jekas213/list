package sky.pro.list.service;

import org.springframework.stereotype.Service;
import sky.pro.list.domain.Employee;
import sky.pro.list.exceptions.EmployeeAlreadyAddedException;
import sky.pro.list.exceptions.EmployeeNotFoundException;
import sky.pro.list.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    List<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {
       Employee employee = new Employee(firstName, lastName);
        final int maxSize = 2;
        if (employees.size() >= maxSize) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> showAll() {
        return Collections.unmodifiableList(employees);
    }


}
