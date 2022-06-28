package com.app.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table
public class Department {
    @Id
//    @Column(name = "dep_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "name")
    private String name;

    @OneToMany(cascade=ALL, mappedBy="department")
    private List<Section> sections = new ArrayList<>();

    @OneToOne(cascade=ALL)
    @JoinColumn(name = "dir_id")
    private Employee director;

    public Department(String name) {
        this.name = name;
    }

    public Department() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }

    public Employee getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", director=" + director.getName() +
                '}';
    }
}
