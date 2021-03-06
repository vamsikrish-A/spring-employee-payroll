package com.bridgelabz.employeepayrollservice.service;
/**
 * @purpose: All the CURD operational methods to&From Repo to controller
 * @author: VamsiKrishna
 * @since: 13-12-2021
 */

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
    /**
     * @purpose: enables to inject the object dependency implicitly.
     */
    @Autowired
    private EmployeePayrollBuilder employeePayrollBuilder;
    @Autowired
    private EmployeeRepo employeeRepo;

    /**
     * @param employeePayrollDto providing data objects
     * @return exceptional super message and http status codes.
     * @Purpose adding addressBook details to repo.
     */
    public ResponseDto saveEmployeeData(EmployeePayrollDto employeePayrollDto) {
        if (employeePayrollDto == null) {
            throw new BadRequestException ("Employee details are Null.");
        }
        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        employeePayrollData = employeePayrollBuilder.buildEmployeePayrollEntity(employeePayrollDto, employeePayrollData);
        employeeRepo.save(employeePayrollData);
        return new ResponseDto(EMPLOYEE_DATA_ADDED_SUCCESSFULLY, HttpStatus.CREATED);
    }

    /**
     * @return data by find method.
     * @Purpose: used to get all the data from the repository.
     */
    public List<EmployeePayrollData> getEmployeeData() {
        return employeeRepo.findAll();
    }

    /**
     * @param id auto generated id to access data from repo.
     * @return data foe specific id .
     * @purpose used to get specific data by given id.
     */
    public EmployeePayrollData getEmployeeDataById(int id) {
        return employeeRepo.findById(id).orElseThrow(() -> new DataNotFoundException(EMPLOYEE_DATA_NOT_FOUND+" "+id));
    }
    /**
     * @param employeeId             auto generated id to access data from repo.
     * @param employeePayrollDto providing data objects
     * @return exceptional super message and status code.
     * @Purpose updating existing specific data by using ID.
     */
    public  ResponseDto updateEmployeeData(int employeeId, EmployeePayrollDto employeePayrollDto) {
        EmployeePayrollData employeePayrollData = getEmployeeDataById(employeeId);
        employeePayrollData = employeePayrollBuilder.buildEmployeePayrollEntity(employeePayrollDto, employeePayrollData);
        employeeRepo.save(employeePayrollData);
        return new ResponseDto(EMPLOYEE_DATA_UPDATED_SUCCESSFULLY, HttpStatus.ACCEPTED);
    }
    /**
     * @param id to identify the specific entry int repo
     * @return super message and http status code
     * @purpose deleting data from repo using id
     */
    public ResponseDto deleteProduct(int id) {
        employeeRepo.deleteById(id);
        return new ResponseDto("Employee with Id: "+id+" deleted !!", HttpStatus.ACCEPTED);
    }
}
