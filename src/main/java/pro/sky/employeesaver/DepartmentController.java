package pro.sky.employeesaver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/maxSalary")
    public ResponseEntity maxSalary(@RequestParam("departmentId") Integer departmentId){
        return departmentService.maxSalary(departmentId);
    }

    @GetMapping("/minSalary")
    public ResponseEntity minSalary(@RequestParam("departmentId") Integer departmentId){
        return departmentService.minSalary(departmentId);
    }

    @GetMapping("/all")
    public ResponseEntity all(@RequestParam(value="departmentId", required=false) Integer departmentId){
        return departmentService.all(departmentId);
    }
}
