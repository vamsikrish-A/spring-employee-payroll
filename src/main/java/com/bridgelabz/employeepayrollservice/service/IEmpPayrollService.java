package com.bridgelabz.employeepayrollservice.service;

import com.bridgelabz.employeepayrollservice.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollservice.entity.EmployeePayrollData;

import java.util.List;

public interface IEmpPayrollService {

    List<EmployeePayrollData> getEmployeepayrollData();

    EmployeePayrollData getEmployeePayrollDataById(int employeeId);

    EmployeePayrollData createEmployeePayrollData(EmployeePayrollDto employeePayrollDto);

    EmployeePayrollData updateEmployeepayrollData(EmployeePayrollDto employeePayrollDto);

    void deleteEmployeePayrollData(int employeeId);
}
