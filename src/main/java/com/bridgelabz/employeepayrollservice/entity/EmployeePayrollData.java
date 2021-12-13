package com.bridgelabz.employeepayrollservice.entity;

import com.bridgelabz.employeepayrollservice.dto.EmployeePayrollDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Emp_payroll")
public class EmployeePayrollData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    private String name;
    private long salary;

    public EmployeePayrollData(int employeeId, EmployeePayrollDto employeePayrollDto) {
        this.employeeId = employeeId;
        this.name = employeePayrollDto.name;
        this.salary = employeePayrollDto.salary;
    }
}
