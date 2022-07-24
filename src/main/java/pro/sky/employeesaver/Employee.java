package pro.sky.employeesaver;

import lombok.Data;

@Data
public class Employee {
    private String name;
    private String surname;

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
