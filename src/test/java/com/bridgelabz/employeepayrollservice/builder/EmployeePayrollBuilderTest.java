package com.bridgelabz.employeepayrollservice.builder;

import com.bridgelabz.employeepayrollservice.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollservice.entity.EmployeePayrollData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeePayrollBuilderTest {
    @InjectMocks
    private EmployeePayrollBuilder employeePayrollBuilder;

    @Test
    void buildEntityTest() {
        EmployeePayrollDto employeePayrollDto = new EmployeePayrollDto();
        employeePayrollDto.setName("Vamsi");
        employeePayrollDto.setDept("Production");
        employeePayrollDto.setSalary(30000);
        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        employeePayrollData = employeePayrollBuilder.buildEmployeePayrollEntity(employeePayrollDto, employeePayrollData);
        Assertions.assertEquals(employeePayrollDto.getName(), employeePayrollData.getName());
        Assertions.assertEquals(employeePayrollDto.getDept(), employeePayrollData.getDept());
        Assertions.assertEquals(employeePayrollDto.getSalary(), employeePayrollData.getSalary());
    }

}
