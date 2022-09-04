package pro.sky.employeesaver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pro.sky.employeesaver.exceptions.EmployeeAlreadyAddedException;
import pro.sky.employeesaver.exceptions.EmployeeNotFoundException;

import java.util.List;


public class EmployeeServiceTest {
    EmployeeService employeeService;
    Employee defaultEmployee;

    @BeforeEach
    public void beforeEach(){
        this.employeeService = new EmployeeService();
        this.defaultEmployee = new Employee("X", "X", 9999, 1);
        this.employeeService.addEmployee("X", "X", 9999, 1);
    }

   @Test
    public void addTestValid(){
        String name = "A";
        String surname = "A";
        int salary = 1000;
        int departmentId = 1;

        ResponseEntity<Employee> result = this.employeeService.addEmployee(name, surname, salary, departmentId);

        Employee employee = new Employee(name, surname, salary, departmentId);

        ResponseEntity<Employee> expected = new ResponseEntity<Employee>(employee, HttpStatus.OK);

        Assertions.assertEquals(result, expected);
        Assertions.assertEquals(employeeService.getAll(), List.of(defaultEmployee, employee));
    }

    @Test
    public void addTestInvalidData(){
        String name = "X";
        String surname = "X";
        int salary = 9999;
        int departmentId = 1;

        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> this.employeeService.addEmployee(name, surname, salary, departmentId));

        Assertions.assertEquals(employeeService.getAll(), List.of(defaultEmployee));
    }

    @Test
    public void addTestInvalidUserInfo(){
        String name = "1";
        String surname = "A";
        int salary = 1000;
        int departmentId = 1;

        ResponseEntity<Employee> result = this.employeeService.addEmployee(name, surname, salary, departmentId);
        ResponseEntity<Employee> expected = new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);

        Assertions.assertEquals(result, expected);
        Assertions.assertEquals(employeeService.getAll(), List.of(defaultEmployee));
    }

    @Test
    public void removeTestValid(){
        String name = "X";
        String surname = "X";

        ResponseEntity<Employee> result = this.employeeService.removeEmployee(name, surname);
        ResponseEntity<Employee> expected = new ResponseEntity<Employee>(defaultEmployee, HttpStatus.OK);

        Assertions.assertEquals(result, expected);
        Assertions.assertEquals(employeeService.getAll(), List.of());
    }

    @Test
    public void removeTestInvalidUserInfo(){
        String name = "A";
        String surname = "";

        ResponseEntity<Employee> result = this.employeeService.removeEmployee(name, surname);
        ResponseEntity<Employee> expected = new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);

        Assertions.assertEquals(result, expected);
        Assertions.assertEquals(employeeService.getAll(), List.of(defaultEmployee));
    }

    @Test
    public void removeTestInvalidData(){
        String name = "A";
        String surname = "X";

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> this.employeeService.removeEmployee(name, surname));

        Assertions.assertEquals(employeeService.getAll(), List.of(defaultEmployee));
    }

    @Test
    public void findTestValid(){
        String name = "X";
        String surname = "X";

        ResponseEntity<Employee> result = this.employeeService.findEmployee(name, surname);
        ResponseEntity<Employee> expected = new ResponseEntity<Employee>(defaultEmployee, HttpStatus.OK);

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void findTestInvalidUserInfo(){
        String name = "A";
        String surname = "";

        ResponseEntity<Employee> result = this.employeeService.removeEmployee(name, surname);
        ResponseEntity<Employee> expected = new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void findTestInvalidData(){
        String name = "A";
        String surname = "X";

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> this.employeeService.findEmployee(name, surname));
    }

    @Test
    public void getAllTest(){
        this.employeeService.addEmployee("A", "A", 1000, 1);

        Assertions.assertEquals(this.employeeService.getAll(), List.of(defaultEmployee, new Employee("A", "A", 1000, 1)));
    }
}
