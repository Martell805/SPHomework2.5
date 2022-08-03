package pro.sky.employeesaver;

import lombok.Data;

@Data
public class Employee {
    private String name;
    private String surname;
    private int salary;
    private int departmentId;

    public Employee(String name, String surname, int salary, int departmentId) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.departmentId = departmentId;
    }
}
