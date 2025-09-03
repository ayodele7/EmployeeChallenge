package com.reliaquest.api;

import com.reliaquest.api.model.Entity;
import com.reliaquest.api.service.EmployeeRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeRepoTest {

        private EmployeeRepoImpl repo;

        @BeforeEach
        void setUp() {
            repo = new EmployeeRepoImpl();
        }

        @Test
        void testFindAll_returnsAllEmployees() {
            List<Entity> result = repo.findAll();

            // Assert
            assertThat(result).isNotNull();
            assertThat(result).hasSize(12);
        }

    @Test
    void testFindById_returnsCorrectEmployee() {
        Entity employee = repo.findById("2");
        assertNotNull(employee);
        assertEquals("Bob Marley", employee.getEmployee_name());
    }

    @Test
    void testFindById_returnsNullIfNotFound() {
        Entity employee = repo.findById("999");
        assertNull(employee);
    }

    @Test
    void testGetEmployeeByName_matchesSubstring() {
        List<Entity> results = repo.getEmployeeByName("Alice");
        assertEquals(1, results.size());
        assertEquals("Alice Nixon", results.get(0).getEmployee_name());
    }

    @Test
    void testGetEmployeeByName_returnsEmptyIfNoMatch() {
        List<Entity> results = repo.getEmployeeByName("Zach");
        assertTrue(results.isEmpty());
    }

    @Test
    void testFindHighestSalary_returnsCorrectValue() {
        Integer highest = repo.findHighestSalary();
        assertEquals(213000, highest);
    }

    @Test
    void testFindTop10Salary_returnsNamesSorted() {
        List<String> topNames = repo.findTop10Salary();
        assertEquals(10, topNames.size());
        assertEquals("Charlie Brown", topNames.get(0));
    }


    @Test
    void testDeleteById_removesEmployee() {
        String result = repo.deleteById("1");
        assertNull(repo.findById("1"));
    }

    @Test
    void testDeleteById_returnsNotFoundForMissingId() {
        String result = repo.deleteById("999");
        assertEquals("null", result);
    }
}
