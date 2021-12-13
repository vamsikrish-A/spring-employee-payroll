package com.bridgelabz.employeepayrollservice.service;

import com.bridgelabz.employeepayrollservice.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollservice.entity.EmployeePayrollData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpPayrollService implements IEmpPayrollService{

    List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();

    @Override
    public List<EmployeePayrollData> getEmployeepayrollData() {
        return employeePayrollDataList;
    }

    @Override
    public EmployeePayrollData getEmployeePayrollDataById(int employeeId) {
        return employeePayrollDataList.get(employeeId);
    }

    @Override
    public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDto employeePayrollDto) {
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = new EmployeePayrollData(employeePayrollDataList.size()+1, employeePayrollDto);
        employeePayrollDataList.add(employeePayrollData);
        return employeePayrollData;
    }

    @Override
    public EmployeePayrollData updateEmployeepayrollData(int empId, EmployeePayrollDto employeePayrollDto) {
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataById(empId);
        employeePayrollData.setName(employeePayrollDto.name);
        employeePayrollData.setSalary(employeePayrollDto.salary);
        employeePayrollDataList.set(empId, employeePayrollData);
        return employeePayrollData;
    }

    @Override
    public void deleteEmployeePayrollData(int employeeId) {
        employeePayrollDataList.remove(employeeId);
    }
}
