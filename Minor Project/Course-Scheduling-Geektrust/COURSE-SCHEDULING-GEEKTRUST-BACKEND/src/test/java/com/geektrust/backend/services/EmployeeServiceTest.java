package com.geektrust.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import com.geektrust.backend.entities.Employee;
import com.geektrust.backend.exceptions.InvalidInputException;
import com.geektrust.backend.repositories.IEmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        // MockitoAnnotations.openMocks(this);
    }

    @Mock
    IEmployeeRepository iEmployeeRepository;

    @InjectMocks
    EmployeeService employeeService;


    @Test
    @DisplayName("Test create() method with valid email address")
    void testCreateWithValidEmail() {
        String email = "bikash.hutait03@gmail.com";
        Employee employee = new Employee(email);
        when(iEmployeeRepository.save(employee)).thenReturn(employee);
        Employee createdEmployee = employeeService.create(email);
        assertEquals(createdEmployee.getEmailAddress(), email);
        verify(iEmployeeRepository, times(1)).save(employee);
    }

    @Test
    @DisplayName("Test create() method with invalid email address")
    void testCreateWithInvalidEmail() {
        String email = "john";
        assertThrows(InvalidInputException.class, () -> {
            employeeService.create(email);
        });
        verify(iEmployeeRepository, never()).save(any(Employee.class));
    }

    
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
