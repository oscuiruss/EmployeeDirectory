package com.app.models;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table
public class Section {
    @Id
//    @Column(name = "sec_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "dep_id", nullable = false)
    Department department;

    @OneToMany(mappedBy = "section", cascade = ALL)
    private List<Employee> employees = new ArrayList<>();


    @OneToOne(cascade = ALL)

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
