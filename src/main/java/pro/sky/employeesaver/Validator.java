package pro.sky.employeesaver;

import org.apache.commons.lang.StringUtils;

public class Validator {
    public static Boolean validateName(String name){
        return !StringUtils.isEmpty(name) && StringUtils.isAlpha(name) && !StringUtils.containsAny(name, new char[]{' ', '\n', '\t'});
    }

    public static Boolean validateName(String name, String surname){
        return validateName(name) && validateName(surname);
    }

    public static Boolean validateDepartmentId(int departmentId){
        return departmentId >= 1 && departmentId <= 5;
    }

    public static Boolean validateUserInfo(String name, String surname, int departmentId){
        return validateName(name, surname) && validateDepartmentId(departmentId);
    }
}
