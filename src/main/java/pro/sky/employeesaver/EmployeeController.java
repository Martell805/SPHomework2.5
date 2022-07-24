package pro.sky.employeesaver;

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
    public Employee add(@RequestParam(value="name") String name,
                        @RequestParam(value="surname", required=false) String surname){
        return employeeService.addEmployee(name, surname);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam(value="name") String name,
                           @RequestParam(value="surname") String surname){
        return employeeService.removeEmployee(name, surname);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam(value="name") String name,
                         @RequestParam(value="surname") String surname){
        return employeeService.findEmployee(name, surname);
    }

    @GetMapping("/getAll")
    public List<Employee> getAll(){
        return employeeService.getAll();
    }
}
