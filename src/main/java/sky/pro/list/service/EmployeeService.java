package sky.pro.list.service;

import org.springframework.stereotype.Service;
import sky.pro.list.domain.Employee;
import sky.pro.list.exceptions.EmployeeAlreadyAddedException;
import sky.pro.list.exceptions.EmployeeNotFoundException;
import sky.pro.list.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    List<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {
        final int maxSize = 2;
        if (employees.size() >= maxSize) {
            throw new EmployeeStorageIsFullException();
        }
        if (checkFind(firstName, lastName)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(new Employee(firstName, lastName));
        return new Employee(firstName, lastName);
    }

    public Employee remove(String firstName, String lastName) {

        if (!checkFind(firstName, lastName)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(new Employee(firstName, lastName));
        return new Employee(firstName, lastName);
    }

    public Employee find(String firstName, String lastName) {

        if (!checkFind(firstName, lastName)) {
            throw new EmployeeNotFoundException();
        }
        return new Employee(firstName, lastName);
    }

    public List<Employee> showAll() {
        return employees;
    }

    private boolean checkFind(String firstName, String lastName) {
        return employees.contains(new Employee(firstName, lastName));
    }

}
