package com.bridgelabz.employeepayrollservice.service;

import com.bridgelabz.employeepayrollservice.builder.EmployeePayrollBuilder;
import com.bridgelabz.employeepayrollservice.controller.EmployeePayroll;
import com.bridgelabz.employeepayrollservice.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollservice.dto.ResponseDto;
import com.bridgelabz.employeepayrollservice.entity.EmployeePayrollData;
import com.bridgelabz.employeepayrollservice.exception.DataNotFoundException;
import com.bridgelabz.employeepayrollservice.repository.EmployeeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeePayrollServiceTest {

    @InjectMocks
    private EmpPayrollService empPayrollService;

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private EmployeePayrollBuilder employeePayrollBuilder;

    @Test
    void whenGetEmployeeDetailsCalled_ShouldReturnTheListOfEmployeePayroll() {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        employeePayrollData.setName("Vamsi");
        employeePayrollData.setDept("Production");
        employeePayrollData.setSalary(30000);
        employeePayrollList.add(employeePayrollData);

        EmployeePayrollData employeePayrollData1 = new EmployeePayrollData();
        employeePayrollData1.setName("Krishna");
        employeePayrollData1.setDept("Design");
        employeePayrollData1.setSalary(35000);
        employeePayrollList.add(employeePayrollData1);

        when(employeeRepo.findAll()).thenReturn(employeePayrollList);
        List<EmployeePayrollData> actualList = empPayrollService.getEmployeeData();
        Assertions.assertEquals(2, actualList.size());
        Assertions.assertEquals(employeePayrollList, actualList);
    }

    @Test
    void whenEmployeeDataFindById_DetailsNotFound_ShouldThrowException() {
        int employeeId = 1;
        when(employeeRepo.findById(employeeId)).thenReturn(Optional.empty());
        Assertions.assertThrows(DataNotFoundException.class, ()-> empPayrollService.getEmployeeDataById(employeeId));
    }

    @Test
    void givenEmployeeWhenAddedDataToRepo_ShouldReturnSuccessAndResponseMessage() {
        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        EmployeePayrollDto employeePayrollDto = new EmployeePayrollDto();
        employeePayrollDto.setName("Vamsi");
        employeePayrollDto.setDept("Design");
        employeePayrollDto.setSalary(30000);

        when(employeePayrollBuilder.buildEmployeePayrollEntity(employeePayrollDto, employeePayrollData))
                .thenReturn(employeePayrollData);
        ResponseDto actualResponse = empPayrollService.saveEmployeeData(employeePayrollDto);
        ResponseDto expectedResponse = new ResponseDto("Employee Added SuccessFully!!", HttpStatus.CREATED);
        Assertions.assertEquals(expectedResponse, actualResponse);
        verify(employeeRepo, times(1)).save(employeePayrollData);
    }
    @Test
    void givenEmployeeUpdatedById_ShouldUpdateEmployeeAndReturnResponseMessage() {
     int employeeId = 1;
     EmployeePayrollDto employeePayrollDto = new EmployeePayrollDto();
     employeePayrollDto.setName("Vamsi");
     employeePayrollDto.setDept("Design");
     employeePayrollDto.setSalary(30000);
     EmployeePayrollData employeePayrollData = new EmployeePayrollData();
     employeePayrollData.setName("Vamsi");
     employeePayrollData.setDept("Production");
     employeePayrollData.setSalary(30000);
     when(employeeRepo.findById(employeeId)).thenReturn(Optional.of(employeePayrollData));
     employeePayrollData.setDept(employeePayrollData.getDept());
     when(employeePayrollBuilder.buildEmployeePayrollEntity(employeePayrollDto, employeePayrollData))
             .thenReturn(employeePayrollData);
     ResponseDto actualResponse = empPayrollService.updateEmployeeData(employeeId, employeePayrollDto);
     ResponseDto expectedResponse = new ResponseDto("Employee Data Updated Successfully", HttpStatus.ACCEPTED);
     verify(employeeRepo, times(1)).save(employeePayrollData);
     Assertions.assertEquals(expectedResponse, actualResponse);
    }
    @Test
    void givenEmployeeUpdatedById_ShouldReturnException() {
        int employeeId = 1;
        EmployeePayrollDto employeePayrollDto = new EmployeePayrollDto();
        employeePayrollDto.setName("Vamsi");
        employeePayrollDto.setDept("Design");
        employeePayrollDto.setSalary(30000);

        when(employeeRepo.findById(employeeId)).thenReturn(Optional.empty());
        Assertions.assertThrows(DataNotFoundException.class,
                ()-> empPayrollService.updateEmployeeData(employeeId, employeePayrollDto));

    }
    @Test
    void givenEmployeeWhenDeletedById_ShouldReturnResponse() {
        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        employeePayrollData.setEmployeeId(1);
        employeePayrollData.setName("Vamsi");
        employeePayrollData.setDept("Design");
        employeePayrollData.setSalary(30000);

        lenient().when(employeeRepo.findById(employeePayrollData.getEmployeeId())).thenReturn(Optional.of(employeePayrollData));
        ResponseDto actualResponse = empPayrollService.deleteProduct(1);
        ResponseDto expectedResponse = new ResponseDto
                ("Employee with Id: "+employeePayrollData.getEmployeeId()+" deleted !!", HttpStatus.ACCEPTED);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }
    @Test
    void givenEmployeeWhenDeleteEmployeeDatById_ShouldThrowException() {
        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        employeePayrollData.setEmployeeId(1);
        employeePayrollData.setName("Vamsi");
        employeePayrollData.setDept("Design");
        employeePayrollData.setSalary(30000);
        lenient().when(employeeRepo.findById(employeePayrollData.getEmployeeId())).thenReturn(Optional.empty());
        Assertions.assertDoesNotThrow(
                ()-> empPayrollService.deleteProduct(employeePayrollData.getEmployeeId()));

    }
}
