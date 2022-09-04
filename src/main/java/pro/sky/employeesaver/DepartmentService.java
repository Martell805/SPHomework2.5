package pro.sky.employeesaver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pro.sky.employeesaver.exceptions.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    public ResponseEntity<Employee> maxSalary(int departmentId){
        if(!Validator.validateDepartmentId(departmentId)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                this.employeeService.getAll().stream()
                        .filter(e -> e.getDepartmentId() == departmentId)
                        .max(Comparator.comparingInt(Employee::getSalary))
                        .orElseThrow(EmployeeNotFoundException::new),
                HttpStatus.OK);
    }

    public ResponseEntity<Employee> minSalary(int departmentId){
        if(!Validator.validateDepartmentId(departmentId)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                this.employeeService.getAll().stream()
                        .filter(e -> e.getDepartmentId() == departmentId)
                        .min(Comparator.comparingInt(Employee::getSalary))
                        .orElseThrow(EmployeeNotFoundException::new),
                HttpStatus.OK);
    }

    public ResponseEntity<List<Employee>> all(Integer departmentId){
        if(departmentId == null)
            return new ResponseEntity<>(
                    this.employeeService.getAll().stream()
                            .sorted(Comparator.comparingInt(Employee::getDepartmentId))
                            .collect(Collectors.toList()),
                    HttpStatus.OK);

        if(!Validator.validateDepartmentId(departmentId)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                this.employeeService.getAll().stream()
                        .filter(e -> e.getDepartmentId() == departmentId)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
