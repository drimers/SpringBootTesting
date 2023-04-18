package net.javaguides.springboot.service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.impl.EmployeeServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    private Employee employee;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    //private EmployeeService employeeService;

    @BeforeEach
    public void setup(){
        //employeeRepository = Mockito.mock(EmployeeRepository.class);
        //employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = Employee.builder()
                .id(1L)
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("Ramesh@example.com")
                .build();
    }

        //JUnit test for saveEmployee method
            @DisplayName("JUnit test for saveEmployee method")
            @Test
            public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
                //given - precondition or setup
//                Employee employee = Employee.builder()
//                        .id(1L)
//                        .firstName("Ramesh")
//                        .lastName("Ramesh")
//                        .email("Ramesh@example.com")
//                        .build();
               given(employeeRepository.findByEmail(employee.getEmail()))
                        .willReturn(Optional.empty());
               given(employeeRepository.save(employee)).willReturn(employee);

                System.out.println(employeeRepository);
                System.out.println(employeeService);
                //when - action or the behaviour that we are going to test
                Employee savedEmployee = employeeService.saveEmployee(employee);
                System.out.println(savedEmployee);
                //then - verify the output
                assertThat(savedEmployee).isNotNull();
            }

    //JUnit test for saveEmployee method which throws exception
    @DisplayName("JUnit test for saveEmployee method which throws exception")
    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowsException(){
        //given - precondition or setup

        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));
        //given(employeeRepository.save(employee)).willReturn(employee);

        System.out.println(employeeRepository);
        System.out.println(employeeService);
        //when - action or the behaviour that we are going to test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.saveEmployee(employee);
        });
//
//        Employee savedEmployee = employeeService.saveEmployee(employee);
//        System.out.println(savedEmployee);
//        //then - verify the output
//        assertThat(savedEmployee).isNotNull();

        //then
        verify(employeeRepository, never()).save(any(Employee.class));
    }
}
