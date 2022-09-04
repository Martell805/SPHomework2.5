package pro.sky.employeesaver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pro.sky.employeesaver.exceptions.EmployeeNotFoundException;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    DepartmentService departmentService;

    @Mock
    private EmployeeService employeeServiceMock;

    @BeforeEach
    public void beforeEach(){
        this.departmentService = new DepartmentService(employeeServiceMock);
    }

    @Test
    public void maxSalaryTestValid(){
        Mockito.when(employeeServiceMock.getAll()).thenReturn(List.of(
                new Employee("A", "A", 1000, 1),
                new Employee("B", "B", 10000, 1),
                new Employee("C", "C", 100000, 1),
                new Employee("D", "D", 1000, 2)
        ));

        ResponseEntity<Employee> result = this.departmentService.maxSalary(1);
        ResponseEntity<Employee> expected = new ResponseEntity<Employee>(new Employee("C", "C", 100000, 1), HttpStatus.OK);

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void maxSalaryTestInvalidDepartmentId(){
        ResponseEntity<Employee> result = this.departmentService.maxSalary(0);
        ResponseEntity<Employee> expected = new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void maxSalaryTestInvalidEmpty(){
        Mockito.when(employeeServiceMock.getAll()).thenReturn(List.of());

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> this.departmentService.maxSalary(1));
    }

    @Test
    public void minSalaryTestValid(){
        Mockito.when(employeeServiceMock.getAll()).thenReturn(List.of(
                new Employee("A", "A", 1000, 1),
                new Employee("B", "B", 10000, 1),
                new Employee("C", "C", 100000, 1),
                new Employee("D", "D", 1000, 2)
        ));

        ResponseEntity<Employee> result = this.departmentService.minSalary(1);
        ResponseEntity<Employee> expected = new ResponseEntity<Employee>(new Employee("A", "A", 1000, 1), HttpStatus.OK);

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void minSalaryTestInvalidDepartmentId(){
        ResponseEntity<Employee> result = this.departmentService.minSalary(0);
        ResponseEntity<Employee> expected = new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void minSalaryTestInvalidEmpty(){
        Mockito.when(employeeServiceMock.getAll()).thenReturn(List.of());

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> this.departmentService.minSalary(1));
    }

    @Test
    public void allTestValidNoNull(){
        Mockito.when(employeeServiceMock.getAll()).thenReturn(List.of(
                new Employee("A", "A", 1000, 1),
                new Employee("B", "B", 10000, 1),
                new Employee("C", "C", 100000, 1),
                new Employee("D", "D", 1000, 2)
        ));

        ResponseEntity<List<Employee>> result = this.departmentService.all(1);
        ResponseEntity<List<Employee>> expected = new ResponseEntity<List<Employee>>(List.of(
                new Employee("A", "A", 1000, 1),
                new Employee("B", "B", 10000, 1),
                new Employee("C", "C", 100000, 1)
        ), HttpStatus.OK);

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void allTestValidNoNullEmpty(){
        Mockito.when(employeeServiceMock.getAll()).thenReturn(List.of(
                new Employee("A", "A", 1000, 1),
                new Employee("B", "B", 10000, 1),
                new Employee("C", "C", 100000, 1),
                new Employee("D", "D", 1000, 2)
        ));

        ResponseEntity<List<Employee>> result = this.departmentService.all(3);
        ResponseEntity<List<Employee>> expected = new ResponseEntity<List<Employee>>(List.of(), HttpStatus.OK);

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void allTestInvalidNoNull(){
        ResponseEntity<List<Employee>> result = this.departmentService.all(0);
        ResponseEntity<List<Employee>> expected = new ResponseEntity<List<Employee>>(HttpStatus.BAD_REQUEST);

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void allTestValidNull(){
        Mockito.when(employeeServiceMock.getAll()).thenReturn(List.of(
                new Employee("A", "A", 1000, 1),
                new Employee("B", "B", 10000, 1),
                new Employee("C", "C", 100000, 1),
                new Employee("D", "D", 1000, 2)
        ));

        ResponseEntity<List<Employee>> result = this.departmentService.all(null);
        ResponseEntity<List<Employee>> expected = new ResponseEntity<List<Employee>>(List.of(
                new Employee("A", "A", 1000, 1),
                new Employee("B", "B", 10000, 1),
                new Employee("C", "C", 100000, 1),
                new Employee("D", "D", 1000, 2)
        ), HttpStatus.OK);

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void allTestValidNullEmpty(){
        Mockito.when(employeeServiceMock.getAll()).thenReturn(List.of());

        ResponseEntity<List<Employee>> result = this.departmentService.all(null);
        ResponseEntity<List<Employee>> expected = new ResponseEntity<List<Employee>>(List.of(), HttpStatus.OK);

        Assertions.assertEquals(result, expected);
    }
}
