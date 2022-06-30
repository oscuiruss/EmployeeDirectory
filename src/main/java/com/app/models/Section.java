package com.app.models;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.*;

@Entity
@Table
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 20, message = "Name can't consist of less than 2 and more than 20 letters")
    private String name;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    Department department;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "section")
    private List<Employee> employees = new ArrayList<>();


    @OneToOne
    private Employee director;

    public Section(String name) {
        this.name = name;
    }

    public Section() {

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getDirector() {
        return director;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }

    public String getDirectorName() {
        if (Objects.nonNull(this.director)){
            return director.getSurname() + " " + director.getName() + " " +  director.getSurname();
        }
        return "-";
    }


    public String getDepartmentName() {
        if (Objects.nonNull(this.department)){
            return department.getName();
        }
        return "-";
    }
}
