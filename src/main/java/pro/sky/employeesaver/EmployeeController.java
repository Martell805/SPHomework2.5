package pro.sky.employeesaver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public ResponseEntity add(@RequestParam("name") String name, @RequestParam("surname") String surname,
                              @RequestParam("salary") Integer salary, @RequestParam("departmentId") Integer departmentId){
        return employeeService.addEmployee(name, surname, salary, departmentId);
    }

    @GetMapping("/remove")
    public ResponseEntity remove(@RequestParam("name") String name, @RequestParam("surname") String surname){
        return employeeService.removeEmployee(name, surname);
    }

    @GetMapping("/find")
    public ResponseEntity find(@RequestParam("name") String name, @RequestParam("surname") String surname){
        return employeeService.findEmployee(name, surname);
    }

    @GetMapping("/maxSalary")
    public ResponseEntity maxSalary(@RequestParam("departmentId") Integer departmentId){
        return employeeService.maxSalary(departmentId);
    }

    @GetMapping("/minSalary")
    public ResponseEntity minSalary(@RequestParam("departmentId") Integer departmentId){
        return employeeService.minSalary(departmentId);
    }

    @GetMapping("/all")
    public ResponseEntity all(@RequestParam(value="departmentId", required=false) Integer departmentId){
        return employeeService.all(departmentId);
    }
}
