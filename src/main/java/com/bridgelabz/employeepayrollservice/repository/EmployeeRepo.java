package com.bridgelabz.employeepayrollservice.repository;
/**
 * @purpose: repository interface dialects the Databases by using JpaRepository
 * @author: VamsiKrishna
 * @since: 13-12-2021
 */
import com.bridgelabz.employeepayrollservice.entity.EmployeePayrollData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeePayrollData, Integer> {
}
