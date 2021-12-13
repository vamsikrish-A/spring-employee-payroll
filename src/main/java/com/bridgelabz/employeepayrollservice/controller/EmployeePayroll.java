package com.bridgelabz.employeepayrollservice.controller;

import com.bridgelabz.employeepayrollservice.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollservice.dto.ResponseDTO;
import com.bridgelabz.employeepayrollservice.entity.EmployeePayrollData;
import com.bridgelabz.employeepayrollservice.service.IEmpPayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayroll")
public class EmployeePayroll {

    @Autowired
    private IEmpPayrollService empPayrollService;

    @RequestMapping(value = {"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
         List<EmployeePayrollData> employeePayrollDataList = null;
        employeePayrollDataList = empPayrollService.getEmployeepayrollData();
        ResponseDTO respDto = new ResponseDTO("Get call Successful", employeePayrollDataList);
        return new ResponseEntity<ResponseDTO>(respDto, HttpStatus.OK);
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId) {
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = empPayrollService.getEmployeePayrollDataById(empId);
        ResponseDTO responseDTO = new ResponseDTO("Get Call For ID Successful", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addEmployeePayrollData(@RequestBody EmployeePayrollDto employeePayrollDto) {
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = empPayrollService.createEmployeePayrollData(employeePayrollDto);
        ResponseDTO responseDTO = new ResponseDTO("Created Employee Payroll Data Successfully", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId,
                                                                 @RequestBody EmployeePayrollDto employeePayrollDto) {
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = empPayrollService.updateEmployeepayrollData(empId, employeePayrollDto);
        ResponseDTO responseDTO = new ResponseDTO("Updated Employee Payroll Data Successfully", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
        empPayrollService.deleteEmployeePayrollData(empId);
        ResponseDTO responseDTO = new ResponseDTO("Deleted Successfully", "Deleted id: "+empId);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
