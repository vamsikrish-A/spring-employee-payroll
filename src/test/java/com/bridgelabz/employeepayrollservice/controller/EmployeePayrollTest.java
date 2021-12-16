package com.bridgelabz.employeepayrollservice.controller;

import com.bridgelabz.employeepayrollservice.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollservice.dto.ResponseDto;
import com.bridgelabz.employeepayrollservice.entity.EmployeePayrollData;
import com.bridgelabz.employeepayrollservice.service.EmpPayrollService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePayrollTest {

    @InjectMocks
    private EmployeePayroll employeePayroll;

    @Mock
    private EmpPayrollService empPayrollService;

    @Test
    void givenEmployee_WhenAddedAddDetails_ShouldReturnSuccessResponseMessage() {
        ResponseEntity<ResponseDto> successResponse = new ResponseEntity<>
                ((new ResponseDto("Employee data added Successfully", HttpStatus.CREATED)), HttpStatus.CREATED);
        EmployeePayrollDto employeePayrollDto = new EmployeePayrollDto();
        employeePayrollDto.setName("Vamsi");
        employeePayrollDto.setDept("Production");
        employeePayrollDto.setSalary(30000);
        when(empPayrollService.saveEmployeeData(employeePayrollDto))
                .thenReturn(new ResponseDto("Employee data added Successfully", HttpStatus.CREATED));
        ResponseEntity<ResponseDto> actualResponse = employeePayroll.addEmployee(employeePayrollDto);
        Assertions.assertEquals(successResponse, actualResponse);
    }

    @Test
    void givenEmployee_WhenWantsToGetEmployeeDetails_ShouldReturnEmployeesList() {
        when(empPayrollService.getEmployeeData()).thenReturn(Lists.newArrayList(new EmployeePayrollData()));
        ResponseEntity<List<EmployeePayrollData>> actualResponse = employeePayroll.getEmployeeDetails();
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        Assertions.assertEquals(1,actualResponse.getBody().size());
    }
    @Test
    void givenEmployee_WhenGetEmployeeDetailsById_ShouldBeReturnThtEmployeeData() {
        int employeeId = 1;
        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        when(empPayrollService.getEmployeeDataById(employeeId)).thenReturn(employeePayrollData);
        ResponseEntity<EmployeePayrollData> actualResponse = employeePayroll.getEmployeeByID(employeeId);
        Assertions.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }
    @Test
    void givenEmployee_WhenEmployeeDetailsWereUpdatedById_ShouldReturnResponse() {
        ResponseEntity<ResponseDto> successResponse = new ResponseEntity<>
                ((new ResponseDto("Employee data was updated Successfully",
                        HttpStatus.ACCEPTED)),HttpStatus.ACCEPTED);
        int employeeId = 1;
        EmployeePayrollDto employeePayrollDto = new EmployeePayrollDto();
        employeePayrollDto.setName("Vamsi");
        employeePayrollDto.setDept("Production");
        employeePayrollDto.setSalary(30000);
        when(empPayrollService.updateEmployeeData(employeeId, employeePayrollDto))
                .thenReturn(new ResponseDto("Employee data was updated Successfully", HttpStatus.ACCEPTED));
        ResponseEntity<ResponseDto> actualResponse = employeePayroll.updateEmployeeData(employeeId, employeePayrollDto);
        Assertions.assertEquals(successResponse, actualResponse);
    }

    @Test
    void givenEmployee_WhenEmployeeDetailsDeletedById_ShouldReturnResponse() {
        ResponseEntity<ResponseDto> successResponse = new ResponseEntity<>
                ((new ResponseDto("Employee data deleted successfully",
                        HttpStatus.ACCEPTED)),HttpStatus.ACCEPTED);
        int employeeId = 1;
        when(empPayrollService.deleteProduct(employeeId))
                .thenReturn(new ResponseDto("Employee data deleted successfully", HttpStatus.ACCEPTED));
        ResponseEntity<ResponseDto> actualResponse = employeePayroll.deleteEmployeeData(employeeId);
        Assertions.assertEquals(successResponse, actualResponse);

    }
}
