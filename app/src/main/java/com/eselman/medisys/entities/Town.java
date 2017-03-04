package com.eselman.medisys.entities;

import java.io.Serializable;

/**
 * Created by eselman on 01/03/2017.
 */

public class Town implements Serializable {
    private Long id;
    private String code;
    private String name;
    private Department department;
    private County county;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }
}
