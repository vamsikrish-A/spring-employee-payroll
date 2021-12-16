package com.bridgelabz.employeepayrollservice.integration;

import com.bridgelabz.employeepayrollservice.controller.EmployeePayroll;
import com.bridgelabz.employeepayrollservice.dto.EmployeePayrollDto;
import com.bridgelabz.employeepayrollservice.dto.ResponseDto;
import com.bridgelabz.employeepayrollservice.entity.EmployeePayrollData;
import com.bridgelabz.employeepayrollservice.service.EmpPayrollService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.security.PrivateKey;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeePayroll.class)
@ActiveProfiles("test")
public class EmployeePayrollIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpPayrollService empPayrollService;

    @Test
    void saveEmployeeDataTest() throws Exception {
        when(empPayrollService.saveEmployeeData(any())).thenReturn(new ResponseDto());
        mockMvc.perform(MockMvcRequestBuilders.post("/employeepayroll/details")
                .content("{\"name\": \"Vamsi\", \"dept\": \"Design\", \"salary\": 30000}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void getEmployeeDataTest() throws Exception {
        when(empPayrollService.getEmployeeData()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/employeepayroll/details"))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployeeDataByIdTest() throws Exception {
        EmployeePayrollData employeePayrollData = new EmployeePayrollData();
        when(empPayrollService.getEmployeeDataById(1)).thenReturn(employeePayrollData);
        mockMvc.perform(MockMvcRequestBuilders.get("/employeepayroll/details/1"))
                .andExpect(status().is5xxServerError());
    }
    @Test
    void updateEmployeeDataTest() throws Exception {
        EmployeePayrollDto employeePayrollDto = new EmployeePayrollDto();
        employeePayrollDto.setName("Vamsi");
        employeePayrollDto.setDept("Design");
        employeePayrollDto.setSalary(30000);
        int employeeId = 1;
        when(empPayrollService.updateEmployeeData(employeeId, employeePayrollDto)).thenReturn(new ResponseDto());
        mockMvc.perform(MockMvcRequestBuilders.put("/employeepayroll/details/1")
                .content("\"{\\\"name\\\": \\\"Vamsi\\\", \\\"dept\\\": \\\"Design\\\", \\\"salary\\\": 40000}\"")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is5xxServerError());
    }
    @Test
    void deleteProductTest() throws Exception {
        when(empPayrollService.deleteProduct(1)).thenReturn(new ResponseDto());
        mockMvc.perform(MockMvcRequestBuilders.delete("/employeepayroll/details/1"))
                .andExpect(status().isAccepted());
    }

}
