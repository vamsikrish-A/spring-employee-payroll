package com.bridgelabz.employeepayrollservice.builder;
/**
 * @Purpose: used to copy the properties of DTO and Entity class variables
 * @author: Vamsi Krishna
 * @since: 13/12/2021
 */
import com.bridgelabz.employeepayrollservice.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollservice.entity.EmployeePayrollData;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeePayrollBuilder {
    /**
     * @purpose this method is created to copy the object properties of POJO class to entity class.
     * @param employeePayrollDto Source object class properties
     * @param employeePayrollData destination class for the copied properties
     * @return object of the entity class.
     */
    public EmployeePayrollData buildEmployeePayrollEntity(EmployeePayrollDto employeePayrollDto,
                                                          EmployeePayrollData employeePayrollData) {
        BeanUtils.copyProperties(employeePayrollDto, employeePayrollData);
        return employeePayrollData;
    }
}
