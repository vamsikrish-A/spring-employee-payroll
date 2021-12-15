package com.bridgelabz.employeepayrollservice.service;

import com.bridgelabz.employeepayrollservice.builder.EmployeePayrollBuilder;
import com.bridgelabz.employeepayrollservice.controller.EmployeePayroll;
import com.bridgelabz.employeepayrollservice.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollservice.dto.ResponseDto;
import com.bridgelabz.employeepayrollservice.entity.EmployeePayrollData;
import com.bridgelabz.employeepayrollservice.exception.BadRequestException;
import com.bridgelabz.employeepayrollservice.exception.DataNotFoundException;
import com.bridgelabz.employeepayrollservice.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Service
public class EmpPayrollService {
    private static final String EMPLOYEE_DATA_ADDED_SUCCESSFULLY = "Employee Added SuccessFully!!";
    private static final String EMPLOYEE_DATA_UPDATED_SUCCESSFULLY = "Employee Data Updated Successfully";
    private static final String EMPLOYEE_DATA_NOT_FOUND = "Data not found to ID: ";
    private EmployeePayrollBuilder employeePayrollBuilder;
    @Autowired
    private EmployeeRepo employeeRepo;

    public ResponseDto saveEmployeeData(EmployeePayrollDto employeePayrollDto) {
        if (employeePayrollDto == null) {
            throw new BadRequestException ("Employee details are Null.");
        }
        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        employeePayrollData = employeePayrollBuilder.buildEmployeePayrollEntity(employeePayrollDto, employeePayrollData);
        employeeRepo.save(employeePayrollData);
        return new ResponseDto(EMPLOYEE_DATA_ADDED_SUCCESSFULLY, HttpStatus.CREATED);
    }

    public List<EmployeePayrollData> getEmployeeData() {
        return employeeRepo.findAll();
    }

    public EmployeePayrollData getEmployeeDataById(int id) {
        return employeeRepo.findById(id).orElseThrow(() -> new DataNotFoundException(EMPLOYEE_DATA_NOT_FOUND+" "+id));
    }
    public  ResponseDto updateEmployeeData(int employeeId, EmployeePayrollDto employeePayrollDto) {
        EmployeePayrollData employeePayrollData = getEmployeeDataById(employeeId);
        employeePayrollData = employeePayrollBuilder.buildEmployeePayrollEntity(employeePayrollDto, employeePayrollData);
        employeeRepo.save(employeePayrollData);
        return new ResponseDto(EMPLOYEE_DATA_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
    }
    public String deleteProduct(int id) {
        employeeRepo.deleteById(id);
        return "Employee with Id: "+id+" deleted !!";
    }
}
