package com.reliaquest.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entity {

    private String id;
    private String employee_name;
    private Integer employee_salary;
    private Integer employee_age;
    private String employee_title;
    private String employee_email;


    // constructors
    public Entity(String id, String name, Integer salary, Integer age, String title, String email) {
        this.id = id;
        this.employee_name = name;
        this.employee_salary = salary;
        this.employee_age = age;
        this.employee_title = title;
        this.employee_email = email;


    }

}

