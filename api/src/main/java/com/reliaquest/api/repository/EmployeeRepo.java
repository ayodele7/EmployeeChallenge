package com.reliaquest.api.repository;


import com.reliaquest.api.model.Entity;
import com.reliaquest.api.model.Input;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo {

    List<Entity> findAll();
    List<Entity> getEmployeeByName(String name);
    Entity findById(String id);
    Integer findHighestSalary();
    List<String> findTop10Salary();
    Entity createEmployee(Input input);
    String deleteById(String id);


}
