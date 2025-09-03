package com.reliaquest.api.controller;

import com.reliaquest.api.model.Entity;
import com.reliaquest.api.model.Input;
import com.reliaquest.api.repository.EmployeeRepo;
import com.reliaquest.api.service.EmployeeRepoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee")
public class EmployeeController implements IEmployeeController<Entity,Input>{

    private final EmployeeRepo employeeRepo;

    @Override
    @GetMapping()
    public ResponseEntity<List<Entity>> getAllEmployees() {
        return ResponseEntity.ok(employeeRepo.findAll());
    }

    @Override
    @GetMapping("/search/{searchString}")
    public ResponseEntity<List<Entity>> getEmployeesByNameSearch(@PathVariable String searchString) {
        List<Entity> list = employeeRepo.getEmployeeByName(searchString);

        if(list.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(list);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Entity> getEmployeeById(@PathVariable String id) {
        Entity entity = employeeRepo.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }

    @Override
    @GetMapping("/highestSalary")
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        return ResponseEntity.ok(employeeRepo.findHighestSalary());
    }

    @Override
    @GetMapping("/topTenHighestEarningEmployeeNames")
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        return ResponseEntity.ok(employeeRepo.findTop10Salary());

    }

    @Override
    @PostMapping()
    public ResponseEntity<Entity> createEmployee(@RequestBody Input employeeInput) {
        Entity created = employeeRepo.createEmployee(employeeInput);

        if (created == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable String id) {

        String res = employeeRepo.deleteById(id);
        if(res == null)
        {
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(res);
    }
}
