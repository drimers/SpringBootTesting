package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    //JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("Ramesh@example.com")
                .build();

        //when - action or the behaviour that we are going to test
        Employee saveEmployee = employeeRepository.save(employee);
        //then - verify the output
        assertThat(saveEmployee).isNotNull();
        assertThat(saveEmployee.getId()).isGreaterThan(0);
    }

    //JUnit test for get all employees operation
    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("Ramesh@example.com")
                .build();

        Employee employee1 = Employee.builder()
                .firstName("John")
                .lastName("Sena")
                .email("John@example.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);
        //when - action or the behaviour that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();
        //then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

    //JUnit test get employee by id operation
    @DisplayName("JUnit test get employee by id")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("Ramesh@example.com")
                .build();
        employeeRepository.save(employee);
        //when - action or the behaviour that we are going to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();
        //then - verify the output
        assertThat(employeeDB).isNotNull();
        //assertThat(employeeDB.getId()).isEqualTo(employee.getId());
    }

    //JUnit test get employee by email operation
    @DisplayName("JUnit test get employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("Ramesh@example.com")
                .build();
        employeeRepository.save(employee);
        //when - action or the behaviour that we are going to test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();
        //then - verify the output
        assertThat(employeeDB).isNotNull();
        //assertThat(employeeDB.getId()).isEqualTo(employee.getId());
    }


    //JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("Ramesh@example.com")
                .build();
        employeeRepository.save(employee);
        //when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("ram@example.com");
        Employee updatedEmployee =  employeeRepository.save(savedEmployee);
        //then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("ram@example.com");

    }
}
