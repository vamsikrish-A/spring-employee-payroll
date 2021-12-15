package com.bridgelabz.employeepayrollservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class EmployeePayrollDto {
    @NotNull
    @Size(min = 3, max = 21)
    @Pattern(regexp = "^[A-Za-z]{3,21}", message = "Name should be min-3 & Max-21 characters.")
    private String name;
    @NotNull
    @Size(max = 10, message = "dept should be valid characters")
    private String dept;
    @NotNull
    @Pattern(regexp = "^[0-9]{3,8}", message = "enter valid salary ")
    private long salary;
}
