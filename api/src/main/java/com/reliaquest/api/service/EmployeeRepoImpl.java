package com.reliaquest.api.service;

import com.reliaquest.api.model.Entity;
import com.reliaquest.api.model.Input;
import com.reliaquest.api.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeRepoImpl implements EmployeeRepo {

    private Map<String, Entity> employees = new HashMap<>();

    public EmployeeRepoImpl() {
        // mock data
        employees.put("1", new Entity("1", "Alice Nixon", 120000,24,"SWE","a.Nixon@lol.com"));
        employees.put("2", new Entity("2", "Bob Marley", 95000,56,"Artist","b.marley@music.com"));
        employees.put("3", new Entity("3", "Charlie Michaels", 105000,45,"Artist","b.marley@music.com"));
        employees.put("4", new Entity("4", "Rex Mike", 160000,21,"Artist","b.marley@music.com"));
        employees.put("5", new Entity("5", "Lola Daniels", 20000,29,"Artist","b.marley@music.com"));
        employees.put("6", new Entity("6", "Mimi Wright", 75000,25,"Artist","b.marley@music.com"));
        employees.put("7", new Entity("7", "Charlie Brown", 213000,30,"Artist","b.marley@music.com"));
        employees.put("8", new Entity("8", "James Roades", 50000,31,"Artist","b.marley@music.com"));
        employees.put("9", new Entity("9", "Micheal Khan", 120000,34,"Artist","b.marley@music.com"));
        employees.put("10", new Entity("10", "Tom Jerry", 100000,24,"Artist","b.marley@music.com"));
        employees.put("11", new Entity("11", "Frank Lampard", 90000,41,"Artist","b.marley@music.com"));
        employees.put("12", new Entity("12", "Leslie Matthews", 85000,43,"Artist","b.marley@music.com"));

    }

    @Override
    public List<Entity> findAll() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public List<Entity> getEmployeeByName(String name) {

        ArrayList<Entity> arr = new ArrayList<>();

        for(Map.Entry<String,Entity> entry: employees.entrySet()){
            if (entry.getValue().getEmployee_name().toLowerCase().contains(name.toLowerCase())) {
                arr.add(entry.getValue());
            }
        }
        return arr;
    }


    @Override
    public Entity findById(String id) {
        return employees.get(id);
    }

    @Override
    public Integer findHighestSalary() {
        return employees.values().stream()
                .mapToInt(Entity::getEmployee_salary)
                .max()
                .orElse(0);
    }

    @Override
    public List<String> findTop10Salary() {

        return employees.values().stream()
                .sorted(Comparator.comparing(Entity::getEmployee_salary).reversed())
                .limit(10).map(Entity::getEmployee_name).collect(Collectors.toList());

    }

    @Override
    public Entity createEmployee(Input input) {
        Random r = new Random();
        r.nextInt(100);

        Entity emp = new Entity(String.valueOf(r), input.getEmployee_name(), input.getEmployee_salary(),input.getEmployee_age(),
                input.getEmployee_title(),input.getEmployee_email());
        employees.put(emp.getId(),emp);

        return emp;
    }

    @Override
    public String deleteById(String id) {
        Entity removed = employees.remove(id);
        if(removed == null)
            return null;
        return "Deleted Successfully";
    }
}

