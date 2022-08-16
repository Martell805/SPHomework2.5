package pro.sky.employeesaver;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private Boolean validateName(String name){
        return !StringUtils.isEmpty(name) && StringUtils.isAlpha(name) && !StringUtils.containsAny(name, new char[]{' ', '\n', '\t'});
    }

    private Boolean validateUserInfo(String name, String surname){
        return validateName(name) && validateName(surname);
    }

    public ResponseEntity addEmployee(String name, String surname, int salary, int departmentId){
        if(!validateUserInfo(name, surname)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        employees.stream()
                .filter(e -> e.getName().equals(name) && e.getSurname().equals(surname))
                .findAny()
                .ifPresent(e -> {throw new EmployeeAlreadyAddedException();});

        Employee newEmployee = new Employee(name, surname, salary, departmentId);
        employees.add(newEmployee);

        return new ResponseEntity<>(newEmployee, HttpStatus.OK);
    }

    public ResponseEntity removeEmployee(String name, String surname){
        if(!validateUserInfo(name, surname)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Employee oldEmployee = employees.stream()
                .filter(e -> e.getName().equals(name) && e.getSurname().equals(surname))
                .findAny()
                .orElseThrow(EmployeeNotFoundException::new);

        employees.remove(oldEmployee);
        return new ResponseEntity<>(oldEmployee, HttpStatus.OK);
    }

    public ResponseEntity findEmployee(String name, String surname) {
        if(!validateUserInfo(name, surname)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                employees.stream()
                    .filter(e -> e.getName().equals(name) && e.getSurname().equals(surname))
                    .findAny()
                    .orElseThrow(EmployeeNotFoundException::new),
                HttpStatus.OK);
    }

    public ResponseEntity maxSalary(int departmentId){
        return new ResponseEntity<>(
                employees.stream()
                        .filter(e -> e.getDepartmentId() == departmentId)
                        .max(Comparator.comparingInt(Employee::getSalary))
                        .orElseThrow(EmployeeNotFoundException::new),
                HttpStatus.OK);
    }

    public ResponseEntity minSalary(int departmentId){
        return new ResponseEntity<>(
                employees.stream()
                        .filter(e -> e.getDepartmentId() == departmentId)
                        .min(Comparator.comparingInt(Employee::getSalary))
                        .orElseThrow(EmployeeNotFoundException::new),
                HttpStatus.OK);
    }

    public ResponseEntity all(Integer departmentId){
        if(departmentId == null)
            return new ResponseEntity<>(
                    employees.stream()
                            .sorted(Comparator.comparingInt(Employee::getDepartmentId))
                            .collect(Collectors.toList()),
                    HttpStatus.OK);

        return new ResponseEntity<>(
                employees.stream()
                        .filter(e -> e.getDepartmentId() == departmentId)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
