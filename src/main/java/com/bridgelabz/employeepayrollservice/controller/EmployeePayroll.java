package com.bridgelabz.employeepayrollservice.controller;
/**
 * @Purpose: used to operate all the RESTful APIs Http methods by using RestController
 * @author: Vamsi Krishna
 * @since: 13/12/2021
 */
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

    /**
     * @purpose: enables to inject the object dependency implicitly.
     */
    @Autowired
    private EmpPayrollService empPayrollService;
    private EmployeePayrollDto employeePayrollDto;

    /**
     * @param employeePayrollDto passing data with multiple attributes
     * @return http method return body and status code.
     * @purpose: @PostMapping annotation maps HTTP POST requests onto specific handler methods.
     */
    @PostMapping("/details")
    public ResponseEntity<ResponseDto> addEmployee(@Valid @RequestBody EmployeePayrollDto employeePayrollDto) {
        this.employeePayrollDto = employeePayrollDto;
        return new ResponseEntity(empPayrollService.saveEmployeeData(employeePayrollDto), HttpStatus.CREATED);
    }
    /**
     * @return http method return body and status codes.
     * @purpose: @GetMapping annotation maps HTTP GET requests onto specific handler methods.
     */
    @GetMapping("/details")
    public ResponseEntity<List<EmployeePayrollData>> getEmployeeDetails() {
        return new ResponseEntity(empPayrollService.getEmployeeData(), HttpStatus.OK);
    }
    /**
     * @param id gets data by find auto generated ID form repo.
     * @return http method body and status code.
     * @purpose: @GetMapping annotation maps HTTP GET requests onto specific handler methods.
     */
    @GetMapping("/details/{Id}")
    public ResponseEntity<EmployeePayrollData> getEmployeeByID(@PathVariable int id) {
        return new ResponseEntity(empPayrollService.getEmployeeDataById(id), HttpStatus.OK);
    }
    /**
     * @param id get data from repo by using auto generated Id
     * @param employeePayrollDto passing data with multiple attributes
     * @return http method body and status code.
     * @purpose: @PutMapping annotation maps HTTP PUT requests onto specific handler methods.
     */
    @PutMapping("/details/{Id}")
    public ResponseEntity<ResponseDto> updateEmployeeData(@PathVariable int id,
                                                          @Valid @RequestBody EmployeePayrollDto employeePayrollDto) {
        return new ResponseEntity(empPayrollService.updateEmployeeData(id,employeePayrollDto), HttpStatus.ACCEPTED);
    }
    /**
     * @param id used to collect the data from repo
     * @return http method body and status code.
     * @purpose: @DeleteMapping annotation maps HTTP DELETE requests onto specific handler methods.
     */
    @DeleteMapping("/details/{id}")
    public ResponseEntity<ResponseDto> deleteEmployeeData(@PathVariable int id) {
        return new ResponseEntity(empPayrollService.deleteProduct(id), HttpStatus.ACCEPTED);
    }

}
