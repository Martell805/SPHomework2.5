package pro.sky.employeesaver;

import org.springframework.stereotype.Service;
import pro.sky.employeesaver.exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeesaver.exceptions.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String name, String surname, int salary, int departmentId){
        employees.stream()
                .filter(e -> e.getName().equals(name) && e.getSurname().equals(surname))
                .findAny()
                .ifPresent(e -> {throw new EmployeeAlreadyAddedException();});

        Employee newEmployee = new Employee(name, surname, salary, departmentId);
        employees.add(newEmployee);
        return newEmployee;
    }

    public Employee removeEmployee(String name, String surname){
        Employee oldEmployee = employees.stream()
                .filter(e -> e.getName().equals(name) && e.getSurname().equals(surname))
                .findAny()
                .orElseThrow(EmployeeNotFoundException::new);

        employees.remove(oldEmployee);
        return oldEmployee;
    }

    public Employee findEmployee(String name, String surname) {
        return employees.stream()
                .filter(e -> e.getName().equals(name) && e.getSurname().equals(surname))
                .findAny()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee maxSalary(int departmentId){
        return employees.stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee minSalary(int departmentId){
        return employees.stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> all(Integer departmentId){
        if(departmentId == null)
            return employees.stream()
                .sorted(Comparator.comparingInt(Employee::getDepartmentId))
                .collect(Collectors.toList());

        return employees.stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }
}
