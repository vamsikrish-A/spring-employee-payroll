package com.bridgelabz.employeepayrollservice.entity;
/**
 * @purpose: entity is lightweight persistence object, represents a table in DB, corresponds to row in table.
 * @author: VamsiKrishna
 * @since: 13/12/2021
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Table(name = "Emp_payroll")
public class EmployeePayrollData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    private String name;
    private String dept;
    private long salary;


}
