package pro.sky.employeesaver;

import org.springframework.stereotype.Service;
import pro.sky.employeesaver.exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeesaver.exceptions.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String name, String surname){
        Employee newEmployee = new Employee(name, surname);

        if(employees.contains(newEmployee)) throw new EmployeeAlreadyAddedException();

        employees.add(newEmployee);
        return newEmployee;
    }

    public Employee removeEmployee(String name, String surname){
        Employee oldEmployee = new Employee(name, surname);

        if(!employees.contains(oldEmployee)) throw new EmployeeNotFoundException();

        employees.remove(oldEmployee);
        return oldEmployee;
    }

    public Employee findEmployee(String name, String surname) {
        Employee targetEmployee = new Employee(name, surname);

        if(!employees.contains(targetEmployee)) throw new EmployeeNotFoundException();

        return targetEmployee;
    }

    public List<Employee> getAll(){
        return employees;
    }
}
