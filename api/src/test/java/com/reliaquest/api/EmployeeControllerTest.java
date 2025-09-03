package com.reliaquest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reliaquest.api.model.Entity;
import com.reliaquest.api.repository.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepo employeeRepo;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testGetEmployeeById_found() throws Exception {
        when(employeeRepo.findById("1"))
                .thenReturn(new Entity("1", "Alice Nixon", 120000,24,"SWE","a.Nixon@lol.com"));

        mockMvc.perform(get("/api/v1/employee/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEmployeesByNameSearch_notFound() throws Exception {
        when(employeeRepo.getEmployeeByName("Zoe")).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/employee/search/Zoe"))
                .andExpect(status().isNotFound());
    }

        @Test
    void testGetEmployeeById_notFound() throws Exception {
        when(employeeRepo.findById("999")).thenReturn(null);

        mockMvc.perform(get("/api/v1/employee/999"))
                .andExpect(status().isNotFound());
    }

    // GET /employee/highestSalary
    @Test
    void testGetHighestSalaryOfEmployees() throws Exception {
        when(employeeRepo.findHighestSalary()).thenReturn(213000);

        mockMvc.perform(get("/api/v1/employee/highestSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(213000));
    }

    @Test
    void testGetTopTenHighestEarningEmployeeNames() throws Exception {
        List<String> top10 = List.of(
                "Charlie Brown","Rex Mike","Alice Nixon","Micheal Khan","Charlie Michaels",
                "Tom Jerry","Bob Marley","Frank Lampard","Leslie Matthews","Mimi Wright"
        );
        when(employeeRepo.findTop10Salary()).thenReturn(top10);

        mockMvc.perform(get("/api/v1/employee/topTenHighestEarningEmployeeNames"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Charlie Brown"))
                .andExpect(jsonPath("$[9]").value("Mimi Wright"));
    }


    @Test
    void testDeleteEmployeeById_success() throws Exception {
        when(employeeRepo.deleteById("1")).thenReturn("Deleted Successfully");

        mockMvc.perform(delete("/api/v1/employee/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteEmployeeById_notFound() throws Exception {
        when(employeeRepo.deleteById("999")).thenReturn(null);

        mockMvc.perform(delete("/api/v1/employee/999"))
                .andExpect(status().isNotFound());
    }
}










