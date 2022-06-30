package com.app.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.*;


@Entity
@Table
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department")
    private List<Section> sections = new ArrayList<>();

    @OneToOne
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

    public long getId() {
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
//
//    public String getDirectorName() {
//        if (Objects.nonNull(this.director)){
//            return director.getSurname() + " " + director.getName() + " " +  director.getSurname();
//        }
//        return "-";
//    }

    public String getDirectorName() {
        if (Objects.nonNull(this.director)){
            return director.getSurname() + " " + director.getName() + " " +  director.getSurname();
        }
        return "-";
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", director=" + director.getName() +
                '}';
    }
}
