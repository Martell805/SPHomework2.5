package pro.sky.employeesaver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ValidatorTest {
    Boolean result;
    Boolean expected;

    @AfterEach
    public void afterEach() {
        Assertions.assertEquals(result, expected);
    }

    public static Stream<Arguments> provideParamsForValidateNameTests(){
        return Stream.of(
                Arguments.of("ASDF", true),
                Arguments.of("AS1F", false),
                Arguments.of("AS F", false),
                Arguments.of("", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForValidateNameTests")
    public void validateNameParamTest(String name, Boolean exp){
        result = Validator.validateName(name);
        expected = exp;
    }

    public static Stream<Arguments> provideParamsForValidateDoubleNameTests(){
        return Stream.of(
                Arguments.of("ASDF", "ASDF", true),
                Arguments.of("", "ASDF", false),
                Arguments.of("ASDF", "", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForValidateDoubleNameTests")
    public void validateDoubleNameParamTest(String name, String surname, Boolean exp){
        result = Validator.validateName(name, surname);
        expected = exp;
    }

    public static Stream<Arguments> provideParamsForValidateDepartmentIdTests(){
        return Stream.of(
                Arguments.of(1, true),
                Arguments.of(5, true),
                Arguments.of(0, false),
                Arguments.of(6, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForValidateDepartmentIdTests")
    public void validateDepartmentIdParamTest(Integer departmentId, Boolean exp){
        result = Validator.validateDepartmentId(departmentId);
        expected = exp;
    }

    public static Stream<Arguments> provideParamsForValidateUserInfoTests(){
        return Stream.of(
                Arguments.of("ASDF", "ASDF", 1, true),
                Arguments.of("ASDF", "ASDF", 0, false),
                Arguments.of("ASDF", "", 1, false),
                Arguments.of("", "ASDF", 1, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForValidateUserInfoTests")
    public void validateUserInfoParamTest(String name, String surname, Integer departmentId, Boolean exp){
        result = Validator.validateUserInfo(name, surname, departmentId);
        expected = exp;
    }
}
