package com.bridgelabz.employeepayrollservice.controller;

import com.bridgelabz.employeepayrollservice.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollservice.dto.ResponseDto;
import com.bridgelabz.employeepayrollservice.entity.EmployeePayrollData;
import com.bridgelabz.employeepayrollservice.service.EmpPayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employeepayroll")
public class EmployeePayroll {

    @Autowired
    private EmpPayrollService empPayrollService;
    private EmployeePayrollDto employeePayrollDto;

    @PostMapping("/details")
    public ResponseEntity<ResponseDto> addEmployee(@Valid @RequestBody EmployeePayrollDto employeePayrollDto) {
        this.employeePayrollDto = employeePayrollDto;
        return new ResponseEntity(empPayrollService.saveEmployeeData(employeePayrollDto), HttpStatus.CREATED);
    }
    @GetMapping("/details")
    public ResponseEntity<List<EmployeePayrollData>> getEmployeeDetails() {
        return new ResponseEntity(empPayrollService.getEmployeeData(), HttpStatus.OK);
    }
    @GetMapping("/details/{Id}")
    public ResponseEntity<EmployeePayrollData> getEmployeeByID(@PathVariable int id) {
        return new ResponseEntity(empPayrollService.getEmployeeDataById(id), HttpStatus.OK);
    }
    @PutMapping("/details/{Id}")
    public ResponseEntity<ResponseDto> updateEmployeeData(@PathVariable int id, @Valid @RequestBody EmployeePayrollDto employeePayrollDto) {
        return new ResponseEntity(empPayrollService.updateEmployeeData(id,employeePayrollDto), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/details/{id}")
    public ResponseEntity<ResponseDto> deleteEmployeeData(@PathVariable int id) {
        return new ResponseEntity(empPayrollService.deleteProduct(id), HttpStatus.ACCEPTED);
    }

}
